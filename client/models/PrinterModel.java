// Date: 08/02/2014 16:08:51
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package opticraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PrinterModel extends ModelBase {
	// fields
	ModelRenderer Base;
	ModelRenderer Back;
	ModelRenderer TopLeft;
	ModelRenderer TopLeft2;
	ModelRenderer TopRight2;
	ModelRenderer TopRight;
	ModelRenderer TopBack;
	ModelRenderer FrontRight;
	ModelRenderer FrontLeft;
	ModelRenderer BackLeft;
	ModelRenderer BackRight;
	ModelRenderer BottomRight;
	ModelRenderer BottomLeft;
	ModelRenderer Pannel;
	ModelRenderer PannelBack;
	ModelRenderer PannelBottom;
	ModelRenderer PannelRight;
	ModelRenderer PannelLeft;
	ModelRenderer FrontBottom;
	ModelRenderer BackRailRight;
	ModelRenderer BackRailLeft;
	ModelRenderer Bed;
	ModelRenderer TopBackRail;
	ModelRenderer TopFrontRail;
	ModelRenderer Laser1;
	ModelRenderer Laser2;
	ModelRenderer Laser3;

	public PrinterModel() {
		textureWidth = 128;
		textureHeight = 32;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(0F, 0F, 0F, 16, 1, 16);
		Base.setRotationPoint(-8F, 23F, -8F);
		Base.setTextureSize(64, 32);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Back = new ModelRenderer(this, 0, 16);
		Back.addBox(0F, 0F, 0F, 16, 15, 1);
		Back.setRotationPoint(-8F, 8F, 7F);
		Back.setTextureSize(64, 32);
		Back.mirror = true;
		setRotation(Back, 0F, 0F, 0F);
		TopLeft = new ModelRenderer(this, 34, 21);
		TopLeft.addBox(0F, 0F, 0F, 2, 1, 10);
		TopLeft.setRotationPoint(-8F, 8F, -3F);
		TopLeft.setTextureSize(64, 32);
		TopLeft.mirror = true;
		setRotation(TopLeft, 0F, 0F, 0F);
		TopLeft2 = new ModelRenderer(this, 58, 22);
		TopLeft2.addBox(0F, 0F, 0F, 1, 1, 9);
		TopLeft2.setRotationPoint(7F, 9F, -3F);
		TopLeft2.setTextureSize(64, 32);
		TopLeft2.mirror = true;
		setRotation(TopLeft2, 0F, 0F, 0F);
		TopRight2 = new ModelRenderer(this, 58, 22);
		TopRight2.addBox(0F, 0F, 0F, 1, 1, 9);
		TopRight2.setRotationPoint(-8F, 9F, -3F);
		TopRight2.setTextureSize(64, 32);
		TopRight2.mirror = true;
		setRotation(TopRight2, 0F, 0F, 0F);
		TopRight = new ModelRenderer(this, 34, 21);
		TopRight.addBox(0F, 0F, 0F, 2, 1, 10);
		TopRight.setRotationPoint(6F, 8F, -3F);
		TopRight.setTextureSize(64, 32);
		TopRight.mirror = true;
		setRotation(TopRight, 0F, 0F, 0F);
		TopBack = new ModelRenderer(this, 49, 0);
		TopBack.addBox(0F, 0F, 0F, 12, 1, 1);
		TopBack.setRotationPoint(-6F, 8F, 6F);
		TopBack.setTextureSize(64, 32);
		TopBack.mirror = true;
		setRotation(TopBack, 0F, 0F, 0F);
		FrontRight = new ModelRenderer(this, 4, 0);
		FrontRight.addBox(0F, 0F, 0F, 1, 11, 1);
		FrontRight.setRotationPoint(-8F, 12F, -8F);
		FrontRight.setTextureSize(64, 32);
		FrontRight.mirror = true;
		setRotation(FrontRight, 0F, 0F, 0F);
		FrontLeft = new ModelRenderer(this, 4, 0);
		FrontLeft.addBox(0F, 0F, 0F, 1, 11, 1);
		FrontLeft.setRotationPoint(7F, 12F, -8F);
		FrontLeft.setTextureSize(64, 32);
		FrontLeft.mirror = true;
		setRotation(FrontLeft, 0F, 0F, 0F);
		BackLeft = new ModelRenderer(this, 0, 0);
		BackLeft.addBox(0F, 0F, 0F, 1, 14, 1);
		BackLeft.setRotationPoint(7F, 9F, 6F);
		BackLeft.setTextureSize(64, 32);
		BackLeft.mirror = true;
		setRotation(BackLeft, 0F, 0F, 0F);
		BackRight = new ModelRenderer(this, 0, 0);
		BackRight.addBox(0F, 0F, 0F, 1, 14, 1);
		BackRight.setRotationPoint(-8F, 9F, 6F);
		BackRight.setTextureSize(64, 32);
		BackRight.mirror = true;
		setRotation(BackRight, 0F, 0F, 0F);
		BottomRight = new ModelRenderer(this, 78, 18);
		BottomRight.addBox(0F, 0F, 0F, 1, 1, 13);
		BottomRight.setRotationPoint(-8F, 22F, -7F);
		BottomRight.setTextureSize(64, 32);
		BottomRight.mirror = true;
		setRotation(BottomRight, 0F, 0F, 0F);
		BottomLeft = new ModelRenderer(this, 78, 18);
		BottomLeft.addBox(0F, 0F, 0F, 1, 1, 13);
		BottomLeft.setRotationPoint(7F, 22F, -7F);
		BottomLeft.setTextureSize(64, 32);
		BottomLeft.mirror = true;
		setRotation(BottomLeft, 0F, 0F, 0F);
		Pannel = new ModelRenderer(this, 49, 2);
		Pannel.addBox(0F, 0F, 0F, 15, 6, 1);
		Pannel.setRotationPoint(-7.5F, 8F, -3F);
		Pannel.setTextureSize(64, 32);
		Pannel.mirror = true;
		setRotation(Pannel, -1.047198F, 0F, 0F);
		PannelBack = new ModelRenderer(this, 49, 0);
		PannelBack.addBox(0F, 0F, 0F, 12, 1, 1);
		PannelBack.setRotationPoint(-6F, 8F, -3F);
		PannelBack.setTextureSize(64, 32);
		PannelBack.mirror = true;
		setRotation(PannelBack, 0F, 0F, 0F);
		PannelBottom = new ModelRenderer(this, 76, 0);
		PannelBottom.addBox(0F, 0F, 0F, 16, 1, 1);
		PannelBottom.setRotationPoint(-8F, 11F, -8F);
		PannelBottom.setTextureSize(64, 32);
		PannelBottom.mirror = true;
		setRotation(PannelBottom, 0F, 0F, 0F);
		PannelRight = new ModelRenderer(this, 9, 0);
		PannelRight.addBox(0F, 0F, 0F, 1, 6, 1);
		PannelRight.setRotationPoint(-8F, 8.5F, -2.5F);
		PannelRight.setTextureSize(64, 32);
		PannelRight.mirror = true;
		setRotation(PannelRight, -1.047198F, 0F, 0F);
		PannelLeft = new ModelRenderer(this, 9, 0);
		PannelLeft.addBox(0F, 0F, 0F, 1, 6, 1);
		PannelLeft.setRotationPoint(7F, 8.5F, -2.5F);
		PannelLeft.setTextureSize(64, 32);
		PannelLeft.mirror = true;
		setRotation(PannelLeft, -1.047198F, 0F, 0F);
		FrontBottom = new ModelRenderer(this, 82, 3);
		FrontBottom.addBox(0F, 0F, 0F, 14, 1, 1);
		FrontBottom.setRotationPoint(-7F, 22F, -8F);
		FrontBottom.setTextureSize(64, 32);
		FrontBottom.mirror = true;
		setRotation(FrontBottom, 0F, 0F, 0F);
		BackRailRight = new ModelRenderer(this, 0, 0);
		BackRailRight.addBox(0F, 0F, 0F, 1, 14, 1);
		BackRailRight.setRotationPoint(-3F, 9F, 6F);
		BackRailRight.setTextureSize(64, 32);
		BackRailRight.mirror = true;
		setRotation(BackRailRight, 0F, 0F, 0F);
		BackRailLeft = new ModelRenderer(this, 0, 0);
		BackRailLeft.addBox(0F, 0F, 0F, 1, 14, 1);
		BackRailLeft.setRotationPoint(2F, 9F, 6F);
		BackRailLeft.setTextureSize(64, 32);
		BackRailLeft.mirror = true;
		setRotation(BackRailLeft, 0F, 0F, 0F);
		Bed = new ModelRenderer(this, 73, 6);
		Bed.addBox(0F, 0F, 0F, 10, 1, 10);
		Bed.setRotationPoint(-5F, 22F, -3F);
		Bed.setTextureSize(64, 32);
		Bed.mirror = true;
		setRotation(Bed, 0F, 0F, 0F);
		TopBackRail = new ModelRenderer(this, 82, 3);
		TopBackRail.addBox(0F, 0F, 0F, 14, 1, 1);
		TopBackRail.setRotationPoint(-7F, 9F, 3F);
		TopBackRail.setTextureSize(64, 32);
		TopBackRail.mirror = true;
		setRotation(TopBackRail, 0F, 0F, 0F);
		TopFrontRail = new ModelRenderer(this, 82, 3);
		TopFrontRail.addBox(0F, 0F, 0F, 14, 1, 1);
		TopFrontRail.setRotationPoint(-7F, 9F, 0F);
		TopFrontRail.setTextureSize(64, 32);
		TopFrontRail.mirror = true;
		setRotation(TopFrontRail, 0F, 0F, 0F);
		Laser1 = new ModelRenderer(this, 49, 10);
		Laser1.addBox(0F, 0F, 0F, 4, 1, 4);
		Laser1.setRotationPoint(-2F, 10F, 0F);
		Laser1.setTextureSize(64, 32);
		Laser1.mirror = true;
		setRotation(Laser1, 0F, 0F, 0F);
		Laser2 = new ModelRenderer(this, 61, 18);
		Laser2.addBox(0F, 0F, 0F, 3, 1, 3);
		Laser2.setRotationPoint(-1.5F, 11F, 0F);
		Laser2.setTextureSize(64, 32);
		Laser2.mirror = true;
		setRotation(Laser2, 0F, 0F, 0F);
		Laser3 = new ModelRenderer(this, 66, 11);
		Laser3.addBox(0F, 0F, 0F, 1, 2, 1);
		Laser3.setRotationPoint(-0.5F, 12F, 0F);
		Laser3.setTextureSize(64, 32);
		Laser3.mirror = true;
		setRotation(Laser3, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Base.render(f5);
		Back.render(f5);
		TopLeft.render(f5);
		TopLeft2.render(f5);
		TopRight2.render(f5);
		TopRight.render(f5);
		TopBack.render(f5);
		FrontRight.render(f5);
		FrontLeft.render(f5);
		BackLeft.render(f5);
		BackRight.render(f5);
		BottomRight.render(f5);
		BottomLeft.render(f5);
		Pannel.render(f5);
		PannelBack.render(f5);
		PannelBottom.render(f5);
		PannelRight.render(f5);
		PannelLeft.render(f5);
		FrontBottom.render(f5);
		BackRailRight.render(f5);
		BackRailLeft.render(f5);
		TopBackRail.render(f5);
		TopFrontRail.render(f5);
	}

	public void renderLaser(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Laser1.render(f5);
		Laser2.render(f5);
		Laser3.render(f5);
	}

	public void renderPlatform(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Bed.render(f5);
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
