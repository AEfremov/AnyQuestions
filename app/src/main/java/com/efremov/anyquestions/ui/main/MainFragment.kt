package com.efremov.anyquestions.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.efremov.anyquestions.platform.BaseFragment
import com.efremov.anyquestions.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlin.random.Random

class MainFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.main_fragment

    companion object {
        fun getInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var questionsArray: ArrayList<String>
    private var randomQuestionIndex: Int = 0
    var isAnimate = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        val sourceQuestions = resources.getStringArray(R.array.questions_list)
        questionsArray = sourceQuestions.toCollection(ArrayList())

        getYourQuestionView.setOnClickListener {
            if (questionsArray.isNotEmpty()) {
                randomQuestionIndex = Random.nextInt(0, questionsArray.size - 1)
                questionsArray.removeAt(randomQuestionIndex)
                questionAnimation(questionsArray[randomQuestionIndex], questionTextView)
//                questionTextView.text = questionsArray[randomQuestionIndex]
            } else {
                questionAnimation(resources.getString(R.string.questions_empty_label), questionTextView)
//                questionTextView.text = resources.getString(R.string.questions_empty_label)
            }
        }
    }

    private fun questionAnimation(text: String, view: TextView?) {
        if (view != null && !isAnimate) {
            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(
                showAnimatorSet(view),
                toNormalAnimatorSet(view)/*,
                hideAnimatorSet(view)*/
            )
            animatorSet.addListener(getQuestionEndListener(view, text))
            animatorSet.start()
        }
        view!!.animate().alphaBy(0f).alpha(1f).start()
    }

    private fun getQuestionEndListener(view: TextView, text: String): AnimatorListenerAdapter {
        return object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                isAnimate = true
                view.visibility = View.VISIBLE
                view.text = text
                view.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                isAnimate = false
//                view.visibility = View.GONE
//                view.text = ""
                view.setLayerType(View.LAYER_TYPE_NONE, null)
            }
        }
    }

    private fun showAnimatorSet(view: View): AnimatorSet {
        val set = AnimatorSet()
        set.duration = 200
        set.playTogether(
            ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f),
            ObjectAnimator.ofFloat(view, View.SCALE_X, 0.2f, 1.4f),
            ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.2f, 1.4f)
        )
        return set
    }

    private fun toNormalAnimatorSet(view: View): AnimatorSet {
        val set = AnimatorSet()
        set.duration = 200
        set.playTogether(
            ObjectAnimator.ofFloat(view, View.SCALE_X, 1.4f, 1f),
            ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.4f, 1f)
        )
        return set
    }

    private fun hideAnimatorSet(view: View): AnimatorSet {
        val set = AnimatorSet()
        set.duration = 200
        set.playTogether(
            ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f),
            ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0.2f),
            ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 0.2f)
        )
        set.startDelay = 200
        return set
    }

}
