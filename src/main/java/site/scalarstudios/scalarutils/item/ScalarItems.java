package site.scalarstudios.scalarutils.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.item.custom.InfiniteFluidChaliceItem;

import java.util.function.Supplier;

public class ScalarItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(ScalarUtils.MODID);

    // Crafting Items
    public static final Supplier<Item> CHALICE = ITEMS.register("chalice", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> HEART_OF_THE_CORE = ITEMS.register("heart_of_the_core", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> MAIL = ITEMS.register("mail", () -> new Item(new Item.Properties()));

    // Utility Items
    public static final Supplier<Item> CHALICE_OF_THE_CORE = ITEMS.register("chalice_of_the_core", () -> new InfiniteFluidChaliceItem(Fluids.LAVA, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Supplier<Item> CHALICE_OF_THE_SEA = ITEMS.register("chalice_of_the_sea", () -> new InfiniteFluidChaliceItem(Fluids.WATER, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
