package com.efremov.anyquestions.features.questions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.efremov.anyquestions.features.questions.QuestionData

@Dao
interface QuestionDataDao {

    @Query("SELECT * from questionData")
    fun getAllQuestions() : List<QuestionData>

    @Insert(onConflict = REPLACE)
    fun insert(questionData: QuestionData)

    @Query("DELETE from questionData")
    fun deleteAll()
}