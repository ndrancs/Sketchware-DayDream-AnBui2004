package extensions.anbui.daydream.setup

import android.content.Context
import extensions.anbui.daydream.configs.Configs
import extensions.anbui.daydream.library.DRFeatureManager
import extensions.anbui.daydream.settings.DRSettings
import mod.hilal.saif.activities.tools.ConfigActivity
import pro.sketchware.R

object DRSetup {
    @JvmStatic
    fun startUp(context : Context) {
        DRFeatureManager.forMinSDK = context.resources.getInteger(R.integer.for_min_sdk)
        DRFeatureManager.isAllowRelease = context.resources.getBoolean(R.bool.enable_relase_app)
        DRFeatureManager.isAllowR8 = context.resources.getBoolean(R.bool.enable_r8)
    }

    @JvmStatic
    fun startNow(context : Context) {
        if (DRSettings.getIsFirstSetup(context)) {
            DRSettings.setIsFirstSetup(context, false)
            if (DRFeatureManager.forMinSDK > 32)
                ConfigActivity.setSetting(ConfigActivity.SETTING_SHOW_BUILT_IN_BLOCKS, true)
        }
    }
}