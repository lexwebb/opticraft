package opticraft.blocks;

import java.util.HashMap;

import cpw.mods.fml.common.FMLCommonHandler;
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
import net.minecraft.block.Block;
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

public class MirrorBlock extends BlockContainer{
	
	protected int renderType;

	//Treat it like a normal block here. The Block Bounds are a good idea - the first three are X Y and Z of the botton-left corner,
    //And the second three are the top-right corner.
    public MirrorBlock(int id) {
            super(id, Material.iron);
            this.renderType = id;
            this.setCreativeTab(CreativeTabs.tabBlock);
//            this.setBlockBounds(0F, 0.0F, 0F, 1f, 1F, 1F);
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
    
    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() {
            return false;
    }
    
    //It's not a normal block, so you need this too.
    public boolean renderAsNormalBlock() {
            return false;
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
    	((TileEntityMirror) par1World.getBlockTileEntity(x, y, z)).orientation = ForgeDirection.NORTH;
    	((TileEntityMirror) par1World.getBlockTileEntity(x, y, z)).direction = ForgeDirection.UP;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t){
    	if(player.inventory.getStackInSlot(player.inventory.currentItem) != null 
    			&& player.inventory.getStackInSlot(player.inventory.currentItem).getItem().itemID - 256 == Ids.laserWrench){
    		
    		TileEntityMirror ent = (TileEntityMirror) world.getBlockTileEntity(x, y, z);
    		
    		if(player.isSneaking()){
    			if(ent.getOrientation() == ForgeDirection.NORTH){

	    			if(ent.getDirection() == ForgeDirection.UP)ent.setDirection(ForgeDirection.EAST);
	    			else if(ent.getDirection() == ForgeDirection.EAST) ent.setDirection(ForgeDirection.DOWN);
	    			else if(ent.getDirection() == ForgeDirection.DOWN) ent.setDirection(ForgeDirection.WEST);
	    			else if(ent.getDirection() == ForgeDirection.WEST) ent.setDirection(ForgeDirection.UP);
	    			
    			} else if(ent.getOrientation() == ForgeDirection.EAST){
    				
    				if(ent.getDirection() == ForgeDirection.UP)ent.setDirection(ForgeDirection.NORTH);
	    			else if(ent.getDirection() == ForgeDirection.NORTH) ent.setDirection(ForgeDirection.DOWN);
	    			else if(ent.getDirection() == ForgeDirection.DOWN) ent.setDirection(ForgeDirection.SOUTH);
	    			else if(ent.getDirection() == ForgeDirection.SOUTH) ent.setDirection(ForgeDirection.UP);
    				
    			} else if(ent.getOrientation() == ForgeDirection.SOUTH){
    				
    				if(ent.getDirection() == ForgeDirection.UP)ent.setDirection(ForgeDirection.WEST);
	    			else if(ent.getDirection() == ForgeDirection.WEST) ent.setDirection(ForgeDirection.DOWN);
	    			else if(ent.getDirection() == ForgeDirection.DOWN) ent.setDirection(ForgeDirection.EAST);
	    			else if(ent.getDirection() == ForgeDirection.EAST) ent.setDirection(ForgeDirection.UP);
    				
    			} else if(ent.getOrientation() == ForgeDirection.WEST){
    				
    				if(ent.getDirection() == ForgeDirection.UP)ent.setDirection(ForgeDirection.SOUTH);
	    			else if(ent.getDirection() == ForgeDirection.SOUTH) ent.setDirection(ForgeDirection.DOWN);
	    			else if(ent.getDirection() == ForgeDirection.DOWN) ent.setDirection(ForgeDirection.NORTH);
	    			else if(ent.getDirection() == ForgeDirection.NORTH) ent.setDirection(ForgeDirection.UP);
    				
    			}  	
    			
    			if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && ent.getDirection() != null)
        			System.out.println("Dir: " + ent.getDirection().toString());
    			
    		} else {
    			if(ent.getOrientation() == ForgeDirection.NORTH)
    				ent.setOrientation(ForgeDirection.EAST);
    			else if(ent.getOrientation() == ForgeDirection.EAST)
    				ent.setOrientation(ForgeDirection.SOUTH);
    			else if(ent.getOrientation() == ForgeDirection.SOUTH)
    				ent.setOrientation(ForgeDirection.WEST);
    			else if(ent.getOrientation() == ForgeDirection.WEST)
    				ent.setOrientation(ForgeDirection.NORTH);
    			ent.setDirection(ForgeDirection.UP);
    			
    			if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && ent.getOrientation() != null)
        			System.out.println("Or: " + ent.getOrientation().toString());
    		}

    		return false;
    	} else {
    		return true;
    	}
    }
}
