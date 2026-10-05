package ru.lolo.seasons.item;

import java.util.Optional;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class SmartphoneItem extends LoloItem {
   public SmartphoneItem(Properties properties) {
      super(properties);
   }

   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
      ItemStack stack = player.getItemInHand(hand);
      if (!level.isClientSide) {
         BlockPos pos = player.blockPosition();
         long t = level.getDayTime() % 24000L;
         int hours = (int)((t / 1000L + 6L) % 24L);
         int minutes = (int)(t % 1000L * 60L / 1000L);
         String time = String.format("%02d:%02d", hours, minutes);
         Optional<Component> biomeName = level.getBiome(pos).unwrapKey().map(key -> Component.translatable(Util.makeDescriptionId("biome", key.location())));
         player.displayClientMessage(
            Component.translatable(
               "item.lolo_seasons.smartphone.info", new Object[]{pos.getX(), pos.getY(), pos.getZ(), time, biomeName.orElse(Component.literal("?"))}
            ),
            true
         );
      }

      return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
   }
}
