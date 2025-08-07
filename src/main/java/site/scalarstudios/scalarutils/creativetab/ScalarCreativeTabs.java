package site.scalarstudios.scalarutils.creativetab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.item.ScalarItems;

public class ScalarCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ScalarUtils.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SCALAR_ITEMS_TAB = CREATIVE_MODE_TABS.register("scalar_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.scalarutils.items"))
            .icon(() -> new ItemStack(ScalarItems.MAIL.get()))
            .build());

    public static void registerTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == SCALAR_ITEMS_TAB.get()) {
            event.accept(ScalarItems.MAIL.get());
        }
    }

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
