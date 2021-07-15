package rac.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;

public class BowListeners implements Listener {


    @EventHandler()
    public void witherBowOnShoot(EntityShootBowEvent event) {

        Arrow arrow = (Arrow) event.getProjectile();
        Player player = (Player) arrow.getShooter();
        Vector velocity = event.getProjectile().getVelocity();
        LivingEntity vic = event.getEntity();
        Vector right = getRightHeadDirection(player).multiply(.3);
        Vector left = getLeftHeadDirection(player).multiply(.3);


        if (player.getInventory().getItemInMainHand() != null) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Wither Bow")) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    event.setCancelled(true);
                    if (player.getInventory().getHelmet() != null)
                        if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Wither Helmet"))
                            if (player.getInventory().getHelmet().getItemMeta().hasLore()) {
                                velocity = velocity.multiply(2);
                                //right = right.multiply(2);
                            }
                    if (checkForWitherFullSet(player)) {
                        //player.getWorld().spawnEntity(player.getLocation().add(2,2,-.2), EntityType.WITHER_SKULL).setVelocity(velocity);
                        //player.getWorld().spawnEntity(player.getLocation().add(-2,2,-.2), EntityType.WITHER_SKULL).setVelocity(velocity);
                        // Location right = getRightHeadDirection(player).toLocation(player.getWorld());
                        player.getWorld().spawnEntity(player.getLocation().add(0,2,0),EntityType.WITHER_SKULL).setVelocity(right);
                        player.getWorld().spawnEntity(player.getLocation().add(0,2,0),EntityType.WITHER_SKULL).setVelocity(left);
                        player.launchProjectile(WitherSkull.class).setVelocity(velocity);

                    } else
                        player.getWorld().spawnEntity(player.getLocation().add(0,1.5,0), EntityType.WITHER_SKULL).setVelocity(velocity);
                }
            }
        }
    }

    public static Vector getRightHeadDirection(Player player) {
        Vector direction = player.getLocation().getDirection().normalize();
        return new Vector(-direction.getZ(), 0.0, direction.getX()).normalize();
    }

    public static Vector getLeftHeadDirection(Player player) {
        Vector direction = player.getLocation().getDirection().normalize();
        return new Vector(direction.getZ(), 0.0, -direction.getX()).normalize();
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

    @EventHandler()
    public void vampireBowOnHit(EntityDamageByEntityEvent event) {

        Arrow arrow = (Arrow) event.getDamager();
        Player player = (Player) arrow.getShooter();
        double percentSteal = .5;
        double healed = event.getDamage() * percentSteal;

        if (player.getInventory().getItemInMainHand() != null) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Vampire Bow")) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    if (event.getFinalDamage() > 0) {
                        if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Vampire Helmet")) {
                            healed += 3;
                            if (20 - player.getHealth() < healed) {
                                healed = 20 - player.getHealth();
                                player.setHealth(player.getHealth() + healed);
                                player.sendMessage(ChatColor.GREEN + "" + "You healed for " + Math.round(healed) + " health.");
                            } else {
                                player.setHealth(player.getHealth() + healed);
                                player.sendMessage(ChatColor.GREEN + "" + "You healed for " + Math.round(healed) + " health.");
                            }
                        } else {
                            if (20 - player.getHealth() < healed) {
                                healed = 20 - player.getHealth();
                                player.setHealth(player.getHealth() + healed);
                                player.sendMessage(ChatColor.GREEN + "" + "You healed for " + Math.round(healed) + " health.");
                            } else {
                                player.setHealth(player.getHealth() + healed);
                                player.sendMessage(ChatColor.GREEN + "" + "You healed for " + Math.round(healed) + " health.");
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler()
    public void debuffBowOnShoot(EntityDamageByEntityEvent event) {

        Arrow arrow = (Arrow) event.getDamager();
        Player player = (Player) arrow.getShooter();
        int random = 1 + (int)(Math.random() * ((3 - 1) + 1));
        LivingEntity vic = (LivingEntity) event.getEntity();

        if (player.getInventory().getItemInMainHand() != null) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Debuff Bow")) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    if (!checkForDebuffFullSet(player)) {
                        if (random == 1) { //Slowness
                            if (player.getInventory().getHelmet() != null) {
                                if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Debuff Helmet")) {
                                    vic.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 2));
                                    player.sendMessage(ChatColor.GREEN + "" + "You gave the enemy amplified " + ChatColor.GRAY + "slowness.");
                                }
                            } else {
                                vic.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
                                player.sendMessage(ChatColor.GREEN + "" + "You gave the enemy " + ChatColor.GRAY + "slowness.");
                            }
                        }
                        if (random == 2) { //Blindness
                            if (player.getInventory().getHelmet() != null) {
                                if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Debuff Helmet")) {
                                    vic.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 2));
                                    player.sendMessage(ChatColor.GREEN + "" + "You gave the enemy amplified " + ChatColor.DARK_GRAY + "blindness.");
                                }
                            } else {
                                vic.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
                                player.sendMessage(ChatColor.GREEN + "" + "You gave the enemy " + ChatColor.DARK_GRAY + "blindness.");
                            }
                        }
                        if (random == 3) { //Poison
                            if (player.getInventory().getHelmet() != null) {
                                if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Debuff Helmet")) {
                                    vic.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 3));
                                    player.sendMessage(ChatColor.GREEN + "" + "You gave the enemy amplified " + ChatColor.DARK_GREEN + "poison.");
                                }
                            } else {
                                vic.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
                                player.sendMessage(ChatColor.GREEN + "" + "You gave the enemy " + ChatColor.DARK_GREEN + "poison.");
                            }
                        }
                        if (player.getInventory().getLeggings() != null) { // Nausea
                            if (player.getInventory().getLeggings().getItemMeta().getDisplayName().contains("Debuff Leggings")) {
                                vic.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 280, 4));
                                player.sendMessage(ChatColor.GREEN + "" + "The enemy is " + ChatColor.DARK_PURPLE + "confused.");
                            }
                        }
                    } else {
                        vic.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 280, 4));
                        vic.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 3));
                        vic.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 2));
                        vic.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 2));
                        player.sendMessage(ChatColor.DARK_GREEN + "" + "You gave the enemy all debuff effects");
                    }
                }
            }
        }
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


    @EventHandler()
    public void explosiveBowOnShoot(ProjectileHitEvent event) {

        Arrow arrow = (Arrow) event.getEntity();
        Player player = (Player) arrow.getShooter();

        Location loc = arrow.getLocation();
        World world = loc.getWorld();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();

        if (player.getInventory().getItemInMainHand() != null) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Explosive Bow")) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    world.createExplosion(x,y,z,2,false,false);
                    arrow.remove();
                }
            }
        }
    }


}









