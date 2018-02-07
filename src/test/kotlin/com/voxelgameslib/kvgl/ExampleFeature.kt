package com.voxelgameslib.kvgl

import com.voxelgameslib.voxelgameslib.event.GameEvent
import com.voxelgameslib.voxelgameslib.feature.AbstractFeature
import com.voxelgameslib.voxelgameslib.feature.features.TeamFeature
import org.bukkit.event.entity.PlayerDeathEvent

class ExampleFeature : AbstractFeature() {

    private val log by logByClass<ExampleFeature>()


    override fun start() {

        val teamFeature = phase.getFeature<TeamFeature>()

        phase.game.players.forEach { user ->
            teamFeature.getTeam(user).ifPresent { log.finer("${user.rawDisplayName} is on team ${it.name}") }
        }

    }

    override fun getDependencies() = depend(TeamFeature::class)


    @GameEvent
    fun PlayerDeathEvent.onDeath() {
        // stuff here I guess
    }

}