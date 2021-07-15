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

public class DebuffArmorSetCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get that");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getDebuffHelmet());
            player.getInventory().addItem(getDebuffChestplate());
            player.getInventory().addItem(getDebuffLeggings());
            player.getInventory().addItem(getDebuffBoots());

        }
        return false;
    }

    // Helmet increases effectiveness of poison,slowness,and blindness
    // Chestplate has a chance to debuff enemies that hit you
    // Leggings gives nausea to every debuff bow it
    // Boots
    // Full set gives every debuff on bow hit


    public ItemStack getDebuffHelmet() {

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetMeta.setColor(Color.fromRGB(0,153,0));

        helmetMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "" + "Debuff Helmet");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + "Increase the effectiveness of debuffs");
        helmetMeta.setLore(lore);
        helmetMeta.setUnbreakable(true);

        helmet.setItemMeta(helmetMeta);

        return helmet;
    }

    public ItemStack getDebuffChestplate() {

        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestMeta = (LeatherArmorMeta) chest.getItemMeta();
        chestMeta.setColor(Color.fromRGB(0,153,0));

        chestMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "" + "Debuff Chestplate");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + "Enemies that hit you have a chance to become debuffed");
        chestMeta.setLore(lore);
        chestMeta.setUnbreakable(true);

        chest.setItemMeta(chestMeta);

        return chest;
    }

    public ItemStack getDebuffLeggings() {

        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta legsMeta = (LeatherArmorMeta) legs.getItemMeta();
        legsMeta.setColor(Color.fromRGB(0,153,0));

        legsMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "" + "Debuff Leggings");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + "Debuff bow hits confuse the enemy");
        legsMeta.setLore(lore);
        legsMeta.setUnbreakable(true);

        legs.setItemMeta(legsMeta);

        return legs;

    }

    public ItemStack getDebuffBoots() {

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(Color.fromRGB(0,153,0));

        bootsMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "" + "Debuff Boots");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + "Debuff effects have less effectiveness");
        bootsMeta.setLore(lore);
        bootsMeta.setUnbreakable(true);

        boots.setItemMeta(bootsMeta);

        return boots;


    }

}
