package site.scalarstudios.scalarutils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import site.scalarstudios.scalarutils.creativetab.ScalarCreativeTabs;
import site.scalarstudios.scalarutils.item.ScalarItems;

@Mod(ScalarUtils.MODID)
public class ScalarUtils {
    public static final String MODID = "scalarutils";

    public ScalarUtils(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Register Items
        ScalarItems.register(modEventBus);

        // Register Creative Tabs
        ScalarCreativeTabs.register(modEventBus);
        modEventBus.addListener(ScalarCreativeTabs::registerTabs);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
}
