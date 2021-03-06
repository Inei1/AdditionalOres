package inei.additionalores.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import inei.additionalores.AdditionalOres;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class PurpliniumArmor extends ItemArmor{

	public PurpliniumArmor(ArmorMaterial material, int id, int slot) {
		super(material, id, slot);
		this.setCreativeTab(AdditionalOres.tabOres);
		
		if(slot == 0 ){
			this.setTextureName("aores:PurpliniumHelmet");
		}else if(slot == 1){
			this.setTextureName("aores:PurpliniumArmor");	
		}else if(slot == 2){
			this.setTextureName("aores:PurpliniumLegs");
		}else if(slot == 3){
			this.setTextureName("aores:PurpliniumBoots");
		}
		
	}
	
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
		if (itemstack.getItem() == AdditionalOres.armorPurpliniumHelm || itemstack.getItem() == AdditionalOres.armorPurpliniumArmor || itemstack.getItem() == AdditionalOres.armorPurpliniumBoots){
			return ("aores:textures/model/armor/Purplinium_Layer_1.png");
			}
		else if (itemstack.getItem() == AdditionalOres.armorPurpliniumLegs){
			return ("aores:textures/model/armor/Purplinium_Layer_2.png");
		}else
			return null;
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("aores:" + this.getUnlocalizedName().substring(5));
	}
}
