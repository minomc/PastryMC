package lol.mino.minecraft.pastry.init;

import lol.mino.minecraft.pastry.PastryMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = PastryMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTabInit {
    public enum PastryType {
        FRIED,
        BAKED
    }

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PastryMod.MOD_ID);

    // Pastry Tab
    public static final List<Supplier<? extends ItemLike>> PASTRY_TAB_ITEMS = new ArrayList<>();
    public static final RegistryObject<CreativeModeTab> PASTRY_TAB = TABS.register("pastry_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pastry_tab"))
                    .icon(Items.CROSSBOW::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            PASTRY_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .build()
    );

    // Fried Tab
    public static final List<Supplier<? extends ItemLike>> FRIED_TAB_ITEMS = new ArrayList<>();
    public static final RegistryObject<CreativeModeTab> FRIED_TAB = TABS.register("fried_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.fried_tab"))
                    .icon(ItemInit.DONUT_ITEM.get()::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            FRIED_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .build()
    );

    // Baked Tab
    public static final List<Supplier<? extends ItemLike>> BAKED_TAB_ITEMS = new ArrayList<>();
    public static final RegistryObject<CreativeModeTab> BAKED_TAB = TABS.register("baked_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.baked_tab"))
                    .icon(ItemInit.MACARON_ITEM.get()::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            BAKED_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .build()
    );

    /*
     * Used to add an item to a specific tab in the creative menu using the PastryType enumerator
     */
    public static <T extends Item> RegistryObject<T> addToTab(PastryType pastryType, RegistryObject<T> itemLike) {
        switch(pastryType) {
            case FRIED:
                FRIED_TAB_ITEMS.add(itemLike);
                break;
            case BAKED:
                BAKED_TAB_ITEMS.add(itemLike);
                break;
            default:
                PASTRY_TAB_ITEMS.add(itemLike);
                break;
        }

        return itemLike;
    }


    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        // Add a crossbow to the pastry tab
        if (event.getTab() == PASTRY_TAB.get()) {
            event.accept(Items.CROSSBOW);
        }
    }

}
