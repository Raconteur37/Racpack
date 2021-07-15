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

public class VampireArmorSetCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get that");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getVampireHelmet());
            player.getInventory().addItem(getVampireChestplate());
            player.getInventory().addItem(getVampireLeggings());
            player.getInventory().addItem(getVampireBoots());


        }
        return false;
    }

    // Helmet increases damage of vampire bow
    // Chestplate gain health back when hit
    // Leggings adds a small life steal to every basic attack
    // Boots killing an enemy fully heals you

    public ItemStack getVampireHelmet() {

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetMeta.setColor(Color.fromRGB(204,0,0));

        helmetMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "" + "Vampire Helmet");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + "Increases vampire bow life steal");
        helmetMeta.setLore(lore);
        helmetMeta.setUnbreakable(true);

        helmet.setItemMeta(helmetMeta);

        return helmet;
    }

    public ItemStack getVampireChestplate() {

        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestMeta = (LeatherArmorMeta) chest.getItemMeta();
        chestMeta.setColor(Color.fromRGB(204,0,0));

        chestMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "" + "Vampire Chestplate");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + "Gain health back when taking damage");
        chestMeta.setLore(lore);
        chestMeta.setUnbreakable(true);

        chest.setItemMeta(chestMeta);

        return chest;

    }

    public ItemStack getVampireLeggings() {

        ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta legsMeta = (LeatherArmorMeta) legs.getItemMeta();
        legsMeta.setColor(Color.fromRGB(204,0,0));

        legsMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "" + "Vampire Leggings");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + "Adds minor life steal to basic attacks");
        legsMeta.setLore(lore);
        legsMeta.setUnbreakable(true);

        legs.setItemMeta(legsMeta);

        return legs;

    }

    public ItemStack getVampireBoots() {

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(Color.fromRGB(204,0,0));

        bootsMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "" + "Vampire Boots");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + "Killing an enemy fully heals you");
        bootsMeta.setLore(lore);
        bootsMeta.setUnbreakable(true);

        boots.setItemMeta(bootsMeta);

        return boots;


    }

}
