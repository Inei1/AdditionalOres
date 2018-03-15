package inei.additionalores.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import inei.additionalores.AdditionalOres;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class BagelliumArmor extends ItemArmor{

	public BagelliumArmor(ArmorMaterial material, int id, int slot) {
		super(material, id, slot);
		this.setCreativeTab(AdditionalOres.tabOres);
		
		if(slot == 0 ){
			this.setTextureName("aores:BagelliumHelmet");
		}else if(slot == 1){
			this.setTextureName("aores:BagelliumArmor");	
		}else if(slot == 2){
			this.setTextureName("aores:BagelliumLegs");
		}else if(slot == 3){
			this.setTextureName("aores:BagelliumBoots");
		}
		
	}
	
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
		if (itemstack.getItem() == AdditionalOres.armorBagelliumHelm || itemstack.getItem() == AdditionalOres.armorBagelliumArmor || itemstack.getItem() == AdditionalOres.armorBagelliumBoots){
			return ("aores:textures/model/armor/Bagellium_Layer_1.png");
			}
		else if (itemstack.getItem() == AdditionalOres.armorBagelliumLegs){
			return ("aores:textures/model/armor/Bagellium_Layer_2.png");
		}else
			return null;
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("aores:" + this.getUnlocalizedName().substring(5));
	}
}
