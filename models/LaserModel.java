// Date: 07/11/2013 21:10:22
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package opticraft.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LaserModel extends ModelBase
{
	//fields
    ModelRenderer Base;
    ModelRenderer LaserBase1;
    ModelRenderer LaserBase2;
    ModelRenderer LaserColumn1;
    ModelRenderer LaserColumn2;
    ModelRenderer LaserColumn3;
    ModelRenderer Tube1v;
    ModelRenderer Tube1h;
    ModelRenderer Tube2v;
    ModelRenderer Tube2h;
    ModelRenderer Tube3v;
    ModelRenderer Tube3h;
    ModelRenderer Tube4v;
    ModelRenderer Tube4h;
    ModelRenderer Laser1;
    ModelRenderer Laser2;
    ModelRenderer Laser3;
    ModelRenderer Laser4;
    ModelRenderer Light1;
    ModelRenderer Light2;
    ModelRenderer Light3;
    ModelRenderer Light4;
    ModelRenderer CableBack;
    ModelRenderer CableBackConnector;
    ModelRenderer CableFront;
    ModelRenderer CableFrontConnector;
    ModelRenderer CableRight;
    ModelRenderer CableRightConnector;
    ModelRenderer CableLeft;
    ModelRenderer CableLeftConnector;
  
  public LaserModel()
  {
    textureWidth = 60;
    textureHeight = 64;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 14, 1, 14);
      Base.setRotationPoint(-7F, 23F, -7F);
      Base.setTextureSize(60, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      LaserBase1 = new ModelRenderer(this, 24, 15);
      LaserBase1.addBox(0F, 0F, 0F, 8, 4, 4);
      LaserBase1.setRotationPoint(-4F, 19F, -2F);
      LaserBase1.setTextureSize(60, 64);
      LaserBase1.mirror = true;
      setRotation(LaserBase1, 0F, 0F, 0F);
      LaserBase2 = new ModelRenderer(this, 0, 15);
      LaserBase2.addBox(0F, 0F, 0F, 4, 4, 8);
      LaserBase2.setRotationPoint(-2F, 19F, -4F);
      LaserBase2.setTextureSize(60, 64);
      LaserBase2.mirror = true;
      setRotation(LaserBase2, 0F, 0F, 0F);
      LaserColumn1 = new ModelRenderer(this, 0, 27);
      LaserColumn1.addBox(0F, 0F, 0F, 2, 3, 2);
      LaserColumn1.setRotationPoint(-1F, 14F, -1F);
      LaserColumn1.setTextureSize(60, 64);
      LaserColumn1.mirror = true;
      setRotation(LaserColumn1, 0F, 0F, 0F);
      LaserColumn2 = new ModelRenderer(this, 8, 27);
      LaserColumn2.addBox(0F, 0F, 0F, 3, 2, 3);
      LaserColumn2.setRotationPoint(-1.5F, 17F, -1.5F);
      LaserColumn2.setTextureSize(60, 64);
      LaserColumn2.mirror = true;
      setRotation(LaserColumn2, 0F, 0F, 0F);
      LaserColumn3 = new ModelRenderer(this, 20, 26);
      LaserColumn3.addBox(0F, 0F, 0F, 1, 5, 1);
      LaserColumn3.setRotationPoint(-0.5F, 9F, -0.5F);
      LaserColumn3.setTextureSize(60, 64);
      LaserColumn3.mirror = true;
      setRotation(LaserColumn3, 0F, 0F, 0F);
      Tube1v = new ModelRenderer(this, 24, 23);
      Tube1v.addBox(0F, 0F, 0F, 1, 3, 2);
      Tube1v.setRotationPoint(5F, 20F, -1F);
      Tube1v.setTextureSize(60, 64);
      Tube1v.mirror = true;
      setRotation(Tube1v, 0F, 0F, 0F);
      Tube1h = new ModelRenderer(this, 36, 29);
      Tube1h.addBox(0F, 0F, 0F, 1, 1, 2);
      Tube1h.setRotationPoint(-5F, 20F, -1F);
      Tube1h.setTextureSize(60, 64);
      Tube1h.mirror = true;
      setRotation(Tube1h, 0F, 0F, 0F);
      Tube2v = new ModelRenderer(this, 30, 28);
      Tube2v.addBox(0F, 0F, 0F, 2, 3, 1);
      Tube2v.setRotationPoint(-1F, 20F, -6F);
      Tube2v.setTextureSize(60, 64);
      Tube2v.mirror = true;
      setRotation(Tube2v, 0F, 0F, 0F);
      Tube2h = new ModelRenderer(this, 24, 30);
      Tube2h.addBox(0F, 0F, 0F, 2, 1, 1);
      Tube2h.setRotationPoint(-1F, 20F, 4F);
      Tube2h.setTextureSize(60, 64);
      Tube2h.mirror = true;
      setRotation(Tube2h, 0F, 0F, 0F);
      Tube3v = new ModelRenderer(this, 30, 23);
      Tube3v.addBox(0F, 0F, 0F, 1, 3, 2);
      Tube3v.setRotationPoint(-6F, 20F, -1F);
      Tube3v.setTextureSize(60, 64);
      Tube3v.mirror = true;
      setRotation(Tube3v, 0F, 0F, 0F);
      Tube3h = new ModelRenderer(this, 42, 23);
      Tube3h.addBox(0F, 0F, 0F, 1, 1, 2);
      Tube3h.setRotationPoint(4F, 20F, -1F);
      Tube3h.setTextureSize(60, 64);
      Tube3h.mirror = true;
      setRotation(Tube3h, 0F, 0F, 0F);
      Tube4v = new ModelRenderer(this, 36, 23);
      Tube4v.addBox(0F, 0F, 0F, 2, 3, 1);
      Tube4v.setRotationPoint(-1F, 20F, 5F);
      Tube4v.setTextureSize(60, 64);
      Tube4v.mirror = true;
      setRotation(Tube4v, 0F, 0F, 0F);
      Tube4h = new ModelRenderer(this, 24, 28);
      Tube4h.addBox(0F, 0F, 0F, 2, 1, 1);
      Tube4h.setRotationPoint(-1F, 20F, -5F);
      Tube4h.setTextureSize(60, 64);
      Tube4h.mirror = true;
      setRotation(Tube4h, 0F, 0F, 0F);
      Laser1 = new ModelRenderer(this, 0, 34);
      Laser1.addBox(0F, 0F, 0F, 8, 1, 8);
      Laser1.setRotationPoint(-4F, 16F, -4F);
      Laser1.setTextureSize(60, 64);
      Laser1.mirror = true;
      setRotation(Laser1, 0F, 0F, 0F);
      Laser2 = new ModelRenderer(this, 0, 43);
      Laser2.addBox(0F, 0F, 0F, 6, 1, 6);
      Laser2.setRotationPoint(-3F, 14F, -3F);
      Laser2.setTextureSize(60, 64);
      Laser2.mirror = true;
      setRotation(Laser2, 0F, 0F, 0F);
      Laser3 = new ModelRenderer(this, 0, 50);
      Laser3.addBox(0F, 0F, 0F, 4, 1, 4);
      Laser3.setRotationPoint(-2F, 12F, -2F);
      Laser3.setTextureSize(60, 64);
      Laser3.mirror = true;
      setRotation(Laser3, 0F, 0F, 0F);
      Laser4 = new ModelRenderer(this, 0, 55);
      Laser4.addBox(0F, 0F, 0F, 2, 1, 2);
      Laser4.setRotationPoint(-1F, 10F, -1F);
      Laser4.setTextureSize(60, 64);
      Laser4.mirror = true;
      setRotation(Laser4, 0F, 0F, 0F);
      Light1 = new ModelRenderer(this, 43, 30);
      Light1.addBox(0F, 0F, 0F, 1, 1, 1);
      Light1.setRotationPoint(5F, 22.5F, -6F);
      Light1.setTextureSize(60, 64);
      Light1.mirror = true;
      setRotation(Light1, 0F, 0F, 0F);
      Light2 = new ModelRenderer(this, 43, 30);
      Light2.addBox(0F, 0F, 0F, 1, 1, 1);
      Light2.setRotationPoint(-6F, 22.5F, -6F);
      Light2.setTextureSize(60, 64);
      Light2.mirror = true;
      setRotation(Light2, 0F, 0F, 0F);
      Light3 = new ModelRenderer(this, 43, 30);
      Light3.addBox(0F, 0F, 0F, 1, 1, 1);
      Light3.setRotationPoint(-6F, 22.5F, 5F);
      Light3.setTextureSize(60, 64);
      Light3.mirror = true;
      setRotation(Light3, 0F, 0F, 0F);
      Light4 = new ModelRenderer(this, 43, 30);
      Light4.addBox(0F, 0F, 0F, 1, 1, 1);
      Light4.setRotationPoint(5F, 22.5F, 5F);
      Light4.setTextureSize(60, 64);
      Light4.mirror = true;
      setRotation(Light4, 0F, 0F, 0F);
      CableBack = new ModelRenderer(this, 35, 34);
      CableBack.addBox(0F, 0F, 0F, 1, 8, 1);
      CableBack.setRotationPoint(-0.5F, 16F, 6F);
      CableBack.setTextureSize(60, 64);
      CableBack.mirror = true;
      setRotation(CableBack, 0F, 0F, 0F);
      CableBackConnector = new ModelRenderer(this, 35, 43);
      CableBackConnector.addBox(0F, 0F, 0F, 2, 2, 1);
      CableBackConnector.setRotationPoint(-1F, 15F, 7F);
      CableBackConnector.setTextureSize(60, 64);
      CableBackConnector.mirror = true;
      setRotation(CableBackConnector, 0F, 0F, 0F);
      CableFront = new ModelRenderer(this, 39, 34);
      CableFront.addBox(0F, 0F, 0F, 1, 8, 1);
      CableFront.setRotationPoint(-0.5F, 16F, -7F);
      CableFront.setTextureSize(60, 64);
      CableFront.mirror = true;
      setRotation(CableFront, 0F, 0F, 0F);
      CableFrontConnector = new ModelRenderer(this, 41, 43);
      CableFrontConnector.addBox(0F, 0F, 0F, 2, 2, 1);
      CableFrontConnector.setRotationPoint(-1F, 15F, -8F);
      CableFrontConnector.setTextureSize(60, 64);
      CableFrontConnector.mirror = true;
      setRotation(CableFrontConnector, 0F, 0F, 0F);
      CableRight = new ModelRenderer(this, 43, 34);
      CableRight.addBox(0F, 0F, 0F, 1, 8, 1);
      CableRight.setRotationPoint(-7F, 16F, -0.5F);
      CableRight.setTextureSize(60, 64);
      CableRight.mirror = true;
      setRotation(CableRight, 0F, 0F, 0F);
      CableRightConnector = new ModelRenderer(this, 35, 46);
      CableRightConnector.addBox(0F, 0F, 0F, 1, 2, 2);
      CableRightConnector.setRotationPoint(-8F, 15F, -1F);
      CableRightConnector.setTextureSize(60, 64);
      CableRightConnector.mirror = true;
      setRotation(CableRightConnector, 0F, 0F, 0F);
      CableLeft = new ModelRenderer(this, 47, 34);
      CableLeft.addBox(0F, 0F, 0F, 1, 8, 1);
      CableLeft.setRotationPoint(6F, 16F, -0.5F);
      CableLeft.setTextureSize(60, 64);
      CableLeft.mirror = true;
      setRotation(CableLeft, 0F, 0F, 0F);
      CableLeftConnector = new ModelRenderer(this, 41, 46);
      CableLeftConnector.addBox(0F, 0F, 0F, 1, 2, 2);
      CableLeftConnector.setRotationPoint(7F, 15F, -1F);
      CableLeftConnector.setTextureSize(60, 64);
      CableLeftConnector.mirror = true;
      setRotation(CableLeftConnector, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    LaserBase1.render(f5);
    LaserBase2.render(f5);
    LaserColumn1.render(f5);
    LaserColumn2.render(f5);
    LaserColumn3.render(f5);
    Tube1v.render(f5);
    Tube1h.render(f5);
    Tube2v.render(f5);
    Tube2h.render(f5);
    Tube3v.render(f5);
    Tube3h.render(f5);
    Tube4v.render(f5);
    Tube4h.render(f5);
    Laser1.render(f5);
    Laser2.render(f5);
    Laser3.render(f5);
    Laser4.render(f5);
    Light1.render(f5);
    Light2.render(f5);
    Light3.render(f5);
    Light4.render(f5);
    CableBack.render(f5);
    CableBackConnector.render(f5);
    CableFront.render(f5);
    CableFrontConnector.render(f5);
    CableRight.render(f5);
    CableRightConnector.render(f5);
    CableLeft.render(f5);
    CableLeftConnector.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}