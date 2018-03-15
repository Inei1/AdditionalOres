package inei.additionalores.blocks.ores;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class Oreite extends Block 
{
        public Oreite(Material material) 
        {
                super(material);
                setHardness(2.5F);
                setStepSound(Block.soundTypePiston);
                setBlockName("OreiteOre");
                setHarvestLevel("pickaxe", 2);
                setBlockTextureName("aores:OreiteOre");
        }
}