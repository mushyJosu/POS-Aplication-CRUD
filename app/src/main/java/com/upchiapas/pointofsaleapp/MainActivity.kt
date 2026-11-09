package com.upchiapas.pointofsaleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.upchiapas.pointofsaleapp.auth.presentation.screens.LoginPage
import com.upchiapas.pointofsaleapp.auth.presentation.screens.RegisterPage
import com.upchiapas.pointofsaleapp.auth.presentation.viewmodels.AuthViewModel
import com.upchiapas.pointofsaleapp.auth.presentation.viewmodels.AuthViewModelFactory
import com.upchiapas.pointofsaleapp.products.presentation.screens.ProductsPage
import com.upchiapas.pointofsaleapp.products.presentation.viewmodels.ProductsViewModel
import com.upchiapas.pointofsaleapp.products.presentation.viewmodels.ProductsViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    //Extraemos el contenedor de inyección de dependencias (AppContainer)
    val context = LocalContext.current
    val appContainer = (context.applicationContext as KtAplication).container

    //Fabricamos los ViewModels inyectándoles los repositorios
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(appContainer.authRepository)
    )

    val productsViewModel: ProductsViewModel = viewModel(
        factory = ProductsViewModelFactory(appContainer.productsRepository)
    )

    //Verificamos si existe el token de sesión evitamos el Login
    val startDestination = if (appContainer.authRepository.isUserLogged()) "home" else "login"

    NavHost(navController = navController, startDestination = startDestination) {

        composable("login") {
            LoginPage(
                viewModel = authViewModel,
                onNavigateToHome = {
                    // Navegamos al Home y destruimos el Login del historial
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterPage(
                viewModel = authViewModel,
                onNavigateToLogin = {
                    // Regresa a la pantalla anterior
                    navController.popBackStack()
                }
            )
        }

        composable("home") {
            ProductsPage(
                viewModel = productsViewModel,
                onLogout = {
                    // Borramos la sesión en el dispositivo y volvemos al Login limpiando el historial
                    appContainer.authRepository.logout()
                    navController.navigate("login") {
                        popUpTo(0)
                    }
                    onNavigateToAddProduct = {
                        navController.navigate("add_product")
                    }
                }
            )
        }
        composable("add_product") {
            AddProductPage(
                viewModel = productsViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}