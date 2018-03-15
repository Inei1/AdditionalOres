package inei.additionalores.blocks.ores;

import inei.additionalores.AdditionalOres;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class GemiumOre extends Block 
{
        public GemiumOre(Material material) 
        {
                super(material);
                setHardness(5.5F);
                setStepSound(Block.soundTypePiston);
                setBlockName("GemiumOre");
                setHarvestLevel("pickaxe", 4);
                setBlockTextureName("aores:GemiumOre");
        }
        @Override
        public Item getItemDropped(int metadata, Random random, int fortune) {
            return AdditionalOres.itemGemium;
        }
}