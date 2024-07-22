package org.raul.solarsports.layout

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.raul.solarsports.R
import org.raul.solarsports.model.Category
import org.raul.solarsports.state.AppViewModel
import org.raul.solarsports.state.appState
import org.raul.solarsports.ui.theme.SolarSportsTheme
import org.raul.solarsports.ui.theme.cardBackgroundColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategoryLayout(
    name: String,
    appViewModel: AppViewModel = appState,
    modifier: Modifier = Modifier,
    onSuccess: () -> Unit
) {
    val context = LocalContext.current

    val errorMessage = stringResource(id = R.string.category_settings_add_category_error_lbl)

    var newCategoryName by remember { mutableStateOf("") }
    var newCategoryDescription by remember { mutableStateOf("") }

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
                    Text(
                        text = stringResource(id = R.string.category_settings_add_lbl),
                        color = Color.White,
                        fontSize = 22.sp
                    )


                    OutlinedTextField(
                        value = newCategoryName,
                        onValueChange = { newCategoryName = it },
                        label = {
                            Text(
                                stringResource(id = R.string.category_settings_add_category_name_lbl),
                                color = Color.White
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { /* Handle next action */ }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textStyle = TextStyle(color = Color.White)
                    )

                    OutlinedTextField(
                        value = newCategoryDescription,
                        onValueChange = { newCategoryDescription = it },
                        label = {
                            Text(
                                stringResource(id = R.string.category_settings_add_category_description_lbl),
                                color = Color.White
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { /* Handle next action */ }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textStyle = TextStyle(color = Color.White)
                    )

                    Button(
                        onClick = {
                            val isValid =
                                newCategoryName != "" && newCategoryDescription != "" && !appState.state.categories.any { category -> category.name == newCategoryDescription }
                            if (isValid) {
                                val category = Category(newCategoryName, newCategoryDescription)
                                appState.addCategory(category)
                                onSuccess()
                            } else {
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }

                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(stringResource(id = R.string.category_settings_add_category_button_lbl))
                    }
                }

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddCategoryLayoutPreview() {
    SolarSportsTheme {
        AddCategoryLayout("AddProductLayout", onSuccess = {})
    }
}