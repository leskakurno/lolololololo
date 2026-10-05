package ru.lolo.seasons;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;
import org.slf4j.Logger;
import ru.lolo.seasons.block.ModBlocks;
import ru.lolo.seasons.entity.ModEntities;
import ru.lolo.seasons.item.ModItems;
import ru.lolo.seasons.item.ModTabs;

@Mod("lolo_seasons")
public class LoloSeasons {
   public static final String MODID = "lolo_seasons";
   public static final Logger LOGGER = LogUtils.getLogger();

   public LoloSeasons(IEventBus modEventBus, ModContainer modContainer) {
      ModBlocks.BLOCKS.register(modEventBus);
      ModItems.ITEMS.register(modEventBus);
      ModEntities.ENTITIES.register(modEventBus);
      ModTabs.TABS.register(modEventBus);
      modEventBus.addListener(ModEntities::registerAttributes);
      modContainer.registerConfig(Type.COMMON, Config.SPEC);
   }

   public static ResourceLocation id(String path) {
      return ResourceLocation.fromNamespaceAndPath("lolo_seasons", path);
   }
}
