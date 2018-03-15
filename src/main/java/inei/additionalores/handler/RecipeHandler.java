package inei.additionalores.handler;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public final class RecipeHandler{
	
	public static void addRecipe(Recipe recipe, Object input, Object output){	
		recipe.put(input, output);
	}
	


	
	public static enum Recipe{
	Ore_Compressor(new HashMap<ItemStack, ItemStack>());
	
	private HashMap recipes;
	
	private Recipe(HashMap map){
		recipes = map;
	}
	
	public void put(Object input, Object output){
		recipes.put(input, output);
	}
	
	public HashMap get(){
		return recipes;
	}
	public static void addOreCompressorRecipe(ItemStack input, ItemStack output){
		Recipe.Ore_Compressor.put(input, output);
	}
	}

	

}
