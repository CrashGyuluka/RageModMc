
package net.mcreator.ragemod.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.ragemod.procedures.Structurehatarolo1BlockAddedProcedure;
import net.mcreator.ragemod.RagemodModElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

import com.google.common.collect.ImmutableMap;

@RagemodModElements.ModElement.Tag
public class Strplacer4Block extends RagemodModElements.ModElement {
	@ObjectHolder("ragemod:strplacer_4")
	public static final Block block = null;

	public Strplacer4Block(RagemodModElements instance) {
		super(instance, 1328);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(null)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f, 40f).setLightLevel(s -> 0));
			setRegistryName("strplacer_4");
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void addInformation(ItemStack itemstack, IBlockReader world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("THIS IS A BLOCK FOR THE MOD'S DEVELOPER! PLEASE DON'T USE IT"));
			list.add(new StringTextComponent("BECAUSE IT CAN RUIN YOUR WORLD! :("));
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 15;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

		@Override
		public void onBlockAdded(BlockState blockstate, World world, BlockPos pos, BlockState oldState, boolean moving) {
			super.onBlockAdded(blockstate, world, pos, oldState, moving);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this, 10);
		}

		@Override
		public void tick(BlockState blockstate, ServerWorld world, BlockPos pos, Random random) {
			super.tick(blockstate, world, pos, random);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			Structurehatarolo1BlockAddedProcedure
					.executeProcedure(ImmutableMap.<String, Object>builder().put("x", x).put("y", y).put("z", z).put("world", world).build());
			world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this, 10);
		}
	}
}
