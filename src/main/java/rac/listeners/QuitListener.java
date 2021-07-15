package rac.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import rac.particleData.ParticleData;

public class QuitListener implements Listener {

    public void onQuit(PlayerQuitEvent event) {
        ParticleData p = new ParticleData(event.getPlayer().getUniqueId());
        if(p.hasID())
            p.endTask();
    }


}
