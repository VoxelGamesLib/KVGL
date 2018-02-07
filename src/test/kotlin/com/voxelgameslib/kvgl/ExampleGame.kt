package com.voxelgameslib.kvgl

import com.voxelgameslib.voxelgameslib.feature.features.TeamSelectFeature
import com.voxelgameslib.voxelgameslib.game.AbstractGame
import com.voxelgameslib.voxelgameslib.game.GameDefinition
import com.voxelgameslib.voxelgameslib.game.GameInfo
import com.voxelgameslib.voxelgameslib.phase.phases.GracePhase
import com.voxelgameslib.voxelgameslib.phase.phases.LobbyWithVotePhase

@GameInfo(name = "Example", author = "Sxtanna", version = "v1.0", description = "Example description")
class ExampleGame : AbstractGame(ExamplePlugin.GAMEMODE) {

    override fun initGameFromModule() {
        minAndMaxPlayers = 2

        val votePhase = createPhase<LobbyWithVotePhase> {
            it.addFeature(createFeature<TeamSelectFeature>(it))
        }

        buildPhases(votePhase) { // will set votePhase to the active phase, and build the phase tree with the `+Phase`
            +createPhase<GracePhase>()
            +createPhase<ExamplePhase>()
        }

        loadMap()
    }

    override fun initGameFromDefinition(gameDefinition: GameDefinition) {
        super.initGameFromDefinition(gameDefinition)

        loadMap()
    }

}