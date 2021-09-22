
package net.mcreator.ragemod.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.ragemod.item.AdularItem;
import net.mcreator.ragemod.RagemodModElements;

@RagemodModElements.ModElement.Tag
public class ErcekItemGroup extends RagemodModElements.ModElement {
	public ErcekItemGroup(RagemodModElements instance) {
		super(instance, 676);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabercek") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(AdularItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
