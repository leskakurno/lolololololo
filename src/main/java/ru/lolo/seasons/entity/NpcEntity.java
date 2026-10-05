package ru.lolo.seasons.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class NpcEntity extends PathfinderMob implements GeoEntity {
   private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
   private static final RawAnimation WALK = RawAnimation.begin().thenLoop("walk");
   private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
   private long lastTalkTick = -100L;

   protected NpcEntity(EntityType<? extends NpcEntity> type, Level level) {
      super(type, level);
   }

   protected abstract String npcId();

   protected abstract int lineCount();

   public static Builder createNpcAttributes() {
      return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0).add(Attributes.MOVEMENT_SPEED, 0.25);
   }

   protected void registerGoals() {
      this.goalSelector.addGoal(0, new FloatGoal(this));
      this.goalSelector.addGoal(1, new PanicGoal(this, 1.4));
      this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
      this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6));
      this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
   }

   public boolean removeWhenFarAway(double distanceToClosestPlayer) {
      return false;
   }

   protected InteractionResult mobInteract(Player player, InteractionHand hand) {
      if (hand != InteractionHand.MAIN_HAND) {
         return InteractionResult.PASS;
      } else {
         if (!this.level().isClientSide) {
            this.getNavigation().stop();
            this.getLookControl().setLookAt(player, 30.0F, 30.0F);
            long now = this.level().getGameTime();
            if (now - this.lastTalkTick >= 10L) {
               this.lastTalkTick = now;
               this.talk(player, player.getItemInHand(hand));
            }
         }

         return InteractionResult.sidedSuccess(this.level().isClientSide);
      }
   }

   protected void talk(Player player, ItemStack held) {
      this.say(player, "dialog.lolo_seasons." + this.npcId() + "." + this.random.nextInt(this.lineCount()));
   }

   protected void say(Player player, String translationKey) {
      MutableComponent msg = this.getName()
         .copy()
         .withStyle(ChatFormatting.AQUA)
         .append(Component.literal(": ").withStyle(ChatFormatting.GRAY))
         .append(Component.translatable(translationKey));
      player.sendSystemMessage(msg);
   }

   @Override
   public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
      controllers.add(new AnimationController<>(this, "main", 5, state -> state.setAndContinue(state.isMoving() ? WALK : IDLE)));
   }

   @Override
   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.geoCache;
   }
}
