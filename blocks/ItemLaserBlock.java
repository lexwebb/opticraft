package opticraft.blocks;

import java.util.HashMap;
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import opticraft.Opticraft;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import opticraft.entitys.TileEntityItemLaser;
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
            return new TileEntityItemLaser();
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
            this.blockIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "LaserIconFlat");
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
    
    public IInventory getInventory(World par1World, int par2, int par3, int par4) {
		return (TileEntityItemLaser)par1World.getBlockTileEntity(par2, par3, par4);
		
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t){
    TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

    // Checking if the TileEntity is nothing or if the player is sneaking
    if(tile_entity == null || player.isSneaking()){
    // Returns false so it doesn't update anything
    	return false;
    }
    
    player.openGui(Opticraft.instance, 0, world, x, y, z);
    // Returns true to force an update
    return true;
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int i, int j){
    	dropItems(world, x, y, z);
    	super.breakBlock(world, x, y, z, i, j);
    }
    
    private void dropItems(World world, int x, int y, int z){
    	Random rand = new Random();

    	TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

    	if(!(tile_entity instanceof IInventory)){
    		return;
    	}

    	IInventory inventory = (IInventory) tile_entity;

    	for(int i = 0; i < inventory.getSizeInventory(); i++){
    		ItemStack item = inventory.getStackInSlot(i);

    		if(item != null && item.stackSize > 0){
    			float rx = rand.nextFloat() * 0.6F + 0.1F;
    			float ry = rand.nextFloat() * 0.6F + 0.1F;
    			float rz = rand.nextFloat() * 0.6F + 0.1F;

    			EntityItem entity_item = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

//    			if(item.hasTagCompound()){
//    				entity_item.item.setTagCompound((NBTTagCompound) item.getTagCompound().copy());
//    			}

    			float factor = 0.5F;

    			entity_item.motionX = rand.nextGaussian() * factor;
    			entity_item.motionY = rand.nextGaussian() * factor + 0.2F;
    			entity_item.motionZ = rand.nextGaussian() * factor;
    			world.spawnEntityInWorld(entity_item);
    			item.stackSize = 0;
    		}
    	}
    }
}
