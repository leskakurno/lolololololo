package ru.lolo.seasons.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import ru.lolo.seasons.Config;

public class TimeClockItem extends LoloItem {
   public TimeClockItem(Properties properties) {
      super(properties);
   }

   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
      ItemStack stack = player.getItemInHand(hand);
      if (level instanceof ServerLevel server) {
         long now = server.getDayTime();
         server.setDayTime((now / 24000L + 1L) * 24000L);
         server.setWeatherParameters(6000, 0, false, false);
         server.sendParticles(ParticleTypes.ENCHANT, player.getX(), player.getY() + 1.0, player.getZ(), 40, 0.6, 0.8, 0.6, 0.5);
         level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.PLAYERS, 1.5F, 0.8F);
         player.getCooldowns().addCooldown(this, Config.CLOCK_COOLDOWN_TICKS.getAsInt());
         stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
      }

      return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
   }
}
