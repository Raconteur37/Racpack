package rac.mobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class CombatMedic {

    public Villager SpawnCombatMedic(Location loc) {

        Villager combatMedic = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        combatMedic.setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "Combat Medic");
        combatMedic.setCustomNameVisible(true);

        return combatMedic;
    }


}
