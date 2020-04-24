package com.efremov.anyquestions.features.root

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.efremov.anyquestions.features.questions.QuestionDataBase
import com.efremov.anyquestions.R
import com.efremov.anyquestions.platform.BaseActivity
import com.efremov.anyquestions.platform.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class RootActivity : BaseActivity() {

    override val layoutRes: Int
        get() = R.layout.activity_root

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.mainContainer) as BaseFragment?

    private lateinit var rootViewModel: RootViewModel

//    private lateinit var dbWorkerThread: DbWorkerThread
//    private val uiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        rootViewModel = ViewModelProviders.of(this).get(RootViewModel::class.java)

//        val navController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.tab_questions, R.id.tab_settings)
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

//        dbWorkerThread = DbWorkerThread("dbWorkerThread")
//        dbWorkerThread.start()

//        setData()

        initMainScreen()
    }

//    private fun insertQuestionDataInDb(questionData: QuestionData) {
//        val task = Runnable {
//            App.db?.questionDataDao()?.insert(questionData)
//            Log.d("insertQuestionDataInDb", questionData.questionName)
//        }
//        dbWorkerThread.postTask(task)
//    }

//    private fun fetchQuestionDataFromDb() {
//        val task = Runnable {
//            val questionData = App.db?.questionDataDao()?.getAllQuestions() as List<QuestionData>
//        }
//        dbWorkerThread.postTask(task)
//    }

    override fun onResumeFragments() {
        super.onResumeFragments()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        QuestionDataBase.destroyInstance()
//        dbWorkerThread.quit()
        super.onDestroy()
    }

    private fun initMainScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.mainContainer,
                RootFragment()
            )
            .commitNow()
    }

    companion object {

        fun start(activity: Activity) : Intent {
            val intent = Intent(activity, RootActivity::class.java)
            activity.overridePendingTransition(0, 0)
            return intent
        }
    }

//    fun setData() {
//        val sourceQuestions = resources.getStringArray(R.array.questions_list).toCollection(ArrayList())
//        sourceQuestions.forEachIndexed { index, s ->
//            val questionData = QuestionData(index.toLong(), s)
//            insertQuestionDataInDb(questionData)
//        }
//    }
}
