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

public class SummonerSwordCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get a bow");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getSummonerSword());

        }
        return false;
    }

    public ItemStack getSummonerSword() {

        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = sword.getItemMeta();

        meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "" + "Summoner Sword");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "" + "Killing an enemy summons a troop for you");
        meta.setLore(lore);
        meta.setUnbreakable(true);

        sword.setItemMeta(meta);

        return sword;

    }


}
