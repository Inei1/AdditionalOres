package inei.additionalores.blocks.metalblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Mediumblock extends Block {

	public Mediumblock(Material material) {
		super(material);
		setHardness(32.0F);
		setBlockName("MediumBlock");
		setStepSound(soundTypePiston);
        setHarvestLevel("pickaxe", 8);
        setBlockTextureName("aores:MediumBlock");
	}

}
