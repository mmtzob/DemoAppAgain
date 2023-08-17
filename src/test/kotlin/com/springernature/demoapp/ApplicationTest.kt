package com.springernature.demoapp

import org.http4k.client.OkHttp
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ApplicationTest {
    private val client = OkHttp()
    private val server = application().asServer(Jetty(8000))

    @BeforeEach
    fun setup() {
        server.start()
    }

    @AfterEach
    fun teardown() {
        server.stop()
    }

    @Test
    fun `app responds`() {
        assertEquals(
            Status.OK,
            client(Request(Method.GET, "http://localhost:${server.port()}/greeting/japan")).status
        )
    }

    @Test
    fun `app responds by eating path value`() {
        assertEquals(
            "Greetings from austria",
            client(Request(Method.GET, "http://localhost:${server.port()}/greeting/austria")).body.toString()
        )
    }
}