package com.upchiapas.pointofsaleapp.auth.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upchiapas.pointofsaleapp.auth.presentation.viewmodels.AuthUiState
import com.upchiapas.pointofsaleapp.auth.presentation.viewmodels.AuthViewModel
import com.upchiapas.pointofsaleapp.ui.theme.PrimarySalmon

@Composable
fun RegisterPage(
    viewModel: AuthViewModel,
    onNavigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    //Lógica de validación reactiva (Estado Derivado)
    val isMinLength = password.length >= 8
    val isMatching = password.isNotEmpty() && password == confirmPassword
    // El formulario solo es válido si todos los campos están llenos y las reglas se cumplen
    val isFormValid = name.isNotBlank() && email.isNotBlank() && isMinLength && isMatching

    //Si el registro es exitoso, lo regresamos al Login
    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Success) {
            onNavigateToLogin()
            viewModel.resetState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.Store, contentDescription = "Logo", modifier = Modifier.size(48.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Crear Cuenta", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(text = "Empieza a gestionar tus productos", color = Color.Gray, modifier = Modifier.padding(bottom = 24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff, contentDescription = null)
                }
            }
        )

        // Indicador visual de longitud (Mínimo 8 caracteres)
        Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            if (isMinLength) Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF4CAF50), modifier = Modifier.size(16.dp))
            Text(" Mínimo 8 caracteres", color = if (isMinLength) Color(0xFF4CAF50) else Color.Gray, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff, contentDescription = null)
                }
            }
        )

        // Indicador visual de coincidencia de contraseñas
        Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            if (isMatching) Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF4CAF50), modifier = Modifier.size(16.dp))
            Text(" Las contraseñas coinciden", color = if (isMatching) Color(0xFF4CAF50) else Color.Gray, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState is AuthUiState.Error) {
            Text(text = (uiState as AuthUiState.Error).message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(bottom = 16.dp))
        }

        if (uiState is AuthUiState.Loading) {
            CircularProgressIndicator(color = PrimarySalmon)
        } else {
            Button(
                onClick = { viewModel.register(name, email, password) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = isFormValid, // El botón está bloqueado hasta que el formulario sea válido
                colors = ButtonDefaults.buttonColors(containerColor = PrimarySalmon)
            ) {
                Text("Crear Cuenta", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text("¿Ya tienes cuenta? ", color = Color.Gray)
            Text("Inicia sesión", color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { onNavigateToLogin() })
        }
    }
}