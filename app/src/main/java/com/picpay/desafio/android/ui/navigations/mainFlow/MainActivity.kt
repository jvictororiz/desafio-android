package com.picpay.desafio.android.ui.navigations.mainFlow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mainViewModel.findAllUser()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHosMainFragment).navigateUp() || super.onSupportNavigateUp()
    }
}
