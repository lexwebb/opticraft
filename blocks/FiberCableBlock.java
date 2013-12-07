package opticraft.blocks;

import java.util.HashMap;
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import opticraft.Opticraft;
import opticraft.lib.DirectionalBlock;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import opticraft.entitys.TileEntityFiberCable;
import opticraft.entitys.TileEntityLaser;
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
import net.minecraftforge.event.ForgeSubscribe;

public class FiberCableBlock extends DirectionalBlock{
	
	protected int renderType;	

	//Treat it like a normal block here. The Block Bounds are a good idea - the first three are X Y and Z of the botton-left corner,
    //And the second three are the top-right corner.
    public FiberCableBlock(int id) {
            super(id, Material.iron, true, false);
            this.renderType = id;
            this.setCreativeTab(CreativeTabs.tabBlock);
            this.setBlockBounds(0.4F, 0.4F, 0.4F, 0.6F, 0.6F, 0.6F);
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z){
    	TileEntityFiberCable ent = (TileEntityFiberCable) blockAccess.getBlockTileEntity(x, y, z);
    	
    	float xMin = 0, yMin = 0, zMin = 0, xMax = 0, yMax = 0, zMax = 0;
    	
    	if(!ent.upHidden)
    		yMax = 0.4F;
    	if(!ent.downHidden)
    		yMin = -0.4F;
    	if(!ent.eastHidden)
    		xMax = 0.4F;
    	if(!ent.westHidden)
    		xMin = -0.4F;
    	if(!ent.northHidden)
    		zMin = -0.4F;
    	if(!ent.southHidden)
    		zMax = 0.4F;
    	
    	this.setBlockBounds(0.4F + xMin, 0.4F + yMin, 0.4F + zMin, 0.6F + xMax, 0.6F + yMax, 0.6F + zMax);
    }
    

    //Make sure you set this as your TileEntity class relevant for the block!
    @Override
    public TileEntity createNewTileEntity(World world) {  		
            return new TileEntityFiberCable();
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
    
}
