
package net.mcreator.ragemod.world.features.ores;

import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;

import net.mcreator.ragemod.procedures.HangingalienshroomAdditionalPlacinggrowthConditionProcedure;
import net.mcreator.ragemod.init.RagemodModBlocks;

import java.util.Set;
import java.util.Random;

public class Hangingalienplantexluse1Feature extends OreFeature {
	public static final Hangingalienplantexluse1Feature FEATURE = (Hangingalienplantexluse1Feature) new Hangingalienplantexluse1Feature()
			.setRegistryName("ragemod:hangingalienplantexluse_1");
	public static final ConfiguredFeature<?, ?> CONFIGURED_FEATURE = FEATURE
			.configured(new OreConfiguration(Hangingalienplantexluse1FeatureRuleTest.INSTANCE,
					RagemodModBlocks.HANGINGALIENPLANTEXLUSE_1.defaultBlockState(), 18))
			.range(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(75)))).squared().count(30);
	public static final Set<ResourceLocation> GENERATE_BIOMES = Set.of(new ResourceLocation("ragemod:alien_forest"));

	public Hangingalienplantexluse1Feature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel world = context.level();
		ResourceKey<Level> dimensionType = world.getLevel().dimension();
		boolean dimensionCriteria = false;
		if (dimensionType == ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("ragemod:alien_dimension_portal_igniter")))
			dimensionCriteria = true;
		if (!dimensionCriteria)
			return false;
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!HangingalienshroomAdditionalPlacinggrowthConditionProcedure.execute(world, x, y, z))
			return false;
		return super.place(context);
	}

	private static class Hangingalienplantexluse1FeatureRuleTest extends RuleTest {
		static final Hangingalienplantexluse1FeatureRuleTest INSTANCE = new Hangingalienplantexluse1FeatureRuleTest();
		static final com.mojang.serialization.Codec<Hangingalienplantexluse1FeatureRuleTest> codec = com.mojang.serialization.Codec
				.unit(() -> INSTANCE);
		static final RuleTestType<Hangingalienplantexluse1FeatureRuleTest> CUSTOM_MATCH = Registry.register(Registry.RULE_TEST,
				new ResourceLocation("ragemod:hangingalienplantexluse_1_match"), () -> codec);

		public boolean test(BlockState blockAt, Random random) {
			boolean blockCriteria = false;
			if (blockAt.getBlock() == Blocks.AIR)
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.VOID_AIR)
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.CAVE_AIR)
				blockCriteria = true;
			return blockCriteria;
		}

		protected RuleTestType<?> getType() {
			return CUSTOM_MATCH;
		}
	}
}
