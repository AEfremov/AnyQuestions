package com.efremov.anyquestions.features.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.efremov.anyquestions.R
import com.efremov.anyquestions.platform.BaseFragment

class NewQuestionFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_new_question

    private lateinit var viewModel: NewQuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(NewQuestionViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}