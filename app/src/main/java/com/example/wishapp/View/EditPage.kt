package com.example.taskmanager.View

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.taskmanager.Model.WishData
import com.example.taskmanager.ViewModel.WishViewModel
import com.example.taskmanager.Widgets.AppBarView
import kotlinx.coroutines.launch


@Composable
fun EditPage(
    id: Long,
    viewModel: WishViewModel,
    navHostController: NavHostController,
) {

    val snackMessage = remember {
        mutableStateOf("")
    }


    val scope = rememberCoroutineScope();
    val scaffoldHostState = remember {
        SnackbarHostState()
    }

    if(id != 0L){
        val wish = viewModel.wishesById(id).collectAsState(initial = WishData(0L,"",""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState  = wish.value.description

    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }


    Scaffold(topBar = {
        AppBarView(
            title = if (id != 0L) "Update Wish" else "Add Wish",
            onBack = { navHostController.popBackStack() })
    }) {
        var context = LocalContext.current;
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            CommonTextFields(value = viewModel.wishTitleState, label = "Title") { it ->
                viewModel.onWishTitleChange(it)
            }
            Spacer(modifier = Modifier.height(10.dp))
            CommonTextFields(value = viewModel.wishDescriptionState, label = "Description") { it ->
                viewModel.onWishDescriptionChange(it)
            }
            Button(onClick = {
                if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()) {
                    if (id != 0L) {
                        viewModel.updateWishes(
                            WishData(
                                id = id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim(),
                            )
                        )
                        scope.launch {
                            navHostController.popBackStack()
                            Toast.makeText(context, "Wish Updated Successfully", Toast.LENGTH_SHORT)
                                .show();
                        }
                    } else {
                        viewModel.addWishes(
                            WishData(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim(),
                            )
                        )
                        scope.launch {
                            navHostController.popBackStack()
                            Toast.makeText(context, "Wish Created Successfully", Toast.LENGTH_SHORT)
                                .show();
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Please fill text fields to add wishes",
                        Toast.LENGTH_SHORT
                    ).show();
                }

            }) {
                Text(text = if (id != 0L) "Update Wish" else "Add Wish")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextFields(value: String, label: String, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = {
            Text(
                text = label,
                color = Color.Black,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedTextColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            focusedLabelColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.Black
        )
    )
}

