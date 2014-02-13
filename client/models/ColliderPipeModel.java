// Date: 04/02/2014 21:40:51
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package opticraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ColliderPipeModel extends ModelBase {
	// fields
	ModelRenderer Bottom;
	ModelRenderer Top;
	ModelRenderer Right;
	ModelRenderer Left;
	ModelRenderer LeftPannel;
	ModelRenderer RightPannel;
	ModelRenderer TopPannel;
	ModelRenderer BottomPannel;
	ModelRenderer UpRight;
	ModelRenderer UpLeft;
	ModelRenderer BottomRight;
	ModelRenderer BottomLeft;
	ModelRenderer TopFrontBar;
	ModelRenderer BottomFrontBar;
	ModelRenderer RightFrontBar;
	ModelRenderer LeftFrontBar;
	ModelRenderer RightUpFrontBarOne;
	ModelRenderer RightUpFrontBarTwo;
	ModelRenderer RightBottomFrontBarOne;
	ModelRenderer RightBottomFrontBarTwo;
	ModelRenderer LeftUpFrontBarOne;
	ModelRenderer LeftUpFrontBarTwo;
	ModelRenderer LeftBottomFrontBarOne;
	ModelRenderer LeftBottomFrontBarTwo;
	ModelRenderer UpLeftBlock;
	ModelRenderer UpRightBlock;
	ModelRenderer BottomRightBlock;
	ModelRenderer BottomLeftBlock;

	public ColliderPipeModel() {
		textureWidth = 190;
		textureHeight = 32;

		Bottom = new ModelRenderer(this, 0, 0);
		Bottom.addBox(0F, 0F, 0F, 10, 0, 16);
		Bottom.setRotationPoint(-5F, 22F, -8F);
		Bottom.setTextureSize(190, 32);
		Bottom.mirror = true;
		setRotation(Bottom, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 20, 0);
		Top.addBox(0F, 0F, 0F, 10, 0, 16);
		Top.setRotationPoint(-5F, 10F, -8F);
		Top.setTextureSize(190, 32);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		Right = new ModelRenderer(this, 0, 0);
		Right.addBox(0F, 0F, 0F, 0, 10, 16);
		Right.setRotationPoint(-6F, 11F, -8F);
		Right.setTextureSize(190, 32);
		Right.mirror = true;
		setRotation(Right, 0F, 0F, 0F);
		Left = new ModelRenderer(this, 32, 0);
		Left.addBox(0F, 0F, 0F, 0, 10, 16);
		Left.setRotationPoint(6F, 11F, -8F);
		Left.setTextureSize(190, 32);
		Left.mirror = true;
		setRotation(Left, 0F, 0F, 0F);
		LeftPannel = new ModelRenderer(this, 154, 0);
		LeftPannel.addBox(0F, 0F, 0F, 1, 6, 16);
		LeftPannel.setRotationPoint(6F, 13F, -8F);
		LeftPannel.setTextureSize(190, 32);
		LeftPannel.mirror = true;
		setRotation(LeftPannel, 0F, 0F, 0F);
		RightPannel = new ModelRenderer(this, 64, 6);
		RightPannel.addBox(0F, 0F, 0F, 1, 6, 16);
		RightPannel.setRotationPoint(-7F, 13F, -8F);
		RightPannel.setTextureSize(190, 32);
		RightPannel.mirror = true;
		setRotation(RightPannel, 0F, 0F, 0F);
		TopPannel = new ModelRenderer(this, 54, 0);
		TopPannel.addBox(0F, 0F, 0F, 6, 1, 16);
		TopPannel.setRotationPoint(-3F, 9F, -8F);
		TopPannel.setTextureSize(190, 32);
		TopPannel.mirror = true;
		setRotation(TopPannel, 0F, 0F, 0F);
		BottomPannel = new ModelRenderer(this, 54, 0);
		BottomPannel.addBox(0F, 0F, 0F, 6, 1, 16);
		BottomPannel.setRotationPoint(-3F, 22F, -8F);
		BottomPannel.setTextureSize(190, 32);
		BottomPannel.mirror = true;
		setRotation(BottomPannel, 0F, 0F, 0F);
		UpRight = new ModelRenderer(this, 82, 12);
		UpRight.addBox(0F, 0F, 0F, 1, 1, 16);
		UpRight.setRotationPoint(-6F, 10F, -8F);
		UpRight.setTextureSize(190, 32);
		UpRight.mirror = true;
		setRotation(UpRight, 0F, 0F, 0F);
		UpLeft = new ModelRenderer(this, 100, 13);
		UpLeft.addBox(0F, 0F, 0F, 1, 1, 16);
		UpLeft.setRotationPoint(5F, 10F, -8F);
		UpLeft.setTextureSize(190, 32);
		UpLeft.mirror = true;
		setRotation(UpLeft, 0F, 0F, 0F);
		BottomRight = new ModelRenderer(this, 118, 14);
		BottomRight.addBox(0F, 0F, 0F, 1, 1, 16);
		BottomRight.setRotationPoint(-6F, 21F, -8F);
		BottomRight.setTextureSize(190, 32);
		BottomRight.mirror = true;
		setRotation(BottomRight, 0F, 0F, 0F);
		BottomLeft = new ModelRenderer(this, 136, 15);
		BottomLeft.addBox(0F, 0F, 0F, 1, 1, 16);
		BottomLeft.setRotationPoint(5F, 21F, -8F);
		BottomLeft.setTextureSize(190, 32);
		BottomLeft.mirror = true;
		setRotation(BottomLeft, 0F, 0F, 0F);
		TopFrontBar = new ModelRenderer(this, 82, 0);
		TopFrontBar.addBox(0F, 0F, 0F, 8, 1, 1);
		TopFrontBar.setRotationPoint(-4F, 8F, -8.5F);
		TopFrontBar.setTextureSize(190, 32);
		TopFrontBar.mirror = true;
		setRotation(TopFrontBar, 0F, 0F, 0F);
		BottomFrontBar = new ModelRenderer(this, 82, 2);
		BottomFrontBar.addBox(0F, 0F, 0F, 8, 1, 1);
		BottomFrontBar.setRotationPoint(-4F, 23F, -8.5F);
		BottomFrontBar.setTextureSize(190, 32);
		BottomFrontBar.mirror = true;
		setRotation(BottomFrontBar, 0F, 0F, 0F);
		RightFrontBar = new ModelRenderer(this, 4, 0);
		RightFrontBar.addBox(0F, 0F, 0F, 1, 8, 1);
		RightFrontBar.setRotationPoint(-8F, 12F, -8.5F);
		RightFrontBar.setTextureSize(190, 32);
		RightFrontBar.mirror = true;
		setRotation(RightFrontBar, 0F, 0F, 0F);
		LeftFrontBar = new ModelRenderer(this, 0, 0);
		LeftFrontBar.addBox(0F, 0F, 0F, 1, 8, 1);
		LeftFrontBar.setRotationPoint(7F, 12F, -8.5F);
		LeftFrontBar.setTextureSize(190, 32);
		LeftFrontBar.mirror = true;
		setRotation(LeftFrontBar, 0F, 0F, 0F);
		RightUpFrontBarOne = new ModelRenderer(this, 8, 0);
		RightUpFrontBarOne.addBox(0F, 0F, 0F, 1, 3, 1);
		RightUpFrontBarOne.setRotationPoint(-7F, 10F, -8.5F);
		RightUpFrontBarOne.setTextureSize(190, 32);
		RightUpFrontBarOne.mirror = true;
		setRotation(RightUpFrontBarOne, 0F, 0F, 0F);
		RightUpFrontBarTwo = new ModelRenderer(this, 0, 9);
		RightUpFrontBarTwo.addBox(0F, 0F, 0F, 3, 1, 1);
		RightUpFrontBarTwo.setRotationPoint(-6F, 9F, -8.5F);
		RightUpFrontBarTwo.setTextureSize(190, 32);
		RightUpFrontBarTwo.mirror = true;
		setRotation(RightUpFrontBarTwo, 0F, 0F, 0F);
		RightBottomFrontBarOne = new ModelRenderer(this, 8, 4);
		RightBottomFrontBarOne.addBox(0F, 0F, 0F, 1, 3, 1);
		RightBottomFrontBarOne.setRotationPoint(-7F, 19F, -8.5F);
		RightBottomFrontBarOne.setTextureSize(190, 32);
		RightBottomFrontBarOne.mirror = true;
		setRotation(RightBottomFrontBarOne, 0F, 0F, 0F);
		RightBottomFrontBarTwo = new ModelRenderer(this, 8, 9);
		RightBottomFrontBarTwo.addBox(0F, 0F, 0F, 3, 1, 1);
		RightBottomFrontBarTwo.setRotationPoint(-6F, 22F, -8.5F);
		RightBottomFrontBarTwo.setTextureSize(190, 32);
		RightBottomFrontBarTwo.mirror = true;
		setRotation(RightBottomFrontBarTwo, 0F, 0F, 0F);
		LeftUpFrontBarOne = new ModelRenderer(this, 12, 0);
		LeftUpFrontBarOne.addBox(0F, 0F, 0F, 1, 3, 1);
		LeftUpFrontBarOne.setRotationPoint(6F, 10F, -8.5F);
		LeftUpFrontBarOne.setTextureSize(190, 32);
		LeftUpFrontBarOne.mirror = true;
		setRotation(LeftUpFrontBarOne, 0F, 0F, 0F);
		LeftUpFrontBarTwo = new ModelRenderer(this, 0, 11);
		LeftUpFrontBarTwo.addBox(0F, 0F, 0F, 3, 1, 1);
		LeftUpFrontBarTwo.setRotationPoint(3F, 9F, -8.5F);
		LeftUpFrontBarTwo.setTextureSize(190, 32);
		LeftUpFrontBarTwo.mirror = true;
		setRotation(LeftUpFrontBarTwo, 0F, 0F, 0F);
		LeftBottomFrontBarOne = new ModelRenderer(this, 12, 4);
		LeftBottomFrontBarOne.addBox(0F, 0F, 0F, 1, 3, 1);
		LeftBottomFrontBarOne.setRotationPoint(6F, 19F, -8.5F);
		LeftBottomFrontBarOne.setTextureSize(190, 32);
		LeftBottomFrontBarOne.mirror = true;
		setRotation(LeftBottomFrontBarOne, 0F, 0F, 0F);
		LeftBottomFrontBarTwo = new ModelRenderer(this, 8, 11);
		LeftBottomFrontBarTwo.addBox(0F, 0F, 0F, 3, 1, 1);
		LeftBottomFrontBarTwo.setRotationPoint(3F, 22F, -8.5F);
		LeftBottomFrontBarTwo.setTextureSize(190, 32);
		LeftBottomFrontBarTwo.mirror = true;
		setRotation(LeftBottomFrontBarTwo, 0F, 0F, 0F);
		UpLeftBlock = new ModelRenderer(this, 0, 13);
		UpLeftBlock.addBox(0F, 0F, 0F, 1, 1, 1);
		UpLeftBlock.setRotationPoint(5F, 10F, -8.5F);
		UpLeftBlock.setTextureSize(190, 32);
		UpLeftBlock.mirror = true;
		setRotation(UpLeftBlock, 0F, 0F, 0F);
		UpRightBlock = new ModelRenderer(this, 4, 13);
		UpRightBlock.addBox(0F, 0F, 0F, 1, 1, 1);
		UpRightBlock.setRotationPoint(-6F, 10F, -8.5F);
		UpRightBlock.setTextureSize(190, 32);
		UpRightBlock.mirror = true;
		setRotation(UpRightBlock, 0F, 0F, 0F);
		BottomRightBlock = new ModelRenderer(this, 8, 13);
		BottomRightBlock.addBox(0F, 0F, 0F, 1, 1, 1);
		BottomRightBlock.setRotationPoint(-6F, 21F, -8.5F);
		BottomRightBlock.setTextureSize(190, 32);
		BottomRightBlock.mirror = true;
		setRotation(BottomRightBlock, 0F, 0F, 0F);
		BottomLeftBlock = new ModelRenderer(this, 12, 13);
		BottomLeftBlock.addBox(0F, 0F, 0F, 1, 1, 1);
		BottomLeftBlock.setRotationPoint(5F, 21F, -8.5F);
		BottomLeftBlock.setTextureSize(190, 32);
		BottomLeftBlock.mirror = true;
		setRotation(BottomLeftBlock, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		UpRight.render(f5);
		UpLeft.render(f5);
		BottomRight.render(f5);
		BottomLeft.render(f5);
		TopFrontBar.render(f5);
		BottomFrontBar.render(f5);
		RightFrontBar.render(f5);
		LeftFrontBar.render(f5);
		RightUpFrontBarOne.render(f5);
		RightUpFrontBarTwo.render(f5);
		RightBottomFrontBarOne.render(f5);
		RightBottomFrontBarTwo.render(f5);
		LeftUpFrontBarOne.render(f5);
		LeftUpFrontBarTwo.render(f5);
		LeftBottomFrontBarOne.render(f5);
		LeftBottomFrontBarTwo.render(f5);
		UpLeftBlock.render(f5);
		UpRightBlock.render(f5);
		BottomRightBlock.render(f5);
		BottomLeftBlock.render(f5);
	}

	public void renderPipeWalls(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		
		Bottom.render(f5);
		Top.render(f5);
		Right.render(f5);
		Left.render(f5);
		LeftPannel.render(f5);
		RightPannel.render(f5);
		TopPannel.render(f5);
		BottomPannel.render(f5);
		
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}