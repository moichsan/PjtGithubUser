package com.moichsan.githubusers.ui.setting

import android.os.Bundle
import com.moichsan.githubusers.R
import com.moichsan.githubusers.base.BaseActivity
import com.moichsan.githubusers.data.local.share.SharedPreferencesHelper
import kotlinx.android.synthetic.main.activity_setting.*
import java.util.*

class SettingActivity : BaseActivity() {
    private lateinit var sharedPrefHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setupToolbar(toolbar, resources.getString(R.string.setting), true)
        sharedPrefHelper = SharedPreferencesHelper(this)
        switch_alarm_settings.setOnCheckedChangeListener { _, isChecked ->
            setAlarm(isChecked)
        }
        switch_alarm_settings.isChecked = sharedPrefHelper.getAlarmState()
    }

    private fun setAlarm(isChecked: Boolean) {
        if (isChecked) {
            AlarmNotification.enabledAlarm(
                context = this,
                title = "Github Users App",
                message = "Let's find users on Github !",
                requestCode = 0,
                time = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 9)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }
            )
            sharedPrefHelper.enabledAlarm()
        } else {
            AlarmNotification.disabledAlarm(
                context = this,
                requestCode = 0
            )
            sharedPrefHelper.disabledAlarm()
        }
    }
}