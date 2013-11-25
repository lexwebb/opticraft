package opticraft.items;

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

public class ItemMatterCrystal extends Item{

	public ItemMatterCrystal(int par1) {
		super(par1);
		setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setUnlocalizedName(Names.matterCrystal_u);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		itemIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "matterCrystal");
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase target) {

		return false;
	}
	
//	@Override
//	  public int getSpriteNumber() {
//	    return 0;
//	  }
}
