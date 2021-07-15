package rac.mobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

public class BasicTroop {

    public Zombie SpawnBasicTroop(Location loc) {

        Zombie basicTroop = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
        basicTroop.setCustomName(ChatColor.AQUA + "Basic Troop");
        basicTroop.setCustomNameVisible(true);
        basicTroop.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
        basicTroop.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
        basicTroop.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
        basicTroop.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));


        basicTroop.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
        return basicTroop;


    }


}
