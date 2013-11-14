package opticraft.blocks.tileentity;

import java.util.HashMap;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import opticraft.blocks.tileentity.ItemLaserEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class ItemLaserBlock extends BlockContainer{
	
	private static HashMap<String, Integer> entList = new HashMap<String, Integer>();

	//Treat it like a normal block here. The Block Bounds are a good idea - the first three are X Y and Z of the botton-left corner,
    //And the second three are the top-right corner.
    public ItemLaserBlock(int id) {
            super(id, Material.iron);
            this.setCreativeTab(CreativeTabs.tabBlock);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1.0F, 1F);
    }

    //Make sure you set this as your TileEntity class relevant for the block!
    @Override
    public TileEntity createNewTileEntity(World world) {  		
            return new ItemLaserEntity();
    }
    
    //You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
            return -1;
    }
    
    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() {
            return false;
    }
    
    //It's not a normal block, so you need this too.
    public boolean renderAsNormalBlock() {
            return false;
    }
    
    //This is the icon to use for showing the block in your hand.
    public void registerIcons(IconRegister icon) {
            this.blockIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + Names.itemLaserTile_unlocalizedName);
    } 
    
    @Override
    public int onBlockPlaced(World par1World, int x, int y, int z, int side, float par6, float par7, float par8, int par9){
    	entList.put(String.valueOf(x) + String.valueOf(y) + String.valueOf(z), side);
    	return 0;
    }
    
    @Override
    public void onPostBlockPlaced(World par1World, int x, int y, int z, int par5) {
    	System.out.println("SIDE = " + String.valueOf(par5));
    	par1World.setBlockMetadataWithNotify(x, y, z, entList.get(String.valueOf(x) + String.valueOf(y) + String.valueOf(z)), 0);
    	entList.remove(String.valueOf(x) + String.valueOf(y) + String.valueOf(z));
    }
}
