package inei.additionalores.blocks.ores;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class NetherStarOre extends Block 
{
        public NetherStarOre(Material material) 
        {
                super(material);
                setHardness(35.0F);
                setStepSound(Block.soundTypePiston);
                setBlockName("NetherStarOre");
                setHarvestLevel("pickaxe", 9);
                setBlockTextureName("aores:NetherStarOre");
        }
}