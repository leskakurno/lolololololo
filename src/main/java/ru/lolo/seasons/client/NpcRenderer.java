package ru.lolo.seasons.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import ru.lolo.seasons.entity.NpcEntity;

/**
 * GeckoLib-рендерер для всех NPC.
 * Модель:     assets/lolo_seasons/geo/entity/<name>.geo.json
 * Анимации:   assets/lolo_seasons/animations/entity/<name>.animation.json  (нужны "idle" и "walk")
 * Текстура:   assets/lolo_seasons/textures/entity/<name>.png
 */
public class NpcRenderer extends GeoEntityRenderer<NpcEntity> {
   public NpcRenderer(EntityRendererProvider.Context context, String name) {
      super(context, new NpcGeoModel(name));
      this.shadowRadius = 0.5F;
   }
}
