package org.zipcoder.utilsmod.mixin.moreCreativeTabs;

import net.minecraftforge.client.gui.CreativeTabsScreenPage;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CreativeTabsScreenPage.class)
public class ForgeCreativeTabsScreenPageMixin {

//    @Inject(method = "isTop", at = @At("RETURN"), cancellable = true)
//    private void injectIsTop(CreativeModeTab tab, CallbackInfoReturnable<Boolean> cir) {
//        cir.setReturnValue(tab.row() == CreativeModeTab.Row.TOP);
//    }
//
//    @Inject(method = "getColumn", at = @At("RETURN"), cancellable = true)
//    private void injectGetColumn(CreativeModeTab tab, CallbackInfoReturnable<Integer> cir) {
//        cir.setReturnValue(tab.column());
//    }

}
