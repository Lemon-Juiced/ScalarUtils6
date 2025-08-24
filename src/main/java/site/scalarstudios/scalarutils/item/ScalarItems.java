package site.scalarstudios.scalarutils.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.scalarutils.ScalarUtils;
import site.scalarstudios.scalarutils.item.custom.*;
import site.scalarstudios.scalarutils.item.tier.ScalarTiers;

import java.util.function.Supplier;

import static dev.lemonjuice.scalar_core.item.ToolItemPropFactory.*;

public class ScalarItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(ScalarUtils.MODID);

    // Crafting Items
    public static final Supplier<Item> CITRINE = ITEMS.register("citrine", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> CHALICE = ITEMS.register("chalice", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> FLOUR = ITEMS.register("flour", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> LUMINITE_SHARD = ITEMS.register("luminite_shard", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> HEART_OF_THE_CORE = ITEMS.register("heart_of_the_core", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> MAIL = ITEMS.register("mail", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ROSE_GOLD_ALLOY_COMPOUND = ITEMS.register("rose_gold_alloy_compound", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ROSE_GOLD_INGOT = ITEMS.register("rose_gold_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ROSE_GOLD_NUGGET = ITEMS.register("rose_gold_nugget", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> UMBRAL_GOO = ITEMS.register("umbral_goo", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> UMBRALITE_GEM = ITEMS.register("umbralite_gem", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> UMBRALITE_SMITHING_TEMPLATE = ITEMS.register("umbralite_smithing_template", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> WHITE_GOLD_ALLOY_COMPOUND = ITEMS.register("white_gold_alloy_compound", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> WHITE_GOLD_INGOT = ITEMS.register("white_gold_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> WHITE_GOLD_NUGGET = ITEMS.register("white_gold_nugget", () -> new Item(new Item.Properties()));

    // Food Items
    public static final Supplier<Item> TOAST = ITEMS.register("toast", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationModifier(1.0F).build())));

    // Utility Items
    public static final Supplier<Item> CHALICE_OF_THE_CORE = ITEMS.register("chalice_of_the_core", () -> new InfiniteFluidChaliceItem(Fluids.LAVA, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Supplier<Item> CHALICE_OF_THE_SEA = ITEMS.register("chalice_of_the_sea", () -> new InfiniteFluidChaliceItem(Fluids.WATER, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    // Umbralite Items
    public static final Supplier<Item> UMBRALITE_SWORD = ITEMS.register("umbralite_sword", () -> new UmbraliteSwordItem(getSwordItemProperties(ScalarTiers.UMBRALITE)));
    public static final Supplier<Item> UMBRALITE_PICKAXE = ITEMS.register("umbralite_pickaxe", () -> new UmbralitePickaxeItem(getPickaxeItemProperties(ScalarTiers.UMBRALITE)));
    public static final Supplier<Item> UMBRALITE_AXE = ITEMS.register("umbralite_axe", () -> new UmbraliteAxeItem(getAxeItemProperties(ScalarTiers.UMBRALITE)));
    public static final Supplier<Item> UMBRALITE_SHOVEL = ITEMS.register("umbralite_shovel", () -> new UmbraliteShovelItem(getShovelItemProperties(ScalarTiers.UMBRALITE)));
    public static final Supplier<Item> UMBRALITE_HOE = ITEMS.register("umbralite_hoe", () -> new UmbraliteHoeItem(getHoeItemProperties(ScalarTiers.UMBRALITE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
