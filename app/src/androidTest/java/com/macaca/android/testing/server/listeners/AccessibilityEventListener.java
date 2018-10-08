package com.macaca.android.testing.server.listeners;

import android.app.Notification;
import android.app.UiAutomation;
import android.os.Parcelable;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;


public class AccessibilityEventListener implements UiAutomation.OnAccessibilityEventListener{

    public static String toast = "获取toast失败";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if(event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED){
            //获取toast
            Parcelable parcelable = event.getParcelableData();
            if (!(parcelable instanceof Notification)) { // without Notification is Toast
                String packageName = event.getPackageName().toString();
                toast = "" + event.getText().get(0);
                Log.d("Test","TOAST:"+toast);
            }
        }
        else if((event.getEventType() & (AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED | AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED)) != 0){

            if("com.miui.securitycenter".equals(event.getPackageName()) && ("android.widget.FrameLayout".equals(event.getClassName())) || "com.miui.permcenter.install.AdbInstallActivity".equals(event.getClassName()) ){
                //安装APP时，小米弹出的继续安装按钮
                try {
                    UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(By.textContains("安装").clazz("android.widget.Button")).click();
                }catch (Exception e){
                    //ignore
                }
            }else if("com.android.packageinstaller.permission.ui.GrantPermissionsActivity".equals(event.getClassName())){
                //权限允许
                try {
                    for(int i=0;i<5;i++) {
                        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(By.textContains("允许").clazz("android.widget.Button")).click();
                    }
                }catch (Exception e){
                    //ignore
                }
            }else if("com.lbe.security.miui".equals(event.getPackageName()) && "com.lbe.security.ui.PermissionDialog".equals(event.getClassName())){
                //小米权限弹窗
                try {
                    for(int i=0;i<5;i++) {
                        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(By.textContains("允许").clazz("android.widget.Button")).click();
                    }
                }catch (Exception e){
                    //ignore
                }
            }
        }

    }

}
