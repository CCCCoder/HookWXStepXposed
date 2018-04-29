package com.n1njac.hookwxstepcount.hook

import android.annotation.SuppressLint
import com.n1njac.hookwxstepcount.Constans
import com.n1njac.hookwxstepcount.XposedInit.Companion.xsp
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge

/*    
 *    Created by N1njaC on 2018/4/29.
 *    email:aiai173cc@gmail.com 
 */
object StepHook {
    @SuppressLint("PrivateApi")
    fun hook() {
        val c1 = Class.forName("android.hardware.SystemSensorManager\$SensorEventQueue")
        XposedBridge.hookAllMethods(c1, "dispatchSensorEvent", object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                xsp.reload()
                if (xsp.getBoolean(Constans.IS_STEP_OPEN, false)) {
                    (param.args[1] as FloatArray)[0] = (param.args[1] as FloatArray)[0] * (xsp.getString(Constans.CUR_STEP_MULT, "1").toInt())
                }
                super.beforeHookedMethod(param)
            }
        })
    }
}