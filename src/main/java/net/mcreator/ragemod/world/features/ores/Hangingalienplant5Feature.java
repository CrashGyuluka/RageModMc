
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

public class Hangingalienplant5Feature extends OreFeature {
	public static final Hangingalienplant5Feature FEATURE = (Hangingalienplant5Feature) new Hangingalienplant5Feature()
			.setRegistryName("ragemod:hangingalienplant_5");
	public static final ConfiguredFeature<?, ?> CONFIGURED_FEATURE = FEATURE
			.configured(new OreConfiguration(Hangingalienplant5FeatureRuleTest.INSTANCE, RagemodModBlocks.HANGINGALIENPLANT_5.defaultBlockState(), 7))
			.range(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(75)))).squared().count(21);
	public static final Set<ResourceLocation> GENERATE_BIOMES = null;

	public Hangingalienplant5Feature() {
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

	private static class Hangingalienplant5FeatureRuleTest extends RuleTest {
		static final Hangingalienplant5FeatureRuleTest INSTANCE = new Hangingalienplant5FeatureRuleTest();
		static final com.mojang.serialization.Codec<Hangingalienplant5FeatureRuleTest> codec = com.mojang.serialization.Codec.unit(() -> INSTANCE);
		static final RuleTestType<Hangingalienplant5FeatureRuleTest> CUSTOM_MATCH = Registry.register(Registry.RULE_TEST,
				new ResourceLocation("ragemod:hangingalienplant_5_match"), () -> codec);

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
