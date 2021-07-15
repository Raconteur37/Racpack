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

public class BasicMobDropBeaconCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get a bow");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getBasicMobDropBeacon());

        }
        return false;
    }

    public ItemStack getBasicMobDropBeacon() {

        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemMeta meta = trident.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "" + "Basic Mob Drop Beacon");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "" + "Throw to call in basic troop backup");
        meta.setLore(lore);
        trident.getItemMeta().setUnbreakable(true);

        trident.setItemMeta(meta);

        return trident;

    }




}
