package extensions.anbui.daydream.java.generator

object DRViewCodeGenerator {
    @JvmStatic
    fun setAlpha(viewId: String, value: String): String {
        return "$viewId.setAlpha(${DRJavaCodeGenerator.castToFloatIfNeeded(value)});"
    }
}