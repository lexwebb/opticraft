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

public class FiberCableBlock extends DirectionalBlock{

	//Treat it like a normal block here. The Block Bounds are a good idea - the first three are X Y and Z of the botton-left corner,
    //And the second three are the top-right corner.
    public FiberCableBlock(int id) {
            super(id, Material.iron, true, false);
            this.setCreativeTab(CreativeTabs.tabBlock);
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1.0F, 1F);
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
    
}
