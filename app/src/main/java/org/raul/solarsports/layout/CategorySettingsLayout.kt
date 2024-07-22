package org.raul.solarsports.layout

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.raul.solarsports.R
import org.raul.solarsports.activities.AddCategoryActivity
import org.raul.solarsports.activities.AddProductActivity
import org.raul.solarsports.model.Category
import org.raul.solarsports.model.Product
import org.raul.solarsports.model.ProductSize
import org.raul.solarsports.model.ProductType
import org.raul.solarsports.state.AppViewModel
import org.raul.solarsports.state.appState
import org.raul.solarsports.ui.theme.SolarSportsTheme
import org.raul.solarsports.ui.theme.cardBackgroundColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategorySettingsLayout(appViewModel: AppViewModel = appState, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val addedCategorySuccessMessage = stringResource(id = R.string.category_settings_add_category_success_lbl)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(context, addedCategorySuccessMessage, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, AddCategoryActivity::class.java)
                    launcher.launch(intent)
                },
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                containerColor = Color.Green
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_product_title),
                    tint = Color.Black
                )
            }
        },
        content = {
            Box(modifier = modifier.padding(10.dp)) {


                // Background Image
                Image(
                    painter = painterResource(id = R.drawable.add_product_background), // Replace with your image resource ID
                    contentDescription = null, // Content description for accessibility
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Column(modifier = modifier) {


                    LazyColumn {
                        items(appState.state.categories) { category ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
                            ) {

                                Text(text = category.name,
                                    color = Color.White,
                                    fontSize = 18.sp)

                                Text(text = category.description, color = Color.White)

                            }
                        }
                    }
                }


            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun CategorySettingsLayoutPreview() {
    SolarSportsTheme {
        CategorySettingsLayout()
    }
}