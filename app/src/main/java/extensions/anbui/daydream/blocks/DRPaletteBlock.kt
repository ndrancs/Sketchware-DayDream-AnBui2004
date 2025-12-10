package extensions.anbui.daydream.blocks

import com.besome.sketch.editor.LogicEditorActivity

object DRPaletteBlock {
    @JvmStatic
    fun addViewBlocks(logicEditor : LogicEditorActivity) {
        logicEditor.a(" ", "setBackgroundResource")
    }

    @JvmStatic
    fun addStringBlocks(logicEditor : LogicEditorActivity) {
        logicEditor.a("b", "stringIsEmpty")
    }
}