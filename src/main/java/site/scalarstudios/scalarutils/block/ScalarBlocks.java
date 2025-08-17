package site.scalarstudios.scalarutils.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.block.factory.ScalarBlockFactory;
import site.scalarstudios.scalarutils.item.ScalarItems;

import java.util.function.Supplier;

public class ScalarBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ScalarUtils.MODID);

    public static final DeferredBlock<Block> CITRINE_BLOCK = registerBlock("citrine_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(5.0F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL)
            .mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> CITRINE_ORE = registerBlock("citrine_ore", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
            .strength(3.0F, 3.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
            .mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_CITRINE_ORE = registerBlock("deepslate_citrine_ore", () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
            .strength(4.5F, 3.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE)
            .mapColor(MapColor.DEEPSLATE)));
    public static final DeferredBlock<Block> ROSE_GOLD_BLOCK = registerBlock("rose_gold_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(5.0F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL)
            .mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> UMBRAL_GOO_ORE = registerBlock("umbral_goo_ore", () -> new Block(BlockBehaviour.Properties.of()
            .strength(3.0F, 3.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL)
            .mapColor(MapColor.SAND)));
    public static final DeferredBlock<Block> UMBRALITE_GEM_BLOCK = registerBlock("umbralite_gem_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(5.0F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL)
            .mapColor(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<Block> WHITE_GOLD_BLOCK = registerBlock("white_gold_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(5.0F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL)
            .mapColor(MapColor.SNOW)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ScalarItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        // Register the Factories
        ScalarBlockFactory.buildConveyorBlocks();

        // Register the blocks
        BLOCKS.register(eventBus);
    }
}
