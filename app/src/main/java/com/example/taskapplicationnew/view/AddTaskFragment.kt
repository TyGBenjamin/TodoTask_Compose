package com.example.taskapplicationnew.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.taskapplicationnew.R
import com.example.taskapplicationnew.model.data.Task
import com.example.taskapplicationnew.ui.theme.TaskApplicationNewTheme
import com.example.taskapplicationnew.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Add email fragment.
 *
 * @constructor Create empty Add email fragment
 */
@AndroidEntryPoint
class AddTaskFragment : Fragment() {
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        AddTask(viewModel = taskViewModel) {
                            findNavController().navigate(R.id.dashboardFragment)
                        }
//                        AddTask(viewModel = taskViewModel) { args ->
//                            val action = AddTaskFragmentDirections.actionAddTaskFragmentToDashboardFragment()
//                            findNavController()
//                                .navigate(action)
//                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTask(
    viewModel: TaskViewModel,
    onNavigate: () -> Unit
) {
    Column(
        Modifier
            .padding(24.dp)
            .wrapContentSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(13.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var taskTitle by remember {
            mutableStateOf("")
        }

        var taskBody by remember {
            mutableStateOf("")
        }

        IconOne()
        TextField(value = taskTitle, onValueChange = { taskTitle = it },
            label = { Label(typeInput = "Task Name?") })
        TextField(value = taskBody, onValueChange = { taskBody = it },
            label = { Label(typeInput = "Task Details?") })
        DividerOne()
        Row(verticalAlignment = Alignment.CenterVertically) {
        }
        DividerOne()
        SpecialButton(text = "Save Task(s)") {
            viewModel.addTask(task = Task(title = taskTitle, body = taskBody))
        }
        SpecialButton(text = stringResource(R.string.viewAll)) {
            onNavigate()
        }
//        Button(
//            onClick = {
//                viewModel.addTask(task = Task(title = taskTitle, body = taskBody))
//            }, modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.White,
//                disabledContainerColor = Color.Gray
//            )
//        ) {
//            Text(
//                text = "Save Task(s)",
//                fontSize = 20.sp,
//                color = Color.Black
//            )
//        }
//        Button(onClick = {
//            val args: Bundle = bundleOf(
//                "taskTitle" to taskTitle,
//                "taskBody" to taskBody,
//            )
//            onNavigate()
//        }) {
//            Text(text = stringResource(R.string.viewAll))
//        }
    }
}

@Composable
fun IconOne() {
    Box(
        modifier = Modifier.padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_note_add_24),
            null,
            Modifier.size(110.dp),
            tint = Color.White
        )
    }
}

/**
 *
 */
@Composable
fun DividerOne() {
    Divider(
        color = Color.White.copy(alpha = 0.3f),
        thickness = 1.dp,
        modifier = Modifier.padding(top = 48.dp)
    )
}


@Composable
fun Label(typeInput: String) {
    Text(
        text = typeInput,
        fontSize = 9.sp
    )
}

@Composable
fun SpecialButton (text: String, onClick: () -> Unit){
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }

    Button(
        onClick = {
            onClick()
        }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            disabledContainerColor = Color.Gray
        )
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}


