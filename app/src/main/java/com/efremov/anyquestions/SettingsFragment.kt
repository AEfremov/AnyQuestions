package com.efremov.anyquestions

import android.content.Intent
import android.os.Bundle
import com.efremov.anyquestions.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_settings

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        allQuestionsCountView.text = App.questionsCount.toString()
        answerQuestionsCountView.text = App.questionsCount.toString()

        questionsCountView.setOnClickListener {

        }

        newQuestionView.setOnClickListener {
            startActivity(Intent(activity, QuestionActivity::class.java))
        }
    }

    companion object {

    }
}