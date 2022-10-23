package speedbilder.bilder;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import speedbilder.bilder.command.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpeedBilderGame  {
    //[3][8][4][7]
    private static Pair<Location,Location> loc1 = new Pair<>(new Location(Bilder.getMainWorld(),18,71,190),new Location(Bilder.getMainWorld(),24,75,197));
    private static Pair<Location,Location> loc2 = new Pair<>(new Location(Bilder.getMainWorld(),18,71,200),new Location(Bilder.getMainWorld(),24,75,207));
    private static Pair<Location,Location> loc3 = new Pair<>(new Location(Bilder.getMainWorld(),18,71,210),new Location(Bilder.getMainWorld(),24,75,217));
    private static int lastRandNum = 0;
    private  static  Pair[] count = {loc1,loc2,loc3};
    private static final Pair<Location, Location> gameField = new Pair<>(new Location(Bilder.getMainWorld(),44,72,203),new Location(Bilder.getMainWorld(),50,76,210));


    public static void spawnRandomShape(){
        Random random = new Random();
        lastRandNum = random.nextInt(count.length);
        filingField(Bilder.getMainWorld(), gameField, count[lastRandNum]);
    }

    public static double getCompletionPercentage() {
        World world = Bilder.getMainWorld();
        double sizeX = Math.abs(gameField.getValue().getX()-gameField.getKey().getX());
        double sizeY = Math.abs(gameField.getValue().getY()-gameField.getKey().getY());
        double sizeZ = Math.abs(gameField.getValue().getZ()-gameField.getKey().getZ());
        double Percentage = 0;
        double counter = 0;
        Pair<Location, Location> example = count[lastRandNum];
        for(double x = 0; x <= sizeX; x++) {
            for (double y = 0; y <= sizeY; y++) {
                for (double z = 0; z <= sizeZ; z++) {
                    Block block0 = world.getBlockAt(new Location(world, gameField.getKey().getX() + x, gameField.getKey().getY() + y, gameField.getKey().getZ() + z));
                    Block block1 = world.getBlockAt(new Location(world, example.getKey().getX() + x, example.getKey().getY() + y, example.getKey().getZ() + z));
                    if(block1.getType() != Material.AIR) {
                        counter++;
                        if (block0.getType() == block1.getType()) {
                            Percentage++;
                        }
                    }
                }
            }
        }
        return (Percentage/counter)*100;
    }

    private static  void filingField(World world, Pair<Location, Location> field, Pair<Location, Location> example){
        double ExSizeX = Math.abs(example.getValue().getX()-example.getKey().getX());
        double ExSizeY = Math.abs(example.getValue().getY()-example.getKey().getY());
        double ExSizeZ = Math.abs(example.getValue().getZ()-example.getKey().getZ());

        double sizeX = Math.abs(field.getValue().getX()-field.getKey().getX());
        double sizeY = Math.abs(field.getValue().getY()-field.getKey().getY());
        double sizeZ = Math.abs(field.getValue().getZ()-field.getKey().getZ());

        if(ExSizeX == sizeX && ExSizeY == sizeY && ExSizeZ == sizeZ) {
            for(double x = 0; x <= sizeX; x++) {
                for(double y = 0; y <= sizeY; y++) {
                    for(double z = 0; z <= sizeZ; z++) {
                            Block block0 = world.getBlockAt(new Location(world,field.getKey().getX()+x,field.getKey().getY()+y,field.getKey().getZ()+z));
                            Location plugInBlock = new Location(world,example.getKey().getX()+x,example.getKey().getY()+y,example.getKey().getZ()+z);
                            block0.setType(plugInBlock.getBlock().getType());
                    }
                }
            }
        }
        else{
            System.out.println("Field size and example do not match:");

            System.out.println("Field size X:"+sizeX);
            System.out.println("Field size X:"+sizeY);
            System.out.println("Field size X:"+sizeZ);

            System.out.println("Example size X:"+ExSizeX);
            System.out.println("Example size X:"+ExSizeY);
            System.out.println("Example size X:"+ExSizeZ);
        }
    }

    public static void clearField(){

        World world = Bilder.getMainWorld();
        double sizeX = Math.abs(gameField.getValue().getX()-gameField.getKey().getX());
        double sizeY = Math.abs(gameField.getValue().getY()-gameField.getKey().getY());
        double sizeZ = Math.abs(gameField.getValue().getZ()-gameField.getKey().getZ());

        for(double x = 0; x <= sizeX; x++) {
            for(double y = 0; y <= sizeY; y++) {
                for(double z = 0; z <= sizeZ; z++) {
                    Block block0 = world.getBlockAt(new Location(world,gameField.getKey().getX()+x,gameField.getKey().getY()+y,gameField.getKey().getZ()+z));
                    block0.setType(Material.AIR);
                }
            }
        }
    }

    private static List<Block> getRegionBlocks(World world, Location loc1, Location loc2) {
        List<Block> blocks = new ArrayList<Block>();
        for(double x = loc1.getX(); x <= loc2.getX(); x++) {
            for(double y = loc1.getY(); y <= loc2.getY(); y++) {
                for(double z = loc1.getZ(); z <= loc2.getZ(); z++) {
                    Location loc = new Location(world, x, y, z);
                    blocks.add(loc.getBlock());
                }
            }
        }
        return blocks;
    }


}
