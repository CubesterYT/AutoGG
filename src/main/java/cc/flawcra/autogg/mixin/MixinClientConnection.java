package com.cubester.autogg.mixin;

import com.cubester.autogg.Autogg;
import com.cubester.autogg.ServerConfig;
import com.cubester.autogg.chat.EventDetector;
import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(method = "disconnect", at = @At("HEAD"))
    private void onDisconnect(Text disconnectReason, CallbackInfo ci) {
        if (Autogg.INSTANCE.client.getCurrentServer() == ServerConfig.MINEPLEX) {
            EventDetector.Companion.setMineplexStart(false);
        }
    }
}
