package extensions.anbui.daydream.project

import extensions.anbui.daydream.configs.Configs
import extensions.anbui.daydream.file.FileUtils

object ProjectInjection {
    @JvmStatic
    fun getLauncherActivity(projectId : String) : String {
        return if (FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectId + Configs.customActivityLauncherConfigDataPath)) {
            val content =
                FileUtils.readTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectId + Configs.customActivityLauncherConfigDataPath).trim()
            content.ifEmpty { "main" }
        } else {
            "main"
        }
    }
}