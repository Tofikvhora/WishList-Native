package com.example.wishapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.Routes.Navigation
import com.example.taskmanager.View.HomePage
import com.example.taskmanager.ViewModel.WishViewModel
import com.example.wishapp.ui.theme.WishAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: WishViewModel = viewModel()
            WishAppTheme {
                Surface(Modifier.fillMaxSize(), color = Color.Black) {
                    Navigation(viewModel = viewModel)
                }

            }
        }
    }
}