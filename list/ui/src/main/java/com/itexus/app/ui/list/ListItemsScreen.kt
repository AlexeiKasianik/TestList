package com.itexus.app.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itexus.app.data.model.Item
import com.itexus.app.logic.listRandomValues.BaseRandomValuesViewModel
import com.itexus.app.ui.common.ErrorWidget
import com.itexus.app.ui.common.LoadingWidget

@Composable
fun ListItemsScreen(
    viewModel: BaseRandomValuesViewModel,
    onItemClick: (Int) -> Unit,
) {

    val listItems by viewModel.listItems.collectAsState()
    val error by viewModel.error.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (listItems.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                listItems,
                itemContent = {
                    when (it) {
                        is Item.Move -> ItemMove(it, onItemClick)
                        is Item.Event -> ItemEvent(it, onItemClick)
                        is Item.Notice -> ItemNotice(it, onItemClick)
                    }
                }
            )
        }
    }

    if (isLoading) {
        LoadingWidget(modifier = Modifier.fillMaxSize())
    }

    if (error.isNotEmpty()) {
        ErrorWidget(
            modifier = Modifier.fillMaxSize(),
            errorMessage = error,
        )
    }
}


@Composable
fun ItemNotice(item: Item.Notice, onItemClick: (Int) -> Unit) {
    Text(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(item.id) },
        text = "${item.title} - ${item.details}",
        fontSize = 22.sp,
        color = Color.Black,
        maxLines = 2,
    )
}

@Composable
fun ItemMove(item: Item.Move, onItemClick: (Int) -> Unit) {
    Text(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(item.id) },
        text = "${item.title} - ${item.details}",
        fontSize = 28.sp,
        color = Color.Blue,
        maxLines = 2,
    )
}

@Composable
fun ItemEvent(item: Item.Event, onItemClick: (Int) -> Unit) {
    Text(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(item.id) },
        text = "${item.title} - ${item.details}",
        fontSize = 24.sp,
        color = Color.Green,
        maxLines = 2,
    )
}
