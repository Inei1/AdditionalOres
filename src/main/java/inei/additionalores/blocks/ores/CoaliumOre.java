package inei.additionalores.blocks.ores;

import java.util.Random;

import inei.additionalores.AdditionalOres;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class CoaliumOre extends Block{

	public CoaliumOre(Material material) {
		super(material);
        setHardness(3.0F);
        setStepSound(Block.soundTypePiston);
        setBlockName("CoaliumOre");
        setHarvestLevel("pickaxe", 4);
        setBlockTextureName("aores:CoaliumOre");
        
	}
        @Override
        public Item getItemDropped(int metadata, Random random, int fortune) {
            return AdditionalOres.itemCoalium;
        }
        	
        
	
	}


