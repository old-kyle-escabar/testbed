package dev.test

import dev.test.api.Api
import dev.test.api.Engine

object Main {

    val ENGINE_CLASS = "RSEngine"
    val API_CLASS = "Engine"

    val injector = Injector()

    @JvmStatic
    fun main(args: Array<String>) {
        injector.scan()

        this.injectEngine()

        this.startEngineFromApi()
    }

    private fun injectEngine() {
        val engine = injector.inject<Engine>(ENGINE_CLASS)
        Api.engine = engine
    }

    private fun startEngineFromApi() {
        println("Starting engine...")
        Api.engine.start()
        println("Stopping engine...")
        Api.engine.stop()
    }
}
