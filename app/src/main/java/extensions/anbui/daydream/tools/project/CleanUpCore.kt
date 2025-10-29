package extensions.anbui.daydream.tools.project

import android.util.Log
import extensions.anbui.daydream.configs.Configs
import extensions.anbui.daydream.file.FileUtils
import java.io.File

object CleanUpCore {

    var TAG: String = Configs.universalTAG + "CleanUpCore"

    @JvmStatic
    fun removeTemporaryFiles(projectID: String?): Boolean {
        try {
            FileUtils.deleteRecursive(File(FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir + projectID + "/"))
            Log.i(
                TAG, "Cleaned: " + FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir + projectID + "/"
            )
            return true
        } catch (e: Exception) {
            Log.e(TAG, "Clean failed: " + e.message, e)
            return false
        }
    }
}