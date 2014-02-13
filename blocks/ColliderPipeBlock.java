package opticraft.blocks;

import java.util.HashMap;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import opticraft.Opticraft;
import opticraft.lib.DirectionalBlock;
import opticraft.lib.Ids;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import opticraft.entitys.TileEntityColliderPipe;
import opticraft.entitys.TileEntityLaser;
import opticraft.entitys.TileEntityMirror;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;

public class ColliderPipeBlock extends DirectionalBlock{
	
	private static HashMap<String, Integer> entList = new HashMap<String, Integer>();
	protected int renderType;	

	//Treat it like a normal block here. The Block Bounds are a good idea - the first three are X Y and Z of the botton-left corner,
    //And the second three are the top-right corner.
    public ColliderPipeBlock(int id) {
            super(id, Material.iron, false, true);
            this.renderType = id;
            this.setCreativeTab(CreativeTabs.tabBlock);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1.0F, 1F);
            this.canFloat = true;
    }

    //Make sure you set this as your TileEntity class relevant for the block!
    @Override
    public TileEntity createNewTileEntity(World world) {  		
            return new TileEntityColliderPipe();
    }
    
    //This is the icon to use for showing the block in your hand.
    public void registerIcons(IconRegister icon) {
            this.blockIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "LaserIconFlat");
    }
    
    @Override
    public int getRenderType()
    {
        return renderType;
    }
    
    public void setRenderType(int id){
    	this.renderType = id;
    }
    
    @Override
    public int onBlockPlaced(World par1World, int x, int y, int z, int side, float par6, float par7, float par8, int par9){
    	entList.put(String.valueOf(x) + String.valueOf(y) + String.valueOf(z), 2);
    	return 0;
    }
    
    @Override
    public void onPostBlockPlaced(World par1World, int x, int y, int z, int par5) {
    	par1World.setBlockMetadataWithNotify(x, y, z, entList.get(String.valueOf(x) + String.valueOf(y) + String.valueOf(z)), 0);
    	entList.remove(String.valueOf(x) + String.valueOf(y) + String.valueOf(z));
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t){
    	if(player.inventory.getStackInSlot(player.inventory.currentItem) != null 
    			&& player.inventory.getStackInSlot(player.inventory.currentItem).getItem().itemID - 256 == Ids.laserWrench){
    		
    		TileEntityColliderPipe ent = (TileEntityColliderPipe) world.getBlockTileEntity(x, y, z);
    		
    			if(ent.getOrientation() == ForgeDirection.NORTH){
    				world.setBlockMetadataWithNotify(x, y, z, 5, 0);
    				ent.setOrientation(ForgeDirection.EAST);
    			}
    			else if(ent.getOrientation() == ForgeDirection.EAST){
    				world.setBlockMetadataWithNotify(x, y, z, 3, 0);
    				ent.setOrientation(ForgeDirection.SOUTH);
    			}
    			else if(ent.getOrientation() == ForgeDirection.SOUTH){
    				world.setBlockMetadataWithNotify(x, y, z, 4, 0);
    				ent.setOrientation(ForgeDirection.WEST);
				}
    			else if(ent.getOrientation() == ForgeDirection.WEST){
    				world.setBlockMetadataWithNotify(x, y, z, 2, 0);
    			ent.setOrientation(ForgeDirection.NORTH);
				}
    			
    			if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && ent.getOrientation() != null)
        			System.out.println("Or: " + ent.getOrientation().toString());

    		return false;
    	} else {
    		return true;
    	}
    }
}
