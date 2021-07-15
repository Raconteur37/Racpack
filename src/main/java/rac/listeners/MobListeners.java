package rac.listeners;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import java.util.List;

public class MobListeners implements Listener {

    SwordListener swordListener = new SwordListener();

    /*@EventHandler
    public void onEntityTarget(EntityTargetLivingEntityEvent event) {

        LivingEntity entity = (LivingEntity) event.getEntity();
        LivingEntity target = event.getTarget();

        if (entity.getCustomName().contains("Death Troop")) {
            Location loc = null;
            LivingEntity closest = null;
            loc = entity.getLocation();
            double lowestDistance = Double.MAX_VALUE;
            List<Entity> near = entity.getNearbyEntities(20.0, 20.0, 20.0); //gets all living entities within range
            for (Entity e : near) {
                if (e instanceof LivingEntity) {
                    double distance = e.getLocation().distance(loc);
                    if (distance < lowestDistance)
                        if (e.getUniqueId() != swordListener.getSummonerUUID()) {
                            e.sendMessage(String.valueOf(swordListener.getSummonerUUID()));
                            distance = e.getLocation().distance(loc);
                            lowestDistance = distance;
                            closest = (LivingEntity) e;
                            event.setTarget(closest);
                        }
                }
            }
        }
    }*/

}
