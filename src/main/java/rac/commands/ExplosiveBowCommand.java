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

public class ExplosiveBowCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get a bow");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getExplosiveBow());

        }
        return false;
    }

    public ItemStack getExplosiveBow() {

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();

        meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + "Explosive Bow");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE + "" + ChatColor.ITALIC + "" + "Fires explosive arrows");
        meta.setLore(lore);
        meta.setUnbreakable(true);

        bow.setItemMeta(meta);

        return bow;

    }
}