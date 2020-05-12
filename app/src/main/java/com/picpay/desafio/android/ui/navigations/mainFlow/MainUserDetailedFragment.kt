package com.picpay.desafio.android.ui.navigations.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.entities.User
import com.picpay.desafio.android.util.ext.setImageFromUrl
import kotlinx.android.synthetic.main.fragment_main_user_detailed.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainUserDetailedFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_user_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navArgs by navArgs<MainUserDetailedFragmentArgs>()
        navArgs.user.apply {
            circle_img.setImageFromUrl(url = img)
            tv_name.text = name
            tv_username.text = username
        }

        findNavController().popBackStack(R.id.action_mainListFragment_to_mainUserDetailedFragment, true)
    }
}