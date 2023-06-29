package com.itexus.app.uikit.coroutines

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun CoroutineScope.launchWithErrorHandler(
    context: CoroutineContext = EmptyCoroutineContext,
    exception: ((Throwable) -> Unit)? = null,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    loading: MutableStateFlow<Boolean>? = null,
    block: suspend CoroutineScope.() -> Unit,
): Job {
    val wrappedBlock: suspend CoroutineScope.() -> Unit = {
        loading?.emit(true)
        block(this)
        loading?.emit(false)
    }
    return launch(context = context + CoroutineExceptionHandler { _, throwable ->
        exception?.let { it(throwable) }
        loading?.value = false
    }, start, wrappedBlock)
}