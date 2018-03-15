package inei.additionalores;

import net.minecraft.item.ItemStack;

public class Utils {
	
	public static ItemStack size(ItemStack itemstack, int size) 
 	{ 
 		ItemStack newStack = itemstack.copy(); 
 		newStack.stackSize = size; 
 		return newStack; 
 	} 

		
		


}
