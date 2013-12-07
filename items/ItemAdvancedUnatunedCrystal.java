package opticraft.items;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import opticraft.lib.Ids;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAdvancedUnatunedCrystal extends Item{

	public ItemAdvancedUnatunedCrystal(int par1) {
		super(par1);
		setMaxStackSize(16);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName(Names.advancedUnatunedCrystal_u);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		itemIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "advancedUnatunedCrystal");
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase target) {

		return false;
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Beam rate: 0.2s");
	}
	
//	@Override
//	  public int getSpriteNumber() {
//	    return 0;
//	  }
}
