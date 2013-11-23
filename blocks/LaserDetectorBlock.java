package opticraft.blocks;

import java.util.HashMap;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import opticraft.lib.DirectionalBlock;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;
import opticraft.entitys.TileEntityItemLaser;
import opticraft.entitys.TileEntityLaserDetector;
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
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;

public class LaserDetectorBlock extends DirectionalBlock{

	//Treat it like a normal block here. The Block Bounds are a good idea - the first three are X Y and Z of the botton-left corner,
    //And the second three are the top-right corner.
    public LaserDetectorBlock(int id) {
            super(id, Material.iron, false);
            this.setCreativeTab(CreativeTabs.tabBlock);
            this.setBlockBounds(0F, 0.0F, 0F, 1f, 1F, 1F);
    }

    //Make sure you set this as your TileEntity class relevant for the block!
    @Override
    public TileEntity createNewTileEntity(World world) {  		
            return new TileEntityLaserDetector();
    }
    
    //This is the icon to use for showing the block in your hand.
    public void registerIcons(IconRegister icon) {
            this.blockIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "LaserIconFlat");
    } 
}
