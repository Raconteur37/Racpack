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

public class IceWispsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get a bow");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getIceWisps());

        }
        return false;
    }

    public ItemStack getIceWisps() {

        ItemStack wisps = new ItemStack(Material.CYAN_DYE);
        ItemMeta meta = wisps.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "" + "Ice Wisps");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "" + "Activate to summon wisps that freeze enemies");
        meta.setLore(lore);
        wisps.getItemMeta().setUnbreakable(true);

        wisps.setItemMeta(meta);

        return wisps;

    }

}
