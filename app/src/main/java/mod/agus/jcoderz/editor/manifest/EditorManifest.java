package mod.agus.jcoderz.editor.manifest;

import java.util.ArrayList;
import java.util.HashMap;

import pro.sketchware.xml.XmlBuilder;

/**
 * A helper class to add various elements to AndroidManifest.xml if components have been added,
 */
public class EditorManifest {

    public static void writeDefFCM(XmlBuilder applicationTag) {
        XmlBuilder firebaseMessagingServiceTag = new XmlBuilder("service");
        firebaseMessagingServiceTag.addAttribute("android", "name", "com.google.firebase.messaging.FirebaseMessagingService");
        firebaseMessagingServiceTag.addAttribute("android", "exported", "false");
        XmlBuilder firebaseMessagingServiceIntentFilterTag = new XmlBuilder("intent-filter");
        firebaseMessagingServiceIntentFilterTag.addAttribute("android", "priority", "-500");
        XmlBuilder messagingEventActionTag = new XmlBuilder("action");
        messagingEventActionTag.addAttribute("android", "name", "com.google.firebase.MESSAGING_EVENT");
        firebaseMessagingServiceIntentFilterTag.addChildNode(messagingEventActionTag);
        firebaseMessagingServiceTag.addChildNode(firebaseMessagingServiceIntentFilterTag);
        XmlBuilder firebaseInstanceIdReceiverTag = new XmlBuilder("receiver");
        firebaseInstanceIdReceiverTag.addAttribute("android", "name", "com.google.firebase.iid.FirebaseInstanceIdReceiver");
        firebaseInstanceIdReceiverTag.addAttribute("android", "exported", "true");
        firebaseInstanceIdReceiverTag.addAttribute("android", "permission", "com.google.android.c2dm.permission.SEND");
        XmlBuilder firebaseInstanceIdReceiverIntentFilterTag = new XmlBuilder("intent-filter");
        XmlBuilder receiveActionTag = new XmlBuilder("action");
        receiveActionTag.addAttribute("android", "name", "com.google.android.c2dm.intent.RECEIVE");
        firebaseInstanceIdReceiverIntentFilterTag.addChildNode(receiveActionTag);
        firebaseInstanceIdReceiverTag.addChildNode(firebaseInstanceIdReceiverIntentFilterTag);
        applicationTag.addChildNode(firebaseMessagingServiceTag);
        applicationTag.addChildNode(firebaseInstanceIdReceiverTag);
    }

