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

public class HealingStaffCommand implements CommandExecutor {

    // A staff that targets the closest entity and healed them with a solid updating vector beam

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console cannot get a bow");
            return false;
        } else {

            Player player = (Player) sender;
            player.getInventory().addItem(getHealingStaff());

        }
        return false;
    }

    public ItemStack getHealingStaff() {

        ItemStack healingStaff = new ItemStack(Material.BAMBOO);
        ItemMeta meta = healingStaff.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "" + "Healing Staff");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "" + "Activate to heal allies around you");
        meta.setLore(lore);
        healingStaff.getItemMeta().setUnbreakable(true);

        healingStaff.setItemMeta(meta);

        return healingStaff;

    }



}
