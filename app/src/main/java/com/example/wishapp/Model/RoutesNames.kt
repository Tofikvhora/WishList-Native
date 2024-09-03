package com.example.taskmanager.Model

sealed class RoutesNames(val route :String){
    object homepage : RoutesNames("HomePage")
    object editPage : RoutesNames("EditPage")
}