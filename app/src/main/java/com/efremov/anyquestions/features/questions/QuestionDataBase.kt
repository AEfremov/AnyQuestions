package com.efremov.anyquestions.features.questions

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuestionData::class], version = 1)
abstract class QuestionDataBase : RoomDatabase() {

    abstract fun questionDataDao() : QuestionDataDao

    companion object {

        private var INSTANCE : QuestionDataBase? = null

        fun getInstance(context: Context) : QuestionDataBase? {
            if (INSTANCE == null) {
                synchronized(QuestionDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        QuestionDataBase::class.java,
                        "question.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}