package io.github.crative.extended_armor.blocks.entity;

import io.github.crative.extended_armor.ExtendedArmor;
import io.github.crative.extended_armor.blocks.ExtendedArmorModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

public class ExtendedArmorBlockEntities {
	public static final BlockEntityType<ArmoryBlockEntity> ARMORY_BLOCK_ENTITY =
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(ExtendedArmor.MOD_ID, "armory_be"),
			QuiltBlockEntityTypeBuilder.create(ArmoryBlockEntity::new,
				ExtendedArmorModBlocks.ARMORY).build());

	public static void registerBlockEntities() {
		ExtendedArmor.LOGGER.info("Registering block entities for " + ExtendedArmor.MOD_ID);
	}
}
