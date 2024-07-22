package org.raul.solarsports.layout

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.raul.solarsports.R
import org.raul.solarsports.activities.AddProductActivity
import org.raul.solarsports.model.Product
import org.raul.solarsports.model.ProductSize
import org.raul.solarsports.model.ProductType
import org.raul.solarsports.state.AppViewModel
import org.raul.solarsports.state.appState
import org.raul.solarsports.ui.theme.SolarSportsTheme
import org.raul.solarsports.ui.theme.cardBackgroundColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeLayout(name: String, appViewModel: AppViewModel = appState, modifier: Modifier = Modifier) {

    val productList = appViewModel.state.products

    val message = if (productList.isEmpty()) {
        stringResource(id = R.string.home_no_report_available)
    } else {
        val totalSavings = productList.map { product -> product.type.capacity() * product.size.value() }.sum()
        String.format(stringResource(id = R.string.home_report), totalSavings, productList.size)
    }

    Surface(
        content = {
            Box(modifier = modifier.padding(10.dp)) {

                // Background Image
                Image(
                    painter = painterResource(id = R.drawable.add_product_background), // Replace with your image resource ID
                    contentDescription = null, // Content description for accessibility
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Center),
                    colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
                ) {

                    Text(text = String.format(stringResource(id = R.string.home_greetings), appViewModel.state.userName),
                        color = Color.White,
                        fontSize = 20.sp)

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = message, color = Color.White)


                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun HomeLayoutPreview() {
    SolarSportsTheme {
        HomeLayout("ProductListLayout")
    }
}