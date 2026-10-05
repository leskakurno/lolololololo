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
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;
import ru.lolo.seasons.Config;

public class SparkStaffItem extends LoloItem {
   public SparkStaffItem(Properties properties) {
      super(properties);
   }

   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
      ItemStack stack = player.getItemInHand(hand);
      if (level instanceof ServerLevel server) {
         double dist = (double)Config.BLINK_DISTANCE.getAsInt();
         Vec3 look = player.getLookAngle();
         Vec3 start = player.getEyePosition();
         Vec3 end = start.add(look.scale(dist));
         BlockHitResult hit = level.clip(new ClipContext(start, end, Block.COLLIDER, Fluid.NONE, player));
         Vec3 target = hit.getType() == Type.MISS ? end : hit.getLocation().subtract(look.scale(0.6));
         double fromX = player.getX();
         double fromY = player.getY();
         double fromZ = player.getZ();
         if (player.randomTeleport(target.x, target.y - (double)player.getEyeHeight(), target.z, true)) {
            server.sendParticles(ParticleTypes.END_ROD, fromX, fromY + 1.0, fromZ, 20, 0.3, 0.6, 0.3, 0.05);
            server.sendParticles(ParticleTypes.END_ROD, player.getX(), player.getY() + 1.0, player.getZ(), 20, 0.3, 0.6, 0.3, 0.05);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.2F);
            player.getCooldowns().addCooldown(this, Config.BLINK_COOLDOWN_TICKS.getAsInt());
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
         }
      }

      return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
   }
}
