package extensions.anbui.daydream.java.generator

import extensions.anbui.daydream.utils.TextUtils

object DRArrayListCodeGenerator {
    @JvmStatic
    fun getAtListMap(targetId: String, position: String, keyName: String): String {
        return "${targetId}.get(${if (TextUtils.isValidInteger(position)) "" else "(int) "}${position}).get(${keyName}).toString()"
    }
}