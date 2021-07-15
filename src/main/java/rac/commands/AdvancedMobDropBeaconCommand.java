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

public class AdvancedMobDropBeaconCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get a bow");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getAdvancedMobDropBeacon());

        }
        return false;
    }

    public ItemStack getAdvancedMobDropBeacon() {

        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemMeta meta = trident.getItemMeta();

        meta.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "" + "Advanced Mob Drop Beacon");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "" + "Throw to call in advanced troop backup");
        meta.setLore(lore);
        trident.getItemMeta().setUnbreakable(true);

        trident.setItemMeta(meta);

        return trident;

    }


}
