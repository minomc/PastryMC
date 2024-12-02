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
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PastryMod.MOD_ID);


    public static final List<Supplier<? extends ItemLike>> PASTRY_TAB_ITEMS = new ArrayList<>();
    public static final RegistryObject<CreativeModeTab> PASTRY_TAB = TABS.register("pastry_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pastry_tab"))
                    .icon(Items.CROSSBOW::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            PASTRY_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .build()
    );
    public static <T extends Item> RegistryObject<T> addToPastryTab(RegistryObject<T> itemLike) {
        PASTRY_TAB_ITEMS.add(itemLike);
        return itemLike;
    }


    public static final List<Supplier<? extends ItemLike>> DOUGH_TAB_ITEMS = new ArrayList<>();
    public static final RegistryObject<CreativeModeTab> DOUGH_TAB = TABS.register("dough_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.dough_tab"))
                    .icon(ItemInit.DONUT_ITEM.get()::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            DOUGH_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .build()
    );
    public static <T extends Item> RegistryObject<T> addToDoughTab(RegistryObject<T> itemLike) {
        DOUGH_TAB_ITEMS.add(itemLike);
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
