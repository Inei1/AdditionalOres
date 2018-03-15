package inei.additionalores.handler;

import inei.additionalores.AdditionalOres;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler{

	@Override
	public int getBurnTime(ItemStack fuel) {

		if (fuel.getItem() == AdditionalOres.itemCoalium) return 3200;
		
		return 0;
	}

}
