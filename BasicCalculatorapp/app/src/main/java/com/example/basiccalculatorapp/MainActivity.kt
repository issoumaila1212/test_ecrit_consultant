package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                // Affichage de l'écran de la calculatrice
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Champ de saisie pour le premier nombre
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Nombre 1") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Champ de saisie pour le deuxième nombre
        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Nombre 2") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ligne de boutons pour les opérations
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { result = calculate(number1, number2, '+') }) {
                Text("+")
            }
            Button(onClick = { result = calculate(number1, number2, '-') }) {
                Text("-")
            }
            Button(onClick = { result = calculate(number1, number2, '*') }) {
                Text("*")
            }
            Button(onClick = { result = calculate(number1, number2, '/') }) {
                Text("/")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Affichage du résultat
        result?.let {
            Text(text = "Résultat : $it", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

fun calculate(number1: String, number2: String, operation: Char): String {
    val num1 = number1.toDoubleOrNull()
    val num2 = number2.toDoubleOrNull()

    if (num1 == null || num2 == null) {
        return "Entrée invalide"
    }

    return when (operation) {
        '+' -> (num1 + num2).toString()
        '-' -> (num1 - num2).toString()
        '*' -> (num1 * num2).toString()
        '/' -> if (num2 != 0.0) (num1 / num2).toString() else "Division par zéro"
        else -> "Opération invalide"
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorAppTheme {
        CalculatorScreen()
    }
}
