package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppRoot(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppRoot(modifier: Modifier = Modifier) {
    var screen by remember { mutableIntStateOf(0) }

    if (screen == 0) {
        HomeScreen(modifier = modifier, onSelect = { screen = it })
    } else if (screen == 1) {
        ActivityInboxScreen(modifier = modifier)
    } else if (screen == 2) {
        UserProfileScreen(modifier = modifier)
    } else {
        SponsorDashboardScreen(modifier = modifier)
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onSelect: (Int) -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { onSelect(1) }) {
            Text(text = "Activity Inbox", fontSize = 16.sp)
        }
        Button(onClick = { onSelect(2) }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "User Profile", fontSize = 16.sp)
        }
        Button(onClick = { onSelect(3) }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Sponsor Dashboard", fontSize = 16.sp)
        }
    }
}

@Composable
fun ActivityInboxScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                Text(text = "Activity Inbox", fontSize = 28.sp)
                Text(
                    text = "Nearby runner, live events, and campus travel alerts.",
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
        item {
            Text(
                text = "3 unread messages",
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, bottom = 8.dp)
            )
        }
        item { NotificationRow("Event 1 Notification", "2m ago", "0.2 mile away") }
        item { NotificationRow("Event 2 Notification", "8m ago", "14 runners checked in") }
        item { NotificationRow("Sarah is starting warmup", "14m ago", "0.5 mile away") }
        item { NotificationRow("Campus Loop Group Run", "22m ago", "6 runners checked in") }
        item { NotificationRow("Track Field Closed", "35m ago", "Maintenance until 6:00 PM") }
        item { NotificationRow("Event 3 Notification", "48m ago", "1.2 mile away") }
        item { NotificationRow("Marcus hit a new PR", "1h ago", "5K in 21:04") }
        item { NotificationRow("Stadium Steps Challenge", "2h ago", "32 runners checked in") }
        item { NotificationRow("Hydration Station Moved", "3h ago", "Now at the north gate") }
        item { NotificationRow("Event 4 Notification", "5h ago", "0.8 mile away") }
        item { NotificationRow("Weekly Mileage Recap", "Yesterday", "42.8 mi across 14 active days") }
    }
}

@Composable
fun NotificationRow(title: String, time: String, detail: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 12.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Text(text = time, fontSize = 12.sp)
        }
        Text(
            text = detail,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun UserProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "My Profile", fontSize = 26.sp)
                Text(text = "STRIDE / Campus Athlete", fontSize = 12.sp)
            }
            OutlinedButton(onClick = { }) {
                Text(text = "Edit Profile", fontSize = 13.sp)
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(text = "Alex Chen", fontSize = 20.sp)
                        Text(text = "Class of 2028 · Sophomore", fontSize = 13.sp)
                    }
                }

                Column {
                    Text(text = "Runner Bio", fontSize = 15.sp)
                    Text(
                        text = "Computer science major who runs to clear my head. " +
                            "Training for a half marathon this spring, usually on the " +
                            "campus loop between classes.",
                        fontSize = 13.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Column {
                    Text(text = "Selected Interest", fontSize = 15.sp)
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = "Marathon", fontSize = 13.sp)
                        Text(text = "Casual Run", fontSize = 13.sp)
                        Text(text = "Trail Run", fontSize = 13.sp)
                    }
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Experience & Reward", fontSize = 18.sp)
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "XP to Next Level",
                                fontSize = 14.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Text(text = "1500/2000", fontSize = 14.sp)
                        }
                    }

                    Column {
                        Text(text = "Unlock Achievement", fontSize = 14.sp)
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(text = "First 5K", fontSize = 13.sp)
                            Text(text = "10 Runs", fontSize = 13.sp)
                            Text(text = "5 Day Streak", fontSize = 13.sp)
                        }
                    }
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Completed Workout Summary", fontSize = 18.sp)
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SummaryStat("Total Distance", "42.8 Mi", modifier = Modifier.weight(1f))
                        SummaryStat("Active Days", "14 Days", modifier = Modifier.weight(1f))
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SummaryStat("Best Pace", "6'45\"", modifier = Modifier.weight(1f))
                        SummaryStat("Active Streak", "5 Days", modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun SummaryStat(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = label, fontSize = 12.sp)
        Text(text = value, fontSize = 20.sp, modifier = Modifier.padding(top = 2.dp))
    }
}

@Composable
fun SponsorDashboardScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Column {
            Text(text = "OFFICIAL EVENT SPONSOR", fontSize = 12.sp)
            Text(text = "Powerade Inc.", fontSize = 26.sp, modifier = Modifier.padding(top = 4.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                label = "ACTIVE REACH",
                value = "1.4K",
                caption = "+12% this wk",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = "XP DISTRIBUTED",
                value = "4.5K XP",
                caption = "Tier 2 Sponsor",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Upcoming Sponsored Events",
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Text(text = "2 SCHEDULED", fontSize = 11.sp)
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = "Powerade Arena Sprints", fontSize = 16.sp, modifier = Modifier.fillMaxWidth())
                Text(text = "OFFICIAL PARTNER", fontSize = 11.sp)
                Text(
                    text = "Sprint the stadium steps. Free Powerade for all active " +
                        "finishers and active student points.",
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 6.dp)
                )
                Row(modifier = Modifier.fillMaxWidth().padding(top = 6.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "BUDGET ALLOCATED", fontSize = 10.sp)
                        Text(text = "+150 XP • $250", fontSize = 14.sp, modifier = Modifier.padding(top = 2.dp))
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "REGISTERED", fontSize = 10.sp)
                        Text(text = "84 Students", fontSize = 14.sp, modifier = Modifier.padding(top = 2.dp))
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Today • 5:30 PM • Track Field",
                        fontSize = 13.sp,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedButton(onClick = { }) {
                        Text(text = "Manage", fontSize = 13.sp)
                    }
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = "Sunset Stadium Sprints", fontSize = 16.sp, modifier = Modifier.fillMaxWidth())
                Text(text = "CAMPUS CHALLENGE", fontSize = 11.sp)
                Text(
                    text = "Weekly tradition starting from the stadium gates. " +
                        "High hydration focus sponsor track.",
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 6.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Oct 28 • 6:30 PM • Main Arena",
                        fontSize = 13.sp,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedButton(onClick = { }) {
                        Text(text = "Manage", fontSize = 13.sp)
                    }
                }
            }
        }

        Text(text = "Past Sponsored Events", fontSize = 18.sp)

        PastEventCard(title = "Kickoff Campus 5K", detail = "Completed Oct 10 • 112 Active Finishers")
        PastEventCard(title = "Garnet Stadium Climb", detail = "Completed Sep 24 • 98 Active Finishers")

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = "+ Sponsor a New Event", fontSize = 15.sp)
        }
    }
}

@Composable
fun StatCard(label: String, value: String, caption: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth().padding(14.dp)) {
            Text(text = label, fontSize = 11.sp)
            Text(text = value, fontSize = 20.sp, modifier = Modifier.padding(top = 4.dp))
            Text(text = caption, fontSize = 11.sp, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Composable
fun PastEventCard(title: String, detail: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 15.sp)
                Text(text = detail, fontSize = 12.sp, modifier = Modifier.padding(top = 2.dp))
            }
            OutlinedButton(onClick = { }) {
                Text(text = "Stats", fontSize = 13.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    GreetingCardTheme {
        HomeScreen(onSelect = { })
    }
}
