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

    @JvmStatic
    fun addStringBlocks(arrayList : ArrayList<HashMap<String, Any>>) {
        var hashMap: HashMap<String, Any> = HashMap()
        hashMap["name"] = "setVarStringWithCondition"
        hashMap["type"] = "s"
        hashMap["code"] = "(%s) ? %s : %s"
        hashMap["color"] = "#EE7D16"
        hashMap["palette"] = "-1"
        hashMap["spec"] = "if %b then %s else %s"
        arrayList.add(hashMap)

        hashMap = HashMap()
        hashMap["name"] = "varStringIsEmpty"
        hashMap["type"] = "b"
        hashMap["code"] = "%s.isEmpty()"
        hashMap["color"] = "#EE7D16"
        hashMap["palette"] = "-1"
        hashMap["spec"] = "%m.varStr isEmpty"
        arrayList.add(hashMap)
    }

    @JvmStatic
    fun addStringOperatorBlocks(arrayList : ArrayList<HashMap<String, Any>>) {
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["name"] = "stringIsEmpty"
        hashMap["type"] = "b"
        hashMap["code"] = "%s.isEmpty()"
        hashMap["color"] = "#5CB722"
        hashMap["palette"] = "-1"
        hashMap["spec"] = "%s isEmpty"
        arrayList.add(hashMap)
    }
}