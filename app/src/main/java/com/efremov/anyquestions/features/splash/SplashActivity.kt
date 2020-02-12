package com.efremov.anyquestions.features.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import com.efremov.anyquestions.App
import com.efremov.anyquestions.R
import com.efremov.anyquestions.core.DbWorkerThread
import com.efremov.anyquestions.features.questions.QuestionData
import com.efremov.anyquestions.features.root.RootActivity
import com.efremov.anyquestions.platform.BaseActivity

class SplashActivity : BaseActivity() {

    override val layoutRes: Int
        get() = R.layout.activity_splash

    private lateinit var dbWorkerThread: DbWorkerThread
    private val uiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        dbWorkerThread =
            DbWorkerThread("dbWorkerThread")
        dbWorkerThread.start()

        setData()

        Handler().postDelayed({
//            startActivity(MainActivity.start(this))
            startActivity(RootActivity.start(this))
            finish()
        }, 2000)

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
            App.questionsCount = questionData.size
            App.questionForAnswerCount = questionData.size
        }
        dbWorkerThread.postTask(task)
    }

    fun setData() {
        val sourceQuestions = resources.getStringArray(R.array.questions_list).toCollection(ArrayList())
        sourceQuestions.forEachIndexed { index, s ->
            val questionData =
                QuestionData(
                    index.toLong(),
                    s
                )
            insertQuestionDataInDb(questionData)
        }
        fetchQuestionDataFromDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbWorkerThread.quit()
    }
}
