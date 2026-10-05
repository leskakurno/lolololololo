package ru.lolo.seasons.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
   public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, "lolo_seasons");
   public static final DeferredHolder<EntityType<?>, EntityType<WandererEntity>> WANDERER = ENTITIES.register(
      "wanderer", () -> Builder.of(WandererEntity::new, MobCategory.CREATURE).sized(0.6F, 1.95F).clientTrackingRange(10).build("wanderer")
   );
   public static final DeferredHolder<EntityType<?>, EntityType<AutomatonEntity>> AUTOMATON = ENTITIES.register(
      "automaton", () -> Builder.of(AutomatonEntity::new, MobCategory.CREATURE).sized(0.6F, 1.95F).clientTrackingRange(10).build("automaton")
   );

   public static void registerAttributes(EntityAttributeCreationEvent event) {
      event.put((EntityType)WANDERER.get(), NpcEntity.createNpcAttributes().build());
      event.put((EntityType)AUTOMATON.get(), NpcEntity.createNpcAttributes().build());
   }
}
