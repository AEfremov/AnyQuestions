package com.efremov.anyquestions

import android.os.Bundle
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.efremov.anyquestions.ui.main.MainFragment
import kotlinx.android.synthetic.main.fragment_root.*

class RootFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_root

    private lateinit var tabs: HashMap<String, BaseFragment>
    private val tabKeys = listOf(
        tabIdFragmentTag(R.id.tab_questions),
        tabIdFragmentTag(R.id.tab_settings)
    )

    private fun tabIdFragmentTag(id: Int) = "tab_$id"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        AHBottomNavigationAdapter(activity, R.menu.main_bottom_menu).apply {
            setupWithBottomNavigation(bottomBar)
        }
        with(bottomBar) {
            titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE
            accentColor = context.color(R.color.colorPrimary)
            inactiveColor = context.color(R.color.colorPrimaryDark)

            setOnTabSelectedListener { position, wasSelected ->
                if (!wasSelected) showTab(position, currentItem)
                true
            }
        }

        if (savedInstanceState == null) {
            tabs = createNewFragments()
            childFragmentManager.beginTransaction()
                .add(R.id.mainScreenContainer, tabs[tabKeys[0]]!!, tabKeys[0])
                .add(R.id.mainScreenContainer, tabs[tabKeys[1]]!!, tabKeys[1])
                .hide(tabs[tabKeys[1]]!!)
                .commitNow()
            bottomBar.setCurrentItem(0, false)
        } else {
             tabs = findFragments()
        }
    }

    private fun showTab(newItem: Int, oldItem: Int) {
        childFragmentManager.beginTransaction()
            .hide(tabs[tabKeys[oldItem]]!!)
            .show(tabs[tabKeys[newItem]]!!)
            .commit()
    }

    private fun createNewFragments() : HashMap<String, BaseFragment> = hashMapOf(
        tabKeys[0] to MainFragment(),
        tabKeys[1] to SettingsFragment()
    )

    private fun findFragments() : HashMap<String, BaseFragment> = hashMapOf(
        tabKeys[0] to childFragmentManager.findFragmentByTag(tabKeys[0]) as BaseFragment,
        tabKeys[1] to childFragmentManager.findFragmentByTag(tabKeys[1]) as BaseFragment
    )

    override fun onBackPressed() {
        super.onBackPressed()
    }
}