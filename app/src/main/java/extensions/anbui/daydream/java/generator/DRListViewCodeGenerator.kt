package extensions.anbui.daydream.java.generator

import extensions.anbui.daydream.utils.TextUtils

object DRListViewCodeGenerator {
    @JvmStatic
    fun listSmoothScrollTo(targetId: String, position: String): String {
        return "${targetId}.smoothScrollToPosition(${if (TextUtils.isNumberOnly(position)) "" else "(int) "}${position});"
    }
}