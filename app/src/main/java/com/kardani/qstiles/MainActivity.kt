package com.kardani.qstiles

import android.annotation.SuppressLint
import android.app.StatusBarManager
import android.content.ComponentName
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kardani.qstiles.tiles.GDGTileService
import com.kardani.qstiles.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                MainContent(
                    onRequestAddTile = {
                        requestTileAdd()
                    }
                )
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun requestTileAdd() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val statusBarManager = getSystemService(Context.STATUS_BAR_SERVICE) as StatusBarManager
            statusBarManager.requestAddTileService(
                ComponentName(this, GDGTileService::class.java),
                "GDG Widget",
                Icon.createWithResource(this, android.R.drawable.star_on),
                {},{}
            )
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    onRequestAddTile: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text =  "GDG Quick Settings Tile",
                    modifier = Modifier.padding(12.dp),
                    fontSize = 24.sp
                )

            }

            FloatingActionButton(
                onClick = onRequestAddTile,
                shape = FloatingActionButtonDefaults.smallShape,
                containerColor = Color.LightGray,
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.BottomEnd)
                    .navigationBarsPadding()
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    AppTheme {
        MainContent(
            onRequestAddTile = {}
        )
    }
}