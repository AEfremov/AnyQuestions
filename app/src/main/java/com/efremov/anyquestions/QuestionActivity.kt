package com.efremov.anyquestions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.efremov.anyquestions.ext.visible
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    private lateinit var dbWorkerThread: DbWorkerThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        dbWorkerThread = DbWorkerThread("dbWorkerThread")
        dbWorkerThread.start()

        newQuestionInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                saveQuestionView.isEnabled = s.toString().isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        saveQuestionView.setOnClickListener {
            insertQuestionDataInDb(QuestionData(1000, newQuestionInput.text.toString()))
            saveQuestionView.isEnabled = false
            saveQuestionView.visible(false)
            questionSavedView.visible(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        dbWorkerThread.quit()
    }

    private fun insertQuestionDataInDb(questionData: QuestionData) {
        val task = Runnable {
            App.db?.questionDataDao()?.insert(questionData)
            Log.d("insertQuestionDataInDb", questionData.questionName)
        }
        dbWorkerThread.postTask(task)
    }
}
