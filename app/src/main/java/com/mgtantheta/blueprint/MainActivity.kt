package com.mgtantheta.blueprint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mgtantheta.blueprint.core.designsystem.theme.BlueprintTheme
import com.mgtantheta.blueprint.navigation.BlueprintNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlueprintTheme {
                BlueprintNavigation()
            }
        }
    }
}
