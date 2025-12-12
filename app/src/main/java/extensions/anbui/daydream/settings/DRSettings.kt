package extensions.anbui.daydream.settings

import android.content.Context
import androidx.core.content.edit

object DRSettings {
    fun getIsFirstSetup(context: Context): Boolean {
        val sp = context.getSharedPreferences("drIsFirstSetup", Context.MODE_PRIVATE)
        return sp.getBoolean("drIsFirstSetup", true)
    }

    fun setIsFirstSetup(context: Context, value: Boolean) {
        val sp = context.getSharedPreferences("drIsFirstSetup", Context.MODE_PRIVATE)
        sp.edit { putBoolean("drIsFirstSetup", value) }
    }
}