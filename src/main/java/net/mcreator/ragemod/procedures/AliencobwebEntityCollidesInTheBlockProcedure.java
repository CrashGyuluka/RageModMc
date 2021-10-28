package net.mcreator.ragemod.procedures;

import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.entity.Entity;

import net.mcreator.ragemod.RagemodMod;

import java.util.Map;

public class AliencobwebEntityCollidesInTheBlockProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				RagemodMod.LOGGER.warn("Failed to load dependency entity for procedure AliencobwebEntityCollidesInTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotionMultiplier(null, new Vector3d(0.25D, (double) 0.05F, 0.25D));
	}
}
