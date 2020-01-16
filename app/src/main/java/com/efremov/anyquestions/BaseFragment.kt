package com.efremov.anyquestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    companion object {
        private val PROGRESS_TAG = "bf_progress"
    }

    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { restoreState(it) }
    }

    open protected fun restoreState(state: Bundle) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(layoutRes, container, false)

    protected fun showProgressDialog(progress: Boolean) {
        if (!isAdded) return

        val fragment = childFragmentManager.findFragmentByTag(PROGRESS_TAG)
        if (fragment != null && !progress) {
            (fragment as ProgressDialog).dismissAllowingStateLoss()
            childFragmentManager.executePendingTransactions()
        } else if (fragment == null && progress) {
            ProgressDialog().show(childFragmentManager, PROGRESS_TAG)
            childFragmentManager.executePendingTransactions()
        }
    }

    open fun onBackPressed() {}
}