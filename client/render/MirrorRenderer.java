package opticraft.client.render;

import opticraft.client.models.CableModel;
import opticraft.client.models.FiberCableModel;
import opticraft.client.models.MirrorModel;
import opticraft.client.models.SolarCollectorModel;
import opticraft.entitys.TileEntityFiberCable;
import opticraft.entitys.TileEntityLaser;
import opticraft.entitys.TileEntityMirror;
import opticraft.entitys.TileEntityRedstoneLaser;
import opticraft.lib.ModInfo;
import opticraft.lib.Names;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class MirrorRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	private final MirrorModel model;
	protected int renderID;

	public boolean showDown = true;
	public boolean showUp = true;
	public boolean showBack = true;
	public boolean showFront = true;
	public boolean showLeft = true;
	public boolean showRight = true;

	public MirrorRenderer(int renderID) {
		this.model = new MirrorModel();
		this.renderID = renderID;
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		GL11.glPushMatrix();
		GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {

		TileEntityMirror ent = (TileEntityMirror) te;
		ForgeDirection or = ent.getOrientation();
		ForgeDirection direction = ent.getDirection();

		if(or == ForgeDirection.DOWN || direction == ForgeDirection.DOWN)
			showDown = false;
		else
			showDown = true;
		if(or == ForgeDirection.UP || direction == ForgeDirection.UP)
			showUp = false;
		else
			showUp = true;
		if(or == ForgeDirection.SOUTH || direction == ForgeDirection.SOUTH)
			showBack = false;
		else
			showBack = true;
		if(or == ForgeDirection.NORTH || direction == ForgeDirection.NORTH)
			showFront = false;
		else
			showFront = true;
		if(or == ForgeDirection.WEST || direction == ForgeDirection.WEST)
			showLeft = false;
		else
			showLeft = true;
		if(or == ForgeDirection.EAST || direction == ForgeDirection.EAST)
			showRight = false;
		else
			showRight = true;

		ResourceLocation textures = (new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/blocks/mirrorTile.png"));
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);

		// -----------------------------------------------

		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

		GL11.glPushMatrix();

		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		
		
		if(or != null && direction != null) //Error Stopper
		switch (or) {
		case NORTH:
			switch (direction) {
			case UP:
				break;
			case EAST:
				GL11.glRotatef(270F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-1f, -1f, 0f);
				break;
			case DOWN:
				GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0f, -2f, 0f);
				break;
			case WEST:
				GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(1f, -1f, 0f);
				break;
			default:
				break;
			}
			break;
		case EAST:
			GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
			switch (direction) {
			case UP:
				break;
			case NORTH:				
				GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(1f, -1f, 0f);
				break;
			case DOWN:				
				GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0f, -1f, -1f);
				break;
			case SOUTH:
				GL11.glRotatef(270F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-1f, -1f, 0f);
				break;
			default:
				break;
			}
			break;
		case SOUTH:
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			switch (direction) {
			case UP:
				break;
			case EAST:				
				GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(1f, -1f, 0f);
				break;
			case DOWN:
				GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0f, -2f, 0f);
				break;
			case WEST:
				GL11.glRotatef(270F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-1f, -1f, 0f);
				break;
			default:
				break;
			}
			break;
		case WEST:
			GL11.glRotatef(270F, 0.0F, 1.0F, 0.0F);
			switch (direction) {
			case UP:
				break;
			case NORTH:
				GL11.glRotatef(270F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-1f, -1f, 0f);				
				break;
			case DOWN:				
				GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0f, -1f, -1f);
				break;
			case SOUTH:
				GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(1f, -1f,0f);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}

		this.model.mirrorRender((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
		GL11.glPopMatrix();

		// ------------------------------

		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

		GL11.glPushMatrix();

		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		if (showUp && ent.getWorldObj().getBlockMaterial(ent.xCoord, ent.yCoord + 1, ent.zCoord).isSolid())
			this.model.renderUp((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if (showDown && ent.getWorldObj().getBlockMaterial(ent.xCoord, ent.yCoord - 1, ent.zCoord).isSolid())
			this.model.renderDown((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if (showLeft && ent.getWorldObj().getBlockMaterial(ent.xCoord - 1, ent.yCoord, ent.zCoord).isSolid())
			this.model.renderLeft((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if (showRight && ent.getWorldObj().getBlockMaterial(ent.xCoord + 1, ent.yCoord, ent.zCoord).isSolid())
			this.model.renderRight((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if (showFront && ent.getWorldObj().getBlockMaterial(ent.xCoord, ent.yCoord, ent.zCoord - 1).isSolid())
			this.model.renderFront((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if (showBack && ent.getWorldObj().getBlockMaterial(ent.xCoord, ent.yCoord, ent.zCoord + 1).isSolid())
			this.model.renderBack((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
		GL11.glPopMatrix();
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

		float scale = 1.5F;
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(0.0f, 1.0f, 0.0f);

		ResourceLocation textures = (new ResourceLocation(ModInfo.ID.toLowerCase() + ":textures/blocks/mirrorTile.png"));
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);

		GL11.glPushMatrix();

		GL11.glRotatef(180F, 0.0F, 0.0F, 0.0F);

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_FRONT);
		this.model.mirrorRender((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		this.model.renderBack((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		this.model.renderLeft((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		this.model.renderRight((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		this.model.renderDown((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glDisable(GL11.GL_CULL_FACE);

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
