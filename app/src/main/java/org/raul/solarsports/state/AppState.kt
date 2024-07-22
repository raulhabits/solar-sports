package org.raul.solarsports.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.raul.solarsports.model.Category
import org.raul.solarsports.model.Product
import org.raul.solarsports.model.ProductSize
import org.raul.solarsports.model.ProductType

data class AppState (
    var userName: String = "",

    var categories : MutableList<Category> = mutableStateListOf(
        Category("Vacia", "Terraza vacia, sin asignacion de espacio deportivo"),
        Category("Jardin", "Descripcion para la categoria de jardin"),
        Category("Tennis", "Descripcion para la categoria de tennis"),
        Category("Ping Pong", "Descripcion para la categoria de Ping Pong"),
        Category("Pista de carreras", "Descripcion para la categoria de pista de carreras")
    ),

    var products : MutableList<Product> = mutableStateListOf(
        Product(1, "Product A", "VACIA", ProductSize.SMALL, ProductType.TYPE_1),
        Product(2, "Product B", "TENNIS", ProductSize.MEDIUM, ProductType.TYPE_1),
        Product(3, "Product C", "PISTA DE CARRERAS", ProductSize.LARGE, ProductType.TYPE_2),
        Product(1, "Product A", "JARDIN", ProductSize.MEDIUM, ProductType.TYPE_2),
        Product(2, "Product B", "PING_PONG", ProductSize.MEDIUM, ProductType.TYPE_3),
        Product(3, "Product C", "JARDIN", ProductSize.MEDIUM, ProductType.TYPE_3)
        )
)

class AppViewModel : ViewModel() {
    private val _state = mutableStateOf(AppState())
    val state : AppState = _state.value

    fun setUser(user : String) {
        _state.value.userName = user
    }

    fun addCategory(category: Category) {
        _state.value.categories.add(category)
    }

    fun addProduct(product : Product) {
        _state.value.products.add(product)
    }

    fun removeProduct(product : Product) {
        _state.value.products.removeIf { it == product }
    }

}



val appState = AppViewModel()
