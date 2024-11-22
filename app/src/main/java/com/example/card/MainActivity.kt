package com.example.card

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.card.ui.theme.CardTheme
import kotlinx.coroutines.delay
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardTheme {
                    AppointmentCard(
                        name = "Кирилл Шаронов",
                        occupacy = "Программист",
                        photoResId = R.drawable.photo,
                        onClick = {}
                    )
            }
        }
    }
}

@Preview
@Composable
fun AppointmentCardPreview (

){
    Surface(color = Color.White.copy(0.40f)) {
        AppointmentCard(
            name = "Кирилл Шаронов",
            occupacy = "Программист",
            photoResId = R.drawable.photo,
            onClick = {}
        )
    }
}

fun getCurrentTime(): String {
    val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return format.format(Date())
}
fun getCurrentDate(): String{
    val format1 = SimpleDateFormat("MM.dd", Locale.getDefault())
    return format1.format(Date())
}

@Composable
fun AppointmentCard(
    name: String,
    occupacy: String,
    photoResId: Int,
    onClick: () -> Unit
) {
    var currentTime by remember {
        mutableStateOf(getCurrentTime())
    }
    var currentDate by remember {
        mutableStateOf(getCurrentDate())
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Обновляем каждую секунду
            currentTime = getCurrentTime()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = Color.Red
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = photoResId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = name,
                            fontSize = 16.sp,
                            color = Color.White,
                            letterSpacing = 1.sp,
                            textDecoration = TextDecoration.LineThrough,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = occupacy,
                            fontSize = 14.sp,
                            letterSpacing = 1.sp,
                            textDecoration = TextDecoration.Underline,

                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFCBE1FF)
                        )
                    }

                }

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_right),
                    tint = Color.White,
                    contentDescription = null,
                )

            }

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.15f)
                    )
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(36.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.calendar_2),
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )

                    Text(
                        text = currentDate,
                        color = Color.White,
                        fontSize = 12.sp
                    )

                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.clock),
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )

                    Text(
                        text = currentTime,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}