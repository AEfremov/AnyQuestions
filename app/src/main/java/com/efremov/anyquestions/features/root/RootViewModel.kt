package com.efremov.anyquestions.features.root

import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModel
import com.efremov.anyquestions.App
import com.efremov.anyquestions.features.questions.QuestionData
import com.efremov.anyquestions.core.DbWorkerThread

class RootViewModel : ViewModel() {

    private lateinit var dbWorkerThread: DbWorkerThread
    private val uiHandler = Handler()

    init {
        dbWorkerThread =
            DbWorkerThread("dbWorkerThread")
        dbWorkerThread.start()

        setData()
    }

    fun setData() {
//        val sourceQuestions = resources.getStringArray(R.array.questions_list).toCollection(ArrayList())
//        sourceQuestions.forEachIndexed { index, s ->
//            val questionData = QuestionData(index.toLong(), s)
//            insertQuestionDataInDb(questionData)
//        }
    }


    private fun insertQuestionDataInDb(questionData: QuestionData) {
        val task = Runnable {
            App.db?.questionDataDao()?.insert(questionData)
            Log.d("insertQuestionDataInDb", questionData.questionName)
        }
        dbWorkerThread.postTask(task)
    }

    private fun fetchQuestionDataFromDb() {
        val task = Runnable {
            val questionData = App.db?.questionDataDao()?.getAllQuestions() as List<QuestionData>
        }
        dbWorkerThread.postTask(task)
    }
}