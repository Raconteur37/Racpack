package rac.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import rac.particleData.ParticleData;

public class DeathListener implements Listener {

    @EventHandler()
    public void onDeath(PlayerDeathEvent event) {

        Player player = (Player) event.getEntity();

        ParticleData p = new ParticleData(player.getUniqueId());
        if(p.hasID())
            p.endTask();

    }


}

