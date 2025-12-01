package extensions.anbui.daydream.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

object TextUtils {
    @JvmStatic
    fun isSingleLine(text : String) : Boolean {
        val lines = text.lines().filter { it.isNotBlank() }
        return lines.size == 1
    }

    @JvmStatic
    fun isNumberOnly(content: String): Boolean {
        //Includes do not accept empty strings.
        return content.matches("\\d+".toRegex())
    }

    @JvmStatic
    fun copyToClipboard(context: Context, text: String?) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("From Sketchware DayDream", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied.", Toast.LENGTH_SHORT).show()
    }
}