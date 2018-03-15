package inei.additionalores.blocks.ores;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class PigoliumOre extends Block 
{
        public PigoliumOre(Material material) 
        {
                super(material);
                setHardness(20.0F);
                setStepSound(Block.soundTypePiston);
                setBlockName("PigoliumOre");
                setHarvestLevel("pickaxe", 7);
                setBlockTextureName("aores:PigoliumOre");
        }
}