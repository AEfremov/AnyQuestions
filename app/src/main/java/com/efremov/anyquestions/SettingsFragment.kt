package com.efremov.anyquestions

import android.os.Bundle
import com.efremov.anyquestions.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_settings

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        questionsSizeLayout.setOnClickListener {

        }

        newQuestionLayout.setOnClickListener {
            
        }
    }

    companion object {

    }
}