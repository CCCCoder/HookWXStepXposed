package com.n1njac.hookwxstepcount

import com.n1njac.hookwxstepcount.hook.StepHook
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import kotlin.properties.Delegates

/*    
 *    Created by N1njaC on 2018/4/29.
 *    email:aiai173cc@gmail.com 
 */
class XposedInit : IXposedHookLoadPackage {
    companion object {
        var xsp by Delegates.notNull<XSharedPreferences>()
    }

    init {
        xsp = XSharedPreferences(BuildConfig.APPLICATION_ID, "config")
        xsp.makeWorldReadable()
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (lpparam.packageName) {
            "com.tencent.mm" -> {
                xsp.reload()
                StepHook.hook()
            }
            "com.n1njac.hookwxstepcount" -> {
                XposedHelpers.findAndHookMethod("com.n1njac.hookwxstepcount.ui.MainActivity", lpparam.classLoader,
                        "isModuleActive", XC_MethodReplacement.returnConstant(true))
            }
        }
    }
}