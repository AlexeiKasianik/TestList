package com.itexus.app.ui.details

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itexus.app.logic.details.BaseDetailsViewModel
import com.itexus.app.ui.common.ErrorWidget
import com.itexus.app.ui.common.LoadingWidget

@Composable
fun DetailsScreen(
    viewModel: BaseDetailsViewModel
) {

    val item by viewModel.item.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (item.isPresent) {
            Text(
                text = item.get().toString(),
                fontSize = 28.sp,
                color = Color.Blue,
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
