package com.efremov.anyquestions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModelProviders

class RootActivity : BaseActivity() {

    override val layoutRes: Int
        get() = R.layout.activity_root

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.mainContainer) as BaseFragment?

    private lateinit var rootViewModel: RootViewModel

    private lateinit var dbWorkerThread: DbWorkerThread
    private val uiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        rootViewModel = ViewModelProviders.of(this).get(RootViewModel::class.java)

        dbWorkerThread = DbWorkerThread("dbWorkerThread")
        dbWorkerThread.start()

        setData()

        initMainScreen()
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

    override fun onResumeFragments() {
        super.onResumeFragments()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        QuestionDataBase.destroyInstance()
        dbWorkerThread.quit()
        super.onDestroy()
    }

    private fun initMainScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, RootFragment())
            .commitNow()
    }

    companion object {

        fun start(activity: Activity) : Intent {
            val intent = Intent(activity, RootActivity::class.java)
            activity.overridePendingTransition(0, 0)
            return intent
        }
    }

    fun setData() {
        val sourceQuestions = resources.getStringArray(R.array.questions_list).toCollection(ArrayList())
        sourceQuestions.forEachIndexed { index, s ->
            val questionData = QuestionData(index.toLong(), s)
            insertQuestionDataInDb(questionData)
        }
    }
}
