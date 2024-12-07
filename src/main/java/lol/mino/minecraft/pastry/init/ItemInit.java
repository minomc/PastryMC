package lol.mino.minecraft.pastry.init;

import lol.mino.minecraft.pastry.PastryMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static lol.mino.minecraft.pastry.init.CreativeTabInit.addToTab;
import static lol.mino.minecraft.pastry.init.CreativeTabInit.PastryType;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PastryMod.MOD_ID);

    public static final RegistryObject<Item> DONUT_ITEM = addToTab(PastryType.FRIED, ITEMS.register("donut",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 200, 2), 1f)
                            .build())
                    .rarity(Rarity.COMMON)
    )));

    public static final RegistryObject<Item> MACARON_ITEM = addToTab(PastryType.BAKED, ITEMS.register("macaron",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1), 1f)
                            .build())
                    .rarity(Rarity.COMMON)
            )));
}
