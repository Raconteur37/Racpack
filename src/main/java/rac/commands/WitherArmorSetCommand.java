package rac.commands;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class WitherArmorSetCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get that");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getWitherHelmet());
            player.getInventory().addItem(getWitherChestplate());
            player.getInventory().addItem(getWitherLeggings());
            player.getInventory().addItem(getWitherBoots());

        }
        return false;
    }

    // Helmet increases wither skull velocity
    // Chestplate increases damage resistance toward wither skulls
    // Leggings makes basic attacks have a chance to wither the enemy
    // Boots make the player immune to wither
    // Full set shoots 3 wither skulls at a time


    public ItemStack getWitherHelmet() {

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetMeta.setColor(Color.fromRGB(64,64,64));

        helmetMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "" + "Wither Helmet");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + "Wither skulls you shoot travel faster");
        helmetMeta.setLore(lore);
        helmetMeta.setUnbreakable(true);

        helmet.setItemMeta(helmetMeta);

        return helmet;
    }

    public ItemStack getWitherChestplate() {

        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestMeta = (LeatherArmorMeta) chest.getItemMeta();
        chestMeta.setColor(Color.fromRGB(64,64,64));

        chestMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "" + "Wither Chestplate");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + "You take less damage from wither skulls");
        chestMeta.setLore(lore);
        chestMeta.setUnbreakable(true);

        chest.setItemMeta(chestMeta);

        return chest;

    }

    public ItemStack getWitherLeggings() {

        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta legsMeta = (LeatherArmorMeta) legs.getItemMeta();
        legsMeta.setColor(Color.fromRGB(64,64,64));

        legsMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "" + "Wither Leggings");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + "Basic attacks have a chance to wither enemies");
        legsMeta.setLore(lore);
        legsMeta.setUnbreakable(true);

        legs.setItemMeta(legsMeta);

        return legs;

    }

    public ItemStack getWitherBoots() {

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(Color.fromRGB(64,64,64));

        bootsMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "" + "Wither Boots");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + "You are immune to wither");
        bootsMeta.setLore(lore);
        bootsMeta.setUnbreakable(true);

        boots.setItemMeta(bootsMeta);

        return boots;


    }


}
