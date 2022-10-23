package speedbilder.bilder;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListner implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.sendMessage("Hello , click on note block and start game!");
        player.teleport(new Location(Bilder.getInstance().getServer().getWorlds().get(0), 38,71,201));

    }

    @EventHandler
    public  void Interact(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(e.getClickedBlock().getType().equals(Material.NOTE_BLOCK))
            {
                e.getPlayer().teleport(new Location(Bilder.getInstance().getServer().getWorlds().get(0), 47,72,201));
                SpeedBilderGame.spawnRandomShape();
                new Countdown(10, Bilder.getInstance()) {

                    @Override
                    public void count(int current) {
                        e.getPlayer().sendMessage(Integer.toString(current));
                        if(current == 0) {
                                SpeedBilderGame.clearField();
                            e.getPlayer().sendMessage("Start to building");
                            new Countdown(15, Bilder.getInstance()) {

                                @Override
                                public void count(int current) {
                                    e.getPlayer().sendMessage(Integer.toString(current));

                                    if(current == 0) {
                                        double percentage = (double) Math.round(SpeedBilderGame.getCompletionPercentage() * 100) / 100;
                                        if(percentage >= 90) {
                                            e.getPlayer().sendMessage("You won with result:" + Double.toString(percentage) +"%");
                                        }
                                        else{
                                            e.getPlayer().sendMessage("You lose with result:" + Double.toString(percentage) +"%");
                                        }
                                        SpeedBilderGame.clearField();
                                        e.getPlayer().teleport(new Location(Bilder.getInstance().getServer().getWorlds().get(0), 38,71,201));

                                    }
                                }

                            }.start();
                        }
                    }

                }.start();


            }

        }

    }

}
