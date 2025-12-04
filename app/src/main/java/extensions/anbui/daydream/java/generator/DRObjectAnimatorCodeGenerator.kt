package extensions.anbui.daydream.java.generator

import extensions.anbui.daydream.utils.TextUtils

object DRObjectAnimatorCodeGenerator {
    @JvmStatic
    fun objectanimatorSetDuration(targetId: String, duration: String): String {
        return "${targetId}.setDuration(${if (TextUtils.isValidInteger(duration)) "" else "(int) "}${duration});"
    }
}