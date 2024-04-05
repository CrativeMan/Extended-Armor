package io.github.crative.extended_armor.datagen;

import io.github.crative.extended_armor.items.ExtendedArmorItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeCategory;
import net.minecraft.util.Identifier;

public class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateRecipes(RecipeExporter exporter) {
		//* Items
		ShapedRecipeJsonFactory.create(RecipeCategory.MISC, ExtendedArmorItems.OBSIDIAN_INGOT)
				.pattern("XOX")
				.pattern("OXO")
				.pattern("XOX")
			    .ingredient('X', ()->Items.OBSIDIAN)
				.ingredient('O', ()->Items.CRYING_OBSIDIAN)
				.criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
				.criterion(hasItem(Items.CRYING_OBSIDIAN), conditionsFromItem(Items.CRYING_OBSIDIAN))
				.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.OBSIDIAN_INGOT)));


		//* Copper
		createHelmet(Items.COPPER_INGOT, ExtendedArmorItems.COPPER_HELMET)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.COPPER_HELMET)));
		createChestplate(Items.COPPER_INGOT, ExtendedArmorItems.COPPER_CHESTPLATE)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.COPPER_CHESTPLATE)));
		createLeggings(Items.COPPER_INGOT, ExtendedArmorItems.COPPER_LEGGINGS)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.COPPER_LEGGINGS)));
		createBoots(Items.COPPER_INGOT, ExtendedArmorItems.COPPER_BOOTS)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.COPPER_BOOTS)));

		//* Stealth
		createHelmet(ExtendedArmorItems.STEALTH_WRAPPINGS, ExtendedArmorItems.STEALTH_HELMET)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.STEALTH_HELMET)));
		createChestplate(ExtendedArmorItems.STEALTH_WRAPPINGS, ExtendedArmorItems.STEALTH_CHESTPLATE)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.STEALTH_CHESTPLATE)));
		createLeggings(ExtendedArmorItems.STEALTH_WRAPPINGS, ExtendedArmorItems.STEALTH_LEGGINGS)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.STEALTH_LEGGINGS)));
		createBoots(ExtendedArmorItems.STEALTH_WRAPPINGS, ExtendedArmorItems.STEALTH_BOOTS)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.STEALTH_BOOTS)));

		//* Obsidian
		createHelmet(ExtendedArmorItems.OBSIDIAN_INGOT, ExtendedArmorItems.OBSIDIAN_HELMET)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.OBSIDIAN_HELMET)));
		createChestplate(ExtendedArmorItems.OBSIDIAN_INGOT, ExtendedArmorItems.OBSIDIAN_CHESTPLATE)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.OBSIDIAN_CHESTPLATE)));
		createLeggings(ExtendedArmorItems.OBSIDIAN_INGOT, ExtendedArmorItems.OBSIDIAN_LEGGINGS)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.OBSIDIAN_LEGGINGS)));
		createBoots(ExtendedArmorItems.OBSIDIAN_INGOT, ExtendedArmorItems.OBSIDIAN_BOOTS)
			.offerTo(exporter, new Identifier(getRecipeName(ExtendedArmorItems.OBSIDIAN_BOOTS)));

	}

	//* Generators
	private ShapedRecipeJsonFactory createHelmet(Item input, Item output) {
		return ShapedRecipeJsonFactory.create(RecipeCategory.COMBAT, output, 1)
			.pattern("CCC")
			.pattern("C C")
			.pattern("   ")
			.ingredient('C', ()->input)
			.criterion(hasItem(input), conditionsFromItem(input));
	}
	private ShapedRecipeJsonFactory createChestplate(Item input, Item output) {
		return ShapedRecipeJsonFactory.create(RecipeCategory.COMBAT, output, 1)
			.pattern("C C")
			.pattern("CCC")
			.pattern("CCC")
			.ingredient('C', ()->input)
			.criterion(hasItem(input), conditionsFromItem(input));
	}
	private ShapedRecipeJsonFactory createLeggings(Item input, Item output) {
		return ShapedRecipeJsonFactory.create(RecipeCategory.COMBAT, output, 1)
			.pattern("CCC")
			.pattern("C C")
			.pattern("C C")
			.ingredient('C', ()->input)
			.criterion(hasItem(input), conditionsFromItem(input));
	}
	private ShapedRecipeJsonFactory createBoots(Item input, Item output) {
		return ShapedRecipeJsonFactory.create(RecipeCategory.COMBAT, output, 1)
			.pattern("   ")
			.pattern("C C")
			.pattern("C C")
			.ingredient('C', ()->input)
			.criterion(hasItem(input), conditionsFromItem(input));
	}
}
