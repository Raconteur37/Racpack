package rac;


import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import rac.commands.*;
import rac.listeners.*;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;
    public static Logger logger;

    @Override
    public void onEnable() {

        registerEvents();

        plugin = this;
        logger = getLogger();

        logger.info("Raconteur's Plugin is active.");
        getCommand("witherbow").setExecutor(new WitherBowCommand());
        getCommand("vampirebow").setExecutor(new VampireBowCommand());
        getCommand("debuffbow").setExecutor(new DebuffBowCommand());
        getCommand("explosivebow").setExecutor(new ExplosiveBowCommand());
        getCommand("basicmobdropbeacon").setExecutor(new BasicMobDropBeaconCommand());
        getCommand("advancedmobdropbeacon").setExecutor(new AdvancedMobDropBeaconCommand());
        getCommand("healingstaff").setExecutor(new HealingStaffCommand());
        getCommand("bleedingsword").setExecutor(new BleedingSwordCommand());
        getCommand("witherset").setExecutor(new WitherArmorSetCommand());
        getCommand("debuffset").setExecutor(new DebuffArmorSetCommand());
        getCommand("vampireset").setExecutor(new VampireArmorSetCommand());
        getCommand("summonersword").setExecutor(new SummonerSwordCommand());
        getCommand("summonerset").setExecutor(new SummonerArmorCommand());
        getCommand("summonerstaff").setExecutor(new SummonerStaffCommand());
        getCommand("iceset").setExecutor(new IceArmorSetCommand());
        getCommand("icesword").setExecutor(new IceSwordCommand());
        getCommand("icewisps").setExecutor(new IceWispsCommand());


    }

    @Override
    public void onDisable() {


        plugin = null;

    }

    private void registerEvents() {

        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(this,this);
        pluginManager.registerEvents(new BowListeners(),this);
        pluginManager.registerEvents(new MobDropBeaconListener(), this);
        pluginManager.registerEvents(new StaffListener(),this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new SwordListener(), this);
        pluginManager.registerEvents(new ArmorListener(),this);
        pluginManager.registerEvents(new MobListeners(),this);
        pluginManager.registerEvents(new WispsListener(),this);


    }


}
