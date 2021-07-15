package rac.listeners;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import rac.Main;
import rac.mobs.AdvancedTroop;
import rac.mobs.BasicTroop;

import java.util.ArrayList;
import java.util.List;

public class MobDropBeaconListener implements Listener {

    BasicTroop spawnBasicTroop = new BasicTroop();
    AdvancedTroop spawnAdvancedTroop = new AdvancedTroop();

    public List<String> basicList = new ArrayList<String>();
    public List<String> advancedList = new ArrayList<String>();

    @EventHandler()
    public void onClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        boolean basicDropTest = event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "" + "Basic Mob Drop Beacon");
        boolean advancedDropTest = event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "" + "Advanced Mob Drop Beacon");


        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.TRIDENT))
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                if (basicDropTest) {
                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                        if (!basicList.contains(player.getName()))
                            basicList.add(player.getName());
                        return;
                    }
                }

            }
        if (basicList.contains(event.getPlayer().getName()))
            basicList.remove(event.getPlayer().getName());
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                if (advancedDropTest) {
                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                        if (!advancedList.contains(player.getName()))
                            advancedList.add(player.getName());
                        return;
                    }
                }

            }
            if (advancedList.contains(event.getPlayer().getName()))
                advancedList.remove(event.getPlayer().getName());
    }




    @EventHandler()
    public void onLand(ProjectileHitEvent event) {

        Trident trident = (Trident) event.getEntity();
        Player player = (Player) trident.getShooter();
        Location loc = trident.getLocation();
        loc.setY(loc.getY() + 3);
        World world = player.getWorld();

        if (event.getEntity() == trident) {
            if (basicList.contains(player.getName())) {

                player.sendMessage(ChatColor.GREEN + "" + "Dropping troops");
                world.playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 2F, 2F);
                spawnBasicTroop.SpawnBasicTroop(loc);
                spawnBasicTroop.SpawnBasicTroop(loc);
                spawnBasicTroop.SpawnBasicTroop(loc);
                trident.remove();
                basicList.remove(player.getName());
            }
            if (advancedList.contains(player.getName())) {

                player.sendMessage(ChatColor.GREEN + "" + "Dropping troops");
                world.playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 2F, 2F);
                spawnAdvancedTroop.SpawnAdvancedTroop(loc);
                spawnAdvancedTroop.SpawnAdvancedTroop(loc);
                spawnAdvancedTroop.SpawnAdvancedTroop(loc);
                trident.remove();
                advancedList.remove(player.getName());
            }
        }



    }



}
