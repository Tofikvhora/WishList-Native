package com.example.taskmanager.View

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.taskmanager.Model.DummyListData
import com.example.taskmanager.Model.RoutesNames
import com.example.taskmanager.Model.WishData
import com.example.taskmanager.ViewModel.WishViewModel
import com.example.taskmanager.Widgets.AppBarView
import androidx.compose.material3.rememberSwipeToDismissBoxState as rememberSwipeToDismissBoxState1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navHostController: NavHostController,viewModel: WishViewModel) {
    val context = LocalContext.current;
    Scaffold(topBar = {
        AppBarView(title = "Wishlist", onBack = {
            Toast.makeText(context, "BackButtonClicked", Toast.LENGTH_SHORT).show();
        })
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(RoutesNames.editPage.route + "/0L");
                },
                containerColor = Color.Black,
                contentColor = Color.White,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.padding(all = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    Modifier.size(25.dp)
                )
            }
        }
    ) {
        val wishList = viewModel.getAllWish.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishList.value, key = {wish -> wish.id}) { items ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {it ->
                        if(it == SwipeToDismissBoxValue.EndToStart){
                            viewModel.deleteWishes(items);
                        }
                        true
                    }
                )

                SwipeToDismissBox(state = dismissState, enableDismissFromEndToStart = true, backgroundContent = {},) {
                    WishListItem(wish = items) {
                        val id = items.id
                        navHostController.navigate(RoutesNames.editPage.route + "/$id")
                    }
                }

            }
        }
    }
}

@Composable
fun WishListItem(wish: WishData, onClick: () -> Unit,) {
    Card(
        onClick = { onClick() },
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(10),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .size(height = 60.dp, width = 0.dp),
        colors = CardDefaults.cardColors(contentColor = Color.White, containerColor = Color.Black)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
            Row (horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = wish.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif,
                        color = Color.White
                    )
                    Text(
                        text = wish.description,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraLight,
                        fontFamily = FontFamily.Serif,
                        color = Color.White
                    )
                }

            }

        }

    }
}