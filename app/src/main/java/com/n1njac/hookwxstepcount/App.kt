package com.n1njac.hookwxstepcount

import android.app.Application
import kotlin.properties.Delegates

/*    
 *    Created by N1njaC on 2018/4/29.
 *    email:aiai173cc@gmail.com 
 */
class App : Application() {
    companion object {
        var instance by Delegates.notNull<App>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}