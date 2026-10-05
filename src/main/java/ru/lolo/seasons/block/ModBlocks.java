package ru.lolo.seasons.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredRegister.Blocks;

public class ModBlocks {
   public static final Blocks BLOCKS = DeferredRegister.createBlocks("lolo_seasons");
   public static final DeferredBlock<Block> ARCHEY_ORE = BLOCKS.register(
      "archey_ore",
      () -> new DropExperienceBlock(
            UniformInt.of(2, 5), Properties.of().mapColor(MapColor.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)
         )
   );
   public static final DeferredBlock<Block> ARCHEY_BLOCK = BLOCKS.registerSimpleBlock(
      "archey_block", Properties.of().mapColor(MapColor.COLOR_CYAN).strength(5.0F, 6.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)
   );
   public static final DeferredBlock<BrazierBlock> BRAZIER = BLOCKS.registerBlock(
      "thirteen_fires_brazier",
      BrazierBlock::new,
      Properties.of()
         .mapColor(MapColor.COLOR_ORANGE)
         .strength(3.5F)
         .requiresCorrectToolForDrops()
         .sound(SoundType.LANTERN)
         .lightLevel(state -> 13)
         .noOcclusion()
   );
}
