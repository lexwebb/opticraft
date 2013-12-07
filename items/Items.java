package opticraft.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import opticraft.blocks.Blocks;
import opticraft.blocks.LaserBlock;
import opticraft.lib.Ids;
import opticraft.lib.Names;

public class Items {
	public static Item laserWrench;
	public static Item basicUnatunedCrystal;
	public static Item unatunedCrystal;
	public static Item advancedUnatunedCrystal;
	public static Item basicMatterCrystal;
	public static Item matterCrystal;
	public static Item advancedMatterCrystal;
	

	public static void init() {
		laserWrench = new ItemLaserWrench(Ids.laserWrench);
		unatunedCrystal = new ItemUnatunedCrystal(Ids.unatunedCrystal);	
		basicUnatunedCrystal = new ItemBasicUnatunedCrystal(Ids.basicUnatunedCrystal);
		advancedUnatunedCrystal = new ItemAdvancedUnatunedCrystal(Ids.advancedUnatunedCrystal);
		matterCrystal = new ItemMatterCrystal(Ids.matterCrystal);
		basicMatterCrystal = new ItemBasicMatterCrystal(Ids.basicMatterCrystal);
		advancedMatterCrystal = new ItemAdvancedMatterCrystal(Ids.advancedMatterCrystal);
		
	}
	
	public static void addNames() {
		LanguageRegistry.addName(laserWrench, Names.laserRench_n);
		LanguageRegistry.addName(unatunedCrystal, Names.unatunedCrystal_n);
		LanguageRegistry.addName(matterCrystal, Names.matterCrystal_n);
		LanguageRegistry.addName(basicUnatunedCrystal, Names.basicUunatunedCrystal_n);
		LanguageRegistry.addName(advancedUnatunedCrystal, Names.advancedUunatunedCrystal_n);
		LanguageRegistry.addName(basicMatterCrystal, Names.basicMatterCrystal_n);
		LanguageRegistry.addName(advancedMatterCrystal, Names.advancedMatterCrystal_n);
	}
	
	public static void initRecipes(){
		GameRegistry.addShapedRecipe(new ItemStack(unatunedCrystal, 1), 
				"rfr", 
				"fqf",
				"rfr", 
				'r', Item.redstone, 
				'f', Item.flint, 
				'q', Item.netherQuartz);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.itemLaserTileBlock,1 ),
				" g ", 
				"iri",
				"sss", 
				's', Block.stone,
				'g', Block.glass,
				'i', Item.ingotIron,
				'r', Item.redstone);
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.laserDetectorTileBlock,1 ),
				"srs", 
				"sgs",
				"srs", 
				's', Block.stone,
				'g', Block.glass,
				'r', Item.redstone);
		GameRegistry.addShapedRecipe(new ItemStack(matterCrystal,1 ),
				"sss", 
				"sus",
				"sss", 
				's', Block.stone,
				'u', unatunedCrystal);
	}
}
