
package net.mcreator.ragemod.block;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.ragemod.itemgroup.TermeszettabItemGroup;
import net.mcreator.ragemod.RagemodModElements;

import java.util.List;
import java.util.Collections;

@RagemodModElements.ModElement.Tag
public class SequoiaPressurePlateBlock extends RagemodModElements.ModElement {
	@ObjectHolder("ragemod:sequoia_pressure_plate")
	public static final Block block = null;
	public SequoiaPressurePlateBlock(RagemodModElements instance) {
		super(instance, 269);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(TermeszettabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends PressurePlateBlock {
		public CustomBlock() {
			super(Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD)
					.hardnessAndResistance(2.4000000000000004f, 3.6000000000000005f).setLightLevel(s -> 0));
			setRegistryName("sequoia_pressure_plate");
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 6;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}