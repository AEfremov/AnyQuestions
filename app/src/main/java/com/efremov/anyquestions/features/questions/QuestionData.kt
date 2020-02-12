package com.efremov.anyquestions.features.questions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questionData")
data class QuestionData(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "question_name") var questionName: String
) {
    constructor() : this(null, "")
}