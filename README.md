fork from macaca

#### build
gradlew clean assembleDebug assembleDebugAndroidTest

#### install app
adb install -t -r app/build/outputs/apk/app-debug.apk
adb install -t -r app/build/outputs/apk/app-debug-androidTest.apk

#### start server
adb shell am instrument -w -e class 'com.macaca.android.testing.UIAutomatorWD' com.macaca.android.testing.test/android.support.test.runner.AndroidJUnitRunner

#### forward
adb forward tcp:9001 tcp:9001

#### toast获取 部分手机自动点击安装/权限确认
app/src/androidTest/java/com/macaca/android/testing/server/listeners/AccessibilityEventListener.java