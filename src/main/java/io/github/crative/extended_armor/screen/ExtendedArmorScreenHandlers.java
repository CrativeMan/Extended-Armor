package io.github.crative.extended_armor.screen;

import io.github.crative.extended_armor.ExtendedArmor;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ExtendedArmorScreenHandlers {

	public static final ScreenHandlerType<ArmoryScreenHandler> ARMORY_SCREEN_HANDLER =
		Registry.register(Registries.SCREEN_HANDLER_TYPE, new Identifier(ExtendedArmor.MOD_ID, "armory"),
			new ExtendedScreenHandlerType<>(ArmoryScreenHandler::new));

	public static void registerScreenHandlers() {
		ExtendedArmor.LOGGER.info("Registering screen handlers for " + ExtendedArmor.MOD_ID);
	}
}
