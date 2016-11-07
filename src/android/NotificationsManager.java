package cordova.plugin.notificationsmanager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.util.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class NotificationsManager extends CordovaPlugin {

    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final String LOG_TAG = "NotificationsManager";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.i(LOG_TAG, "execute");
        if (action.equals("isNotificationEnabled")) {
            this.isNotificationEnabled(callbackContext);
            return true;
        }
        return false;
    }

    /**
     * Check if the notifications is enabled
     * @return
     */
    public void isNotificationEnabled(CallbackContext callbackContext) {
        Log.i(LOG_TAG, "isNotificationEnabled");
        Context mContext = this.cordova.getActivity().getApplicationContext();
        AppOpsManager mAppOps = (AppOpsManager) mContext.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = mContext.getApplicationInfo();
        String pkg = mContext.getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        Class appOpsClass = null;

        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (Integer)opPostNotificationValue.get(Integer.class);
            boolean enabled = ((Integer)checkOpNoThrowMethod.invoke(mAppOps,value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
            callbackContext.success(String.valueOf(enabled));

        } catch (Exception e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }

        callbackContext.success(String.valueOf(false));
    }
}
