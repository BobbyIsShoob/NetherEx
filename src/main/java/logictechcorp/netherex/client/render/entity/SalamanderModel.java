/*
 * NetherEx
 * Copyright (c) 2016-2019 by LogicTechCorp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package logictechcorp.netherex.client.render.entity;

import logictechcorp.netherex.entity.neutral.SalamanderEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SalamanderModel extends EntityModel<SalamanderEntity>
{
    private RendererModel head;
    private RendererModel upperBody;
    private RendererModel frontRightLeg;
    private RendererModel frontLeftLeg;
    private RendererModel backRightLeg;
    private RendererModel backLeftLeg;
    private RendererModel jaw;
    private RendererModel teeth;
    private RendererModel lowerBody;
    private RendererModel tail;

    public SalamanderModel()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.frontLeftLeg = new RendererModel(this, 50, 54);
        this.frontLeftLeg.mirror = true;
        this.frontLeftLeg.setRotationPoint(5.0F, 19.0F, -6.5F);
        this.frontLeftLeg.addBox(0.0F, -1.0F, -2.0F, 3, 6, 4, 0.0F);
        this.backLeftLeg = new RendererModel(this, 50, 54);
        this.backLeftLeg.mirror = true;
        this.backLeftLeg.setRotationPoint(4.0F, 19.0F, 6.5F);
        this.backLeftLeg.addBox(0.0F, -1.0F, -2.0F, 3, 6, 4, 0.0F);
        this.teeth = new RendererModel(this, 30, 0);
        this.teeth.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.teeth.addBox(-3.5F, -1.0F, -9.0F, 7, 1, 9, 0.0F);
        this.upperBody = new RendererModel(this, 0, 25);
        this.upperBody.setRotationPoint(0.0F, 19.0F, -10.5F);
        this.upperBody.addBox(-5.0F, -3.0F, 0.0F, 10, 6, 12, 0.0F);
        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(0.0F, 20.0F, -10.5F);
        this.head.addBox(-3.0F, -4.0F, -8.5F, 6, 4, 9, 0.0F);
        this.setRotateAngle(this.head, -0.061086523819801536F, 0.0F, 0.0F);
        this.jaw = new RendererModel(this, 0, 13);
        this.jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.jaw.addBox(-3.5F, 0.0F, -9.0F, 7, 3, 9, 0.0F);
        this.setRotateAngle(this.jaw, 0.17453292519943295F, 0.0F, 0.0F);
        this.lowerBody = new RendererModel(this, 0, 43);
        this.lowerBody.setRotationPoint(0.0F, 0.0F, 10.0F);
        this.lowerBody.addBox(-4.0F, -2.5F, 0.0F, 8, 5, 10, 0.0F);
        this.tail = new RendererModel(this, 36, 10);
        this.tail.setRotationPoint(0.0F, 0.0F, 10.0F);
        this.tail.addBox(-2.5F, -1.5F, -1.0F, 5, 3, 9, 0.0F);
        this.setRotateAngle(this.tail, -0.17453292519943295F, 0.0F, 0.0F);
        this.frontRightLeg = new RendererModel(this, 50, 54);
        this.frontRightLeg.setRotationPoint(-5.0F, 19.0F, -6.5F);
        this.frontRightLeg.addBox(-3.0F, -1.0F, -2.0F, 3, 6, 4, 0.0F);
        this.backRightLeg = new RendererModel(this, 50, 54);
        this.backRightLeg.setRotationPoint(-4.0F, 19.0F, 6.5F);
        this.backRightLeg.addBox(-3.0F, -1.0F, -2.0F, 3, 6, 4, 0.0F);
        this.jaw.addChild(this.teeth);
        this.head.addChild(this.jaw);
        this.upperBody.addChild(this.lowerBody);
        this.lowerBody.addChild(this.tail);
    }

    @Override
    public void render(SalamanderEntity salamander, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale)
    {
        this.setRotationAngles(salamander, limbSwing, limbSwingAmount, ageInTicks, rotationYaw, rotationPitch, scale);
        this.frontRightLeg.render(scale);
        this.frontLeftLeg.render(scale);
        this.head.render(scale);
        this.upperBody.render(scale);
        this.backRightLeg.render(scale);
        this.backLeftLeg.render(scale);
    }

    @Override
    public void setRotationAngles(SalamanderEntity salamander, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scaleFactor)
    {
        this.head.rotateAngleX = rotationPitch * 0.017453292F;
        this.head.rotateAngleY = rotationYaw * 0.017453292F;
        this.frontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.frontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
        this.backRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
        this.backLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    private void setRotateAngle(RendererModel modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}