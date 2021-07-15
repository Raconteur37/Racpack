package rac.particleData;

import org.bukkit.Bukkit;

import java.util.*;

public class ParticleData {

    private static Map<UUID, Integer> TRAILS = new HashMap<>();
    private final UUID uuid;

    public ParticleData(UUID uuid) {

        this.uuid = uuid;
    }

    public void setID(int id) {

        TRAILS.put(uuid,id);
    }

    public int getId() {

        return TRAILS.get(uuid);
    }

    public boolean hasID() {

        if (TRAILS.containsKey(uuid))
            return true;
        return false;
    }

    public void removeID() {

        TRAILS.remove(uuid);
    }

    public void endTask() {

        if (getId() == 1)
            return;
        Bukkit.getScheduler().cancelTask(getId());
    }

    public static boolean hasFakeID(UUID uuid) {
        if (TRAILS.containsKey(uuid))
            if (TRAILS.get(uuid) == 1)
                return true;
        return false;
    }

}
