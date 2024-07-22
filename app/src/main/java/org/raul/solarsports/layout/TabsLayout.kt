package org.raul.solarsports.layout

import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.raul.solarsports.R
import org.raul.solarsports.ui.theme.SolarSportsTheme

@Composable
fun TabsLayout(modifier: Modifier = Modifier) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Home", "Paneles", "Categorias")

    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        when (index) {
                            0 -> Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = stringResource(id = R.string.add_product_title),
                                tint = Color.Black
                            )
                            1 -> Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = stringResource(id = R.string.add_product_title),
                                tint = Color.Black
                            )
                            2 -> Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = stringResource(id = R.string.add_product_title),
                                tint = Color.Black
                            )
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> HomeLayout("")
            1 -> ProductListLayout("")
            2 -> CategorySettingsLayout()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabsLayoutPreview() {
    SolarSportsTheme {
        TabsLayout()
    }
}