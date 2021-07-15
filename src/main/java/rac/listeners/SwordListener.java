package rac.listeners;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import org.bukkit.scheduler.BukkitTask;
import rac.Main;
import rac.mobs.SummonerTroops;

import java.util.List;
import java.util.UUID;

public class SwordListener implements Listener {

    private BukkitTask task;
    private int taskID;
    SummonerTroops summonerTroops = new SummonerTroops();

    @EventHandler()
    public void bleedingSwordHit(EntityDamageByEntityEvent event) {

        Player player = (Player) event.getDamager();
        LivingEntity vic = (LivingEntity) event.getEntity();
        int random = 1 + (int) (Math.random() * ((4 - 1) + 1));
        Particle.DustOptions dust = new Particle.DustOptions(
                Color.fromRGB(0 ,255 ,0 ), 1);

        if (player.getInventory().getItemInMainHand() != null)
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Bleeding Sword")) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                    if (random == 1) {
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You caused the enemy to bleed.");
                        vic.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 180,2));
                        vic.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180,2));
                        task = Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getPlugin(Main.class), new Runnable() {

                            public void run() {
                                vic.setHealth(vic.getHealth() - .75);
                                if (vic.getHealth() - .75 <= 0)
                                    vic.setHealth(0);
                            }
                        },0,20);
                        taskID = task.getTaskId();
                        BukkitTask stopBleeding = Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(Main.class), new Runnable() {
                            @Override
                            public void run() {
                                Bukkit.getScheduler().cancelTask(taskID);
                            }
                        },140);

                    }
            }
    }

    UUID summonerUUID = null;
    String summonerName = null;

    @EventHandler
    public void onSummonerSetKill(EntityDeathEvent event) {

        Player attacker = (Player) event.getEntity().getKiller();
        LivingEntity entity = event.getEntity();
        Location spawnLocation = entity.getLocation();

        if (checkForSummonerFullSet(attacker)) {
            summonerUUID = attacker.getUniqueId();
            summonerName = attacker.getName();
            summonerTroops.SpawnSummonerDeathTroop(attacker,spawnLocation);
            summonerTroops.SpawnSummonerDeathTroop(attacker,spawnLocation);
        }

    }

    @EventHandler()
    public void summonerSwordKill(EntityDeathEvent event) {

        Player player = event.getEntity().getKiller();
        LivingEntity entity = event.getEntity();
        Location spawnLocation = entity.getLocation();

        if (player.getInventory().getItemInMainHand() != null)
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Summoner Sword"))
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    summonerUUID = player.getUniqueId();
                    summonerName = player.getName();
                    summonerTroops.SpawnSummonerDeathTroop(player,spawnLocation);
                    if (player.getInventory().getLeggings() != null)
                        if (player.getInventory().getLeggings().getItemMeta().getDisplayName().contains("Summoner Leggings"))
                            if (player.getInventory().getLeggings().getItemMeta().hasLore()) {
                                summonerTroops.SpawnSummonerDeathTroop(player,spawnLocation);
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

        if (entity.getCustomName().contains("Death Troop")) {
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
