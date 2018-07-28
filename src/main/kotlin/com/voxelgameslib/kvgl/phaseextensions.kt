package com.voxelgameslib.kvgl

import com.voxelgameslib.voxelgameslib.feature.Feature
import com.voxelgameslib.voxelgameslib.game.Game
import com.voxelgameslib.voxelgameslib.phase.Phase

// Look into changing the base methods to allow Kotlin to do this automatically
var Phase.allowJoin: Boolean
    get() = allowJoin()
    set(value) = setAllowJoin(value)

var Phase.allowSpectate: Boolean
    get() = allowSpectate()
    set(value) = setAllowSpectate(value)


inline fun <reified T : Feature> Phase.addFeature(to: Game = game, block: T.() -> Unit = { }): T {
    return to.createFeature<T>(this).apply { block(); addFeature(this) }
}

inline fun <reified T : Feature> Phase.createFeature(to: Game = game, block: T.() -> Unit = { }): T {
    return to.createFeature<T>(this).apply(block)
}


inline fun <reified T : Feature> Phase.getFeature(): T {
    return getFeature(T::class.java)
}

inline fun <reified T : Feature> Phase.getOptionalFeature(): T? {
    return getOptionalFeature(T::class.java).orElse(null)
}