
package net.mcreator.ragemod.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.ragemod.itemgroup.SvasblocktabItemGroup;
import net.mcreator.ragemod.RagemodModElements;

@RagemodModElements.ModElement.Tag
public class Savkristalyp2Item extends RagemodModElements.ModElement {
	@ObjectHolder("ragemod:savkristalyp_2")
	public static final Item block = null;
	public Savkristalyp2Item(RagemodModElements instance) {
		super(instance, 318);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(SvasblocktabItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("savkristalyp_2");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
