package opticraft.client.render;

import opticraft.client.models.ColliderPipeModel;
import opticraft.client.models.LaserDetectorModel;
import opticraft.client.models.LaserModel;
import opticraft.client.models.PrinterModel;
import opticraft.entitys.TileEntityColliderPipe;
import opticraft.entitys.TileEntityLaserDetector;
import opticraft.entitys.TileEntityPrinter;
import opticraft.items.ItemBasicMatterCrystal;
import opticraft.items.ItemEnergyCrystal;
import opticraft.items.Items;
import opticraft.lib.Ids;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.common.ForgeDirection;

public class PrinterRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	private final PrinterModel model;
	protected int renderID;
	float laserAnim;
	boolean positive;
	boolean animatingLaser;
	EntityItem entItem;

	public PrinterRenderer(int renderID) {
		this.model = new PrinterModel();
		this.renderID = renderID;
		laserAnim = 0.01f;
		positive = true;
		animatingLaser = true;
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		GL11.glPushMatrix();
		GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {

		TileEntityPrinter ent = (TileEntityPrinter) te;
		ForgeDirection or = ent.getOrientation();

		// ------------RenderItem
		if (entItem == null) {
			entItem = new EntityItem(te.worldObj);
			entItem.setEntityItemStack(new ItemStack(Items.energyCrystal));
			entItem.rotationYaw = 0;
		}

		entItem.age = 0;
		entItem.rotationYaw = 0;
		entItem.rotationPitch = 0;

		GL11.glPushMatrix();
		GL11.glScalef(0.8f, 0.8f, 0.8f);
		GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);

		RenderManager.instance.renderEntityWithPosYaw(entItem, 0, 0, 0, 0, 0);
		GL11.glPopMatrix();
		// --------------

		// The PushMatrix tells the renderer to "start" doing something.
		GL11.glPushMatrix();
		// This is setting the initial location.

		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

		// This is the texture of your block. It's pathed to be the same place
		// as your other blocks here.
		// Outdated
		// bindTextureByName("/mods/roads/textures/blocks/TrafficLightPoleRed.png");
		// Use in 1.6.2 this
		ResourceLocation textures = (new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/blocks/printerTile.png"));
		// the ':' is very important
		// binding the textures
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);

		// This rotation part is very important! Without it, your model will
		// render upside-down! And for some reason you DO need PushMatrix again!
		GL11.glPushMatrix();

		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

		if (or == ForgeDirection.EAST)
			GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
		else if (or == ForgeDirection.WEST)
			GL11.glRotatef(270F, 0.0F, 1.00F, 0.0F);
		else if (or == ForgeDirection.SOUTH)
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);

		// A reference to your Model file. Again, very important.
		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		this.model.renderPlatform((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		if (or == ForgeDirection.EAST)
			GL11.glTranslatef((float) 0, (float) 0, (float) laserAnim);
		else if (or == ForgeDirection.WEST)
			GL11.glTranslatef((float) 0, (float) 0, (float) laserAnim);
		else if (or == ForgeDirection.NORTH)
			GL11.glTranslatef((float) laserAnim, (float) 0, (float) 0);
		else if (or == ForgeDirection.SOUTH)
			GL11.glTranslatef((float) laserAnim, (float) 0, (float) 0);

		this.model.renderLaser((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		if (animatingLaser) {
			if (laserAnim > 0.2f)
				positive = false;
			else if (laserAnim < -0.2f)
				positive = true;

			if (positive)
				laserAnim = laserAnim + 0.0005f;
			else
				laserAnim = laserAnim - 0.0005f;
		}

		// Tell it to stop rendering for both the PushMatrix's
		GL11.glPopMatrix();
		GL11.glPopMatrix();

	}

	public void startLaser() {
		laserAnim = 0.00001f;
		animatingLaser = true;
	}

	public void stopLaser() {
		laserAnim = 0.0f;
		animatingLaser = false;
	}

	// Set the lighting stuff, so it changes it's brightness properly.
	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
		Tessellator tess = Tessellator.instance;
		float brightness = block.getBlockBrightness(world, i, j, k);
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		GL11.glPushMatrix();

		GL11.glTranslatef(0.0f, 1.4f, 0.0f);

		ResourceLocation textures = (new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/blocks/printerTile.png"));
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);

		GL11.glPushMatrix();

		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		return this.renderID;
	}
}
