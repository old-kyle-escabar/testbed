package dev.test.engine

import dev.test.annotations.Api
import dev.test.annotations.Hook

/**
 * Represents a simple [Engine] class
 */

@Api("Engine")
class RSEngine {

    @Hook("maxPlayers")
    val maxPlayers: Int = 2048

    var running = false

    @Hook("isRunning")
    val isRunning: Boolean get() = running

    @Hook("start")
    fun start() {
        if(running) {
            println("The engine is already running.")
        } else {
            running = true
            println("The engine has been started.")
        }
    }

    @Hook("stop")
    fun stop() {
        if(!running) {
            println("The engine is not running.")
        } else {
            running = false
            println("The engined has been stopped.")
        }
    }

}