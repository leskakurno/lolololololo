package ru.lolo.seasons.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
   public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "lolo_seasons");
   public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = TABS.register(
      "main",
      () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.lolo_seasons"))
            .withTabsBefore(new ResourceKey[]{CreativeModeTabs.COMBAT})
            .icon(() -> ((Item)ModItems.SPARK_SHARD.get()).getDefaultInstance())
            .displayItems((parameters, output) -> {
               output.accept((ItemLike)ModItems.SPARK_SHARD.get());
               output.accept((ItemLike)ModItems.TIME_FRAGMENT.get());
               output.accept((ItemLike)ModItems.ARCHEY_INGOT.get());
               output.accept((ItemLike)ModItems.EMBER.get());
               output.accept((ItemLike)ModItems.ARCHEY_ORE_ITEM.get());
               output.accept((ItemLike)ModItems.ARCHEY_BLOCK_ITEM.get());
               output.accept((ItemLike)ModItems.BRAZIER_ITEM.get());
               output.accept((ItemLike)ModItems.ARCHEY_SWORD.get());
               output.accept((ItemLike)ModItems.ARCHEY_PICKAXE.get());
               output.accept((ItemLike)ModItems.SPARK_STAFF.get());
               output.accept((ItemLike)ModItems.TIME_CLOCK.get());
               output.accept((ItemLike)ModItems.SMARTPHONE.get());
               output.accept((ItemLike)ModItems.SCARAB_AMULET.get());
               output.accept((ItemLike)ModItems.RUBBER_DUCK.get());
               output.accept((ItemLike)ModItems.WANDERER_SPAWN_EGG.get());
               output.accept((ItemLike)ModItems.AUTOMATON_SPAWN_EGG.get());
            })
            .build()
   );
}
