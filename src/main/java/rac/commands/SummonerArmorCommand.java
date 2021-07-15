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

public class SummonerArmorCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get that");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getSummonerHelmet());
            player.getInventory().addItem(getSummonerChestplate());
            player.getInventory().addItem(getSummonerBoots());
            player.getInventory().addItem(getSummonerLeggings());

        }
        return false;
    }

    // Helmet gives troops you summon buffs
    // Chestplate gives troops you summon more armor
    // Leggings summons double troops
    // Boots summons many baby death troops when you are on critical health
    // Full set summons death troops when killing an enemy with anything and staff uses less health
    // msg is The undead cling to you for guidance

    public ItemStack getSummonerHelmet() {

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetMeta.setColor(Color.fromRGB(224,224,224));

        helmetMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "" + "Summoner Helmet");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + "Troops you summon gain buffs");
        helmetMeta.setLore(lore);
        helmetMeta.setUnbreakable(true);

        helmet.setItemMeta(helmetMeta);

        return helmet;
    }

    public ItemStack getSummonerChestplate() {

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateItemMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        chestplateItemMeta.setColor(Color.fromRGB(224,224,224));

        chestplateItemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "" + "Summoner Chestplate");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + "Troops you summon have extra armor");
        chestplateItemMeta.setLore(lore);
        chestplateItemMeta.setUnbreakable(true);

        chestplate.setItemMeta(chestplateItemMeta);

        return chestplate;
    }

    public ItemStack getSummonerLeggings() {

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
        leggingsMeta.setColor(Color.fromRGB(224,224,224));

        leggingsMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "" + "Summoner Leggings");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + "Summon double troops");
        leggingsMeta.setLore(lore);
        leggingsMeta.setUnbreakable(true);

        leggings.setItemMeta(leggingsMeta);

        return leggings;
    }

    public ItemStack getSummonerBoots() {

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(Color.fromRGB(224,224,224));

        bootsMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "" + "Summoner Boots");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + "Death troops are now mini, summon them on critical health");
        bootsMeta.setLore(lore);
        bootsMeta.setUnbreakable(true);

        boots.setItemMeta(bootsMeta);

        return boots;
    }


}
