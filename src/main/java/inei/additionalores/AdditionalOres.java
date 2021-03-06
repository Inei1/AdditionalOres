package inei.additionalores;

import inei.additionalores.items.armor.*;
import inei.additionalores.items.compressedingots.CompressedBagellium;
import inei.additionalores.items.compressedingots.CompressedCakium;
import inei.additionalores.items.compressedingots.CompressedGemium;
import inei.additionalores.items.compressedingots.CompressedMedium;
import inei.additionalores.items.compressedingots.CompressedNetherStar;
import inei.additionalores.items.compressedingots.CompressedOreite;
import inei.additionalores.items.compressedingots.CompressedPigolium;
import inei.additionalores.items.compressedingots.CompressedPurplinium;
import inei.additionalores.items.compressedingots.CompressedSoftium;
import inei.additionalores.items.compressedingots.CompressedWeakium;
import inei.additionalores.blocks.*;
import inei.additionalores.blocks.metalblocks.*;
import inei.additionalores.blocks.machines.*;
import inei.additionalores.fluid.*;
import inei.additionalores.handler.*;
import inei.additionalores.items.ingots.*;
import inei.additionalores.items.machinecomponents.CoalBurningCore;
import inei.additionalores.blocks.ores.*;
import inei.additionalores.items.tools.*;
import inei.additionalores.worldgen.WorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/*CONTENTS
 base mod stuff
 creatuve tab
 ore generator
 same as modid
 proxy
 machines
 tool materials
 armor materials
 tool starter
 	swords
 	axes
 	shovels
 	pickaxes
 armor starter
 	helmets
 	chestplates
 	leggings
 	boots
 armor continued
 	helmets
 	chestplates
 	leggings
 	boots
 metal blocks
 main ores
 side ores
 ingots
 FML Preinit
 	ingots
 	ingot registry
 	tool finisher
 		swords
 		axes
 		shovels
 		pickaxes
 	tool registry
 		swords
 		axes
 		shovels
 		pickaxes
 	armor finisher
 		helmets
 		chestplates
 		leggings
 		boots
 	armor registry
 		helmets
 		chestplates
 		leggings
 		boots
 FML Init
 	metal block registry
 	main ore registry
 	side ore registry
 	recipe registry
 		varible definer
 		metal blocks
 		tools
 			swords
 			axes
 				symmetric recipes for axes
 			shovels
 			pickaxes
 	ore gen registry
 	proxy
 FML Postinit
 	smelting registry
 	fuel handler registry
 */
//current crashes:29
/*TODO
 *=priority
 
 ****add more uses for ores
 
 ***make more ores
 
 *make an ore melter/solidifier 
 
 *make a fluid ore tech system
 
 */

//base mod stuff

@Mod(modid="aores", name="Additional Ores", version="Beta 0.1")
public class AdditionalOres {
	
	//creative tab
	
