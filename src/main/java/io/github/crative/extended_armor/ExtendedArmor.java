package io.github.crative.extended_armor;

import io.github.crative.extended_armor.items.ExtendedArmorItemGroup;
import io.github.crative.extended_armor.items.ExtendedArmorItems;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtendedArmor implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Example Mod");
	public static final String MOD_ID = "extended_armor";

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Initializing mod " + MOD_ID);
		ExtendedArmorItems.registerModItems();
		LOGGER.info("Registered mod items for " + MOD_ID);
		ExtendedArmorItemGroup.registerItemGroup();
		LOGGER.info("Registered item group for " + MOD_ID);
	}
}
