package com.example.contactapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.contactapp.ui.ContactScreen
import com.example.contactapp.ui.theme.ContactAppTheme
import com.example.contactapp.ui.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ContactViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactAppTheme {
                val state by viewModel.state.collectAsState()
                ContactScreen(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}