	public static CreativeTabs tabOres = new CreativeTabs("tabOres") {
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	        return Item.getItemFromBlock(oreNetherStarOre);
	    }
	};
	




	
	
	//ore generator
	
	WorldGen WorldGen= new WorldGen(); 
	

		//same as modid
	
        @Instance(value = "aores")
        public static AdditionalOres instance;
        
        //proxy
        
        @SidedProxy(clientSide="inei.additionalores.client.ClientProxy", serverSide="inei.additionalores.CommonProxy")
        public static CommonProxy proxy;
        
        //compressed ingot starter
        public static Item itemCompressedWeakium;
        public static Item itemCompressedPurplinium;
        public static Item itemCompressedOreite;
        public static Item itemCompressedSoftium;
        public static Item itemCompressedGemium;
        public static Item itemCompressedBagellium;
        public static Item itemCompressedCakium;
        public static Item itemCompressedPigolium;
        public static Item itemCompressedMedium;
        public static Item itemCompressedNetherStar;
        
        //fluids
        
        public static Fluid LiquidOre;
        public static Block blockLiquidOre;
        	
        
        //machines
        
        public static Item itemCoalBurningCore;
        
        public static Block blockBasicMachineFrame = new BasicMachineFrame(Material.iron).setBlockName("BasicMachineFrame").setHardness(3.0F).setBlockTextureName("aores:BasicMachineFrame");
        
        public static Block blockOreCompressorIdle = new OreCompressor(false).setBlockName("OreCompressorIdle").setCreativeTab(tabOres).setHardness(5.0F);
        public static Block blockOreCompressorActive = new OreCompressor(false).setBlockName("OreCompressorActive").setHardness(5.0F);
        public static final int guiIDOreCompressor = 1;
        
        //public static Block blockTank = new BlockTank(Material.iron);
        
        //tool materials,(String name, int harvestLevel, int durability, float speed, float damage, int enchantability)
        
        public static final Item.ToolMaterial WeakiumTools = EnumHelper.addToolMaterial("WeakiumTools", 1, 150, 4.0F, 1.0F, 10);
        public static final Item.ToolMaterial PurpliniumTools = EnumHelper.addToolMaterial("PurpliniumTools", 2, 180, 5.0F, 1.5F, 15);
        public static final Item.ToolMaterial OreiteTools = EnumHelper.addToolMaterial("OreiteTools", 3, 250, 6.0F, 2.0F, 18);
        public static final Item.ToolMaterial SoftiumTools = EnumHelper.addToolMaterial("MediumTools", 4, 333, 7.0F, 2.5F, 20);
        public static final Item.ToolMaterial GemiumTools = EnumHelper.addToolMaterial("GemiumTools", 5, 500, 8.0F, 3.0F, 23);
        public static final Item.ToolMaterial BagelliumTools = EnumHelper.addToolMaterial("BagelliumTools", 6, 725, 9.0F, 9.0F, 25);
        public static final Item.ToolMaterial CakiumTools = EnumHelper.addToolMaterial("CakiumTools", 7, 1000, 10.0F, 4.0F, 28);
        public static final Item.ToolMaterial PigoliumTools = EnumHelper.addToolMaterial("PigoliumTools", 8, 1200, 11.0F, 4.5F, 31);
        public static final Item.ToolMaterial MediumTools = EnumHelper.addToolMaterial("MediumTools", 9, 1500, 12.0F, 5.0F, 34);
        public static final Item.ToolMaterial NetherStarTools = EnumHelper.addToolMaterial("NetherStarTools", 10, 2000, 15.0F, 7.0F, 40);
        
        //Armor meterials
        //(String name, int durability, int[] reductionAmounts, int enchantability)
        
        public static final ArmorMaterial WeakiumArmor = EnumHelper.addArmorMaterial("WeakiumArmor", 5, new int[] {1,2,1,1}, 10);
        public static final ArmorMaterial PurpliniumArmor = EnumHelper.addArmorMaterial("PurpliniumArmor", 8, new int[] {1,2,2,1}, 15);
        public static final ArmorMaterial OreiteArmor = EnumHelper.addArmorMaterial("OreiteArmor", 12, new int[] {2,3,2,1}, 18);
        public static final ArmorMaterial SoftiumArmor = EnumHelper.addArmorMaterial("SoftiumArmor", 15, new int[] {2,4,3,2}, 20);
        public static final ArmorMaterial GemiumArmor = EnumHelper.addArmorMaterial("GemiumArmor", 19, new int[] {3,6,4,2}, 23);
        public static final ArmorMaterial BagelliumArmor = EnumHelper.addArmorMaterial("BagelliumArmor", 23, new int[] {3,7,5,3}, 25);
        public static final ArmorMaterial CakiumArmor = EnumHelper.addArmorMaterial("CakiumArmor", 28, new int[] {4,8,5,3}, 28);
        public static final ArmorMaterial PigoliumArmor = EnumHelper.addArmorMaterial("PigoliumArmor", 33, new int[] {4,8,5,3}, 31);
        public static final ArmorMaterial MediumArmor = EnumHelper.addArmorMaterial("MediumArmor", 39, new int[] {4,8,5,3}, 34);
        public static final ArmorMaterial NetherStarArmor = EnumHelper.addArmorMaterial("NetherStarArmor", 50, new int[] {4,8,5,3}, 40);
        
        //tool starter
        
        	//swords
        
        	public static Item itemWeakiumSword;
        	public static Item itemPurpliniumSword;
        	public static Item itemOreiteSword;
        	public static Item itemSoftiumSword;
        	public static Item itemGemiumSword;
        	public static Item itemBagelliumSword;
        	public static Item itemCakiumSword;
        	public static Item itemPigoliumSword;
        	public static Item itemMediumSword;
        	public static Item itemNetherStarSword;
        	//public static Item itemOreiteSword;
        	//public static Item itemOreiteSword;
        	//public static Item itemOreiteSword;
        	public static Item itemHardiumSword;
        	
        	//axes
        	
        	public static Item itemWeakiumAxe;
        	public static Item itemPurpliniumAxe;
        	public static Item itemOreiteAxe;
        	public static Item itemSoftiumAxe;
        	public static Item itemGemiumAxe;
        	public static Item itemBagelliumAxe;
        	public static Item itemCakiumAxe;
        	public static Item itemPigoliumAxe;
        	public static Item itemMediumAxe;
        	public static Item itemNetherStarAxe;
        	//public static Item itemOreiteAxe;
        	//public static Item itemOreiteAxe;
        	//public static Item itemOreiteAxe;
        	public static Item itemHardiumAxe;
        	
        	//shovels
        	
        	public static Item itemWeakiumShovel;
        	public static Item itemPurpliniumShovel;
        	public static Item itemOreiteShovel;
        	public static Item itemSoftiumShovel;
        	public static Item itemGemiumShovel;
        	public static Item itemBagelliumShovel;
        	public static Item itemCakiumShovel;
        	public static Item itemPigoliumShovel;
        	public static Item itemMediumShovel;
        	public static Item itemNetherStarShovel;
        	//public static Item itemOreiteShovel;
        	//public static Item itemOreiteShovel;
        	//public static Item itemOreiteShovel;
        	public static Item itemHardiumShovel;
        	
        	//pickaxes
        	
        	public static Item itemWeakiumPickaxe;    
        	public static Item itemPurpliniumPickaxe;
        	public static Item itemOreitePickaxe;
        	public static Item itemSoftiumPickaxe;
        	public static Item itemGemiumPickaxe;
        	public static Item itemBagelliumPickaxe;
        	public static Item itemCakiumPickaxe;
        	public static Item itemPigoliumPickaxe;
        	public static Item itemMediumPickaxe;
        	public static Item itemNetherStarPickaxe;
        	//public static Item itemOreitePickaxe;
        	//public static Item itemOreitePickaxe;
        	//public static Item itemOreitePickaxe;
        	public static Item itemHardiumPickaxe;
        
        //armor starter
        	
        	//helm
        	
        	public static int armorWeakiumHelmID;
        	public static int armorPurpliniumHelmID;
        	public static int armorOreiteHelmID;
        	public static int armorSoftiumHelmID;
        	public static int armorGemiumHelmID;
        	public static int armorBagelliumHelmID;
        	public static int armorCakiumHelmID;
        	public static int armorPigoliumHelmID;
        	public static int armorMediumHelmID;
        	public static int armorNetherStarHelmID;
        	
        	//chestplate
        	
        	public static int armorWeakiumArmorID;
        	public static int armorPurpliniumArmorID;
        	public static int armorOreiteArmorID;
        	public static int armorSoftiumArmorID;
        	public static int armorGemiumArmorID;
        	public static int armorBagelliumArmorID;
        	public static int armorCakiumArmorID;
        	public static int armorPigoliumArmorID;
        	public static int armorMediumArmorID;
        	public static int armorNetherStarArmorID;
        	
        	//leggings
        	
        	public static int armorWeakiumLegsID;
        	public static int armorPurpliniumLegsID;
        	public static int armorOreiteLegsID;
        	public static int armorSoftiumLegsID;
        	public static int armorGemiumLegsID;
        	public static int armorBagelliumLegsID;
        	public static int armorCakiumLegsID;
        	public static int armorPigoliumLegsID;
        	public static int armorMediumLegsID;
        	public static int armorNetherStarLegsID;
        	
        	//boots
        	
        	public static int armorWeakiumBootsID;
        	public static int armorPurpliniumBootsID;
        	public static int armorOreiteBootsID;
        	public static int armorSoftiumBootsID;
        	public static int armorGemiumBootsID;
        	public static int armorBagelliumBootsID;
        	public static int armorCakiumBootsID;
        	public static int armorPigoliumBootsID;
        	public static int armorMediumBootsID;
        	public static int armorNetherStarBootsID;
        
        
        //armor continued
        	
        	//helmets
        	
        	public static Item armorWeakiumHelm;
        	public static Item armorPurpliniumHelm;
        	public static Item armorOreiteHelm;
        	public static Item armorSoftiumHelm;
        	public static Item armorGemiumHelm;
        	public static Item armorBagelliumHelm;
        	public static Item armorCakiumHelm;
        	public static Item armorPigoliumHelm;
        	public static Item armorMediumHelm;
        	public static Item armorNetherStarHelm;
        	
        	//chestplates
        	
        	public static Item armorWeakiumArmor;
        	public static Item armorPurpliniumArmor;
        	public static Item armorOreiteArmor;
        	public static Item armorSoftiumArmor;
        	public static Item armorGemiumArmor;
        	public static Item armorBagelliumArmor;
        	public static Item armorCakiumArmor;
        	public static Item armorPigoliumArmor;
        	public static Item armorMediumArmor;
        	public static Item armorNetherStarArmor;
        	
        	//leggings
        	
        	public static Item armorWeakiumLegs;
        	public static Item armorPurpliniumLegs;
        	public static Item armorOreiteLegs;
        	public static Item armorSoftiumLegs;
        	public static Item armorGemiumLegs;
        	public static Item armorBagelliumLegs;
        	public static Item armorCakiumLegs;
        	public static Item armorPigoliumLegs;
        	public static Item armorMediumLegs;
        	public static Item armorNetherStarLegs;
        	
        	//boots
        	
        	public static Item armorWeakiumBoots;
        	public static Item armorPurpliniumBoots;
        	public static Item armorOreiteBoots;
        	public static Item armorSoftiumBoots;
        	public static Item armorGemiumBoots;
        	public static Item armorBagelliumBoots;
        	public static Item armorCakiumBoots;
        	public static Item armorPigoliumBoots;
        	public static Item armorMediumBoots;
        	public static Item armorNetherStarBoots;
        
        //metal blocks
        	
        public static final Block blockWeakiumBlock = new WeakiumBlock(Material.anvil).setCreativeTab(tabOres);
        public static final Block blockPurpliniumBlock = new PurpliniumBlock(Material.anvil).setCreativeTab(tabOres);
        public static final Block blockOreiteBlock = new OreiteBlock(Material.anvil).setCreativeTab(tabOres);
        public static final Block blockSoftiumBlock = new Softiumblock(Material.anvil).setCreativeTab(tabOres);
        public static final Block blockGemiumBlock = new Gemiumblock(Material.anvil).setCreativeTab(tabOres);
        public static final Block blockBagelliumBlock = new Bagelliumblock(Material.anvil).setCreativeTab(tabOres);
        public static final Block blockCakiumBlock = new Cakiumblock(Material.anvil).setCreativeTab(tabOres);
        public static final Block blockPigoliumBlock = new Pigoliumblock(Material.anvil).setCreativeTab(tabOres);
        public static final Block blockMediumBlock = new Mediumblock(Material.anvil).setCreativeTab(tabOres);
        public static final Block blockNetherStarBlock = new NetherStarblock(Material.anvil).setCreativeTab(tabOres);
        
        //main ores
        
        public static final Block oreWeakiumOre = new WeakiumOre(Material.rock).setCreativeTab(tabOres);
        public static final Block orePurpliniumOre = new PurpliniumOre(Material.rock).setCreativeTab(tabOres);
        public static final Block oreOreiteOre = new Oreite(Material.rock).setCreativeTab(tabOres);
        public static final Block oreSoftiumOre = new SoftiumOre(Material.rock).setCreativeTab(tabOres);
        public static final Block oreGemiumOre = new GemiumOre(Material.rock).setCreativeTab(tabOres);
        public static final Block oreBagelliumOre = new BagelliumOre(Material.rock).setCreativeTab(tabOres);
        public static final Block oreCakiumOre = new CakiumOre(Material.rock).setCreativeTab(tabOres);
        public static final Block orePigoliumOre = new PigoliumOre(Material.rock).setCreativeTab(tabOres);
        public static final Block oreMediumOre = new MediumOre(Material.rock).setCreativeTab(tabOres);
        public static final Block oreNetherStarOre = new NetherStarOre(Material.rock).setCreativeTab(tabOres);
        //public static final Block oreHardiumOre = new HardiumOre(Material.rock).setCreativeTab(tabOres);
        //public static final Block oreHardiumOre = new HardiumOre(Material.rock).setCreativeTab(tabOres);
        //public static final Block oreHardiumOre = new HardiumOre(Material.rock).setCreativeTab(tabOres);
        public static final Block oreHardiumOre = new HardiumOre(Material.rock).setCreativeTab(tabOres);
        
        //side ores
        
        public static final Block oreCoaliumOre = new CoaliumOre(Material.rock).setCreativeTab(tabOres);
        public static final Block oreStreudiumOre = new StreudiumOre(Material.rock).setCreativeTab(tabOres);
        
        //ingots
        
        public static Item itemWeakiumIngot;
        public static Item itemPurpliniumIngot;
        public static Item itemOreiteIngot;
        public static Item itemSoftiumIngot;
        public static Item itemGemium;
        public static Item itemBagelliumIngot;
        public static Item itemCakiumIngot;
        public static Item itemPigoliumIngot;
        public static Item itemMediumIngot;
        public static Item itemNetherStarIngot;
        public static Item itemCoalium;
        


        
        @EventHandler
        public void modConstruction(FMLConstructionEvent event){
        	
        }
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
        	
        	//machines
        	
        	itemCoalBurningCore = new CoalBurningCore().setCreativeTab(tabOres).setUnlocalizedName("CoalBurningCore").setTextureName("aores:CoalBurningCore");
        	
        	//machine registry
        	
        	GameRegistry.registerItem(itemCoalBurningCore, "CoalBurningCore");
        	
        	//ingots
        	
        	itemWeakiumIngot = new WeakiumIngot().setCreativeTab(tabOres).setUnlocalizedName("WeakiumIngot").setTextureName("aores:WeakiumIngot");
            itemPurpliniumIngot = new PurpliniumIngot().setCreativeTab(tabOres).setUnlocalizedName("PurpliniumIngot").setTextureName("aores:PurpliniumIngot");
            itemOreiteIngot = new OreiteIngot().setCreativeTab(tabOres).setUnlocalizedName("OreiteIngot").setTextureName("aores:OreiteIngot");
            itemSoftiumIngot = new SoftiumIngot().setCreativeTab(tabOres).setUnlocalizedName("SoftiumIngot").setTextureName("aores:SoftiumIngot");
            itemGemium = new Gemium().setCreativeTab(tabOres).setUnlocalizedName("Gemium").setTextureName("aores:Gemium");
            itemBagelliumIngot = new BagelliumIngot().setCreativeTab(tabOres).setUnlocalizedName("BagelliumIngot").setTextureName("aores:BagelliumIngot");
            itemCakiumIngot = new CakiumIngot().setCreativeTab(tabOres).setUnlocalizedName("CakiumIngot").setTextureName("aores:CakiumIngot");
            itemPigoliumIngot = new PigoliumIngot().setCreativeTab(tabOres).setUnlocalizedName("PigoliumGem").setTextureName("aores:PigoliumGem");
            itemMediumIngot = new MediumIngot().setCreativeTab(tabOres).setUnlocalizedName("MediumIngot").setTextureName("aores:MediumIngot");
            itemNetherStarIngot = new NetherStarIngot().setCreativeTab(tabOres).setUnlocalizedName("NetherStarIngot").setTextureName("aores:NetherStarIngot");
            
        	itemCoalium = new Coalium().setCreativeTab(tabOres).setUnlocalizedName("Coalium").setTextureName("aores:Coalium");
        	
        	//compressed ingots
            itemCompressedWeakium = new CompressedWeakium().setCreativeTab(tabOres).setUnlocalizedName("CompressedWeakium").setTextureName("aores:CompressedWeakium");
            itemCompressedPurplinium = new CompressedPurplinium().setCreativeTab(tabOres).setUnlocalizedName("CompressedPurplinium").setTextureName("aores:CompressedPurplinium");
            itemCompressedOreite = new CompressedOreite().setCreativeTab(tabOres).setUnlocalizedName("CompressedOreite").setTextureName("aores:CompressedOreite");
            itemCompressedSoftium = new CompressedSoftium().setCreativeTab(tabOres).setUnlocalizedName("CompressedSoftium").setTextureName("aores:CompressedSoftium");
            itemCompressedGemium = new CompressedGemium().setCreativeTab(tabOres).setUnlocalizedName("CompressedGemium").setTextureName("aores:CompressedGemium");
            itemCompressedBagellium = new CompressedBagellium().setCreativeTab(tabOres).setUnlocalizedName("CompressedBagellium").setTextureName("aores:CompressedBagellium");
            itemCompressedCakium = new CompressedCakium().setCreativeTab(tabOres).setUnlocalizedName("CompressedCakium").setTextureName("aores:CompressedCakium");
            itemCompressedPigolium = new CompressedPigolium().setCreativeTab(tabOres).setUnlocalizedName("CompressedPigolium").setTextureName("aores:CompressedPigolium");
            itemCompressedMedium = new CompressedMedium().setCreativeTab(tabOres).setUnlocalizedName("CompressedMedium").setTextureName("aores:CompressedMedium");
            itemCompressedNetherStar = new CompressedNetherStar().setCreativeTab(tabOres).setUnlocalizedName("CompressedNetherStar").setTextureName("aores:CompressedNetherStar");
        	
        	
        	//ingot registry
        	
        	GameRegistry.registerItem(itemWeakiumIngot, "WeakiumIngot");
        	GameRegistry.registerItem(itemPurpliniumIngot, "PurpliniumGem");
        	GameRegistry.registerItem(itemOreiteIngot, "OreiteIngot");
        	GameRegistry.registerItem(itemSoftiumIngot, "SoftiumIngot");
        	GameRegistry.registerItem(itemGemium, "Gemium");
        	GameRegistry.registerItem(itemBagelliumIngot, "BagelliumIngot");
        	GameRegistry.registerItem(itemCakiumIngot, "CakiumIngot");
        	GameRegistry.registerItem(itemPigoliumIngot, "PigoliumIngot");
        	GameRegistry.registerItem(itemMediumIngot, "MediumIngot");
        	GameRegistry.registerItem(itemNetherStarIngot, "NetherStarIngot");
        	
        	GameRegistry.registerItem(itemCoalium, "Coalium");
        	
        	//compressed ingot registry
        	
        	GameRegistry.registerItem(itemCompressedWeakium, "CompressedWeakium");
        	GameRegistry.registerItem(itemCompressedPurplinium, "CompressedPurplinium");
        	GameRegistry.registerItem(itemCompressedOreite, "CompressedOreite");
        	GameRegistry.registerItem(itemCompressedSoftium, "CompressedSoftium");
        	GameRegistry.registerItem(itemCompressedGemium, "CompressedGemium");
        	GameRegistry.registerItem(itemCompressedBagellium, "CompressedBagellium");
        	GameRegistry.registerItem(itemCompressedCakium, "CompressedCakium");
        	GameRegistry.registerItem(itemCompressedPigolium, "CompressedPigolium");
        	GameRegistry.registerItem(itemCompressedMedium, "CompressedMedium");
        	GameRegistry.registerItem(itemCompressedNetherStar, "CompressedNetherStar");
        	
        	//tool finisher
        	
        		//swords
        	
        		itemWeakiumSword = new Swords(WeakiumTools).setCreativeTab(tabOres).setUnlocalizedName("WeakiumSword").setTextureName("aores:WeakiumSword");
        		itemPurpliniumSword = new Swords(PurpliniumTools).setCreativeTab(tabOres).setUnlocalizedName("PurpliniumSword").setTextureName("aores:PurpliniumSword");
        		itemOreiteSword = new Swords(OreiteTools).setCreativeTab(tabOres).setUnlocalizedName("OreiteSword").setTextureName("aores:OreiteSword");
        		itemSoftiumSword = new Swords(SoftiumTools).setCreativeTab(tabOres).setUnlocalizedName("SoftiumSword").setTextureName("aores:SoftiumSword");
        		itemGemiumSword = new Swords(GemiumTools).setCreativeTab(tabOres).setUnlocalizedName("GemiumSword").setTextureName("aores:GemiumSword");
        		itemBagelliumSword = new Swords(BagelliumTools).setCreativeTab(tabOres).setUnlocalizedName("BagelliumSword").setTextureName("aores:BagelliumSword");
        		itemCakiumSword = new Swords(CakiumTools).setCreativeTab(tabOres).setUnlocalizedName("CakiumSword").setTextureName("aores:CakiumSword");
        		itemPigoliumSword = new Swords(PigoliumTools).setCreativeTab(tabOres).setUnlocalizedName("PigoliumSword").setTextureName("aores:PigoliumSword");
        		itemMediumSword = new Swords(MediumTools).setCreativeTab(tabOres).setUnlocalizedName("MediumSword").setTextureName("aores:MediumSword");
        		itemNetherStarSword = new Swords(NetherStarTools).setCreativeTab(tabOres).setUnlocalizedName("NetherStarSword").setTextureName("aores:NetherStarSword");
        		
        		//axes
        		
        		itemWeakiumAxe = new Axes(WeakiumTools).setCreativeTab(tabOres).setUnlocalizedName("WeakiumAxe").setTextureName("aores:WeakiumAxe");
        		itemPurpliniumAxe = new Axes(PurpliniumTools).setCreativeTab(tabOres).setUnlocalizedName("PurpliniumAxe").setTextureName("aores:PurpliniumAxe");
        		itemOreiteAxe = new Axes(OreiteTools).setCreativeTab(tabOres).setUnlocalizedName("OreiteAxe").setTextureName("aores:OreiteAxe");
        		itemSoftiumAxe = new Axes(SoftiumTools).setCreativeTab(tabOres).setUnlocalizedName("SoftiumAxe").setTextureName("aores:SoftiumAxe");
        		itemGemiumAxe = new Axes(GemiumTools).setCreativeTab(tabOres).setUnlocalizedName("GemiumAxe").setTextureName("aores:GemiumAxe");
        		itemBagelliumAxe = new Axes(BagelliumTools).setCreativeTab(tabOres).setUnlocalizedName("BagelliumAxe").setTextureName("aores:BagelliumAxe");
        		itemCakiumAxe = new Axes(CakiumTools).setCreativeTab(tabOres).setUnlocalizedName("CakiumAxe").setTextureName("aores:CakiumAxe");
        		itemPigoliumAxe = new Axes(PigoliumTools).setCreativeTab(tabOres).setUnlocalizedName("PigoliumAxe").setTextureName("aores:PigoliumAxe");
        		itemMediumAxe = new Axes(MediumTools).setCreativeTab(tabOres).setUnlocalizedName("MediumAxe").setTextureName("aores:MediumAxe");
        		itemNetherStarAxe = new Axes(NetherStarTools).setCreativeTab(tabOres).setUnlocalizedName("NetherStarAxe").setTextureName("aores:NetherStarAxe");
        		
        		//shovels
        		
        		itemWeakiumShovel = new Shovels(WeakiumTools).setCreativeTab(tabOres).setUnlocalizedName("WeakiumShovel").setTextureName("aores:WeakiumShovel");
        		itemPurpliniumShovel = new Shovels(PurpliniumTools).setCreativeTab(tabOres).setUnlocalizedName("PurpliniumShovel").setTextureName("aores:PurpliniumShovel");
        		itemOreiteShovel = new Shovels(OreiteTools).setCreativeTab(tabOres).setUnlocalizedName("OreiteShovel").setTextureName("aores:OreiteShovel");
        		itemSoftiumShovel = new Shovels(SoftiumTools).setCreativeTab(tabOres).setUnlocalizedName("SoftiumShovel").setTextureName("aores:SoftiumShovel");
        		itemGemiumShovel = new Shovels(GemiumTools).setCreativeTab(tabOres).setUnlocalizedName("GemiumShovel").setTextureName("aores:GemiumShovel");
        		itemBagelliumShovel = new Shovels(BagelliumTools).setCreativeTab(tabOres).setUnlocalizedName("BagelliumShovel").setTextureName("aores:BagelliumShovel");
        		itemCakiumShovel = new Shovels(CakiumTools).setCreativeTab(tabOres).setUnlocalizedName("CakiumShovel").setTextureName("aores:CakiumShovel");
        		itemPigoliumShovel = new Shovels(PigoliumTools).setCreativeTab(tabOres).setUnlocalizedName("PigoliumShovel").setTextureName("aores:PigoliumShovel");
        		itemMediumShovel = new Shovels(MediumTools).setCreativeTab(tabOres).setUnlocalizedName("MediumShovel").setTextureName("aores:MediumShovel");
        		itemNetherStarShovel = new Shovels(NetherStarTools).setCreativeTab(tabOres).setUnlocalizedName("NetherStarShovel").setTextureName("aores:NetherStarShovel");
        		
        		//pickaxes
        		
        		itemWeakiumPickaxe = new Pickaxes(WeakiumTools).setCreativeTab(tabOres).setUnlocalizedName("WeakiumPickaxe").setTextureName("aores:WeakiumPickaxe");
        		itemPurpliniumPickaxe = new Pickaxes(PurpliniumTools).setCreativeTab(tabOres).setUnlocalizedName("PurpliniumPickaxe").setTextureName("aores:PurpliniumPickaxe");
        		itemOreitePickaxe = new Pickaxes(OreiteTools).setCreativeTab(tabOres).setUnlocalizedName("OreitePickaxe").setTextureName("aores:OreitePickaxe");
        		itemSoftiumPickaxe = new Pickaxes(SoftiumTools).setCreativeTab(tabOres).setUnlocalizedName("SoftiumPickaxe").setTextureName("aores:SoftiumPickaxe");
        		itemGemiumPickaxe = new Pickaxes(GemiumTools).setCreativeTab(tabOres).setUnlocalizedName("GemiumPickaxe").setTextureName("aores:GemiumPickaxe");
        		itemBagelliumPickaxe = new Pickaxes(BagelliumTools).setCreativeTab(tabOres).setUnlocalizedName("BagelliumPickaxe").setTextureName("aores:BagelliumPickaxes");
        		itemCakiumPickaxe = new Pickaxes(CakiumTools).setCreativeTab(tabOres).setUnlocalizedName("CakiumPickaxe").setTextureName("aores:CakiumPickaxe");
        		itemPigoliumPickaxe = new Pickaxes(PigoliumTools).setCreativeTab(tabOres).setUnlocalizedName("PigoliumPickaxe").setTextureName("aores:PigoliumPickaxe");
        		itemMediumPickaxe = new Pickaxes(MediumTools).setCreativeTab(tabOres).setUnlocalizedName("MediumPickaxe").setTextureName("aores:MediumPickaxe");
        		itemNetherStarPickaxe = new Pickaxes(NetherStarTools).setCreativeTab(tabOres).setUnlocalizedName("NetherStarPickaxe").setTextureName("aores:NetherStarPickaxe");
        	//tool registry
        		
        		//swords
        		
        		GameRegistry.registerItem(itemWeakiumSword, "WeakiumSword");
        		GameRegistry.registerItem(itemPurpliniumSword, "PurpliniumSword");
        		GameRegistry.registerItem(itemOreiteSword, "OreiteSword");
        		GameRegistry.registerItem(itemSoftiumSword, "SoftiumSword");
        		GameRegistry.registerItem(itemGemiumSword, "GemiumSword");
        		GameRegistry.registerItem(itemBagelliumSword, "BagelliumSword");
        		GameRegistry.registerItem(itemCakiumSword, "CakiumSword");
        		GameRegistry.registerItem(itemPigoliumSword, "PigoliumSword");
        		GameRegistry.registerItem(itemMediumSword, "MediumSword");
        		GameRegistry.registerItem(itemNetherStarSword, "NetherStarSword");
        		
        		//axes
        		
        		GameRegistry.registerItem(itemWeakiumAxe, "WeakiumAxe");
        		GameRegistry.registerItem(itemPurpliniumAxe, "PurpliniumAxe");
        		GameRegistry.registerItem(itemOreiteAxe, "OreiteAxe");
        		GameRegistry.registerItem(itemSoftiumAxe, "SoftiumAxe");
        		GameRegistry.registerItem(itemGemiumAxe, "GemiumAxe");
        		GameRegistry.registerItem(itemBagelliumAxe, "BagelliumAxe");
        		GameRegistry.registerItem(itemCakiumAxe, "CakiumAxe");
        		GameRegistry.registerItem(itemPigoliumAxe, "PigoliumAxe");
        		GameRegistry.registerItem(itemMediumAxe, "MediumAxe");
        		GameRegistry.registerItem(itemNetherStarAxe, "NetherStarAxe");
        		
        		//shovels
        		
        		GameRegistry.registerItem(itemWeakiumShovel, "WeakiumShovel");
        		GameRegistry.registerItem(itemPurpliniumShovel, "PurpliniumShovel");
        		GameRegistry.registerItem(itemOreiteShovel, "OreiteShovel");
        		GameRegistry.registerItem(itemSoftiumShovel, "SoftiumShovel");
        		GameRegistry.registerItem(itemGemiumShovel, "GemiumShovel");
        		GameRegistry.registerItem(itemBagelliumShovel, "BagelliumShovel");
        		GameRegistry.registerItem(itemCakiumShovel, "CakiumShovel");
        		GameRegistry.registerItem(itemPigoliumShovel, "PigoliumShovel");
        		GameRegistry.registerItem(itemMediumShovel, "MediumShovel");
        		GameRegistry.registerItem(itemNetherStarShovel, "NetherStarShovel");
        		
        		//pickaxes
        		
        		GameRegistry.registerItem(itemWeakiumPickaxe, "WeakiumPickaxe");
        		GameRegistry.registerItem(itemPurpliniumPickaxe, "PurpliniumPickaxe");
        		GameRegistry.registerItem(itemOreitePickaxe, "OreitePickaxe");
        		GameRegistry.registerItem(itemSoftiumPickaxe, "SoftiumPickaxe");
        		GameRegistry.registerItem(itemGemiumPickaxe, "GemiumPickaxe");
        		GameRegistry.registerItem(itemBagelliumPickaxe, "BagelliumPickaxe");
        		GameRegistry.registerItem(itemCakiumPickaxe, "CakiumPickaxe");
        		GameRegistry.registerItem(itemPigoliumPickaxe, "PigoliumPickaxe");
        		GameRegistry.registerItem(itemMediumPickaxe, "MediumPickaxe");
        		GameRegistry.registerItem(itemNetherStarPickaxe, "NetherStarPickaxe");
         	
            //armor finisher
        		
        		//helms
        		
        		armorWeakiumHelm = new WeakiumArmor(WeakiumArmor, armorWeakiumHelmID, 0).setUnlocalizedName("WeakiumHelmet").setTextureName("WeakiumHelmet");
        		armorPurpliniumHelm = new PurpliniumArmor(PurpliniumArmor, armorPurpliniumHelmID, 0).setUnlocalizedName("PurpliniumHelmet").setTextureName("PurpliniumHelmet");
        		armorOreiteHelm = new OreiteArmor(OreiteArmor, armorOreiteHelmID, 0).setUnlocalizedName("OreiteHelmet").setTextureName("OreiteHelmet");
        		armorSoftiumHelm = new SoftiumArmor(SoftiumArmor, armorSoftiumHelmID, 0).setUnlocalizedName("SoftiumHelmet").setTextureName("SoftiumHelmet");
        		armorGemiumHelm = new GemiumArmor(GemiumArmor, armorGemiumHelmID, 0).setUnlocalizedName("GemiumHelmet").setTextureName("GemiumHelmet");
        		armorBagelliumHelm = new BagelliumArmor(BagelliumArmor, armorBagelliumHelmID, 0).setUnlocalizedName("BagelliumHelmet").setTextureName("BagelliumHelmet");
        		armorCakiumHelm = new CakiumArmor(CakiumArmor, armorCakiumHelmID, 0).setUnlocalizedName("CakiumHelmet").setTextureName("CakiumHelmet");
        		armorPigoliumHelm = new PigoliumArmor(PigoliumArmor, armorPigoliumHelmID, 0).setUnlocalizedName("PigoliumHelmet").setTextureName("PigoliumHelmet");
        		armorMediumHelm = new MediumArmor(MediumArmor, armorMediumHelmID, 0).setUnlocalizedName("MediumHelmet").setTextureName("MediumHelmet");
        		armorNetherStarHelm = new NetherStarArmor(NetherStarArmor, armorNetherStarHelmID, 0).setUnlocalizedName("NetherStarHelmet").setTextureName("NetherStarHelmet");
        		
        		//chestplates
        		
        		armorWeakiumArmor = new WeakiumArmor(WeakiumArmor, armorWeakiumArmorID, 1).setUnlocalizedName("WeakiumArmor").setTextureName("WeakiumArmor");
        		armorPurpliniumArmor = new PurpliniumArmor(PurpliniumArmor, armorPurpliniumArmorID, 1).setUnlocalizedName("PurpliniumArmor").setTextureName("PurpliniumArmor");
        		armorOreiteArmor = new OreiteArmor(OreiteArmor, armorOreiteArmorID, 1).setUnlocalizedName("OreiteArmor").setTextureName("OreiteArmor");
        		armorSoftiumArmor = new SoftiumArmor(SoftiumArmor, armorSoftiumArmorID, 1).setUnlocalizedName("SoftiumArmor").setTextureName("SoftiumArmor");
        		armorGemiumArmor = new GemiumArmor(GemiumArmor, armorGemiumArmorID, 1).setUnlocalizedName("GemiumArmor").setTextureName("GemiumArmor");
        		armorBagelliumArmor = new BagelliumArmor(BagelliumArmor, armorBagelliumArmorID, 1).setUnlocalizedName("BagelliumArmor").setTextureName("BagelliumArmor");
        		armorCakiumArmor = new CakiumArmor(CakiumArmor, armorCakiumArmorID, 1).setUnlocalizedName("CakiumArmor").setTextureName("CakiumArmor");
        		armorPigoliumArmor = new PigoliumArmor(PigoliumArmor, armorPigoliumArmorID, 1).setUnlocalizedName("PigoliumArmor").setTextureName("PigoliumArmor");
        		armorMediumArmor = new MediumArmor(MediumArmor, armorMediumArmorID, 1).setUnlocalizedName("MediumArmor").setTextureName("MediumArmor");
        		armorNetherStarArmor = new NetherStarArmor(NetherStarArmor, armorNetherStarArmorID, 1).setUnlocalizedName("NetherStarArmor").setTextureName("NetherStarArmor");
        		
        		//leggings
        		
        		armorWeakiumLegs = new WeakiumArmor(WeakiumArmor, armorWeakiumLegsID, 2).setUnlocalizedName("WeakiumLegs").setTextureName("WeakiumLegs");
        		armorPurpliniumLegs = new PurpliniumArmor(PurpliniumArmor, armorPurpliniumLegsID, 2).setUnlocalizedName("PurpliniumLegs").setTextureName("PurpliniumLegs");
        		armorOreiteLegs = new OreiteArmor(OreiteArmor, armorOreiteLegsID, 2).setUnlocalizedName("OreiteLegs").setTextureName("OreiteLegs");
        		armorSoftiumLegs = new SoftiumArmor(SoftiumArmor, armorSoftiumLegsID, 2).setUnlocalizedName("SoftiumLegs").setTextureName("SoftiumLegs");
        		armorGemiumLegs = new GemiumArmor(GemiumArmor, armorGemiumLegsID, 2).setUnlocalizedName("GemiumLegs").setTextureName("GemiumLegs");
        		armorBagelliumLegs = new BagelliumArmor(BagelliumArmor, armorBagelliumLegsID, 2).setUnlocalizedName("BagelliumLegs").setTextureName("BagelliumLegs");
        		armorCakiumLegs = new CakiumArmor(CakiumArmor, armorCakiumLegsID, 2).setUnlocalizedName("CakiumLegs").setTextureName("CakiumLegs");
        		armorPigoliumLegs = new PigoliumArmor(PigoliumArmor, armorPigoliumLegsID, 2).setUnlocalizedName("PigoliumLegs").setTextureName("PigoliumLegs");
        		armorMediumLegs = new MediumArmor(MediumArmor, armorMediumLegsID, 2).setUnlocalizedName("MediumLegs").setTextureName("MediumLegs");
        		armorNetherStarLegs = new NetherStarArmor(NetherStarArmor, armorNetherStarLegsID, 2).setUnlocalizedName("NetherStarLegs").setTextureName("NetherStarLegs");
        		
        		//boots
        		
        		armorWeakiumBoots = new WeakiumArmor(WeakiumArmor, armorWeakiumBootsID, 3).setUnlocalizedName("WeakiumBoots").setTextureName("WeakiumBoots");
        		armorPurpliniumBoots = new PurpliniumArmor(PurpliniumArmor, armorPurpliniumBootsID, 3).setUnlocalizedName("PurpliniumBoots").setTextureName("PurpliniumBoots");
        		armorOreiteBoots = new OreiteArmor(OreiteArmor, armorOreiteBootsID, 3).setUnlocalizedName("OreiteBoots").setTextureName("OreiteBoots");
        		armorSoftiumBoots = new SoftiumArmor(SoftiumArmor, armorSoftiumBootsID, 3).setUnlocalizedName("SoftiumBoots").setTextureName("SoftiumBoots");
        		armorGemiumBoots = new GemiumArmor(GemiumArmor, armorGemiumBootsID, 3).setUnlocalizedName("GemiumBoots").setTextureName("GemiumBoots");
        		armorBagelliumBoots = new BagelliumArmor(BagelliumArmor, armorBagelliumBootsID, 3).setUnlocalizedName("BagelliumBoots").setTextureName("BagelliumBoots");
        		armorCakiumBoots = new CakiumArmor(CakiumArmor, armorCakiumBootsID, 3).setUnlocalizedName("CakiumBoots").setTextureName("CakiumBoots");
        		armorPigoliumBoots = new PigoliumArmor(PigoliumArmor, armorPigoliumBootsID, 3).setUnlocalizedName("PigoliumBoots").setTextureName("PigoliumBoots");
        		armorMediumBoots = new MediumArmor(MediumArmor, armorMediumBootsID, 3).setUnlocalizedName("MediumBoots").setTextureName("MediumBoots");
        		armorNetherStarBoots = new NetherStarArmor(NetherStarArmor, armorNetherStarBootsID, 3).setUnlocalizedName("NetherStarBoots").setTextureName("NetherStarBoots");
        		
            //armor registry
        		
        		//helmets
        		
        		GameRegistry.registerItem(armorWeakiumHelm,"WeakiumHelm");
        		GameRegistry.registerItem(armorPurpliniumHelm,"PurpliniumHelm");
        		GameRegistry.registerItem(armorOreiteHelm,"OreiteHelm");
        		GameRegistry.registerItem(armorSoftiumHelm,"SoftiumHelm");
        		GameRegistry.registerItem(armorGemiumHelm,"GemiumHelm");
        		GameRegistry.registerItem(armorBagelliumHelm,"BagelliumHelm");
        		GameRegistry.registerItem(armorCakiumHelm,"CakiumHelm");
        		GameRegistry.registerItem(armorPigoliumHelm,"PigoliumHelm");
        		GameRegistry.registerItem(armorMediumHelm,"MediumHelm");
        		GameRegistry.registerItem(armorNetherStarHelm,"NetherStarHelm");
        		
        		//chestplates
        		
        		GameRegistry.registerItem(armorWeakiumArmor,"WeakiumArmor");
        		GameRegistry.registerItem(armorPurpliniumArmor,"PurpliniumArmor");
        		GameRegistry.registerItem(armorOreiteArmor,"OreiteArmor");
        		GameRegistry.registerItem(armorSoftiumArmor,"SoftiumArmor");
        		GameRegistry.registerItem(armorGemiumArmor,"GemiumArmor");
        		GameRegistry.registerItem(armorBagelliumArmor,"BagelliumArmor");
        		GameRegistry.registerItem(armorCakiumArmor,"CakiumArmor");
        		GameRegistry.registerItem(armorPigoliumArmor,"PigoliumArmor");
        		GameRegistry.registerItem(armorMediumArmor,"MediumArmor");
        		GameRegistry.registerItem(armorNetherStarArmor,"NetherStarArmor");
        		
        		//leggings
        		
        		GameRegistry.registerItem(armorWeakiumLegs,"WeakiumLegs");
        		GameRegistry.registerItem(armorPurpliniumLegs,"PurpliniumLegs");
        		GameRegistry.registerItem(armorOreiteLegs,"OreiteLegs");
        		GameRegistry.registerItem(armorSoftiumLegs,"SoftiumLegs");
        		GameRegistry.registerItem(armorGemiumLegs,"GemiumLegs");
        		GameRegistry.registerItem(armorBagelliumLegs,"BagelliumLegs");
        		GameRegistry.registerItem(armorCakiumLegs,"CakiumLegs");
        		GameRegistry.registerItem(armorPigoliumLegs,"PigoliumLegs");
        		GameRegistry.registerItem(armorMediumLegs,"MediumLegs");
        		GameRegistry.registerItem(armorNetherStarLegs,"NetherStarLegs");
        		
        		//boots
        		
        		GameRegistry.registerItem(armorWeakiumBoots,"WeakiumBoots");
        		GameRegistry.registerItem(armorPurpliniumBoots,"PurpliniumBoots");
        		GameRegistry.registerItem(armorOreiteBoots,"OreiteBoots");
        		GameRegistry.registerItem(armorSoftiumBoots,"SoftiumBoots");
        		GameRegistry.registerItem(armorGemiumBoots,"GemiumBoots");
        		GameRegistry.registerItem(armorBagelliumBoots,"BagelliumBoots");
        		GameRegistry.registerItem(armorCakiumBoots,"CakiumBoots");
        		GameRegistry.registerItem(armorPigoliumBoots,"PigoliumBoots");
        		GameRegistry.registerItem(armorMediumBoots,"MediumBoots");
        		GameRegistry.registerItem(armorNetherStarBoots,"NetherStarBoots");
         	
        }
        //forge Init
        @EventHandler
        public void Init(FMLInitializationEvent event) {
        	
        	//fluids
        	
        	LiquidOre = new Fluid("LiquidOre").setDensity(2000).setTemperature(1000).setViscosity(6000).setLuminosity(8).setGaseous(false);
        	FluidRegistry.registerFluid(LiquidOre);
        	blockLiquidOre = new BlockLiquidOre(LiquidOre, Material.lava).setBlockName("LiquidOre");
        	GameRegistry.registerBlock(blockLiquidOre, "aores_" + blockLiquidOre.getUnlocalizedName().substring(5));
        	LiquidOre.setUnlocalizedName(blockLiquidOre.getUnlocalizedName());
        	
        	//gui handler
        	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        	
        	//tile entity registry
        	GameRegistry.registerTileEntity(inei.additionalores.tileentity.TileEntityOreCompressor.class, "OreCompressor");
        	
        	//machines registry
        	GameRegistry.registerBlock(blockBasicMachineFrame, "BasicMachineFrame");
        	
        	GameRegistry.registerBlock(blockOreCompressorIdle, "OreCompressorIdle");
        	GameRegistry.registerBlock(blockOreCompressorActive, "OreCompressorActive");
        	
        	//metal block registry
        	
        	GameRegistry.registerBlock(blockWeakiumBlock, "WeakiumBlock");
        	GameRegistry.registerBlock(blockPurpliniumBlock, "PurpliniumBlock");
        	GameRegistry.registerBlock(blockOreiteBlock, "OreiteBlock");
        	GameRegistry.registerBlock(blockSoftiumBlock, "SoftiumBlock");
        	GameRegistry.registerBlock(blockGemiumBlock, "GemiumBlock");
        	GameRegistry.registerBlock(blockBagelliumBlock, "BagelliumBlock");
        	GameRegistry.registerBlock(blockCakiumBlock, "CakiumBlock");
        	GameRegistry.registerBlock(blockPigoliumBlock, "PigoliumBlock");
        	GameRegistry.registerBlock(blockMediumBlock, "MediumBlock");
        	GameRegistry.registerBlock(blockNetherStarBlock, "NetherStarBlock");
        	
        	//main ore registry
        	
        	GameRegistry.registerBlock(oreWeakiumOre, "WeakiumOre");
        	GameRegistry.registerBlock(orePurpliniumOre, "PurpliniumOre");
        	GameRegistry.registerBlock(oreOreiteOre, "OreiteOre");
        	GameRegistry.registerBlock(oreSoftiumOre, "SoftiumOre");
        	GameRegistry.registerBlock(oreGemiumOre, "GemiumOre");
        	GameRegistry.registerBlock(oreBagelliumOre, "BagelliumOre");
        	GameRegistry.registerBlock(oreCakiumOre, "CakiumOre");
        	GameRegistry.registerBlock(orePigoliumOre, "PigoliumOre");
        	GameRegistry.registerBlock(oreMediumOre, "MediumOre");
        	GameRegistry.registerBlock(oreNetherStarOre, "NetherStarOre");
          //GameRegistry.registerBlock(oreHardiumOre, "HardiumOre");
          //GameRegistry.registerBlock(oreHardiumOre, "HardiumOre");
          //GameRegistry.registerBlock(oreHardiumOre, "HardiumOre");
          //GameRegistry.registerBlock(oreHardiumOre, "HardiumOre");
        	
        	//side ore registry
        	
        	GameRegistry.registerBlock(oreCoaliumOre, "CoaliumOre");
        	
        	//recipe registry
        	
        	//variable definer
        	
        	ItemStack Stick = new ItemStack(Items.stick);
        	ItemStack Coal = new ItemStack(Items.coal);
        	ItemStack BasicFrame = new ItemStack(blockBasicMachineFrame);
        	ItemStack Weakium = new ItemStack(itemWeakiumIngot);
        	ItemStack Purple = new ItemStack(itemPurpliniumIngot);
        	ItemStack Oreite = new ItemStack(itemOreiteIngot);
        	ItemStack Softium = new ItemStack(itemSoftiumIngot);
        	ItemStack Gemium = new ItemStack(itemGemium);
        	ItemStack Bagellium = new ItemStack(itemBagelliumIngot);
        	ItemStack Cakium = new ItemStack(itemCakiumIngot);
        	ItemStack Pig = new ItemStack(itemPigoliumIngot);
        	ItemStack Medium = new ItemStack(itemMediumIngot);
        	ItemStack NetherStar = new ItemStack(itemNetherStarIngot);
        	
        	//machines
        	
        	GameRegistry.addRecipe(BasicFrame,"xyx","yzy","xyx",'x',Purple,'y',Oreite,'z',new ItemStack(blockWeakiumBlock));
        	GameRegistry.addRecipe(BasicFrame,"xyx","yzy","xyx",'y',Purple,'x',Oreite,'z',new ItemStack(blockWeakiumBlock));
        	GameRegistry.addRecipe(new ItemStack(blockOreCompressorIdle),"xxx","xyx","xzx",'x',Oreite,'y',BasicFrame,'z',new ItemStack(itemCoalBurningCore));
        	GameRegistry.addRecipe(new ItemStack(itemCoalBurningCore),"xxx","xyx","xxx",'x',Weakium,'y',Items.coal);
        	
        	//other
        	
        	GameRegistry.addShapelessRecipe(new ItemStack(Items.coal, 2), new ItemStack(AdditionalOres.itemCoalium));
        	
        	//compressed ingots
        	
        	GameRegistry.addShapelessRecipe(new ItemStack(itemCompressedWeakium), new ItemStack(AdditionalOres.itemWeakiumIngot,4));
        	GameRegistry.addShapelessRecipe(new ItemStack(itemCompressedPurplinium),new ItemStack(AdditionalOres.itemPurpliniumIngot,4));
        	GameRegistry.addShapelessRecipe(new ItemStack(itemCompressedOreite),new ItemStack(AdditionalOres.itemOreiteIngot,4));
        	GameRegistry.addShapelessRecipe(new ItemStack(itemCompressedSoftium),new ItemStack(AdditionalOres.itemSoftiumIngot,4));
        	GameRegistry.addShapelessRecipe(new ItemStack(itemCompressedGemium),new ItemStack(AdditionalOres.itemGemium,4));
        	GameRegistry.addShapelessRecipe(new ItemStack(itemCompressedBagellium),new ItemStack(AdditionalOres.itemBagelliumIngot,4));
        	GameRegistry.addShapelessRecipe(new ItemStack(itemCompressedCakium),new ItemStack(AdditionalOres.itemCakiumIngot,4));
        	GameRegistry.addShapelessRecipe(new ItemStack(itemCompressedPigolium),new ItemStack(AdditionalOres.itemPigoliumIngot,4));
        	GameRegistry.addShapelessRecipe(new ItemStack(itemCompressedMedium),new ItemStack(AdditionalOres.itemMediumIngot,4));
        	GameRegistry.addShapelessRecipe(new ItemStack(itemCompressedNetherStar),new ItemStack(AdditionalOres.itemNetherStarIngot,4));
        	
        	//metal blocks
        	
        	GameRegistry.addRecipe(new ItemStack(blockWeakiumBlock),"xxx", "xxx", "xxx",'x',Weakium);
			GameRegistry.addRecipe(new ItemStack(blockPurpliniumBlock),"xxx","xxx","xxx",'x',Purple);
			GameRegistry.addRecipe(new ItemStack(blockOreiteBlock),"xxx","xxx","xxx",'x',Oreite);
			GameRegistry.addRecipe(new ItemStack(blockSoftiumBlock),"xxx","xxx","xxx",'x',Softium);
			GameRegistry.addRecipe(new ItemStack(blockGemiumBlock),"xxx","xxx","xxx",'x',Gemium);
			GameRegistry.addRecipe(new ItemStack(blockBagelliumBlock),"xxx","xxx","xxx",'x',Bagellium);
			GameRegistry.addRecipe(new ItemStack(blockCakiumBlock),"xxx","xxx","xxx",'x',Cakium);
			GameRegistry.addRecipe(new ItemStack(blockPigoliumBlock),"xxx","xxx","xxx",'x',Pig);
			GameRegistry.addRecipe(new ItemStack(blockMediumBlock),"xxx","xxx","xxx",'x',Medium);
			GameRegistry.addRecipe(new ItemStack(blockNetherStarBlock),"xxx","xxx","xxx",'x',NetherStar);
			
        	//tools
        	
        		//swords
        	
    			GameRegistry.addRecipe(new ItemStack(itemWeakiumSword)," x "," x "," y ",'x',Weakium, 'y',Stick);
    			GameRegistry.addRecipe(new ItemStack(itemPurpliniumSword)," x "," x "," y ",'x',Purple, 'y',Stick);
    			GameRegistry.addRecipe(new ItemStack(itemOreiteSword)," x "," x "," y ",'x',Oreite, 'y',Stick);
    			GameRegistry.addRecipe(new ItemStack(itemSoftiumSword)," x "," x "," y ",'x',Softium, 'y',Stick);
    			GameRegistry.addRecipe(new ItemStack(itemGemiumSword)," x "," x "," y ",'x',Gemium, 'y',Stick);
    			GameRegistry.addRecipe(new ItemStack(itemBagelliumSword)," x "," x "," y ",'x',Bagellium, 'y',Stick);
    			GameRegistry.addRecipe(new ItemStack(itemCakiumSword)," x "," x "," y ",'x',Cakium, 'y',Stick);
    			GameRegistry.addRecipe(new ItemStack(itemPigoliumSword)," x "," x "," y ",'x',Pig, 'y',Stick);
    			GameRegistry.addRecipe(new ItemStack(itemMediumSword)," x "," x "," y ",'x',Medium, 'y',Stick);
    			GameRegistry.addRecipe(new ItemStack(itemNetherStarSword)," x "," x "," y ",'x',NetherStar, 'y',Stick);
        		
        		//axes
        		
    			GameRegistry.addRecipe(new ItemStack(itemWeakiumAxe),"xx ","xy "," y ",'x',Weakium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemPurpliniumAxe),"xx ","xy "," y ",'x',Purple, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemOreiteAxe),"xx ","xy "," y ",'x',Oreite, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemSoftiumAxe),"xx ","xy "," y ",'x',Softium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemGemiumAxe),"xx ","xy "," y ",'x',Gemium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemBagelliumAxe),"xx ","xy "," y ",'x',Bagellium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemCakiumAxe),"xx ","xy "," y ",'x',Cakium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemPigoliumAxe),"xx ","xy "," y ",'x',Pig, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemMediumAxe),"xx ","xy "," y ",'x',Medium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemNetherStarAxe),"xx ","xy "," y ",'x',NetherStar, 'y',Stick);
        		
        			//symmetic recipes for axes
        		
    				GameRegistry.addRecipe(new ItemStack(itemWeakiumAxe)," xx"," yx"," y ",'x',Weakium, 'y',Stick);
    				GameRegistry.addRecipe(new ItemStack(itemPurpliniumAxe)," xx"," yx"," y ",'x',Purple, 'y',Stick);
    				GameRegistry.addRecipe(new ItemStack(itemOreiteAxe)," xx"," yx"," y ",'x',Oreite, 'y',Stick);
    				GameRegistry.addRecipe(new ItemStack(itemSoftiumAxe)," xx"," yx"," y ",'x',Softium, 'y',Stick);
    				GameRegistry.addRecipe(new ItemStack(itemGemiumAxe)," xx"," yx"," y ",'x',Gemium, 'y',Stick);
    				GameRegistry.addRecipe(new ItemStack(itemBagelliumAxe)," xx"," yx"," y ",'x',Bagellium, 'y',Stick);
    				GameRegistry.addRecipe(new ItemStack(itemCakiumAxe)," xx"," yx"," y ",'x',Cakium, 'y',Stick);
    				GameRegistry.addRecipe(new ItemStack(itemPigoliumAxe)," xx"," yx"," y ",'x',Pig, 'y',Stick);
    				GameRegistry.addRecipe(new ItemStack(itemMediumAxe)," xx"," yx"," y ",'x',Medium, 'y',Stick);
    				GameRegistry.addRecipe(new ItemStack(itemNetherStarAxe)," xx"," yx"," y ",'x',NetherStar, 'y',Stick);
        		
        		//shovels
        		
        		GameRegistry.addRecipe(new ItemStack(itemWeakiumShovel)," x "," y "," y ",'x',Weakium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemPurpliniumShovel)," x "," y "," y ",'x',Purple, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemOreiteShovel)," x "," y "," y ",'x',Oreite, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemSoftiumShovel)," x "," y "," y ",'x',Softium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemGemiumShovel)," x "," y "," y ",'x',Gemium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemBagelliumShovel)," x "," y "," y ",'x',Bagellium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemCakiumShovel)," x "," y "," y ",'x',Cakium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemPigoliumShovel)," x "," y "," y ",'x',Pig, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemMediumShovel)," x "," y "," y ",'x',Medium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemNetherStarShovel)," x "," y "," y ",'x',NetherStar, 'y',Stick);
        		
        		//pickaxes
        		
        		GameRegistry.addRecipe(new ItemStack(itemWeakiumPickaxe),"xxx"," y "," y ",'x',Weakium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemPurpliniumPickaxe),"xxx"," y "," y ",'x',Purple, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemOreitePickaxe),"xxx"," y "," y ",'x',Oreite, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemSoftiumPickaxe),"xxx"," y "," y ",'x',Softium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemGemiumPickaxe),"xxx"," y "," y ",'x',Gemium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemBagelliumPickaxe),"xxx"," y "," y ",'x',Bagellium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemCakiumPickaxe),"xxx"," y "," y ",'x',Cakium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemPigoliumPickaxe),"xxx"," y "," y ",'x',Pig, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemMediumPickaxe),"xxx"," y "," y ",'x',Medium, 'y',Stick);
        		GameRegistry.addRecipe(new ItemStack(itemNetherStarPickaxe),"xxx"," y "," y ",'x',NetherStar, 'y',Stick);
        		
        	//armor
        		
        		//helmets
        		
        		GameRegistry.addRecipe(new ItemStack(armorWeakiumHelm),"xxx","x x","   ",'x',Weakium);
        		GameRegistry.addRecipe(new ItemStack(armorPurpliniumHelm),"xxx","x x","   ",'x',Purple);
        		GameRegistry.addRecipe(new ItemStack(armorOreiteHelm),"xxx","x x","   ",'x',Oreite);
        		GameRegistry.addRecipe(new ItemStack(armorSoftiumHelm),"xxx","x x","   ",'x',Softium);
        		GameRegistry.addRecipe(new ItemStack(armorGemiumHelm),"xxx","x x","   ",'x',Gemium);
        		GameRegistry.addRecipe(new ItemStack(armorBagelliumHelm),"xxx","x x","   ",'x',Bagellium);
        		GameRegistry.addRecipe(new ItemStack(armorCakiumHelm),"xxx","x x","   ",'x',Cakium);
        		GameRegistry.addRecipe(new ItemStack(armorPigoliumHelm),"xxx","x x","   ",'x',Pig);
        		GameRegistry.addRecipe(new ItemStack(armorMediumHelm),"xxx","x x","   ",'x',Medium);
        		GameRegistry.addRecipe(new ItemStack(armorNetherStarHelm),"xxx","x x","   ",'x',NetherStar);
        		
        		//chestplates
        		
        		GameRegistry.addRecipe(new ItemStack(armorWeakiumArmor),"x x","xxx","xxx",'x',Weakium);
        		GameRegistry.addRecipe(new ItemStack(armorPurpliniumArmor),"x x","xxx","xxx",'x',Purple);
        		GameRegistry.addRecipe(new ItemStack(armorOreiteArmor),"x x","xxx","xxx",'x',Oreite);
        		GameRegistry.addRecipe(new ItemStack(armorSoftiumArmor),"x x","xxx","xxx",'x',Softium);
        		GameRegistry.addRecipe(new ItemStack(armorGemiumArmor),"x x","xxx","xxx",'x',Gemium);
        		GameRegistry.addRecipe(new ItemStack(armorBagelliumArmor),"x x","xxx","xxx",'x',Bagellium);
        		GameRegistry.addRecipe(new ItemStack(armorCakiumArmor),"x x","xxx","xxx",'x',Cakium);
        		GameRegistry.addRecipe(new ItemStack(armorPigoliumArmor),"x x","xxx","xxx",'x',Pig);
        		GameRegistry.addRecipe(new ItemStack(armorMediumArmor),"x x","xxx","xxx",'x',Medium);
        		GameRegistry.addRecipe(new ItemStack(armorNetherStarArmor),"x x","xxx","xxx",'x',NetherStar);
        		
        		//leggings
        		
        		GameRegistry.addRecipe(new ItemStack(armorWeakiumLegs),"xxx","x x","x x",'x',Weakium);
        		GameRegistry.addRecipe(new ItemStack(armorPurpliniumLegs),"xxx","x x","x x",'x',Purple);
        		GameRegistry.addRecipe(new ItemStack(armorOreiteLegs),"xxx","x x","x x",'x',Oreite);
        		GameRegistry.addRecipe(new ItemStack(armorSoftiumLegs),"xxx","x x","x x",'x',Softium);
        		GameRegistry.addRecipe(new ItemStack(armorGemiumLegs),"xxx","x x","x x",'x',Gemium);
        		GameRegistry.addRecipe(new ItemStack(armorBagelliumLegs),"xxx","x x","x x",'x',Bagellium);
        		GameRegistry.addRecipe(new ItemStack(armorCakiumLegs),"xxx","x x","x x",'x',Cakium);
        		GameRegistry.addRecipe(new ItemStack(armorPigoliumLegs),"xxx","x x","x x",'x',Pig);
				GameRegistry.addRecipe(new ItemStack(armorMediumLegs),"xxx","x x","x x",'x',Medium);
				GameRegistry.addRecipe(new ItemStack(armorNetherStarLegs),"xxx","x x","x x",'x',NetherStar);
				
				//boots
				
        		GameRegistry.addRecipe(new ItemStack(armorWeakiumBoots),"   ","x x","x x",'x',Weakium);
        		GameRegistry.addRecipe(new ItemStack(armorPurpliniumBoots),"   ","x x","x x",'x',Purple);
        		GameRegistry.addRecipe(new ItemStack(armorOreiteBoots),"   ","x x","x x",'x',Oreite);
        		GameRegistry.addRecipe(new ItemStack(armorSoftiumBoots),"   ","x x","x x",'x',Softium);
        		GameRegistry.addRecipe(new ItemStack(armorGemiumBoots),"   ","x x","x x",'x',Gemium);
        		GameRegistry.addRecipe(new ItemStack(armorBagelliumBoots),"   ","x x","x x",'x',Bagellium);
        		GameRegistry.addRecipe(new ItemStack(armorCakiumBoots),"   ","x x","x x",'x',Cakium);
        		GameRegistry.addRecipe(new ItemStack(armorPigoliumBoots),"   ","x x","x x",'x',Pig);
        		GameRegistry.addRecipe(new ItemStack(armorMediumBoots),"   ","x x","x x",'x',Medium);
        		GameRegistry.addRecipe(new ItemStack(armorNetherStarBoots),"   ","x x","x x",'x',NetherStar);
        	
        	//ore gen registry
        		
        	GameRegistry.registerWorldGenerator(WorldGen, 0);
        	
        	//proxy
        	
                proxy.registerRenderers();
                
        }
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
        	
        	//smelting registry
        	
        	GameRegistry.addSmelting(oreWeakiumOre, new ItemStack(itemWeakiumIngot), 0.1F);
        	GameRegistry.addSmelting(orePurpliniumOre, new ItemStack(itemPurpliniumIngot), 0.2F);
        	GameRegistry.addSmelting(oreOreiteOre, new ItemStack(itemOreiteIngot), 0.3F);
        	GameRegistry.addSmelting(oreSoftiumOre, new ItemStack(itemSoftiumIngot), 0.4F);
        	GameRegistry.addSmelting(oreGemiumOre, new ItemStack(itemGemium), 1.0F);
        	GameRegistry.addSmelting(oreBagelliumOre, new ItemStack(itemBagelliumIngot), 0.5F);
        	GameRegistry.addSmelting(oreCakiumOre, new ItemStack(itemCakiumIngot), 0.6F);
        	GameRegistry.addSmelting(orePigoliumOre, new ItemStack(itemPigoliumIngot), 0.7F);
        	GameRegistry.addSmelting(oreMediumOre, new ItemStack(itemMediumIngot), 0.8F);
        	GameRegistry.addSmelting(oreNetherStarOre, new ItemStack(itemNetherStarIngot), 0.9F);
        	
        	GameRegistry.addSmelting(oreCoaliumOre, new ItemStack(itemCoalium), 0.5F);
        	
        	GameRegistry.addSmelting(Items.diamond, new ItemStack(itemGemium), 5.0F);
        	GameRegistry.addSmelting(Items.nether_star, new ItemStack(itemNetherStarIngot), 10.0F);
        	
        	//fuel handler registry
        	
        	GameRegistry.registerFuelHandler(new FuelHandler());
        	
        
        }


}