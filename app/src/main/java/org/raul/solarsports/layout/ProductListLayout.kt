package org.raul.solarsports.layout

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
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
import org.raul.solarsports.state.AppViewModel
import org.raul.solarsports.state.appState
import org.raul.solarsports.ui.theme.SolarSportsTheme
import org.raul.solarsports.ui.theme.cardBackgroundColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductListLayout(name: String, appViewModel: AppViewModel = appState, modifier: Modifier = Modifier) {

    val productList = appViewModel.state.products

    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, AddProductActivity::class.java)
                    context.startActivity(intent)
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

            Box(modifier = modifier) {

                // Background Image
                Image(
                    painter = painterResource(id = R.drawable.product_list_page_background), // Replace with your image resource ID
                    contentDescription = null, // Content description for accessibility
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                LazyColumn {
                    items(productList) { product ->
                        ProductListItemLayout(product = product, appViewModel)
                    }
                }
            }
        }
    )
}

@Composable
fun ProductListItemLayout(product: Product, appViewModel: AppViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = product.name,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = String.format(stringResource(id = R.string.product_list_category_prefix), product.category),
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = String.format(stringResource(id = R.string.product_list_size_prefix), product.size),
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = String.format(stringResource(id = R.string.product_list_type_prefix), product.type),
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = String.format(stringResource(id = R.string.product_list_savings_prefix), product.size.value() * product.type.capacity()),
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = { appViewModel.removeProduct(product) }
                ) {
                    Text(stringResource(id = R.string.product_list_remove_product),
                        fontSize = 18.sp,
                        color = Color.Red)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListLayoutPreview() {
    SolarSportsTheme {
        ProductListLayout("ProductListLayout")
    }
}