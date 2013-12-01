package opticraft.blocks;

import java.util.Random;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import opticraft.entitys.TileEntitySolarCollector;
import opticraft.lib.DirectionalBlock;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SolarCollectorBlock extends DirectionalBlock{
	
	long gameTime;
	protected int renderType;

	//Treat it like a normal block here. The Block Bounds are a good idea - the first three are X Y and Z of the botton-left corner,
    //And the second three are the top-right corner.
    public SolarCollectorBlock(int id) {
            super(id, Material.iron, true, false);
            this.renderType = id;
            this.setCreativeTab(CreativeTabs.tabBlock);
            this.setBlockBounds(0F, 0.0F, 0F, 1f, 0.4F, 1F);
    }

    //Make sure you set this as your TileEntity class relevant for the block!
    @Override
    public TileEntity createNewTileEntity(World world) {
            return new TileEntitySolarCollector();
    }
    
    //This is the icon to use for showing the block in your hand.
    public void registerIcons(IconRegister icon) {
            this.blockIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "SolarIcon");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateTick(World world, int x, int y, int z, Random random){
    	gameTime = ModLoader.getMinecraftInstance().theWorld.getWorldTime();
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
