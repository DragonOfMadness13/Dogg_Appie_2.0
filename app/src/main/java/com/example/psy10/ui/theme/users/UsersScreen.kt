package com.example.psy10.ui.theme.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.psy10.ui.theme.TopBar
import com.example.psy10.data.UserWithDogs





@Composable
fun UsersScreen(
    onNavigateBack: () -> Unit,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val users by viewModel.users.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Zarejestrowani Właściciele",
            canNavigateBack = true,
            onNavigateBack = onNavigateBack
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(users) { userWithDogs ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFADD8E6), // Tło karty (jasny zielony)
                        contentColor = Color.Black
                    )
                    ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(bckground()),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("\uD83D\uDC15\u200D\uD83E\uDDBA", fontSize = 16.sp)
                            }
                            Column {
                                Text(
                                    text = userWithDogs.name,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                if (userWithDogs.dogNames.isNotEmpty()) {
                                    Spacer(
                                        modifier = Modifier
                                            .height(4.dp)
                                    )
                                    Text(
                                        text = "Pieski: ${userWithDogs.dogNames.joinToString(", ")}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
private fun bckground(): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color(0xFF006400), // Ciemna zieleń
            Color(0xFF32CD32), // Zieleń limonkowa
            Color(0xFF98FB98)  // Jasna zieleń
        )
    )
}