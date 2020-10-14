package com.lumi.surfeducationproject.Utils

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

object Exceptions {
    val NETWORK_EXCEPTIONS: List<Class<*>> = Arrays.asList(
        UnknownHostException::class.java,
        SocketTimeoutException::class.java,
        ConnectException::class.java
    )
}