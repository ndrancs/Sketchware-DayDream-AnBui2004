package extensions.anbui.daydream.settings

import android.content.Context
import androidx.core.content.edit

object DRSettings {
    @JvmStatic
    fun getIsFirstSetup(context: Context): Boolean {
        val sp = context.getSharedPreferences("drIsFirstSetup", Context.MODE_PRIVATE)
        return sp.getBoolean("drIsFirstSetup", true)
    }

    @JvmStatic
    fun setIsFirstSetup(context: Context, value: Boolean) {
        val sp = context.getSharedPreferences("drIsFirstSetup", Context.MODE_PRIVATE)
        sp.edit { putBoolean("drIsFirstSetup", value) }
    }

    @JvmStatic
    fun getUseBackupTool(context: Context): Boolean {
        val sp = context.getSharedPreferences("drUseBackupTool", Context.MODE_PRIVATE)
        return sp.getBoolean("drUseBackupTool", true)
    }

    @JvmStatic
    fun setUseBackupTool(context: Context, value: Boolean) {
        val sp = context.getSharedPreferences("drUseBackupTool", Context.MODE_PRIVATE)
        sp.edit { putBoolean("drUseBackupTool", value) }
    }
}