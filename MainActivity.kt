package nl.mysignal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Forest = Color(0xFF142D27)
private val Green = Color(0xFF269D58)
private val Orange = Color(0xFFE99A27)
private val Red = Color(0xFFD94B4B)
private val Canvas = Color(0xFFF5F7F5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MySignalApp() }
    }
}

private enum class Page(val label: String) {
    Home("Home"), Agenda("Agenda"), Feeling("Mijn gevoel"), Day("Mijn dag"), Profile("Profiel")
}

@Composable
private fun MySignalApp() {
    var page by remember { mutableStateOf(Page.Home) }
    var feeling by remember { mutableStateOf("Nog niet gekozen") }
    var sensory by remember { mutableStateOf("Nog niet gekozen") }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Forest,
            secondary = Green,
            background = Canvas,
            surface = Color.White
        )
    ) {
        Scaffold(
            containerColor = Canvas,
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    val items = listOf(
                        Page.Home to Icons.Default.Home,
                        Page.Agenda to Icons.Default.CalendarMonth,
                        Page.Feeling to Icons.Default.Timeline,
                        Page.Day to Icons.Default.WbSunny,
                        Page.Profile to Icons.Default.Person
                    )
                    items.forEach { (item, icon) ->
                        NavigationBarItem(
                            selected = page == item,
                            onClick = { page = item },
                            icon = { Icon(icon, contentDescription = item.label) },
                            label = { Text(item.label, fontSize = 10.sp) }
                        )
                    }
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (page) {
                    Page.Home -> HomeScreen(feeling, sensory, onOpen = { page = it })
                    Page.Agenda -> PlaceholderScreen(
                        "Mijn agenda",
                        "Hier komt je agenda met pictogrammen, herinneringen en controle op overlappende afspraken."
                    )
                    Page.Feeling -> FeelingScreen(feeling, sensory,
                        onFeeling = { feeling = it }, onSensory = { sensory = it })
                    Page.Day -> PlaceholderScreen(
                        "Mijn dag",
                        "Hier komen je dagroutine, ademhalingsoefeningen en tandenpoetscoach."
                    )
                    Page.Profile -> PlaceholderScreen(
                        "Profiel",
                        "Hier komen je account, privacy-instellingen en persoonlijke voorkeuren."
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(feeling: String, sensory: String, onOpen: (Page) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("MySignal", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Forest)
        Text("Your feelings. Your voice.", color = Color.DarkGray, fontSize = 15.sp)
        Card(colors = CardDefaults.cardColors(containerColor = Forest), shape = RoundedCornerShape(20.dp)) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Fijn dat je er bent.", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                Text("Neem rustig de tijd. Wat heb je nu nodig?", color = Color.White.copy(alpha = .9f))
            }
        }
        InfoCard("Mijn gevoel", feeling, Green) { onOpen(Page.Feeling) }
        InfoCard("Mijn prikkels", sensory, Orange) { onOpen(Page.Feeling) }
        InfoCard("Mijn agenda", "Bekijk je afspraken en planning", Forest) { onOpen(Page.Agenda) }
        InfoCard("Mijn dag", "Routines en oefeningen", Green) { onOpen(Page.Day) }
    }
}

@Composable
private fun InfoCard(title: String, detail: String, accent: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(8.dp, 48.dp).background(accent, RoundedCornerShape(8.dp)))
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title, color = Forest, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Text(detail, color = Color.DarkGray, fontSize = 14.sp)
            }
        }
    }
}

@Composable
private fun FeelingScreen(
    feeling: String,
    sensory: String,
    onFeeling: (String) -> Unit,
    onSensory: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Mijn gevoel", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Forest)
        Text("Hoe voel je je op dit moment?")
        listOf("😊 Heel goed", "🙂 Goed", "😐 Neutraal", "🙁 Minder goed", "😟 Niet goed").forEach {
            ChoiceRow(it, feeling == it) { onFeeling(it) }
        }
        Text("Mijn prikkels", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Forest)
        Text("Hoeveel prikkels ervaar je nu?")
        listOf("🟢 Rustig", "🟡 Een beetje", "🟠 Best veel", "🔴 Heel veel", "🛑 Het is te veel").forEach {
            ChoiceRow(it, sensory == it) { onSensory(it) }
        }
        Text(
            "Je keuze wordt in deze prototypeversie alleen tijdelijk op dit scherm bewaard. Er wordt niets naar een begeleider verstuurd.",
            color = Color.DarkGray,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun ChoiceRow(label: String, selected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = if (selected) Color(0xFFE4F2E8) else Color.White),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(label, modifier = Modifier.weight(1f), color = Forest, fontSize = 16.sp)
            if (selected) Text("Gekozen", color = Green, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun PlaceholderScreen(title: String, description: String) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(title, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Forest)
        Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(18.dp)) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(description, color = Color.DarkGray, fontSize = 16.sp)
                Text("Onderdeel in ontwikkeling", color = Orange, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
