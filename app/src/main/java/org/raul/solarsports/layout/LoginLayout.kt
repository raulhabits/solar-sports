package org.raul.solarsports.layout

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.raul.solarsports.R
import org.raul.solarsports.activities.ProductListActivity
import org.raul.solarsports.activities.TabsActivity
import org.raul.solarsports.state.AppViewModel
import org.raul.solarsports.state.appState
import org.raul.solarsports.ui.theme.SolarSportsTheme
import org.raul.solarsports.ui.theme.cardBackgroundColor


@Composable
fun LoginLayout(name: String, appViewModel: AppViewModel = appState, modifier: Modifier = Modifier){
    val context = LocalContext.current

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.login_page_background), // Replace with your image resource ID
            contentDescription = null, // Content description for accessibility
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )


        // Login Content aligned at the bottom
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
                .background(cardBackgroundColor, shape = RoundedCornerShape(15.dp))
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text =stringResource(id = R.string.app_name),
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text(stringResource(id = R.string.user_name), color = Color.White) },
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
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(id = R.string.password), color = Color.White) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Handle login action
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textStyle = TextStyle(color = Color.White)
            )

            Button(
                onClick = {
                    appViewModel.setUser(userName)
                    val intent = Intent(context, TabsActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(stringResource(id = R.string.login))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginLayoutPreview() {
    SolarSportsTheme {
        LoginLayout("Android")
    }
}