package com.kardani.qstiles.tiles

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.service.quicksettings.TileService
import android.util.Log
import com.kardani.qstiles.MainActivity

class GDGTileService : TileService() {

    // Called when the user adds your tile.
    override fun onTileAdded() {
        super.onTileAdded()
        Log.d("GDGTileService", "onTileAdded")
    }
    // Called when your app can update your tile.
    override fun onStartListening() {
        super.onStartListening()
        Log.d("GDGTileService", "onStartListening")
    }

    // Called when your app can no longer update your tile.
    override fun onStopListening() {
        super.onStopListening()
        Log.d("GDGTileService", "onStopListening")
    }

    // Called when the user taps on your tile in an active or inactive state.
    @SuppressLint("StartActivityAndCollapseDeprecated")
    override fun onClick() {
        super.onClick()
        Log.d("GDGTileService", "onClick")

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startActivityAndCollapse(
                PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
        } else {
            startActivityAndCollapse(intent)
        }
    }

    // Called when the user removes your tile.
    override fun onTileRemoved() {
        super.onTileRemoved()
        Log.d("GDGTileService", "onTileRemoved")
    }

}