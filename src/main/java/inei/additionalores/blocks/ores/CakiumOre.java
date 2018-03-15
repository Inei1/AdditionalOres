package inei.additionalores.blocks.ores;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class CakiumOre extends Block 
{
        public CakiumOre(Material material) 
        {
                super(material);
                setHardness(15.0F);
                setStepSound(Block.soundTypePiston);
                setBlockName("CakiumOre");
                setHarvestLevel("pickaxe", 6);
                setBlockTextureName("aores:CakiumOre");
        }
}