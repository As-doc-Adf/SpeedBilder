package speedbilder.bilder.command;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import speedbilder.bilder.Bilder;
import speedbilder.bilder.SpeedBilderGame;

import java.util.List;

public class Command extends AbstractCommand{

    public Command(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if(sender.hasPermission("reload")) {Bilder.getInstance().reloadConfig();}
    }


    public List<String> complete(CommandSender sender, String[] args){
        if(args.length == 1) return Lists.newArrayList("reload");
        return  Lists.newArrayList();
    }

}
