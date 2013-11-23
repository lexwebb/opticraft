package opticraft.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import opticraft.lib.Ids;
import opticraft.lib.Names;

public class Items {
	public static Item item;

	public static void init() {
		item = new ItemLaserWrench(Ids.laserWrench);
		System.out.println(String.valueOf(item.itemID));
	}
	
	public static void addNames() {
		LanguageRegistry.addName(item, Names.laserRench_name);
	}
}
