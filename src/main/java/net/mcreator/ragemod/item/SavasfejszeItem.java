
package net.mcreator.ragemod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.BlockState;

import net.mcreator.ragemod.procedures.SavasfejszeBlockDestroyedWithToolProcedure;
import net.mcreator.ragemod.itemgroup.SvasblocktabItemGroup;
import net.mcreator.ragemod.RagemodModElements;

import java.util.Map;
import java.util.HashMap;

@RagemodModElements.ModElement.Tag
public class SavasfejszeItem extends RagemodModElements.ModElement {
	@ObjectHolder("ragemod:savasfejsze")
	public static final Item block = null;
	public SavasfejszeItem(RagemodModElements instance) {
		super(instance, 278);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 2000;
			}

			public float getEfficiency() {
				return 13f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 3;
			}

			public int getEnchantability() {
				return 12;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Savkristalyp2Item.block));
			}
		}, 1, -2.7999999999999998f, new Item.Properties().group(SvasblocktabItemGroup.tab)) {
			@Override
			public boolean onBlockDestroyed(ItemStack itemstack, World world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
				boolean retval = super.onBlockDestroyed(itemstack, world, blockstate, pos, entity);
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					SavasfejszeBlockDestroyedWithToolProcedure.executeProcedure($_dependencies);
				}
				return retval;
			}
		}.setRegistryName("savasfejsze"));
	}
}
