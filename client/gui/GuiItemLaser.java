package opticraft.client.gui;

import org.lwjgl.opengl.GL11;

import opticraft.blocks.containers.ItemLaserContainer;
import opticraft.energy.LuxContainerTileEntity;
import opticraft.entitys.TileEntityItemLaser;
import opticraft.lib.ModInfo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiItemLaser extends GuiContainer{
	
	LuxContainerTileEntity ent;

	public GuiItemLaser(InventoryPlayer inv, TileEntityItemLaser ent) {
		super(new ItemLaserContainer(ent, inv));
		this.ent = (LuxContainerTileEntity) ent;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Item Laser", 6, 6, 0xffffff);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 6, 50, 0xffffff);
		fontRenderer.drawString(String.valueOf(ent.lux.get()), 140, 10, 0xffffff);
		int amount = (int) Math.floor((50 - ((30.0/ent.maxCharge) * ent.lux.get())));
		drawRect(140,50, 160, amount, 0xFFFE6800);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {		
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/gui/InventoryLaser.png"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
