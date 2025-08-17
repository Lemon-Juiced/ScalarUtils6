package site.scalarstudios.scalarutils.block.factory;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import site.scalarstudios.scalarutils.block.custom.ConveyorBlock;
import site.scalarstudios.scalarutils.item.ScalarItems;
import site.scalarstudios.scalarutils.item.blockitem.ConveyorBlockItem;

import java.util.function.Supplier;

import static site.scalarstudios.scalarutils.block.ScalarBlocks.BLOCKS;

/**
 * Handles the creation of repetitive blocks.
 */
public class ScalarBlockFactory {
    private static String[] cardinalDirections = new String[]{"north", "south", "west", "east"};
    private static String[] speedLevels = new String[]{"slow", "medium", "fast"};

    public static void buildConveyorBlocks(){
        double initialVelocity = 0.2; // Initial velocity for the conveyor blocks
        for (int i = 0; i < cardinalDirections.length; i++) {
            for (int j = 0; j < speedLevels.length; j++) {
                String direction = cardinalDirections[i];
                String speed = speedLevels[j];
                double velocity = initialVelocity * (j + 1); // Increase velocity based on speed level
                String blockName = speed + "_conveyor_" + direction;
                DeferredBlock<Block> conveyorBlock = registerConveyorBlock(blockName, () -> new ConveyorBlock(BlockBehaviour.Properties.of()
                        .strength(2.0F, 2.0F)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.METAL)
                        .mapColor(MapColor.METAL),
                    direction, velocity));
            }
        }
    }

    public static <T extends Block> DeferredBlock<T> registerConveyorBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerConveyorBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerConveyorBlockItem(String name, DeferredBlock<T> block) {
        ScalarItems.ITEMS.register(name, () -> new ConveyorBlockItem(block.get(), new Item.Properties()));
    }

}
