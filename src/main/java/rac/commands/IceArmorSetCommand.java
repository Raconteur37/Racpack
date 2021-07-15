package rac.commands;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class IceArmorSetCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get that");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getIceHelmet());
            player.getInventory().addItem(getIceChestplate());
            player.getInventory().addItem(getIceLeggings());
            player.getInventory().addItem(getIceBoots());

        }
        return false;
    }

    // Helmet Freezing enemies lasts longer and does more damage
    // Chestplate Getting hit has a chance to freeze enemies
    // Leggings Killing an enemy grants you small resistance
    // Boots Frozen enemies take minor damage over time
    // Full


    public ItemStack getIceHelmet() {

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetMeta.setColor(Color.fromRGB(102,255,255));

        helmetMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "" + "Ice Helmet");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "" + "Freezing enemies lasts longer and does more damage");
        helmetMeta.setLore(lore);
        helmetMeta.setUnbreakable(true);

        helmet.setItemMeta(helmetMeta);

        return helmet;
    }

    public ItemStack getIceChestplate() {

        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestMeta = (LeatherArmorMeta) chest.getItemMeta();
        chestMeta.setColor(Color.fromRGB(102,255,255));

        chestMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "" + "Ice Chestplate");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "" + "Taking damage has a chance to freeze enemies");
        chestMeta.setLore(lore);
        chestMeta.setUnbreakable(true);

        chest.setItemMeta(chestMeta);

        return chest;

    }

    public ItemStack getIceLeggings() {

        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta legsMeta = (LeatherArmorMeta) legs.getItemMeta();
        legsMeta.setColor(Color.fromRGB(102,255,255));

        legsMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "" + "Ice Leggings");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "" + "Killing an enemy grants you resistance");
        legsMeta.setLore(lore);
        legsMeta.setUnbreakable(true);

        legs.setItemMeta(legsMeta);

        return legs;

    }

    public ItemStack getIceBoots() {

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(Color.fromRGB(102,255,255));

        bootsMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "" + "Ice Boots");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "" + "Frozen enemies take damage over time");
        bootsMeta.setLore(lore);
        bootsMeta.setUnbreakable(true);

        boots.setItemMeta(bootsMeta);

        return boots;


    }

}
