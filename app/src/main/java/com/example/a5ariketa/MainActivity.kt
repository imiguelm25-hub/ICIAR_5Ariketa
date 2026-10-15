package com.example.a5ariketa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a5ariketa.ui.theme._5AriketaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _5AriketaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GGIKalkulua(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GGIKalkulua(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        GGIKalkuluaFondoa()
        GGIKalkuluaTestua()
    }
}

@Composable
fun GGIKalkuluaFondoa(modifier: Modifier = Modifier) {
    val fondoa = painterResource(R.drawable.fondoariketa5)

    Image(
        painter = fondoa,
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun GGIKalkuluaTestua(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Spacer(modifier = modifier.height(36.dp))

        Text(
            text = "Gorputz Gantzaren Indizearen Kalkulagailua",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = modifier.height(36.dp))

        var pisua by remember { mutableStateOf("") }
        var altuera by remember { mutableStateOf("") }
        var adina by remember { mutableStateOf("") }
        var sexua by remember { mutableStateOf("") }

        //Pisua
        TextField(
            value = pisua,
            onValueChange = { pisua = it },
            label = { Text("Pisua (kg)") },
            placeholder = { Text("adib. 75.5") },
        )

        //Altuera
        TextField(
            value = altuera,
            onValueChange = { altuera = it },
            label = { Text("Altuera (cm)") },
            placeholder = { Text("adib. 175") }
        )

        //Adina
        TextField(
            value = adina,
            onValueChange = { adina = it },
            label = { Text("Adina") },
            placeholder = { Text("adib. 25") }
        )

        //Sexua
        TextField(
            value = sexua,
            onValueChange = { sexua = it },
            label = { Text("Sexua (g edo e)") },
            placeholder = { Text("g / e") }
        )

        val pisuaVal = pisua.toDoubleOrNull()
        val altueraVal = altuera.toDoubleOrNull()
        val adinaVal = adina.toDoubleOrNull()
        val sexuaVal = if (sexua.lowercase().startsWith("g")) 1 else 0

        val GMI: Double? =
            if (pisuaVal != null && altueraVal != null && altueraVal > 0) {
                pisuaVal / ((altueraVal / 100) * (altueraVal / 100))
            } else null

        val GGI: Double? =
            if (GMI != null && adinaVal != null && sexua.isNotBlank()) {
                (1.20 * GMI) + (0.23 * adinaVal) - (10.8 * sexuaVal) - 5.4
            } else null

        Spacer(modifier = modifier.height(16.dp))

        Column(
            modifier = Modifier
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Kalkulatutako GMI: $GMI",
                fontSize = 18.sp
            )

            Text(
                text = "Gorputz Gantzaren Prozentjea: $GGI",
                fontSize = 18.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GGIKalkuluaPreview() {
    _5AriketaTheme {
        GGIKalkulua()
    }
}