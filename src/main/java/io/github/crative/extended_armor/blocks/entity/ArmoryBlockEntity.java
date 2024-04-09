package io.github.crative.extended_armor.blocks.entity;

import io.github.crative.extended_armor.items.ExtendedArmorItems;
import io.github.crative.extended_armor.screen.ArmoryScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ArmoryBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(16, ItemStack.EMPTY);
	private static final int OUTPUT_SLOT = 15;

	public ArmoryBlockEntity(BlockPos pos, BlockState state) {
		super(ExtendedArmorBlockEntities.ARMORY_BLOCK_ENTITY, pos, state);
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
		buf.writeBlockPos(this.pos);
	}

	@Override
	public Text getDisplayName() {
		return Text.translatable("extended_armor.armory");
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, inventory);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, inventory);
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new ArmoryScreenHandler(i, playerInventory, this);
	}

	public void tick(World world, BlockPos pos, BlockState state){
		if(world.isClient())
			return;

		if(isOutputSlotEmptyOrRecievable()){
			if(this.hasRecipe()){
				markDirty(world, pos, state);
				this.craftItem();
			}
		} else {
			markDirty(world, pos, state);
		}
	}

	private void craftItem() {
		int i = 0;
		for(ItemStack  itemStack: this.inventory){
			removeStack(i, 1);
			i++;
		}
		ItemStack result = new ItemStack(ExtendedArmorItems.OBSIDIAN_CHESTPLATE);

		this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount()+result.getCount()));
	}

	private boolean hasRecipe() {
		ItemStack result = new ItemStack(ExtendedArmorItems.OBSIDIAN_CHESTPLATE);
		int hasInput = 0;
		for(ItemStack stack : inventory) {
			if(stack.getItem() == ExtendedArmorItems.OBSIDIAN_INGOT) {
				hasInput ++;
			}
		}
		return hasInput == 15 && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
	}

	private boolean canInsertItemIntoOutputSlot(Item item) {
		return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
	}

	private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
		return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();

	}

	private boolean isOutputSlotEmptyOrRecievable() {
		return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
	}
}
