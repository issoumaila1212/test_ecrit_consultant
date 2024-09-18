package com.example.formulaireapp

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formulaireapp.ui.theme.FormulaireappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormulaireappTheme {
                FormScreen()
            }
        }
    }
}

@Composable
fun FormScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var validationMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Champ de saisie pour le nom
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Champ de saisie pour l'email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton de soumission
        Button(
            onClick = {
                validationMessage = validateForm(name, email)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Soumettre")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Affichage du message de validation ou d'erreur
        Text(
            text = validationMessage,
            color = if (validationMessage == "Formulaire soumis avec succès") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        )
    }
}

// Fonction de validation
fun validateForm(name: String, email: String): String {
    if (name.isEmpty()) {
        return "Le nom est requis"
    }
    if (email.isEmpty()) {
        return "L'email est requis"
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return "Format de l'email invalide"
    }
    return "Formulaire soumis avec succès"
}

@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
    FormulaireappTheme {
        FormScreen()
    }
}
