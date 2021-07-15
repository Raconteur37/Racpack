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

public class SummonerStaffCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get a bow");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getSummonerStaff());

        }
        return false;
    }

    public ItemStack getSummonerStaff() {

        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta meta = stick.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "" + "Summoner Staff");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + "Activate to sacrifice health and summon a troop");
        meta.setLore(lore);

        stick.setItemMeta(meta);

        return stick;

    }

}
