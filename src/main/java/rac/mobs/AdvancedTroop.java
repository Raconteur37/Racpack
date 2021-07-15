package rac.mobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

public class AdvancedTroop {

    public Zombie SpawnAdvancedTroop(Location loc) {

        Zombie advancedTroop = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
        advancedTroop.setCustomName(ChatColor.DARK_AQUA + "Advanced Troop");
        advancedTroop.setCustomNameVisible(true);
        advancedTroop.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        advancedTroop.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        advancedTroop.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        advancedTroop.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));

        advancedTroop.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));

        return advancedTroop;


    }


}
