package extensions.anbui.daydream.ui

object ColorUtils {
    @JvmStatic
    fun isColorLight(color: Int): Boolean {
        val r = (color shr 16) and 0xFF
        val g = (color shr 8) and 0xFF
        val b = color and 0xFF

        val luminance = 0.299 * r + 0.587 * g + 0.114 * b
        return luminance > 186
    }
}