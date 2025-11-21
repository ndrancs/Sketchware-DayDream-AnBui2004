package extensions.anbui.daydream.project

import android.app.Activity
import android.content.Context
import android.util.Log
import extensions.anbui.daydream.configs.Configs
import extensions.anbui.daydream.file.FileUtils
import extensions.anbui.daydream.ui.DialogUtils
import pro.sketchware.R

object DRProjectTracker {
    @JvmStatic
    var TAG: String = Configs.universalTAG + "DRProjectTracker"

    @JvmStatic
    var configData: String = ""
    @JvmStatic
    var buildConfigData: String = ""

    @JvmStatic
    fun startNow(projectID: String?) {
        if (projectID == null) return

        Configs.currentProjectID = projectID
        val contentDRConfigs =
            FileUtils.readTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/DataDayDream.json")
        configData = contentDRConfigs.ifEmpty { "{}" }

        val contentBuildConfigs =
            FileUtils.readTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/build_config")
        buildConfigData = contentBuildConfigs.ifEmpty { "{}" }

        Log.i(TAG, "Loaded configs data.")
    }

    @JvmStatic
    fun isAllowBuildNow(context: Context?): Boolean {
        if (context != null && Configs.isBuilding) {
            DialogUtils.oneDialog(
                context as Activity?,
                "Oops!",
                "Another project is building, please wait for it to complete before building in this project.",
                "OK",
                true,
                R.drawable.ic_mtrl_warning,
                true,
                null,
                null
            )
        }

        return !Configs.isBuilding
    }
}