package dev.test.api

interface Engine {

    val maxPlayers: Int

    val isRunning: Boolean

    fun start()

    fun stop()

}