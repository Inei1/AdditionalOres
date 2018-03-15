package inei.additionalores.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import inei.additionalores.AdditionalOres;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BasicMachineFrame extends Block{
	
	
	@SideOnly(Side.CLIENT)
	private IIcon iconfront;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconregister){
		this.blockIcon = iconregister.registerIcon("aores:BasicMachineFrameSide");
		this.iconfront = iconregister.registerIcon("aores:BasicMachineFrameSide");
		this.iconTop = iconregister.registerIcon("aores:BasicMachineFrameTop");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		return metadata == 0 && side == 3 ? this.iconfront : side == 1 ? this.iconTop : (side == 0 ? this.iconTop : (side == metadata ? this.iconfront : this.blockIcon));
	}

	public BasicMachineFrame(Material material) {
		super(material);
		setCreativeTab(AdditionalOres.tabOres);
	}

}
