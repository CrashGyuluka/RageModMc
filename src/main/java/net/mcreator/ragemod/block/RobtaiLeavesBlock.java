
package net.mcreator.ragemod.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.fluid.FluidState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.ragemod.procedures.RobtaiLeavesBlockDestroyedByPlayerProcedure;
import net.mcreator.ragemod.itemgroup.TermeszettabItemGroup;
import net.mcreator.ragemod.RagemodModElements;

import com.google.common.collect.ImmutableMap;

@RagemodModElements.ModElement.Tag
public class RobtaiLeavesBlock extends RagemodModElements.ModElement {
	@ObjectHolder("ragemod:robtai_leaves")
	public static final Block block = null;

	public RobtaiLeavesBlock(RagemodModElements instance) {
		super(instance, 405);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(TermeszettabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends LeavesBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.LEAVES).sound(SoundType.PLANT).hardnessAndResistance(0.1f, 0.4f).setLightLevel(s -> 0)
					.harvestLevel(-1).harvestTool(ToolType.HOE).setRequiresTool().notSolid());
			setRegistryName("robtai_leaves");
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 1;
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 60;
		}

		@Override
		public boolean removedByPlayer(BlockState blockstate, World world, BlockPos pos, PlayerEntity entity, boolean willHarvest, FluidState fluid) {
			boolean retval = super.removedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			RobtaiLeavesBlockDestroyedByPlayerProcedure.executeProcedure(
					ImmutableMap.<String, Object>builder().put("entity", entity).put("x", x).put("y", y).put("z", z).put("world", world).build());
			return retval;
		}
	}
}
