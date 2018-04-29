package com.n1njac.hookwxstepcount.utils

import android.widget.Toast
import com.n1njac.hookwxstepcount.App

/*    
 *    Created by N1njaC on 2018/4/29.
 *    email:aiai173cc@gmail.com 
 */
fun shortToast(msg:String) = Toast.makeText(App.instance,msg,Toast.LENGTH_SHORT).show()
fun longToast(msg:String) = Toast.makeText(App.instance,msg,Toast.LENGTH_LONG).show()
