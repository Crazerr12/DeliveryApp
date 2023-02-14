package com.example.deliveryapp.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.deliveryapp.presentation.managers.BottomnavigationViewManager

abstract class BaseFragment : Fragment() {

    open val showBottomNavigationView: Boolean
        get() = (parentFragment as? BaseFragment)?.showBottomNavigationView ?: false

    private var bottomNavigationViewManager: BottomnavigationViewManager? = null

    override fun onAttach(context: Context) {
        if (context is BottomnavigationViewManager)
            bottomNavigationViewManager = context
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationViewManager?.setNavigationViewVisibility(showBottomNavigationView)
    }
}