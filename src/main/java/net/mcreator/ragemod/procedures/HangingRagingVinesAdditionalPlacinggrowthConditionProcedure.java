package net.mcreator.ragemod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class HangingRagingVinesAdditionalPlacinggrowthConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).canOcclude()) {
			return true;
		}
		return false;
	}
}