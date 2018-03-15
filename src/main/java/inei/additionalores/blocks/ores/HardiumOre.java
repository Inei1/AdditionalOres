package inei.additionalores.blocks.ores;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class HardiumOre extends Block 
{
        public HardiumOre(Material material) 
        {
                super(material);
                setHardness(20.0F);
                setStepSound(Block.soundTypePiston);
                setBlockName("HardiumOre");
                setHarvestLevel("pickaxe", 13);
                setBlockTextureName("aores:HardiumOre");
        }
}