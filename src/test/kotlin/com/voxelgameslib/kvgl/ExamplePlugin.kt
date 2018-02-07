package com.voxelgameslib.kvgl

import com.voxelgameslib.voxelgameslib.module.ModuleInfo
import com.voxelgameslib.voxelgameslib.plugin.VGLPlugin

@ModuleInfo(name = "Example", authors = ["Sxtanna"], version = "1.0.0")
class ExamplePlugin : VGLPlugin() {

    override fun getGameMode() = GAMEMODE


    companion object {

        val GAMEMODE = newGameMode<ExampleGame>("Example")

    }

}