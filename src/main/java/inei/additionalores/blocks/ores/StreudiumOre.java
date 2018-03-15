package inei.additionalores.blocks.ores;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class StreudiumOre extends Block 
{
        public StreudiumOre(Material material) 
        {
                super(material);
                setHardness(20.0F);
                setStepSound(Block.soundTypePiston);
                setBlockName("StreudiumOre");
                setHarvestLevel("pickaxe", 9);
                setBlockTextureName("aores:StreudiumOre");
        }
}