package com.example.flickr.common.mvp

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.flickr.main.ui.fragments.RecyclerFragment


abstract class BaseActivity : AppCompatActivity() {
    protected fun changeFragment(fragment: RecyclerFragment, id: Int) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(id, fragment)
        transaction.commit()
    }


    protected fun clearBackStack() {
        val manager: FragmentManager = supportFragmentManager
        if (manager.backStackEntryCount > 1) {
            val firstFragment = manager.getBackStackEntryAt(0)
            manager.popBackStack(firstFragment.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}