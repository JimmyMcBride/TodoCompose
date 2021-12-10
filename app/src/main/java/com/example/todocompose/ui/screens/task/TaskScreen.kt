package com.example.todocompose.ui.screens.task

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.todocompose.data.models.Priority
import com.example.todocompose.data.models.TodoTask
import com.example.todocompose.util.Action
import com.example.todocompose.viewmodels.SharedViewModel

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: TodoTask?,
    sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val context = LocalContext.current
    
    BackHandler {
        navigateToListScreen(Action.NO_ACTION)
    }

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = { action ->
                    Log.d("TaskScreen", "TaskScreen: $action")
                    if (action == Action.NO_ACTION) {

                        navigateToListScreen(action)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            Log.d("TaskScreenNavigate", "TaskScreen: $action")
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                },
                selectedTask
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                }
            )
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty.",
        Toast.LENGTH_SHORT
    ).show()
}

//@Composable
//fun BackHandler(
//    backPressedDispatcher: OnBackPressedDispatcher? =
//        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
//    onBackPressed: () -> Unit
//) {
//    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)
//
//    val backCallBack = remember {
//        object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                currentOnBackPressed()
//            }
//        }
//    }
//    DisposableEffect(key1 = backPressedDispatcher) {
//        backPressedDispatcher?.addCallback(backCallBack)
//
//        onDispose {
//            backCallBack.remove()
//        }
//    }
//}

//@Composable
//@Preview
//fun TaskScreenPreview() {
//    TaskScreen(
//        navigateToListScreen = {},
//        selectedTask = TodoTask(
//            1,
//            "Hey!",
//            "This is a description...",
//            Priority.HIGH
//        ),
//        sharedViewModel = hiltViewModel()
//    )
//}
