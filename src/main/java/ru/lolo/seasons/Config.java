package ru.lolo.seasons;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;

public class Config {
   private static final Builder BUILDER = new Builder();
   public static final IntValue BLINK_DISTANCE = BUILDER.comment("Дальность прыжка Посоха Мироходца (в блоках)").defineInRange("blinkDistance", 16, 4, 64);
   public static final IntValue BLINK_COOLDOWN_TICKS = BUILDER.comment("Перезарядка Посоха Мироходца (в тиках, 20 тиков = 1 секунда)")
      .defineInRange("blinkCooldownTicks", 60, 0, 6000);
   public static final IntValue CLOCK_COOLDOWN_TICKS = BUILDER.comment("Перезарядка Часов Конца Времени (в тиках)")
      .defineInRange("clockCooldownTicks", 1200, 0, 72000);
   public static final ModConfigSpec SPEC = BUILDER.build();
}
