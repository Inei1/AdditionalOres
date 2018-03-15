package inei.additionalores.blocks.ores;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class PurpliniumOre extends Block 
{
        public PurpliniumOre(Material material) 
        {
                super(material);
                setHardness(1.8F);
                setStepSound(Block.soundTypePiston);
                setBlockName("PurpliniumOre");
                setHarvestLevel("pickaxe", 1);
                setBlockTextureName("aores:PurpliniumOre");
        }
}