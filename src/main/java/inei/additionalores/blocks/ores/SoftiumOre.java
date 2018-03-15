package inei.additionalores.blocks.ores;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class SoftiumOre extends Block 
{
        public SoftiumOre(Material material) 
        {
                super(material);
                setHardness(4.0F);
                setStepSound(Block.soundTypePiston);
                setBlockName("SoftiumOre");
                setHarvestLevel("pickaxe", 3);
                setBlockTextureName("aores:SoftiumOre");
        }
}