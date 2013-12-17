package opticraft.blocks;

import java.util.HashMap;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import opticraft.lib.DirectionalBlock;
import opticraft.lib.Ids;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import opticraft.entitys.TileEntityFiberCable;
import opticraft.entitys.TileEntityLaser;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntityMirror;
import opticraft.entitys.TileEntityRedstoneLaser;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;

public class MirrorBlock extends DirectionalBlock{
	
	protected int renderType;

	//Treat it like a normal block here. The Block Bounds are a good idea - the first three are X Y and Z of the botton-left corner,
    //And the second three are the top-right corner.
    public MirrorBlock(int id) {
            super(id, Material.iron, true, true);
            this.renderType = id;
            this.setCreativeTab(CreativeTabs.tabBlock);
//            this.setBlockBounds(0F, 0.0F, 0F, 1f, 1F, 1F);
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z){
//    	TileEntityLaserDetector ent = (TileEntityLaserDetector) blockAccess.getBlockTileEntity(x, y, z);
//    	
//    	if(ent.getOrientation() == "U"){
//    		this.setBlockBounds(0F, 0.0F, 0F, 1f, 0.2F, 1F);
//    	} else if(ent.getOrientation() == "D"){
//    		this.setBlockBounds(0F, 0.8F, 0F, 1f, 1F, 1F);
//    	} else if(ent.getOrientation() == "E"){
//    		this.setBlockBounds(0F, 0F, 0F, 0.2f, 1F, 1F);
//    	} else if(ent.getOrientation() == "W"){
//    		this.setBlockBounds(0.8F, 0F, 0F, 1f, 1F, 1F);
//    	} else if(ent.getOrientation() == "N"){
//    		this.setBlockBounds(0F, 0F, 0.8F, 1f, 1F, 1F);
//    	} else if(ent.getOrientation() == "S"){
//    		this.setBlockBounds(0F, 0F, 0F, 1f, 1F, 0.2F);
//    	}
    }

    //Make sure you set this as your TileEntity class relevant for the block!
    @Override
    public TileEntity createNewTileEntity(World world) {  		
            return new TileEntityMirror();
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
    public void onPostBlockPlaced(World par1World, int x, int y, int z, int par5) {
    	super.onPostBlockPlaced(par1World, x, y, z, par5);
    	((TileEntityMirror) par1World.getBlockTileEntity(x, y, z)).direction = ForgeDirection.UP;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t){
    	if(player.inventory.getStackInSlot(player.inventory.currentItem) != null 
    			&& player.inventory.getStackInSlot(player.inventory.currentItem).getItem().itemID - 256 == Ids.laserWrench){
    		
    		TileEntityMirror ent = (TileEntityMirror) world.getBlockTileEntity(x, y, z);
    		
    		if(ent.getOrientation() == ForgeDirection.UP || ent.getOrientation() == ForgeDirection.DOWN){
    			switch(ent.getDirection()){
	    			case NORTH : ent.setDirection(ForgeDirection.EAST); break;
	    			case EAST : ent.setDirection(ForgeDirection.SOUTH); break;
	    			case SOUTH : ent.setDirection(ForgeDirection.WEST); break;
	    			case WEST : ent.setDirection(ForgeDirection.NORTH); break;
	    			default: break;
    			}
    		} else if(ent.getOrientation() == ForgeDirection.EAST || ent.getOrientation() == ForgeDirection.WEST){
    			switch(ent.getDirection()){
	    			case NORTH : ent.setDirection(ForgeDirection.UP); break;
	    			case UP : ent.setDirection(ForgeDirection.SOUTH); break;
	    			case SOUTH : ent.setDirection(ForgeDirection.DOWN); break;
	    			case DOWN : ent.setDirection(ForgeDirection.NORTH); break;
	    			default: break;
    			}
    		} else if(ent.getOrientation() == ForgeDirection.NORTH || ent.getOrientation() == ForgeDirection.SOUTH){
    			switch(ent.getDirection()){
	    			case EAST : ent.setDirection(ForgeDirection.UP); break;
	    			case UP : ent.setDirection(ForgeDirection.WEST); break;
	    			case WEST : ent.setDirection(ForgeDirection.DOWN); break;
	    			case DOWN : ent.setDirection(ForgeDirection.EAST); break;
	    			default: break;
				}
			}
    		return false;
    	} else {
    		return true;
    	}
    }
}
