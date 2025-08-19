package site.scalarstudios.scalarutils.creativetab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.block.ScalarBlocks;
import site.scalarstudios.scalarutils.block.custom.ConveyorBlock;
import site.scalarstudios.scalarutils.item.ScalarItems;

public class ScalarCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ScalarUtils.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SCALAR_BLOCKS_TAB = CREATIVE_MODE_TABS.register("scalar_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.scalarutils.blocks"))
            .icon(() -> new ItemStack(ScalarBlocks.CITRINE_BLOCK.get()))
            .build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SCALAR_ITEMS_TAB = CREATIVE_MODE_TABS.register("scalar_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.scalarutils.items"))
            .icon(() -> new ItemStack(ScalarItems.MAIL.get()))
            .build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SCALAR_TOOLS_TAB = CREATIVE_MODE_TABS.register("scalar_tools", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.scalarutils.tools"))
            .icon(() -> new ItemStack(ScalarItems.UMBRALITE_SWORD.get()))
            .build());

    public static void registerTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == SCALAR_BLOCKS_TAB.get()) {
            event.accept(ScalarBlocks.CITRINE_BLOCK.get());
            event.accept(ScalarBlocks.CITRINE_ORE.get());
            event.accept(ScalarBlocks.DEEPSLATE_CITRINE_ORE.get());
            event.accept(ScalarBlocks.ROSE_GOLD_BLOCK.get());
            event.accept(ScalarBlocks.UMBRAL_GOO_ORE.get());
            event.accept(ScalarBlocks.UMBRALITE_GEM_BLOCK.get());
            event.accept(ScalarBlocks.WHITE_GOLD_BLOCK.get());
            for (DeferredHolder<Block, ? extends Block> block : ScalarBlocks.BLOCKS.getEntries())
                if (block.get() instanceof ConveyorBlock) event.accept(block.get());
        } else if (event.getTab() == SCALAR_ITEMS_TAB.get()) {
            event.accept(ScalarItems.CITRINE.get());
            event.accept(ScalarItems.CHALICE.get());
            event.accept(ScalarItems.FLOUR.get());
            event.accept(ScalarItems.HEART_OF_THE_CORE.get());
            event.accept(ScalarItems.MAIL.get());
            event.accept(ScalarItems.ROSE_GOLD_ALLOY_COMPOUND.get());
            event.accept(ScalarItems.ROSE_GOLD_INGOT.get());
            event.accept(ScalarItems.ROSE_GOLD_NUGGET.get());
            event.accept(ScalarItems.TOAST.get());
            event.accept(ScalarItems.UMBRAL_GOO.get());
            event.accept(ScalarItems.UMBRALITE_GEM.get());
            event.accept(ScalarItems.UMBRALITE_SMITHING_TEMPLATE.get());
            event.accept(ScalarItems.WHITE_GOLD_ALLOY_COMPOUND.get());
            event.accept(ScalarItems.WHITE_GOLD_INGOT.get());
            event.accept(ScalarItems.WHITE_GOLD_NUGGET.get());
        } else if (event.getTab() == SCALAR_TOOLS_TAB.get()) {
            event.accept(ScalarItems.CHALICE_OF_THE_CORE.get());
            event.accept(ScalarItems.CHALICE_OF_THE_SEA.get());
            event.accept(ScalarItems.UMBRALITE_SWORD.get());
            event.accept(ScalarItems.UMBRALITE_PICKAXE.get());
            event.accept(ScalarItems.UMBRALITE_AXE.get());
            event.accept(ScalarItems.UMBRALITE_SHOVEL.get());
            event.accept(ScalarItems.UMBRALITE_HOE.get());
        }
    }

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
