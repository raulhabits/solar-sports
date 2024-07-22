package org.raul.solarsports.activities

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.raul.solarsports.layout.AddProductLayout
import org.raul.solarsports.layout.ProductListLayout
import org.raul.solarsports.ui.theme.SolarSportsTheme

class AddProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SolarSportsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AddProductLayout(name = "Add Product", modifier = Modifier.padding(innerPadding), onSuccess = this::onSuccess )
                }
            }
        }
    }

    fun onSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}