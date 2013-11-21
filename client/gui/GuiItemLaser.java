package opticraft.client.gui;

import org.lwjgl.opengl.GL11;

import opticraft.blocks.tileentity.ItemLaserContainer;
import opticraft.blocks.tileentity.ItemLaserEntity;
import opticraft.lib.ModInfo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiItemLaser extends GuiContainer{

	public GuiItemLaser(InventoryPlayer inv, ItemLaserEntity ent) {
		super(new ItemLaserContainer(ent, inv));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Item Laser", 6, 6, 0xffffff);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 6, ySize - 96 + 2, 0xffffff);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {		
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/gui/ItemLaserInventory"));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}