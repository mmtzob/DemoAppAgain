package com.springernature.demoapp

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun application(): HttpHandler {

    return routes(
        "/" bind Method.GET to {
            Response(OK).body("Hello World :)")
        },
        "/eat/{food}" bind Method.GET to {
            Response(OK).body("Yum, thanks for the ${it.path("food") ?: "un-named snack!"}")
        },
        "/internal/status" bind Method.GET to {
            Response(OK).body("health check")
        }
    )
}

fun main() {
    application().asServer(Jetty(8080)).start().block()
}