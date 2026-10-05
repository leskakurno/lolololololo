package ru.lolo.seasons.item;

import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

/**
 * Базовый класс для предметов, которые носятся в слоте Curios.
 * Наследуйте его и переопределяйте curioTick / onEquip / onUnequip.
 * Подходящий слот задаётся тегом: data/curios/tags/item/<слот>.json (charm, necklace, ring, belt, back, head ...).
 */
public class LoloCurioItem extends LoloItem implements ICurioItem {
   public LoloCurioItem(Properties properties) {
      super(properties);
   }

   @Override
   public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
      return true;
   }
}
