
package net.mcreator.ragemod.world.biome;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.BiomeManager;

import net.minecraft.world.gen.trunkplacer.MegaJungleTrunkPlacer;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.LeaveVineTreeDecorator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.foliageplacer.JungleFoliagePlacer;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.RegistryKey;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.block.Blocks;

import net.mcreator.ragemod.entity.TraderrtEntity;
import net.mcreator.ragemod.block.BukkLogBlock;
import net.mcreator.ragemod.block.BukkLeavesBlock;
import net.mcreator.ragemod.RagemodModElements;

import com.google.common.collect.ImmutableList;

@RagemodModElements.ModElement.Tag
public class BukkbiomeBiome extends RagemodModElements.ModElement {
	public static Biome biome;
	public BukkbiomeBiome(RagemodModElements instance) {
		super(instance, 1220);
		FMLJavaModLoadingContext.get().getModEventBus().register(new BiomeRegisterHandler());
	}
	private static class BiomeRegisterHandler {
		@SubscribeEvent
		public void registerBiomes(RegistryEvent.Register<Biome> event) {
			if (biome == null) {
				BiomeAmbience effects = new BiomeAmbience.Builder().setFogColor(12638463).setWaterColor(4159204).setWaterFogColor(329011)
						.withSkyColor(7972607).withFoliageColor(-16736768).withGrassColor(-16743680).build();
				BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder()
						.withSurfaceBuilder(SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(),
								Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState())));
				biomeGenerationSettings.withStructure(StructureFeatures.MINESHAFT);
				biomeGenerationSettings.withStructure(StructureFeatures.JUNGLE_PYRAMID);
				biomeGenerationSettings.withStructure(StructureFeatures.SHIPWRECK);
				biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.TREE
						.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BukkLogBlock.block.getDefaultState()),
								new SimpleBlockStateProvider(BukkLeavesBlock.block.getDefaultState()),
								new JungleFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 2),
								new MegaJungleTrunkPlacer(7, 2, 19), new TwoLayerFeature(1, 1, 2)))
										.setDecorators(
												ImmutableList.of(TrunkVineTreeDecorator.field_236879_b_, LeaveVineTreeDecorator.field_236871_b_))
										.setMaxWaterDepth(0).build())
						.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
						.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(34, 0.1F, 1))));
				biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
						Feature.RANDOM_PATCH.withConfiguration(Features.Configs.GRASS_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT)
								.withPlacement(Placement.COUNT_NOISE.configure(new NoiseDependant(-0.8D, 5, 8))));
				biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
						Feature.FLOWER.withConfiguration(Features.Configs.NORMAL_FLOWER_CONFIG)
								.withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
								.func_242731_b(6));
				biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
						Feature.RANDOM_PATCH.withConfiguration(
								(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.BROWN_MUSHROOM.getDefaultState()),
										SimpleBlockPlacer.PLACER)).tries(1).func_227317_b_().build()));
				biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
						Feature.RANDOM_PATCH.withConfiguration(
								(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.RED_MUSHROOM.getDefaultState()),
										SimpleBlockPlacer.PLACER)).tries(1).func_227317_b_().build()));
				biomeGenerationSettings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
						Feature.DISK
								.withConfiguration(new SphereReplaceConfig(Blocks.GRAVEL.getDefaultState(), FeatureSpread.func_242253_a(2, 3), 2,
										ImmutableList.of(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState())))
								.withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT).func_242731_b(2));
				DefaultBiomeFeatures.withChanceBerries(biomeGenerationSettings);
				DefaultBiomeFeatures.withCavesAndCanyons(biomeGenerationSettings);
				DefaultBiomeFeatures.withFossils(biomeGenerationSettings);
				DefaultBiomeFeatures.withInfestedStone(biomeGenerationSettings);
				DefaultBiomeFeatures.withLavaAndWaterLakes(biomeGenerationSettings);
				DefaultBiomeFeatures.withMonsterRoom(biomeGenerationSettings);
				DefaultBiomeFeatures.withOverworldOres(biomeGenerationSettings);
				DefaultBiomeFeatures.withTallGrass(biomeGenerationSettings);
				DefaultBiomeFeatures.withSugarCaneAndPumpkins(biomeGenerationSettings);
				DefaultBiomeFeatures.withLargeFern(biomeGenerationSettings);
				DefaultBiomeFeatures.withForestRocks(biomeGenerationSettings);
				MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();
				mobSpawnInfo.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.FOX, 1, 1, 1));
				mobSpawnInfo.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(TraderrtEntity.entity, 1, 1, 1));
				mobSpawnInfo.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PARROT, 1, 1, 1));
				mobSpawnInfo.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 1, 1, 1));
				biome = new Biome.Builder().precipitation(Biome.RainType.RAIN).category(Biome.Category.NONE).depth(0.5f).scale(0.5f).temperature(0.5f)
						.downfall(0.6f).setEffects(effects).withMobSpawnSettings(mobSpawnInfo.copy())
						.withGenerationSettings(biomeGenerationSettings.build()).build();
				event.getRegistry().register(biome.setRegistryName("ragemod:beech_forest"));
			}
		}
	}
	@Override
	public void init(FMLCommonSetupEvent event) {
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM,
				new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, WorldGenRegistries.BIOME.getKey(biome)), 7));
	}
}
