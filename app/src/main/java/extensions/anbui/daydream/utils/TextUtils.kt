package extensions.anbui.daydream.utils

object TextUtils {
    @JvmStatic
    fun isSingleLine(text : String) : Boolean {
        val lines = text.lines().filter { it.isNotBlank() }
        return lines.size == 1
    }
}