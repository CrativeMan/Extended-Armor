package io.github.crative.extended_armor.screen;

import io.github.crative.extended_armor.blocks.entity.ArmoryBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ArmoryScreenHandler extends ScreenHandler {

	private final Inventory inventory;
	public final ArmoryBlockEntity blockEntity;

	public ArmoryScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf){
		this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()));
	}


	public ArmoryScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
		super(ExtendedArmorScreenHandlers.ARMORY_SCREEN_HANDLER,syncId);
		checkSize((Inventory) blockEntity, 16);
		this.inventory = (Inventory) blockEntity;
		inventory.onOpen(playerInventory.player);
		this.blockEntity = (ArmoryBlockEntity) blockEntity;

		addSlots(inventory);
		addPlayerInventory(playerInventory);
		addPlayerHotbar(playerInventory);
	}

	private void addPlayerHotbar(PlayerInventory inventory) {
		for(int i = 0; i < 9; i++) {
			this.addSlot(new Slot(inventory, i, 8+i*18, 142));
		}
	}

	private void addPlayerInventory(PlayerInventory inventory) {
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; j++){
				this.addSlot(new Slot(inventory, j+i*9+9, 8+j*18, 84+i*18));
			}
		}
	}

	private void addSlots(Inventory inventory) {
		int index = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 5; j++){
				this.addSlot(new Slot(inventory, index, 16+(j*18), 17+(i*18)));
				index ++;
			}
		}
		this.addSlot(new Slot(inventory, 15, 134, 35));
	}

	@Override
	public ItemStack quickTransfer(PlayerEntity player, int invSlot) {
		ItemStack newStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(invSlot);
		if(slot != null && slot.hasStack()) {
			ItemStack originalStack = slot.getStack();
			newStack = originalStack.copy();
			if(invSlot < this.inventory.size()) {
				if(!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)){
					return ItemStack.EMPTY;
				}
			} else if(!this.insertItem(originalStack, 0, this.inventory.size(), false)){
				return ItemStack.EMPTY;
			}

			if(originalStack.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
		}

		return newStack;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUse(player);
	}
}
