package com.example.liste_filtreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.liste_filtreapp.ui.theme.Liste_filtreappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Liste_filtreappTheme {
                // Main content of the app
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NumberFilterApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NumberFilterApp(modifier: Modifier = Modifier) {
    var threshold by remember { mutableStateOf("") } // State for the threshold input
    val numbersList = (1..100).toList()  // List of numbers from 1 to 100
    val filteredNumbers = numbersList.filter { it > (threshold.toIntOrNull() ?: 0) }  // Filter the list based on the threshold

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input field for threshold
        OutlinedTextField(
            value = threshold,
            onValueChange = { threshold = it },
            label = { Text("Saisissez un seuil") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // LazyColumn to display the filtered list of numbers
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(filteredNumbers) { number ->
                Text(
                    text = number.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumberFilterAppPreview() {
    Liste_filtreappTheme {
        NumberFilterApp()
    }
}
