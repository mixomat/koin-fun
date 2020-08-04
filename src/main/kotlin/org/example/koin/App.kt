package org.example.koin

import org.example.koin.hello.HelloService
import org.example.koin.hello.helloModule
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class App : KoinComponent {
    private val helloService by inject<HelloService>()

    fun start() {
        routes(helloRoutingHandler()).asServer(Netty()).start()
    }

    private fun helloRoutingHandler() = "hello" bind Method.GET to { Response(Status.OK).body(helloService.hello()) }
}

fun main() {
    startKoin {
        printLogger()
        modules(helloModule)

    }
    App().start()
}
