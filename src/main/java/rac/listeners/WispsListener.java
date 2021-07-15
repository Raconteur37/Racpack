package rac.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import rac.Main;
import rac.particleData.ParticleData;
import rac.particleEffects.Effects;

public class WispsListener implements Listener {

    @EventHandler
    public void onIceWispClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand() != null) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Ice Wisps"))
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                        ParticleData particle = new ParticleData(player.getUniqueId());
                        if (particle.hasID()) {
                            particle.endTask();
                            particle.removeID();


                        } else {
                            player.sendMessage(ChatColor.AQUA + "Ice Wisps activated");
                            Effects trail = new Effects(player);
                            //trail.healingShieldParticle();
                            trail.iceWispParticle();
                            //e.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,4,1));
                        }
                    }
        }
    }

}
