package opticraft.blocks.containers;

import opticraft.lib.Ids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCrystal extends Slot{

	public SlotCrystal(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		
	}
	
	public boolean isItemValid(ItemStack iStack) {
		switch(iStack.itemID - 256){
			case Ids.basicMatterCrystal: return true;
			case Ids.matterCrystal: return true;
			case Ids.advancedMatterCrystal: return true;
			case Ids.unatunedCrystal: return true;
			case Ids.basicUnatunedCrystal: return true;
			case Ids.advancedUnatunedCrystal: return true;
			case Ids.basicEnergyCrystal: return true;
			case Ids.energyCrystal: return true;
			case Ids.advancedEnergyCrystal: return true;
			default: return false;
		}	
    }

}
