package extensions.anbui.daydream.project;

import android.util.Log;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.tools.ToolCore;

public class ProjectView {
    public static String TAG = Configs.universalTAG + "ProjectView";

    public static boolean isThisActivityHaveOnBackPressed(String projectID, String activityName) {
        Log.i(TAG, "isThisActivityHaveOnBackPressed: " + projectID + activityName);
        //activityName format: MainActivity
        String result = readViewData(projectID, ProjectUtils.convertXMLNameToJavaName(activityName, false));
        if (result == null) return false;
        return result.contains("onBackPressed");

    }

    public static String readViewData(String projectID, String viewName) {
        Log.i(TAG, "readActivityData: " + projectID + viewName);
        return ProjectData.readFullDataWithDataType(viewName, read(projectID));
    }

    public static String read(String projectID) {
        Log.i(TAG, "read: " + projectID);
        ToolCore.copyToTemp(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/view");
        return ProjectDecryptor.decryptProjectFile(ToolCore.getTempFilePath("view"));
    }
}
