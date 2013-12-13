package opticraft.blocks;

import java.util.HashMap;
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import opticraft.Opticraft;
import opticraft.items.Items;
import opticraft.lib.DirectionalBlock;
import opticraft.lib.Ids;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import opticraft.entitys.TileEntityLaser;
import opticraft.entitys.TileEntityRedstoneLaser;
import net.minecraft.block.Block;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;

public class RedstoneLaserBlock extends DirectionalBlock{
	
	private static HashMap<String, Integer> entList = new HashMap<String, Integer>();
	protected int renderType;	
	boolean powered;

	//Treat it like a normal block here. The Block Bounds are a good idea - the first three are X Y and Z of the botton-left corner,
    //And the second three are the top-right corner.
    public RedstoneLaserBlock(int id) {
            super(id, Material.iron, false, true);
            this.renderType = id;
            this.setTickRandomly(true);
            this.setCreativeTab(CreativeTabs.tabBlock);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1.0F, 1F);
    }

    //Make sure you set this as your TileEntity class relevant for the block!
    @Override
    public TileEntity createNewTileEntity(World world) {  		
            return new TileEntityRedstoneLaser();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t){
    	if(player.inventory.getStackInSlot(player.inventory.currentItem) != null 
    			&& player.inventory.getStackInSlot(player.inventory.currentItem).getItem().itemID - 256 == Ids.laserWrench){
    		
    		TileEntityRedstoneLaser ent = (TileEntityRedstoneLaser) world.getBlockTileEntity(x, y, z);
    		
    		if(ent.getOrientation() == "U" || ent.getOrientation() == "D"){
    			switch(ent.getDirection()){
	    			case NORTH : ent.setDirection(ForgeDirection.EAST); break;
	    			case EAST : ent.setDirection(ForgeDirection.SOUTH); break;
	    			case SOUTH : ent.setDirection(ForgeDirection.WEST); break;
	    			case WEST : ent.setDirection(ForgeDirection.NORTH); break;
	    			default: break;
    			}
    		} else if(ent.getOrientation() == "E" || ent.getOrientation() == "W"){
    			switch(ent.getDirection()){
	    			case NORTH : ent.setDirection(ForgeDirection.UP); break;
	    			case UP : ent.setDirection(ForgeDirection.SOUTH); break;
	    			case SOUTH : ent.setDirection(ForgeDirection.DOWN); break;
	    			case DOWN : ent.setDirection(ForgeDirection.NORTH); break;
	    			default: break;
    			}
    		} else if(ent.getOrientation() == "N" || ent.getOrientation() == "S"){
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
    		TileEntityRedstoneLaser ent = (TileEntityRedstoneLaser) world.getBlockTileEntity(x, y, z);
    		if(ent.reciever){
    			ent.reciever = false;
    			ent.providedPower = 0;
    			ent.recievedPower = 0;
    		}else
    			ent.reciever = true;
    	}
    	return true;
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
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side){
    	return true;
    }
    
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {  	
        return ((TileEntityRedstoneLaser) par1IBlockAccess.getBlockTileEntity(x, y, z)).providedPower;
    }
    
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        return ((TileEntityRedstoneLaser) par1IBlockAccess.getBlockTileEntity(x, y, z)).providedPower;
    }
    
    public boolean isPoweringTo(World par1World, int par2, int par3, int par4, int par5)
    {
             return true;
    }
    
    @Override
    public boolean canProvidePower()
    {
        return true;
    }
    
    @Override
    public void updateTick(World par1World, int x, int y, int z, Random par5Random) {
    	par1World.notifyBlocksOfNeighborChange(x, y + 1, z, this.blockID);
    	par1World.notifyBlocksOfNeighborChange(x, y - 1, z, this.blockID);
    	par1World.notifyBlocksOfNeighborChange(x + 1, y, z, this.blockID);
    	par1World.notifyBlocksOfNeighborChange(x - 1, y, z, this.blockID);
    	par1World.notifyBlocksOfNeighborChange(x, y, z + 1, this.blockID);
    	par1World.notifyBlocksOfNeighborChange(x, y, z - 1, this.blockID);
    	
    }
    
    @Override
    public int tickRate(World par1World)
    {
        return 2;
    }  
}
