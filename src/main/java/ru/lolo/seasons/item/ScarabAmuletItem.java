package ru.lolo.seasons.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

public class ScarabAmuletItem extends LoloCurioItem {
   public ScarabAmuletItem(Properties properties) {
      super(properties);
   }

   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
      ItemStack stack = player.getItemInHand(hand);
      if (!level.isClientSide) {
         int duration = 1800;
         player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, duration, 0, true, false));
         player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, 0, true, false));
         player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration, 0, true, false));
         level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.8F, 1.6F);
         player.getCooldowns().addCooldown(this, 1200);
         stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
      }

      return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
   }

   /** Пока амулет надет в слоте Curios, постоянно держит ночное зрение и огнестойкость. */
   @Override
   public void curioTick(SlotContext slotContext, ItemStack stack) {
      LivingEntity wearer = slotContext.entity();
      if (!wearer.level().isClientSide && wearer.tickCount % 40 == 0) {
         wearer.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0, true, false));
         wearer.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0, true, false));
      }
   }
}
