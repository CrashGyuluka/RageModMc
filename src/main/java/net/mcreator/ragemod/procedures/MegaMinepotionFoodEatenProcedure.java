package net.mcreator.ragemod.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.ragemod.init.RagemodModMobEffects;

public class MegaMinepotionFoodEatenProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(RagemodModMobEffects.MEGAMINE_EFFECT, 12000, 1, (false), (false)));
	}
}
