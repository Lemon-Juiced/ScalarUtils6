package site.scalarstudios.scalarutils.block.factory;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import site.scalarstudios.scalarutils.block.custom.ConveyorBlock;
import site.scalarstudios.scalarutils.block.custom.FlatLightBlock;
import site.scalarstudios.scalarutils.item.ScalarItems;
import site.scalarstudios.scalarutils.item.blockitem.ConveyorBlockItem;
import site.scalarstudios.scalarutils.item.blockitem.FlatLightBlockItem;
import site.scalarstudios.scalarutils.item.blockitem.InvertedFlatLightBlockItem;

import java.util.function.Supplier;

import static site.scalarstudios.scalarutils.block.ScalarBlocks.BLOCKS;

/**
 * Handles the creation of repetitive blocks.
 */
public class ScalarBlockFactory {
    private static String[] cardinalDirections = new String[]{"north", "south", "west", "east"};
    private static String[] speedLevels = new String[]{"slow", "medium", "fast"};
    private static String[] colors = new String[]{"white", "light_gray", "gray", "black", "brown", "red", "orange", "yellow", "lime", "green", "cyan", "light_blue", "blue", "purple", "magenta", "pink"};
    private static MapColor[] mapColors = new MapColor[]{MapColor.SNOW, MapColor.COLOR_LIGHT_GRAY, MapColor.COLOR_GRAY, MapColor.COLOR_BLACK, MapColor.COLOR_BROWN, MapColor.COLOR_RED, MapColor.COLOR_ORANGE, MapColor.COLOR_YELLOW, MapColor.COLOR_LIGHT_GREEN, MapColor.COLOR_GREEN, MapColor.COLOR_CYAN, MapColor.COLOR_LIGHT_BLUE, MapColor.COLOR_BLUE, MapColor.COLOR_PURPLE, MapColor.COLOR_MAGENTA, MapColor.COLOR_PINK};

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

    public static void buildFlatLightBlocks() {
        for (int i = 0; i < colors.length; i++) {
            int finalI = i; // Final variable for lambda expression
            DeferredBlock<Block> lightBlock = registerFlatLightBlock(colors[i] + "_flat_light", () -> new FlatLightBlock(BlockBehaviour.Properties.of()
                    .mapColor(mapColors[finalI])
                    .strength(0.3F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS), false));
            DeferredBlock<Block> invertedLightBlock = registerInvertedFlatLightBlock("inverted_" + colors[i] + "_flat_light", () -> new FlatLightBlock(BlockBehaviour.Properties.of()
                    .mapColor(mapColors[finalI])
                    .strength(0.3F)
                    .lightLevel(state -> 15)
                    .sound(SoundType.GLASS), true));
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

    public static <T extends Block> DeferredBlock<T> registerFlatLightBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerFlatLightBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerFlatLightBlockItem(String name, DeferredBlock<T> block) {
        ScalarItems.ITEMS.register(name, () -> new FlatLightBlockItem(block.get(), new Item.Properties()));
    }

    public static <T extends Block> DeferredBlock<T> registerInvertedFlatLightBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerInvertedFlatLightBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerInvertedFlatLightBlockItem(String name, DeferredBlock<T> block) {
        ScalarItems.ITEMS.register(name, () -> new InvertedFlatLightBlockItem(block.get(), new Item.Properties()));
    }

}
