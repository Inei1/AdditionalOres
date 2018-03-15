package inei.additionalores;

import java.lang.reflect.Method;

import net.minecraft.item.ItemStack;

public class RecipeHelper {
	
	public static void addEnrichmentChamberRecipe(ItemStack input, ItemStack output) 
	 	{ 
	 		try { 
	 			Class recipeClass = Class.forName("additionalores.handler.RecipeHandler"); 
	 			Method m = recipeClass.getMethod("addOreCompressorRecipe", ItemStack.class, ItemStack.class); 
	 			m.invoke(null, input, output); 
	 		} catch(Exception e) { 
	 			System.err.println("Error: failed to add recipe " + e.getMessage()); 
	 		} 
	 	} 


}
