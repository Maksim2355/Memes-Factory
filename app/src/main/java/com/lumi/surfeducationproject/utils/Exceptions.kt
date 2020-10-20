package com.lumi.surfeducationproject.utils

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Exceptions {
    val NETWORK_EXCEPTIONS: List<Class<*>> = listOf(
        UnknownHostException::class.java,
        SocketTimeoutException::class.java,
        ConnectException::class.java
    )
}