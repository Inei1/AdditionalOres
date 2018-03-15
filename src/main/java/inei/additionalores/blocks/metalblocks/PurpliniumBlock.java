package inei.additionalores.blocks.metalblocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class PurpliniumBlock extends Block {

	public PurpliniumBlock(Material material) {
		super(material);
		setHardness(2.0F);
		setBlockName("PurpliniumBlock");
		setStepSound(soundTypePiston);
        setHarvestLevel("pickaxe", 1);
        setBlockTextureName("aores:PurpliniumBlock");
	}

}
