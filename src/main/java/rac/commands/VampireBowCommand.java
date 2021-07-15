package rac.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class VampireBowCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get a bow");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getVampireBow());

        }
        return false;
    }

    public ItemStack getVampireBow() {

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();

        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "" + "Vampire Bow");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + "Damaging an enemy heals you");
        meta.setLore(lore);
        meta.setUnbreakable(true);

        bow.setItemMeta(meta);

        return bow;

    }






}
