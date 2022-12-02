package com.example.taskapplicationnew.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.taskapplicationnew.R
import com.example.taskapplicationnew.model.data.Task
import com.example.taskapplicationnew.viewmodel.TaskViewModel
import com.example.taskapplicationnew.ui.theme.TaskApplicationNewTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val taskViewModel by activityViewModels<TaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireActivity()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                TaskApplicationNewTheme {
                    taskViewModel.getTasks()
                    val tasks = taskViewModel.taskList.collectAsState().value
                    Column(

                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        Text(text = "Hello", color = Color.White)
                        HomeScreen(tasks = tasks)
                        Button(
                            onClick = {
                                println("HAS BEEN CLICKED")
                                val action =
                                    DashboardFragmentDirections.actionDashboardFragmentToAddTaskFragment()
                                findNavController().navigate(action)
                            }
                        ) {
                            Text(
                                text = context.getString(R.string.addTaskButton),
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                        Button(
                            onClick = {
                                lifecycleScope.launch {
                                    taskViewModel.taskList.value.forEach { task ->
                                        if (task.isCompleted) {
                                            taskViewModel.deleteTask(task)
                                            taskViewModel.getTasks()
                                        }

                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "Delete Completed Task",
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    tasks: List<Task>
) {
    LazyColumn(state = rememberLazyListState(), modifier = Modifier.padding(5.dp)) {
        items(items = tasks) { task ->
            ContactRow(
                task = task,
            )
        }
    }
}

@Composable
fun ContactRow(task: Task) {
    Column() {
        val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }
        Row(modifier = Modifier.clickable {
            setSnackBarState(!snackbarVisibleState)
        }) {
            Text(
                text = "Task: ${task.title}",
                color = Color.White,
                fontSize = 25.sp
            )
            if (task.isCompleted) {
                Text(
                    text = " ✅",
                    fontSize = 15.sp
                )
            }
        }
        if (snackbarVisibleState) {
            Snackbar(
                action = {
                    if (task.isCompleted != true) {
                        Button(onClick = {
                            if (task.isCompleted != true) task.isCompleted = true
                        }) {
                            Text(text = "Mark as Complete")
                        }
                    } else Button(onClick = {
                        if (task.isCompleted == true) task.isCompleted = false
                    }) {
                        Text(text = "Mark incomplete")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Column() {
                    Text(text = "Task Details: ${task.body}")
                    if (task.isCompleted) {
                        Text(text = "This Task has been completed ✅")
                    }
                }
            }
        }
    }
}

