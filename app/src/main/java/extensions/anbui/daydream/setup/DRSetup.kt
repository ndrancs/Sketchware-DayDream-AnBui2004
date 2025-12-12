package extensions.anbui.daydream.setup

import android.content.Context
import extensions.anbui.daydream.configs.Configs
import extensions.anbui.daydream.settings.DRSettings
import mod.hilal.saif.activities.tools.ConfigActivity

object DRSetup {
    @JvmStatic
    fun startNow(context : Context) {
        if (DRSettings.getIsFirstSetup(context)) {
            DRSettings.setIsFirstSetup(context, false)
            if (Configs.forMinSDK > 32)
                ConfigActivity.setSetting(ConfigActivity.SETTING_SHOW_BUILT_IN_BLOCKS, true)
        }
    }
}