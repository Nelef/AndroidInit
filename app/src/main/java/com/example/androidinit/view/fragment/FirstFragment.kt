package com.example.androidinit.view.fragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidinit.base.BaseFragment

class FirstFragment : BaseFragment() {
    init {
        baseCompose.content = {
            val testList = remember {
                mutableListOf(
                    TestData("test1"),
                    TestData("test2"),
                    TestData("test3"),
                    TestData("test4"),
                    TestData("test5")
                )
            }
            TestGrid(testList)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestGrid(list: List<TestData>) {
    var selectedPosition by remember { mutableStateOf(-1) }

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 300.dp)) {
        items(list.size) { index ->
            Card(
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp),
                onClick = { selectedPosition = index }
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Checkbox(
                        checked = selectedPosition == index,
                        onCheckedChange = {
                            selectedPosition = index
                        }
                    )
                    Text(text = list[index].name)
                }
            }
        }
    }
}

data class TestData(
    val name: String
)