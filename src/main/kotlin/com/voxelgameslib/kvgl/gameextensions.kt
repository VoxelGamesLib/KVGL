package com.voxelgameslib.kvgl

import com.voxelgameslib.voxelgameslib.feature.Feature
import com.voxelgameslib.voxelgameslib.game.AbstractGame
import com.voxelgameslib.voxelgameslib.game.Game
import com.voxelgameslib.voxelgameslib.game.GameMode
import com.voxelgameslib.voxelgameslib.phase.Phase

// Maybe change the "get" behaviour of this, or maybe just make a function?
inline var Game.minAndMaxPlayers: Int
    get() = if (minPlayers == maxPlayers) minPlayers else -1
    set(value) {
        minPlayers = value
        maxPlayers = value
    }


inline fun <reified T : Game> newGameMode(name: String): GameMode {
    return GameMode(name, T::class.java)
}


inline fun <reified T : Phase> Game.createPhase(block: (T) -> Unit = { }): T {
    return createPhase(T::class.java).apply(block)
}

inline fun <reified T : Feature> Game.createFeature(phase: Phase): T {
    return createFeature(T::class.java, phase)
}


inline fun AbstractGame.buildPhases(first: Phase, block: PhaseBuilder.() -> Unit) {
    activePhase = first
    PhaseBuilder(first).apply(block)
}


class PhaseBuilder @PublishedApi internal constructor(first: Phase) { // yes, I know its ugly..

    var prev = first

    operator fun Phase.unaryPlus() {
        prev.setNextPhase(this)
        prev = this
    }

}