package inei.additionalores.blocks.ores;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class WeakiumOre extends Block 
{
        public WeakiumOre(Material material) 
        {
                super(material);
                setHardness(1.2F);
                setStepSound(Block.soundTypePiston);
                setBlockName("WeakiumOre");
                setHarvestLevel("pickaxe", 0);
                setBlockTextureName("aores:WeakiumOre");
        }
}