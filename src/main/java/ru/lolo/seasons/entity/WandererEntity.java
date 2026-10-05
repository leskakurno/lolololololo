package ru.lolo.seasons.entity;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import ru.lolo.seasons.item.ModItems;

public class WandererEntity extends NpcEntity {
   public WandererEntity(EntityType<? extends WandererEntity> type, Level level) {
      super(type, level);
   }

   @Override
   protected String npcId() {
      return "wanderer";
   }

   @Override
   protected int lineCount() {
      return 5;
   }

   @Override
   protected void talk(Player player, ItemStack held) {
      if (held.is((Item)ModItems.SPARK_SHARD.get()) && held.getCount() >= 4) {
         held.shrink(4);
         ItemStack reward = new ItemStack((ItemLike)ModItems.TIME_FRAGMENT.get(), 2);
         if (!player.addItem(reward)) {
            player.drop(reward, false);
         }

         this.level().playSound(null, this.blockPosition(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.NEUTRAL, 1.0F, 1.0F);
         this.say(player, "dialog.lolo_seasons.wanderer.trade");
      } else {
         super.talk(player, held);
      }
   }
}
