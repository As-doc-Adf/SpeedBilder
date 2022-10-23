package speedbilder.bilder;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import speedbilder.bilder.command.Pair;

import java.util.HashMap;

public final class Bilder extends JavaPlugin {

    private static Bilder instance;
    private  Storage data;
    private static World world;

    @Override
    public void onEnable() {

        instance = this;
        world = instance.getServer().getWorlds().get(0) ;
        Bukkit.getPluginManager().registerEvents(new EventListner(),this);

        saveDefaultConfig();
        data = new Storage("Buildings.yml");

    }


    public  static  World getMainWorld() {
        return world;
    }
    public  static  Bilder getInstance() {
        return instance;
    }

    public static Storage getBuildings() {
        return  instance.data;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
