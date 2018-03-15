package inei.additionalores.handler;

import java.util.Set;

import inei.additionalores.handler.RecipeHandler.Recipe;

public class OreCompressorRecipeHandler {
	
public Set getRecipes(){
	return Recipe.Ore_Compressor.get().entrySet();
}

}
