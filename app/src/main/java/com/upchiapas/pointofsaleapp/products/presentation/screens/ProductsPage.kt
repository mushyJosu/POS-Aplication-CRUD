package com.upchiapas.pointofsaleapp.products.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upchiapas.pointofsaleapp.products.domain.model.Product
import com.upchiapas.pointofsaleapp.products.presentation.viewmodels.ProductsUiState
import com.upchiapas.pointofsaleapp.products.presentation.viewmodels.ProductsViewModel
import com.upchiapas.pointofsaleapp.ui.theme.PrimarySalmon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsPage(
    viewModel: ProductsViewModel,
    onLogout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Productos", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { onLogout() }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar Sesión")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddForm = true },
                containerColor = PrimarySalmon
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar", tint = Color.Black)
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (uiState) {
                is ProductsUiState.Idle, is ProductsUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = PrimarySalmon
                    )
                }
                is ProductsUiState.Error -> {
                    Text(
                        text = (uiState as ProductsUiState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is ProductsUiState.Success -> {
                    val products = (uiState as ProductsUiState.Success).products
                    if (products.isEmpty()) {
                        Text(
                            "No hay productos. ¡Agrega uno!",
                            color = Color.Gray,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(products) { product ->
                                ProductItem(
                                    product = product,
                                    onDelete = { viewModel.deleteProduct(product.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)

                val categoryText = if (product.categoryId != null) "Cat: ${product.categoryId}" else "Sin categoría"
                Text(text = "$categoryText • Stock: ${product.stock}", color = Color.Gray, fontSize = 12.sp)

                // Extrae solo la fecha
                val dateStr = if (product.createdAt.length >= 10) product.createdAt.take(10) else product.createdAt
                Text(text = "Agregado: $dateStr", color = Color.LightGray, fontSize = 10.sp)
            }
            Text(
                text = "$${product.price}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 16.dp)
            )
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
            }
        }
    }
}