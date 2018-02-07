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


inline fun AbstractGame.buildPhases(first: Phase, block: PhaseBuilder0.() -> Unit) {
    activePhase = first
    PhaseBuilder0(first).apply(block)
}

inline fun AbstractGame.buildPhases(block: PhaseBuilder1.() -> Unit) {
    PhaseBuilder1(this).apply(block)
}


class PhaseBuilder0 @PublishedApi internal constructor(first: Phase) { // yes, I know its ugly..

    var prev = first


    operator fun Phase.unaryPlus() {
        prev.setNextPhase(this)
        prev = this
    }

}

class PhaseBuilder1 @PublishedApi internal constructor(val absGame : AbstractGame) {

    private var prev : Phase? = null


    operator fun Phase.unaryPlus() {
        if (prev == null) {
            prev = this
            absGame.activePhase = this
        }
        else {
            prev?.setNextPhase(this)
            prev = this
        }
    }

}