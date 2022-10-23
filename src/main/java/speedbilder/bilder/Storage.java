package speedbilder.bilder;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Storage {
    private File file;
    private FileConfiguration config;

    public Storage(String name) {
        file = new File(Bilder.getInstance().getDataFolder(), name);
        try {
            if(!file.exists() && !file.createNewFile()) throw  new IOException();
        }
        catch (IOException e) {
            throw  new RuntimeException("Failied to create  "+ name,e);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig(){
        return config;
    }

    public void save(){
        try{
            config.save(file);
        }
        catch (IOException e) {
            throw new RuntimeException("Failied to save",e);
        }
    }

}
