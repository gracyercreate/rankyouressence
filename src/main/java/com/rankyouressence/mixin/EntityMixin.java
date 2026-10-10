package com.rankyouressence.mixin;

import com.rankyouressence.Rank;
import com.rankyouressence.RankStore;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "getDisplayName", at = @At("RETURN"), cancellable = true)
    private void rankyouressence$addIcon(CallbackInfoReturnable<Component> cir) {
        Entity self = (Entity) (Object) this;
        if (!(self instanceof Player player)) return;
        Rank rank = RankStore.get(self.level().isClientSide() ? RankStore.CLIENT : RankStore.SERVER, player.getUUID());
        cir.setReturnValue(Component.literal(rank.glyph() + " ").append(cir.getReturnValue()));
    }
}