    public static void manifestOneSignal(XmlBuilder applicationTag, String packageName, HashMap<String, ArrayList<String>> hashMap) {
        if (!packageName.isEmpty()) {
            XmlBuilder receiverTag = new XmlBuilder("receiver");

            receiverTag.addAttribute("android", "name", "com.onesignal.notifications.receivers.FCMBroadcastReceiver");
            receiverTag.addAttribute("android", "exported", "true");
            receiverTag.addAttribute("android", "permission", "com.google.android.c2dm.permission.SEND");

            XmlBuilder intentFilterTag = new XmlBuilder("intent-filter");
            intentFilterTag.addAttribute("android", "priority", "999");
            XmlBuilder actionTag = new XmlBuilder("action");
            actionTag.addAttribute("android", "name", "com.google.android.c2dm.intent.RECEIVE");
            XmlBuilder categoryTag = new XmlBuilder("category");
            categoryTag.addAttribute("android", "name", packageName);

            intentFilterTag.addChildNode(categoryTag);
            intentFilterTag.addChildNode(actionTag);
            receiverTag.addChildNode(intentFilterTag);
            applicationTag.addChildNode(receiverTag);
        }

        XmlBuilder hmsMessageServiceOneSignalTag = new XmlBuilder("service");

        hmsMessageServiceOneSignalTag.addAttribute("android", "name", "com.onesignal.notifications.services.HmsMessageServiceOneSignal");
        hmsMessageServiceOneSignalTag.addAttribute("android", "exported", "false");
        hmsMessageServiceOneSignalTag.addAttribute("android", "permission", "com.google.android.c2dm.permission.SEND");

        XmlBuilder intentFilterTag = new XmlBuilder("intent-filter");
        XmlBuilder actionTag = new XmlBuilder("action");
        actionTag.addAttribute("android", "name", "com.huawei.push.action.MESSAGING_EVENT");

        intentFilterTag.addChildNode(actionTag);
        hmsMessageServiceOneSignalTag.addChildNode(intentFilterTag);
        applicationTag.addChildNode(hmsMessageServiceOneSignalTag);


        XmlBuilder notificationOpenedActivityHMSTag = new XmlBuilder("activity");

        notificationOpenedActivityHMSTag.addAttribute("android", "name", "com.onesignal.notifications.activities.NotificationOpenedActivityHMS");
        notificationOpenedActivityHMSTag.addAttribute("android", "exported", "true");
        notificationOpenedActivityHMSTag.addAttribute("android", "noHistory", "true");
        notificationOpenedActivityHMSTag.addAttribute("android", "theme", "@android:style/Theme.Translucent.NoTitleBar");

        XmlBuilder intentFilterTag1 = new XmlBuilder("intent-filter");
        XmlBuilder actionTag1 = new XmlBuilder("action");
        actionTag1.addAttribute("android", "name", "android.intent.action.VIEW");

        intentFilterTag1.addChildNode(actionTag1);
        notificationOpenedActivityHMSTag.addChildNode(intentFilterTag1);
        applicationTag.addChildNode(notificationOpenedActivityHMSTag);


        XmlBuilder NotificationDismissReceiverTag = new XmlBuilder("receiver");

        NotificationDismissReceiverTag.addAttribute("android", "name", "com.onesignal.notifications.receivers.NotificationDismissReceiver");
        NotificationDismissReceiverTag.addAttribute("android", "exported", "true");

        applicationTag.addChildNode(NotificationDismissReceiverTag);


        XmlBuilder bootUpReceiverTag = new XmlBuilder("receiver");
        bootUpReceiverTag.addAttribute("android", "name", "com.onesignal.notifications.receivers.BootUpReceiver");
        bootUpReceiverTag.addAttribute("android", "exported", "false");
        XmlBuilder bootUpReceiverIntentFilterTag = new XmlBuilder("intent-filter");
        XmlBuilder bootUpReceiverBootCompleteActionTag = new XmlBuilder("action");
        bootUpReceiverBootCompleteActionTag.addAttribute("android", "name", "android.intent.action.BOOT_COMPLETED");
        XmlBuilder bootUpReceiverQuickBootPowerOnActionTag = new XmlBuilder("action");
        bootUpReceiverQuickBootPowerOnActionTag.addAttribute("android", "name", "android.intent.action.QUICKBOOT_POWERON");
        bootUpReceiverIntentFilterTag.addChildNode(bootUpReceiverBootCompleteActionTag);
        bootUpReceiverIntentFilterTag.addChildNode(bootUpReceiverQuickBootPowerOnActionTag);
        bootUpReceiverTag.addChildNode(bootUpReceiverIntentFilterTag);
        applicationTag.addChildNode(bootUpReceiverTag);


        XmlBuilder UpgradeReceiverTag = new XmlBuilder("receiver");

        UpgradeReceiverTag.addAttribute("android", "name", "com.onesignal.notifications.receivers.UpgradeReceiver");
        UpgradeReceiverTag.addAttribute("android", "exported", "true");

        XmlBuilder intentFilterTag3 = new XmlBuilder("intent-filter");
        XmlBuilder actionTag3 = new XmlBuilder("action");
        actionTag3.addAttribute("android", "name", "android.intent.action.MY_PACKAGE_REPLACED");

        intentFilterTag3.addChildNode(actionTag3);
        UpgradeReceiverTag.addChildNode(intentFilterTag3);
        applicationTag.addChildNode(UpgradeReceiverTag);


        XmlBuilder NotificationOpenedActivityTag = new XmlBuilder("activity");

        NotificationOpenedActivityTag.addAttribute("android", "name", "com.onesignal.notifications.activities.NotificationOpenedActivity");
        NotificationOpenedActivityTag.addAttribute("android", "excludeFromRecents", "true");
        NotificationOpenedActivityTag.addAttribute("android", "exported", "true");
        NotificationOpenedActivityTag.addAttribute("android", "noHistory", "true");
        NotificationOpenedActivityTag.addAttribute("android", "taskAffinity", "");
        NotificationOpenedActivityTag.addAttribute("android", "theme", "@android:style/Theme.Translucent.NoTitleBar");

        applicationTag.addChildNode(NotificationOpenedActivityTag);


        XmlBuilder NotificationOpenedActivityAndroid22AndOlderTag = new XmlBuilder("activity");

        NotificationOpenedActivityAndroid22AndOlderTag.addAttribute("android", "name", "com.onesignal.notifications.activities.NotificationOpenedActivityAndroid22AndOlder");
        NotificationOpenedActivityAndroid22AndOlderTag.addAttribute("android", "excludeFromRecents", "true");
        NotificationOpenedActivityAndroid22AndOlderTag.addAttribute("android", "exported", "true");
        NotificationOpenedActivityAndroid22AndOlderTag.addAttribute("android", "noHistory", "true");
        NotificationOpenedActivityAndroid22AndOlderTag.addAttribute("android", "theme", "@android:style/Theme.Translucent.NoTitleBar");

        applicationTag.addChildNode(NotificationOpenedActivityAndroid22AndOlderTag);


        XmlBuilder SyncServiceTag = new XmlBuilder("service");

        SyncServiceTag.addAttribute("android", "name", "com.onesignal.core.services.SyncService");
        SyncServiceTag.addAttribute("android", "exported", "false");
        SyncServiceTag.addAttribute("android", "stopWithTask", "true");

        applicationTag.addChildNode(SyncServiceTag);


        XmlBuilder SyncJobServiceTag = new XmlBuilder("service");

        SyncJobServiceTag.addAttribute("android", "name", "com.onesignal.core.services.SyncJobService");
        SyncJobServiceTag.addAttribute("android", "exported", "false");
        SyncJobServiceTag.addAttribute("android", "permission", "android.permission.BIND_JOB_SERVICE");

        applicationTag.addChildNode(SyncJobServiceTag);


        XmlBuilder PermissionsActivityTag = new XmlBuilder("activity");

        PermissionsActivityTag.addAttribute("android", "name", "com.onesignal.core.activities.PermissionsActivity");
        PermissionsActivityTag.addAttribute("android", "exported", "false");
        PermissionsActivityTag.addAttribute("android", "theme", "@android:style/Theme.Translucent.NoTitleBar");

        applicationTag.addChildNode(PermissionsActivityTag);
    }

    public static void manifestFBGoogleLogin(XmlBuilder applicationTag) {
        XmlBuilder activityTag = new XmlBuilder("activity");
        activityTag.addAttribute("android", "name", "com.google.android.gms.auth.api.signin.internal.SignInHubActivity");
        activityTag.addAttribute("android", "excludeFromRecents", "true");
        activityTag.addAttribute("android", "exported", "false");
        activityTag.addAttribute("android", "theme", "@android:style/Theme.Translucent.NoTitleBar");
        applicationTag.addChildNode(activityTag);
        XmlBuilder serviceTag = new XmlBuilder("service");
        serviceTag.addAttribute("android", "name", "com.google.android.gms.auth.api.signin.RevocationBoundService");
        serviceTag.addAttribute("android", "exported", "true");
        serviceTag.addAttribute("android", "permission", "com.google.android.gms.auth.api.signin.permission.REVOCATION_NOTIFICATION");
        applicationTag.addChildNode(serviceTag);
    }
}
