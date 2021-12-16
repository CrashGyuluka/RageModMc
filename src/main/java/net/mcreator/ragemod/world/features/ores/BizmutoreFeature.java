
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

import net.mcreator.ragemod.init.RagemodModBlocks;

import java.util.Set;
import java.util.Random;

public class BizmutoreFeature extends OreFeature {
	public static final BizmutoreFeature FEATURE = (BizmutoreFeature) new BizmutoreFeature().setRegistryName("ragemod:bizmutore");
	public static final ConfiguredFeature<?, ?> CONFIGURED_FEATURE = FEATURE
			.configured(new OreConfiguration(BizmutoreFeatureRuleTest.INSTANCE, RagemodModBlocks.BIZMUTORE.defaultBlockState(), 5))
			.range(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)))).squared().count(5);
	public static final Set<ResourceLocation> GENERATE_BIOMES = null;

	public BizmutoreFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel world = context.level();
		ResourceKey<Level> dimensionType = world.getLevel().dimension();
		boolean dimensionCriteria = false;
		if (dimensionType == Level.END)
			dimensionCriteria = true;
		if (!dimensionCriteria)
			return false;
		return super.place(context);
	}

	private static class BizmutoreFeatureRuleTest extends RuleTest {
		static final BizmutoreFeatureRuleTest INSTANCE = new BizmutoreFeatureRuleTest();
		static final com.mojang.serialization.Codec<BizmutoreFeatureRuleTest> codec = com.mojang.serialization.Codec.unit(() -> INSTANCE);
		static final RuleTestType<BizmutoreFeatureRuleTest> CUSTOM_MATCH = Registry.register(Registry.RULE_TEST,
				new ResourceLocation("ragemod:bizmutore_match"), () -> codec);

		public boolean test(BlockState blockAt, Random random) {
			boolean blockCriteria = false;
			if (blockAt.getBlock() == Blocks.END_STONE)
				blockCriteria = true;
			return blockCriteria;
		}

		protected RuleTestType<?> getType() {
			return CUSTOM_MATCH;
		}
	}
}