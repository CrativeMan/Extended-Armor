package io.github.crative.extended_armor;

import io.github.crative.extended_armor.screen.ArmoryScreen;
import io.github.crative.extended_armor.screen.ExtendedArmorScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class ExtendedArmorClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		HandledScreens.register(ExtendedArmorScreenHandlers.ARMORY_SCREEN_HANDLER, ArmoryScreen::new);
	}
}
