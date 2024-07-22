package org.raul.solarsports.layout

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.raul.solarsports.R
import org.raul.solarsports.model.Product
import org.raul.solarsports.model.ProductSize
import org.raul.solarsports.model.ProductType
import org.raul.solarsports.state.AppViewModel
import org.raul.solarsports.state.appState
import org.raul.solarsports.ui.theme.SolarSportsTheme
import org.raul.solarsports.ui.theme.cardBackgroundColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductLayout(name: String,
                     appViewModel: AppViewModel = appState,
                     modifier: Modifier = Modifier,
                     onSuccess: () -> Unit) {

    val context = LocalContext.current

    val categories = appViewModel.state.categories

    var productName by remember { mutableStateOf("") }


    var categorySelectExpanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("") }

    var productTypeSelectExpanded by remember { mutableStateOf(false) }
    var selectedProductType by remember { mutableStateOf("") }


    var productSizeSelectExpanded by remember { mutableStateOf(false) }
    var selectedProductSize by remember { mutableStateOf("") }

    val errorMessage = stringResource(id = R.string.add_product_validation_error_message)

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

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.Center)
                        .background(cardBackgroundColor, shape = RoundedCornerShape(15.dp))
                ) {

                    OutlinedTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        label = { Text(stringResource(id = R.string.add_product_product_name_lbl), color = Color.White) },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.White)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ExposedDropdownMenuBox(
                        expanded = categorySelectExpanded,
                        onExpandedChange = { categorySelectExpanded = !categorySelectExpanded },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                    ) {
                        TextField(
                            value = selectedCategory,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categorySelectExpanded) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = categorySelectExpanded,
                            onDismissRequest = { categorySelectExpanded = false }
                        ) {
                            categories.forEachIndexed { _, category ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = category.name
                                        )
                                    },
                                    onClick = {
                                        selectedCategory = category.name
                                        categorySelectExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ExposedDropdownMenuBox(
                        expanded = productSizeSelectExpanded,
                        onExpandedChange = { productSizeSelectExpanded = !productSizeSelectExpanded },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                    ) {
                        TextField(
                            value = selectedProductSize,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = productSizeSelectExpanded) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = productSizeSelectExpanded,
                            onDismissRequest = { productSizeSelectExpanded = false }
                        ) {
                            ProductSize.entries.forEachIndexed { _, item ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = item.name
                                        )
                                    },
                                    onClick = {
                                        selectedProductSize = item.name
                                        productSizeSelectExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    ExposedDropdownMenuBox(
                        expanded = productTypeSelectExpanded,
                        onExpandedChange = { productTypeSelectExpanded = !productTypeSelectExpanded },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                    ) {
                        TextField(
                            value = selectedProductType,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = productTypeSelectExpanded) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = productTypeSelectExpanded,
                            onDismissRequest = { productTypeSelectExpanded = false }
                        ) {
                            ProductType.entries.forEachIndexed { _, item ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = item.name
                                        )
                                    },
                                    onClick = {
                                        selectedProductType = item.name
                                        productTypeSelectExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val isValid = productName != "" && selectedCategory != "" && selectedProductSize != "" && selectedProductType != ""
                            if (isValid) {
                                val product = Product(Math.random().toInt(), productName, selectedCategory, ProductSize.valueOf(selectedProductSize), ProductType.valueOf(selectedProductType))
                                appViewModel.addProduct(product)
                                onSuccess()
                            } else {
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }

                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(stringResource(id = R.string.add_product_button))
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddProductLayoutPreview() {
    SolarSportsTheme {
        AddProductLayout("AddProductLayout", onSuccess = {})
    }
}