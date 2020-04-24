package com.efremov.anyquestions.features.questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Scroller
import com.efremov.anyquestions.App
import com.efremov.anyquestions.R
import com.efremov.anyquestions.core.DbWorkerThread
import com.efremov.anyquestions.ext.visible
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    private var dbWorkerThread: DbWorkerThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        with(toolbar) {
            setNavigationOnClickListener {
                onBackPressed()
            }
        }

        with(newQuestionView) {
            setScroller(Scroller(applicationContext))
            isVerticalFadingEdgeEnabled = true
            isVerticalScrollBarEnabled = true
            addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable?) {
                    saveQuestionView.isEnabled = s.toString().isNotEmpty()
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }

        saveQuestionView.setOnClickListener {
            try {
                dbWorkerThread =
                    DbWorkerThread("dbWorkerThread")
                dbWorkerThread!!.start()
                insertQuestionDataInDb(
                    QuestionData(
                        1000,
                        newQuestionView.text.toString()
                    )
                )
            } catch (t: Throwable) {
                t.printStackTrace()
            }
            saveQuestionView.isEnabled = false
            saveQuestionView.visible(false)
            questionSavedView.visible(true)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()

        dbWorkerThread?.quit()
    }

    private fun insertQuestionDataInDb(questionData: QuestionData) {
        val task = Runnable {
            App.db?.questionDataDao()?.insert(questionData)
            Log.d("insertQuestionDataInDb", questionData.questionName)
        }
        dbWorkerThread?.postTask(task)
    }
}
