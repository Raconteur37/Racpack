package rac.particleEffects;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import rac.Main;
import rac.particleData.ParticleData;

import java.util.List;

public class Effects {

    private int taskID;
    private final Player player;
    public LivingEntity livingEntity = null;

    public Effects(Player player) {
        this.player = player;
    }

    public void healingShieldParticle() {

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

            double var = 0;
            Location loc, first, second;
            ParticleData particle = new ParticleData(player.getUniqueId());

            @Override
            public void run() {
                if (!particle.hasID()) {
                   particle.setID(taskID);
                }

                var += Math.PI / 16;

                loc = player.getLocation();
                first = loc.clone().add(Math.cos(var), Math.sin(var) + 1, Math.sin(var));
                second = loc.clone().add(Math.cos(var + Math.PI), Math.sin(var) + 1, Math.sin(var + Math.PI));

                player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
                player.getWorld().spawnParticle(Particle.TOTEM, second, 0);

            }

        }, 0 ,1);

    }

    public void healingBeamParticle() {

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

            ParticleData particle = new ParticleData(player.getUniqueId());

            double var = 0;
            Location loc, first, second;

            @Override
            public void run() {
                if (!particle.hasID()) {
                    particle.setID(taskID);
                }

                loc = player.getLocation();
                //Location loc = player.getLocation();
                double lowestDistance = Double.MAX_VALUE;
                LivingEntity closest = null;
                List<Entity> near = player.getNearbyEntities(12.0,12.0,12.0); //gets all living entities within range
                for (Entity e : near) {
                    if (e instanceof  LivingEntity) {
                        double distance = e.getLocation().distance(loc);
                        if (distance < lowestDistance)
                            if (e.getType() != EntityType.ITEM_FRAME) {
                                distance = e.getLocation().distance(loc);
                                lowestDistance = distance;
                                closest = (LivingEntity) e;
                            }
                    }
                }

                Particle.DustOptions dust = new Particle.DustOptions(
                        Color.fromRGB(0 ,255 ,0 ), 1);

                Location start = player.getLocation().add(0,1,0);
                Vector dir = closest.getLocation().add(0,0.5,0).subtract(loc).toVector();
                for (double i = 0; i < lowestDistance ; i += 0.2) {
                    if (i == 0) continue;
                    dir.multiply(i); // multiply
                    start.add(dir); // add
                    loc.getWorld().spawnParticle(Particle.REDSTONE, start, 0, 0, 0, 0, dust);
                    start.subtract(dir); // subtract
                    dir.normalize(); // normalize
                }

                closest.setHealth(closest.getHealth() + .5);
                closest.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20,1));

            }
        }, 0, 5);

    }

    public void iceWispParticle() {

            ParticleData particle = new ParticleData(player.getUniqueId());

            double var = 0;
            Location loc, first, second;



                loc = player.getLocation();
                //Location loc = player.getLocation();
                double lowestDistance = Double.MAX_VALUE;
                LivingEntity closest = null;
                List<Entity> near = player.getNearbyEntities(12.0,12.0,12.0); //gets all living entities within range
                for (Entity e : near) {
                    if (e instanceof  LivingEntity) {
                        double distance = e.getLocation().distance(loc);
                        if (distance < lowestDistance)
                            if (e.getType() != EntityType.ITEM_FRAME) {
                                distance = e.getLocation().distance(loc);
                                lowestDistance = distance;
                                closest = (LivingEntity) e;
                            }
                    }
                }

                Particle.DustOptions dust = new Particle.DustOptions(
                        Color.fromRGB(0 ,0 ,255 ), 1);

                Location start = player.getLocation().add(0,1,0);
                Vector dir = closest.getLocation().add(0,0.5,0).subtract(loc).toVector();
                for (double i = 0; i < lowestDistance ; i += 0.2) {
                    if (i == 0) continue;
                    dir.multiply(i); // multiply
                    start.add(dir); // add
                    loc.getWorld().spawnParticle(Particle.REDSTONE, start, 0, 0, 0, 0, dust);
                    start.subtract(dir); // subtract
                    dir.normalize(); // normalize
                }

                closest.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,10,1));


    }
}

