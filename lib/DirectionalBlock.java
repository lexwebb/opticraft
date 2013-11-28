package opticraft.lib;

import java.util.HashMap;
import java.util.Random;

import opticraft.Opticraft;
import opticraft.entitys.TileEntityItemLaser;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class DirectionalBlock extends BlockContainer{
	
	private static HashMap<String, Integer> entList = new HashMap<String, Integer>();
	
	public boolean isDirectional;
	public boolean canFloat;

	/**
     * Instantiates Direction Block. Parameters: id, Material, canFloat
     */
	protected DirectionalBlock(int par1, Material par2Material, boolean canFloat, boolean isDirectional) {
		super(par1, par2Material);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1.0F, 1F);
        this.canFloat = canFloat;
        this.isDirectional = true;
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

    @Override
    public int onBlockPlaced(World par1World, int x, int y, int z, int side, float par6, float par7, float par8, int par9){
    	entList.put(String.valueOf(x) + String.valueOf(y) + String.valueOf(z), side);
    	return 0;
    }
    
    @Override
    public void onPostBlockPlaced(World par1World, int x, int y, int z, int par5) {
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
    	if(tile_entity == null || player.isSneaking() || !(tile_entity instanceof IInventory)){
    		// Returns false so it doesn't update anything   	
    		return false;
    	}

    	player.openGui(Opticraft.instance, 0, world, x, y, z);
    	// Returns true to force an update
    	return true;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int par5){
    	if(isDirectional && !canFloat){
	    	DirectionalTileEntity tile_entity = (DirectionalTileEntity) world.getBlockTileEntity(x, y, z);	
	    	boolean canStay = true;;  	
	    	String orientation = tile_entity.getOrientation();
	    	
	    	if(orientation == "U"){
				if(!world.getBlockMaterial(x, y - 1, z).isSolid())
					canStay = false;			
			} else if(orientation == "D"){
				if(!world.getBlockMaterial(x, y + 1, z).isSolid())
					canStay = false;	
			} else if(orientation == "N"){
				if(!world.getBlockMaterial(x, y, z + 1).isSolid())
					canStay = false;	
			} else if(orientation == "S"){
				if(!world.getBlockMaterial(x, y, z - 1).isSolid())
					canStay = false;	
			} else if(orientation == "W"){
				if(!world.getBlockMaterial(x + 1, y, z).isSolid())
					canStay = false;	
			} else if(orientation == "E"){
				if(!world.getBlockMaterial(x - 1, y, z).isSolid())
					canStay = false;	
			}
			
	    	if(!canStay)
	    		world.destroyBlock(x, y, z, true);
    	}
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

    			float factor = 0.1F;

    			entity_item.motionX = rand.nextGaussian() * factor;
    			entity_item.motionY = rand.nextGaussian() * factor + 0.2F;
    			entity_item.motionZ = rand.nextGaussian() * factor;
    			world.spawnEntityInWorld(entity_item);
    			item.stackSize = 0;
    		}
    	}
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return null;
	}
}
