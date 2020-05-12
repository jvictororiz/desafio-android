package com.picpay.desafio.android.ui.navigations.mainFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.util.ext.navigateWithAnimation
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainListFragment : Fragment() {
    val viewModel: MainViewModel by viewModel()
    //activityViewModels

    private val adapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_users.adapter = adapter
        subscribe()
        setupListeners()
    }

    private fun subscribe() {
        viewModel.loadUsesrObserver.observe(viewLifecycleOwner, Observer {
            swipe_refresh.isRefreshing = it
        })

        viewModel.alertOfflineObserver.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.errorObserver.observe(viewLifecycleOwner, Observer {
            tv_error.visibility = View.VISIBLE
            rv_users.visibility = View.GONE
            tv_error.text = it
        })

        viewModel.resultUsersObserver.observe(viewLifecycleOwner, Observer {
            tv_error.visibility = View.GONE
            rv_users.visibility = View.VISIBLE
            adapter.users = it
        })
    }

    private fun setupListeners() {
        swipe_refresh.setOnRefreshListener {
            viewModel.findAllUser()
        }
        adapter.eventClick = {
            val action = MainListFragmentDirections.actionMainListFragmentToMainUserDetailedFragment(it)
            findNavController().navigateWithAnimation(action)
        }
    }
}