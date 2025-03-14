package com.kardani.qstiles.tiles

import android.annotation.SuppressLint
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log

class GDGTileStateService : TileService() {

    private var isPlaying = false

    // Called when the user adds your tile.
    override fun onTileAdded() {
        super.onTileAdded()
        Log.d("GDGTileStateService", "onTileAdded")
    }
    // Called when your app can update your tile.
    override fun onStartListening() {
        super.onStartListening()
        Log.d("GDGTileStateService", "onStartListening")
        updateTileState()
    }

    // Called when your app can no longer update your tile.
    override fun onStopListening() {
        super.onStopListening()
        Log.d("GDGTileStateService", "onStopListening")
    }

    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()
        Log.d("GDGTileStateService", "onClick")

        if (isPlaying) {
            isPlaying = false
        } else {
            isPlaying = true
        }
        updateTileState()
    }

    // Called when the user removes your tile.
    override fun onTileRemoved() {
        super.onTileRemoved()
        Log.d("GDGTileStateService", "onTileRemoved")
    }

    private fun updateTileState() {
        qsTile?.apply {
            state = if (isPlaying) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
            label = if (isPlaying) "Playing" else "Paused"
            updateTile()
        }
    }

}