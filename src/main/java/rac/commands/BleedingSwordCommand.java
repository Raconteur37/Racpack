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

public class BleedingSwordCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get a sword");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getBleedingSword());

        }
        return false;
    }

    public ItemStack getBleedingSword() {

        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = sword.getItemMeta();

        meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + "Bleeding Sword");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "" + "Hitting an enemy has a chance to make them bleed.");
        meta.setLore(lore);
        meta.setUnbreakable(true);

        sword.setItemMeta(meta);

        return sword;

    }
}
