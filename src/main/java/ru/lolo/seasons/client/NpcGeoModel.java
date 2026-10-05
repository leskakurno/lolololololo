package ru.lolo.seasons.client;

import ru.lolo.seasons.LoloSeasons;
import ru.lolo.seasons.entity.NpcEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

/**
 * Модель NPC по имени. Второй аргумент (true) включает автоматический поворот кости "head" за взглядом.
 * Пути: geo/entity/<name>.geo.json, animations/entity/<name>.animation.json, textures/entity/<name>.png
 */
public class NpcGeoModel extends DefaultedEntityGeoModel<NpcEntity> {
   public NpcGeoModel(String name) {
      super(LoloSeasons.id(name), true);
   }
}
