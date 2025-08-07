package site.scalarstudios.scalarutils.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;

import java.util.function.Supplier;

public class ScalarItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(ScalarUtils.MODID);

    public static final Supplier<Item> MAIL = ITEMS.register("mail", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
