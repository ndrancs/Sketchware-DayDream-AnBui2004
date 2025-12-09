package extensions.anbui.daydream.blocks

object DRBlockHandler {
    @JvmStatic
    fun addViewBlocks(arrayList : ArrayList<HashMap<String, Any>>) {
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["name"] = "setBackgroundResource"
        hashMap["type"] = " "
        hashMap["code"] = "%s.setBackgroundResource(R.drawable.%s);"
        hashMap["color"] = "#4A6CD4"
        hashMap["palette"] = "-1"
        hashMap["spec"] = "%m.view setBackgroundResource %m.drawable"
        arrayList.add(hashMap)
    }
}