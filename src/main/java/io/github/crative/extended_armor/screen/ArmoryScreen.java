package io.github.crative.extended_armor.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.crative.extended_armor.ExtendedArmor;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ArmoryScreen extends HandledScreen<ArmoryScreenHandler> {

	private static final Identifier TEXTURE = new Identifier(ExtendedArmor.MOD_ID, "textures/gui/armory_gui.png");
	public ArmoryScreen(ArmoryScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Override
	protected void drawBackground(GuiGraphics graphics, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - backgroundWidth)/2;
		int y = (height - backgroundHeight)/2;

		graphics.drawTexture(TEXTURE,x, y, 0, 0, backgroundWidth, backgroundHeight);
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
		renderBackground(graphics, mouseX, mouseY, delta);
		super.render(graphics, mouseX, mouseY, delta);
		drawMouseoverTooltip(graphics, mouseX, mouseY);
	}
}
