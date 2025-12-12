package extensions.anbui.daydream.blocks

import com.besome.sketch.editor.LogicEditorActivity

object DRPaletteBlock {
    @JvmStatic
    fun addViewBlocks(logicEditor : LogicEditorActivity) {
        logicEditor.a(" ", "setBackgroundResource")
    }

    @JvmStatic
    fun addStringBlocks(logicEditor : LogicEditorActivity) {
        logicEditor.a(" ", "concatenateVarString")
//        logicEditor.a("s", "setVarStringWithCondition")
//        logicEditor.a("b", "varStringIsEmpty")
    }

    @JvmStatic
    fun addStringOperatorBlocks(logicEditor : LogicEditorActivity) {
//        logicEditor.a("b", "stringIsEmpty")
    }

    @JvmStatic
    fun addSharedPreferencesBlocks(logicEditor : LogicEditorActivity) {
        logicEditor.a("b", "getBooleanSharedPreferences")
        logicEditor.a(" ", "putBooleanSharedPreferences")
        logicEditor.a("d", "getIntSharedPreferences")
        logicEditor.a(" ", "putIntSharedPreferences")

    }
}