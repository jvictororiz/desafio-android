package com.picpay.desafio.android.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.ui.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val viewModel: MainViewModel by viewModel()

    private val adapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rv_users.adapter = adapter
        subscribe()
        setupListeners()
        viewModel.findAllUser()
    }

    private fun subscribe() {
        viewModel.loadUsesrObserver.observe(this, Observer {
            swipe_refresh.isRefreshing = it
        })

        viewModel.alertOfflineObserver.observe(this, Observer {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.errorObserver.observe(this, Observer {
            tv_error.visibility = View.VISIBLE
            rv_users.visibility = View.GONE
            tv_error.text = it
        })

        viewModel.resultUsersObserver.observe(this, Observer {
            tv_error.visibility = View.GONE
            rv_users.visibility = View.VISIBLE
            adapter.users = it
        })
    }

    private fun setupListeners() {
        swipe_refresh.setOnRefreshListener {
            viewModel.findAllUser()
        }
    }

}
