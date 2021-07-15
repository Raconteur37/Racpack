package rac.mobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SummonerTroops {

    public Zombie SpawnSummonerDeathTroop(Player summoner, Location loc) {

        Zombie deathTroop = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
        deathTroop.setCustomName(ChatColor.GRAY + summoner.getName() + "'s Death Troop");
        deathTroop.setCustomNameVisible(true);
        deathTroop.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
        if (summoner.getInventory().getChestplate() != null)
            if (summoner.getInventory().getChestplate().getItemMeta().getDisplayName().contains("Summoner Chestplate"))
                if (summoner.getInventory().getChestplate().getItemMeta().hasLore()) {
                    deathTroop.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
                    deathTroop.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                    deathTroop.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));
                }
        deathTroop.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
        if (summoner.getInventory().getHelmet() != null)
            if (summoner.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Summoner Helmet"))
                if (summoner.getInventory().getHelmet().getItemMeta().hasLore()) {
                    deathTroop.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 1));
                    deathTroop.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
                    deathTroop.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 99999, 1));
                }
        if (summoner.getInventory().getBoots() != null)
            if (summoner.getInventory().getBoots().getItemMeta().getDisplayName().contains("Summoner Boots"))
                if (summoner.getInventory().getBoots().getItemMeta().hasLore()) {
                        deathTroop.setBaby();
                }

        return deathTroop;

    }

    public Zombie SpawnFallenTroop(Player summoner, Location loc) {

        Zombie fallenTroop = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
        fallenTroop.setCustomName(ChatColor.GRAY + summoner.getName() + "'s Fallen Troop");
        fallenTroop.setCustomNameVisible(true);
        fallenTroop.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
        fallenTroop.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        fallenTroop.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        fallenTroop.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
        fallenTroop.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
        if (summoner.getInventory().getChestplate() != null)
            if (summoner.getInventory().getChestplate().getItemMeta().getDisplayName().contains("Summoner Chestplate"))
                if (summoner.getInventory().getChestplate().getItemMeta().hasLore()) {
                    fallenTroop.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
                    fallenTroop.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                }
        if (summoner.getInventory().getHelmet() != null)
            if (summoner.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Summoner Helmet"))
                if (summoner.getInventory().getHelmet().getItemMeta().hasLore()) {
                    fallenTroop.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 1));
                    fallenTroop.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
                    fallenTroop.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 99999, 1));
                }

        return fallenTroop;

    }



}
