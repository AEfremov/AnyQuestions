package com.efremov.anyquestions

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.efremov.anyquestions.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    companion object {

        fun start(activity: Activity) : Intent {
            val intent = Intent(activity, MainActivity::class.java)
            activity.overridePendingTransition(0, 0)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.getInstance())
                .commitNow()
        }
    }

}
