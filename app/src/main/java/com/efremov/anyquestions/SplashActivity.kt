package com.efremov.anyquestions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.efremov.anyquestions.RootActivity

class SplashActivity : BaseActivity() {

    override val layoutRes: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        Handler().postDelayed({
//            startActivity(MainActivity.start(this))
            startActivity(RootActivity.start(this))
            finish()
        }, 2000)

    }
}
