package com.n1njac.hookwxstepcount.ui

/*
 *    Created by N1njaC on 2018/4/29.
 *    email:aiai173cc@gmail.com
 */

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.n1njac.hookwxstepcount.Constans
import com.n1njac.hookwxstepcount.R
import com.n1njac.hookwxstepcount.utils.SharedPreferenceUtils
import com.n1njac.hookwxstepcount.utils.shortToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        if (!isModuleActive()) shortToast("Xposed模块未激活！")
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {

        val currentCount = SharedPreferenceUtils.getSP(Constans.CUR_STEP_MULT, "0").toString().toInt() + 1
        tv_mutiple.text = resources.getString(R.string.cur_multiple) + currentCount

        cb_step_switch.isChecked = SharedPreferenceUtils.getSP(Constans.IS_STEP_OPEN, false) as Boolean
        cb_step_switch.setOnCheckedChangeListener({ _, isChecked ->
            SharedPreferenceUtils.putSP(Constans.IS_STEP_OPEN, true)
        })
        sb_multiple.progress = SharedPreferenceUtils.getSP(Constans.CUR_STEP_MULT, "1").toString().toInt()
        sb_multiple.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tv_mutiple.text = resources.getString(R.string.cur_multiple) + (progress + 1)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                SharedPreferenceUtils.putSP(Constans.CUR_STEP_MULT, "" + seekBar.progress)
            }

        })
    }

    private fun isModuleActive() = false

}
