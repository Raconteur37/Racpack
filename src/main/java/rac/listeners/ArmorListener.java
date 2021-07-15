package rac.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import rac.Main;
import rac.mobs.SummonerTroops;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public class ArmorListener implements Listener {

    private BukkitTask task;
    private int taskID;
    SummonerTroops summonerTroops = new SummonerTroops();

    @EventHandler
    public void witherChestplate(EntityDamageByEntityEvent event) {

        Player vic = (Player) event.getEntity();
        double damageResistance = .5; // 50% damage reduction

        if (vic.getInventory().getChestplate() != null)
            if (vic.getInventory().getChestplate().getItemMeta().getDisplayName().contains("Wither Chestplate"))
                if (vic.getInventory().getChestplate().getItemMeta().hasLore())
                    if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                        event.setDamage(7.5 * damageResistance);
                    }
    }

    @EventHandler
    public void witherLeggings(EntityDamageByEntityEvent event) {

        LivingEntity vic = (LivingEntity) event.getEntity();
        Player player = (Player) event.getDamager();
        int random = 1 + (int)(Math.random() * ((5 - 1) + 1));

        if (player.getInventory().getLeggings() != null)
            if (player.getInventory().getLeggings().getItemMeta().getDisplayName().contains("Wither Leggings"))
                if (player.getInventory().getLeggings().getItemMeta().hasLore())
                    if (random == 4) {
                        vic.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 80, 1));
                        player.sendMessage(ChatColor.GRAY + "" + "You wither the enemy.");
                    }


    }

    @EventHandler
    public void witherBoots(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (player.getInventory().getBoots() != null)
            if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Wither Boots"))
                if (player.getInventory().getBoots().getItemMeta().hasLore())
                    player.removePotionEffect(PotionEffectType.WITHER);
    }

    @EventHandler
    public void debuffChestplate(EntityDamageByEntityEvent event) {

        LivingEntity attacker = (LivingEntity) event.getDamager();
        Player vic = (Player) event.getEntity();

        if (vic.getInventory().getChestplate() != null)
            if (vic.getInventory().getChestplate().getItemMeta().getDisplayName().contains("Debuff Chestplate"))
                if (vic.getInventory().getChestplate().getItemMeta().hasLore()) {
                    int randomCheck = 1 + (int) (Math.random() * ((3 - 1) + 1));
                    if (randomCheck == 1) {
                        int randomDebuff = 1 + (int) (Math.random() * ((3 - 1) + 1));
                        if (randomDebuff == 1) { //Slowness
                            if (vic.getInventory().getHelmet() != null) {
                                if (vic.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Debuff Helmet")) {
                                    attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 2));
                                    vic.sendMessage(ChatColor.GREEN + "" + "You gave the enemy amplified " + ChatColor.GRAY + "slowness.");
                                }
                            } else {
                                attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
                                vic.sendMessage(ChatColor.GREEN + "" + "You gave the enemy " + ChatColor.GRAY + "slowness.");
                            }
                        }
                        if (randomDebuff == 2) { //Blindness
                            if (vic.getInventory().getHelmet() != null) {
                                if (vic.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Debuff Helmet")) {
                                    attacker.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 2));
                                    vic.sendMessage(ChatColor.GREEN + "" + "You gave the enemy amplified " + ChatColor.DARK_GRAY + "blindness.");
                                }
                            } else {
                                attacker.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
                                vic.sendMessage(ChatColor.GREEN + "" + "You gave the enemy " + ChatColor.DARK_GRAY + "blindness.");
                            }
                        }
                        if (randomDebuff == 3) { //Poison
                            if (vic.getInventory().getHelmet() != null) {
                                if (vic.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Debuff Helmet")) {
                                    attacker.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 3));
                                    vic.sendMessage(ChatColor.GREEN + "" + "You gave the enemy amplified " + ChatColor.DARK_GREEN + "poison.");
                                }
                            } else {
                                attacker.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
                                vic.sendMessage(ChatColor.GREEN + "" + "You gave the enemy " + ChatColor.DARK_GREEN + "poison.");
                            }
                        }
                    }
                }
    }

    @EventHandler
    public void debuffBoots(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (player.getInventory().getBoots() != null)
            if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Debuff Boots"))
                if (player.getInventory().getBoots().getItemMeta().hasLore()) {

                    PotionEffect poison = event.getPlayer().getPotionEffect( PotionEffectType.POISON );
                    if (poison != null && poison.getAmplifier() >= 1) {
                        player.removePotionEffect(poison.getType());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON,40,0));
                    }

                    PotionEffect slowness = event.getPlayer().getPotionEffect( PotionEffectType.SLOW );
                    if (slowness != null && slowness.getAmplifier() >= 1) {
                        player.removePotionEffect(slowness.getType());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,35,0));
                    }

                    PotionEffect blindness = event.getPlayer().getPotionEffect( PotionEffectType.BLINDNESS );
                    if (blindness != null && blindness.getAmplifier() >= 1) {
                        player.removePotionEffect(blindness.getType());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,20,0));
                    }

                    PotionEffect confusion = event.getPlayer().getPotionEffect( PotionEffectType.CONFUSION );
                    if (confusion != null && confusion.getAmplifier() >= 1) {
                        player.removePotionEffect(confusion.getType());
                        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60,0));
                    }

                }

    }

    @EventHandler
    public void onVampireChestplateHit(EntityDamageEvent event) {

        Player vic = (Player) event.getEntity();
        double dmg = event.getFinalDamage();
        double healed = dmg * .4;

        if (vic.getInventory().getChestplate() != null)
            if (vic.getInventory().getChestplate().getItemMeta().getDisplayName().contains("Vampire Chestplate"))
                if (vic.getInventory().getChestplate().getItemMeta().hasLore()) {
                    if (vic.getHealth() + healed > 20) {
                        healed = 20 - vic.getHealth();
                        vic.setHealth(vic.getHealth() + healed);
                    } else
                        vic.setHealth(vic.getHealth() + healed);
                }


    }

    @EventHandler
    public void onVampireLeggingsHit(EntityDamageByEntityEvent event) {

        Player attacker = (Player) event.getDamager();
        double dmg = event.getFinalDamage();
        double healed = dmg * .3;

        if (attacker.getInventory().getLeggings() != null)
            if (attacker.getInventory().getLeggings().getItemMeta().getDisplayName().contains("Vampire Leggings"))
                if (attacker.getInventory().getLeggings().getItemMeta().hasLore()) {
                    if (attacker.getHealth() + healed > 20) {
                        healed = 20 - attacker.getHealth();
                        attacker.setHealth(attacker.getHealth() + healed);
                    } else
                        attacker.setHealth(attacker.getHealth() + healed);
                }


    }

    @EventHandler
    public void onVampireBootsKill(EntityDeathEvent event) {

        Player attacker = (Player) event.getEntity().getKiller();

        if (attacker.getInventory().getBoots() != null)
            if (attacker.getInventory().getBoots().getItemMeta().getDisplayName().contains("Vampire Boots"))
                if (attacker.getInventory().getBoots().getItemMeta().hasLore()) {
                    double healed = 20 - attacker.getHealth();
                    attacker.setHealth(attacker.getHealth() + healed);
                    attacker.sendMessage(ChatColor.GREEN + "" + "You fully heal");
                }


    }

    @EventHandler
    public void fullSetCheck(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();

        if (checkForDebuffFullSet(player))
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You master all debuffs");
        if (checkForWitherFullSet(player))
            player.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "You feel the power of the wither");
        if (checkForVampireFullSet(player))
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You feast on the enemies blood");
        if (checkForSummonerFullSet(player))
            player.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "The undead cling to you for guidance");


    }

    @EventHandler
    public void onVampireSetHit(EntityDamageByEntityEvent event) {

        Player attacker = (Player) event.getDamager();
        LivingEntity vic = (LivingEntity) event.getEntity();
        int random = 1 + (int) (Math.random() * ((4 - 1) + 1));

        if (checkForVampireFullSet(attacker))
            if (random == 1) {
                attacker.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You caused the enemy to bleed.");
                task = Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getPlugin(Main.class), new Runnable() {
                    public void run() {
                        double healed = .75;
                        vic.setHealth(vic.getHealth() - .75);
                        if (vic.getHealth() - .75 <= 0)
                            vic.setHealth(0);
                        if (attacker.getHealth() + healed > 20) {
                            healed = 20 - attacker.getHealth();
                            attacker.setHealth(attacker.getHealth() + healed);
                        } else
                            attacker.setHealth(attacker.getHealth() + healed);
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

    UUID summonerUUID = null;
    String summonerName = null;

    @EventHandler
    public void onSummonerBootsHit(EntityDamageEvent event) {

        Player vic = (Player) event.getEntity();
        double damage = event.getDamage();
        Location loc = vic.getLocation();
        if (vic.getInventory().getBoots() != null)
            if (vic.getInventory().getBoots().getItemMeta().getDisplayName().contains("Summoner Boots"))
                if (vic.getInventory().getBoots().getItemMeta().hasLore()) {
                    if (vic.getHealth() - damage <= 4) {
                        summonerUUID = vic.getUniqueId();
                        summonerName = vic.getName();
                        summonerTroops.SpawnSummonerDeathTroop(vic,loc);
                        summonerTroops.SpawnSummonerDeathTroop(vic,loc);
                        summonerTroops.SpawnSummonerDeathTroop(vic,loc);
                        if (vic.getInventory().getLeggings() != null)
                            if (vic.getInventory().getLeggings().getItemMeta().getDisplayName().contains("Summoner Leggings"))
                                if (vic.getInventory().getLeggings().getItemMeta().hasLore()) {
                                    summonerTroops.SpawnSummonerDeathTroop(vic,loc);
                                    summonerTroops.SpawnSummonerDeathTroop(vic,loc);
                                    summonerTroops.SpawnSummonerDeathTroop(vic,loc);
                                }
                    }
                }

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


    public boolean checkForVampireFullSet(Player player) {

        boolean hasFullSet = false;
        if (player.getInventory().getHelmet() != null)
            if (player.getInventory().getChestplate() != null)
                if (player.getInventory().getLeggings() != null)
                    if (player.getInventory().getBoots() != null)
                        if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Vampire Helmet"))
                            if (player.getInventory().getChestplate().getItemMeta().getDisplayName().contains("Vampire Chestplate"))
                                if (player.getInventory().getLeggings().getItemMeta().getDisplayName().contains("Vampire Leggings"))
                                    if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Vampire Boots"))
                                        if (player.getInventory().getHelmet().getItemMeta().hasLore()) {
                                            hasFullSet = true;
                                            return hasFullSet;
                                        }
        return hasFullSet;

    }

    public boolean checkForDebuffFullSet(Player player) {

        boolean hasFullSet = false;
        if (player.getInventory().getHelmet() != null)
            if (player.getInventory().getChestplate() != null)
                if (player.getInventory().getLeggings() != null)
                    if (player.getInventory().getBoots() != null)
                        if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Debuff Helmet"))
                            if (player.getInventory().getChestplate().getItemMeta().getDisplayName().contains("Debuff Chestplate"))
                                if (player.getInventory().getLeggings().getItemMeta().getDisplayName().contains("Debuff Leggings"))
                                    if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Debuff Boots"))
                                        if (player.getInventory().getHelmet().getItemMeta().hasLore()) {
                                            hasFullSet = true;
                                            return hasFullSet;
                                        }
        return hasFullSet;

    }

    public boolean checkForWitherFullSet(Player player) {

        boolean hasFullSet = false;
        if (player.getInventory().getHelmet() != null)
            if (player.getInventory().getChestplate() != null)
                if (player.getInventory().getLeggings() != null)
                    if (player.getInventory().getBoots() != null)
                        if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Wither Helmet"))
                            if (player.getInventory().getChestplate().getItemMeta().getDisplayName().contains("Wither Chestplate"))
                                if (player.getInventory().getLeggings().getItemMeta().getDisplayName().contains("Wither Leggings"))
                                    if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Wither Boots"))
                                        if (player.getInventory().getHelmet().getItemMeta().hasLore()) {
                                            hasFullSet = true;
                                            return hasFullSet;
                                        }
        return hasFullSet;

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

}
