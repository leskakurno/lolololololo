package ru.lolo.seasons.client;

import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import ru.lolo.seasons.entity.ModEntities;

@Mod(
   value = "lolo_seasons",
   dist = {Dist.CLIENT}
)
public class LoloSeasonsClient {
   public LoloSeasonsClient(IEventBus modEventBus, ModContainer container) {
      container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
      modEventBus.addListener(LoloSeasonsClient::registerRenderers);
   }

   private static void registerRenderers(RegisterRenderers event) {
      event.registerEntityRenderer((EntityType)ModEntities.WANDERER.get(), ctx -> new NpcRenderer(ctx, "wanderer"));
      event.registerEntityRenderer((EntityType)ModEntities.AUTOMATON.get(), ctx -> new NpcRenderer(ctx, "automaton"));
   }
}
