package com.efremov.anyquestions.features.settings

import android.content.Intent
import android.os.Bundle
import com.efremov.anyquestions.App
import com.efremov.anyquestions.features.questions.QuestionActivity
import com.efremov.anyquestions.R
import com.efremov.anyquestions.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_settings

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        allQuestionsCountView.text = App.questionsCount.toString()
        answerQuestionsCountView.text = App.questionForAnswerCount.toString()

//        questionsCountView.setOnClickListener {
//
//        }

//        setQuestionsForAnswerView.setOnClickListener {
//
//        }

        newQuestionView.setOnClickListener {
            startActivity(Intent(activity, QuestionActivity::class.java))
        }
    }

    companion object {

        fun getInstance() : SettingsFragment {
            return SettingsFragment()
        }
    }
}