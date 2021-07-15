package rac.listeners;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import rac.Main;
import rac.mobs.SummonerTroops;
import rac.particleData.ParticleData;
import rac.particleEffects.Effects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class StaffListener implements Listener {

    SummonerTroops summonerTroops = new SummonerTroops();
    public List<String> healingStaff = new ArrayList<String>();
    public static Main plugin;

    @EventHandler
    public void onHealingStaffClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand() != null) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Healing Staff"))
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                        ParticleData particle = new ParticleData(player.getUniqueId());
                        if (particle.hasID()) {
                            particle.endTask();
                            particle.removeID();
                            healingStaff.remove(player.getName());
                            player.sendMessage(ChatColor.RED + "Healing Beam deactivated");


                        } else {
                            healingStaff.add(player.getName());
                            player.sendMessage(ChatColor.GREEN + "Healing Beam activated");
                            Effects trail = new Effects(player);
                            //trail.healingShieldParticle();
                            trail.healingBeamParticle();
                            //e.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,4,1));
                            Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {


                                @Override
                                public void run() {

                                    //if ((!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Healing Staff")) || player.getInventory().getItemInMainHand().getType() == Material.AIR)  {
                                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Healing Staff")) {
                                        ParticleData particle = new ParticleData(player.getUniqueId());
                                        if (particle.hasID()) {
                                            particle.endTask();
                                            particle.removeID();
                                            healingStaff.remove(player.getName());
                                            player.sendMessage(ChatColor.RED + "Healing Beam deactivated");
                                        }
                                    }

                                }
                            }, 0, 1);
                        }
                    }
        }
    }

    UUID summonerUUID = null;
    String summonerName = null;

    @EventHandler
    public void onSummonerStaffClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Location loc = player.getLocation();
        double dmg = 4;

        if (player.getInventory().getItemInMainHand() != null)
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Summoner Staff"))
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                        if (checkForSummonerFullSet(player))
                            dmg = 2;
                        if (player.getHealth() - dmg < 1) {
                            player.sendMessage(ChatColor.RED + "You don't have enough health");
                        } else {
                            player.setHealth(player.getHealth() - dmg);
                            summonerTroops.SpawnFallenTroop(player, loc);
                            summonerUUID = player.getUniqueId();
                            summonerName = player.getName();
                            player.sendMessage(ChatColor.WHITE + "You bring the fallen to your realm");
                            if (player.getInventory().getLeggings() != null)
                                if (player.getInventory().getLeggings().getItemMeta().getDisplayName().contains("Summoner Leggings"))
                                    if (player.getInventory().getLeggings().getItemMeta().hasLore()) {
                                        summonerTroops.SpawnFallenTroop(player, loc);
                                    }
                        }
                    }
    }

    public boolean checkForSummonerFullSet(Player player) {

        boolean hasFullSet = false;
        if (player.getInventory().getHelmet() != null)
            if (player.getInventory().getChestplate() != null)
                if (player.getInventory().getLeggings() != null)
                    if (player.getInventory().getBoots() != null)
                        if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Summoner Helmet"))
                            if (player.getInventory().getChestplate().getItemMeta().getDisplayName().contains("Summoner Chestplate"))
                                if (player.getInventory().getLeggings().getItemMeta().getDisplayName().contains("Summoner Leggings"))
                                    if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Summoner Boots"))
                                        if (player.getInventory().getHelmet().getItemMeta().hasLore()) {
                                            hasFullSet = true;
                                            return hasFullSet;
                                        }
        return hasFullSet;
    }

    @EventHandler
    public void onEntityTarget(EntityTargetLivingEntityEvent event) {

        LivingEntity entity = (LivingEntity) event.getEntity();
        LivingEntity target = event.getTarget();

        if (entity.getCustomName().contains("Fallen Troop")) {
            Location loc = null;
            LivingEntity closest = null;
            loc = entity.getLocation();
            double lowestDistance = Double.MAX_VALUE;
            List<Entity> near = entity.getNearbyEntities(20.0, 20.0, 20.0); //gets all living entities within range
            for (Entity e : near) {
                if (e instanceof LivingEntity) {
                    double distance = e.getLocation().distance(loc);
                    if (distance < lowestDistance)
                        if (e.getUniqueId() != summonerUUID && !e.getName().contains(summonerName)) {
                            distance = e.getLocation().distance(loc);
                            lowestDistance = distance;
                            closest = (LivingEntity) e;
                            event.setTarget(closest);
                        } else {
                            event.setTarget(null);
                        }
                }
            }
        }
    }

}