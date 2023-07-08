package com.example.androidinit.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

open class BaseFragment : Fragment() {

    protected val baseCompose = BaseCompose()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                baseCompose.baseScreen.invoke()
            }
        }
    }

    protected fun navigate(destination: NavDirections) = with(findNavController()) {
        currentDestination?.getAction(destination.actionId)?.let { navigate(destination) }
    }
}