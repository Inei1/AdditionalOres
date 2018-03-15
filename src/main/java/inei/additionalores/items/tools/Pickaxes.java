package inei.additionalores.items.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import inei.additionalores.AdditionalOres;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;

public class Pickaxes extends ItemPickaxe {

	

	public Pickaxes(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("aores:" + this.getUnlocalizedName().substring(5));
	}
}
