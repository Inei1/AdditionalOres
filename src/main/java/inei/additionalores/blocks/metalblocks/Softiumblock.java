package inei.additionalores.blocks.metalblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Softiumblock extends Block {

	public Softiumblock(Material material) {
		super(material);
		setHardness(5.5F);
		setBlockName("SoftiumBlock");
		setStepSound(soundTypePiston);
        setHarvestLevel("pickaxe", 3);
        setBlockTextureName("aores:SoftiumBlock");
	}

}
