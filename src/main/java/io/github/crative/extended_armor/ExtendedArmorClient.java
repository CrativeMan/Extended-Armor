package io.github.crative.extended_armor;

import com.mojang.blaze3d.platform.InputUtil;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBind;
import net.minecraft.text.Text;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;

public class ExtendedArmorClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		KeyBind keyBind1 = KeyBindingHelper.registerKeyBinding(new KeyBind(
			"key.extended_armor.test_keybinding_1",
			InputUtil.KEY_C_CODE,
			"key.category.first.test"));
		ClientTickEvents.END.register(client -> {
			while (keyBind1.isPressed()){
				assert client.player != null;
				client.player.sendMessage(Text.literal("Test"), false);
			}
		});
	}
}
