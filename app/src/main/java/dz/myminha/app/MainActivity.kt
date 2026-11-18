package dz.myminha.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF16834A)
private val Background = Color(0xFFF4F8F5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MyMinhaApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMinhaApp() {
    var page by remember { mutableStateOf("home") }

    BackHandler(enabled = page != "home") {
        page = "home"
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Scaffold(
            containerColor = Background,
            topBar = {
                if (page != "home") {
                    TopAppBar(
                        title = {
                            Text(
                                "My Minha",
                                fontWeight = FontWeight.Bold
                            )
                        },
                        navigationIcon = {
                            TextButton(
                                onClick = { page = "home" }
                            ) {
                                Text("رجوع", color = Green)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.White
                        )
                    )
                }
            }
        ) { padding ->

            when (page) {
                "home" -> HomeScreen(
                    modifier = Modifier.padding(padding),
                    onNavigate = { page = it }
                )

                "minha" -> ServiceScreen(
                    modifier = Modifier.padding(padding),
                    title = "منحة البطالة",
                    description = "فضاء خاص بخدمات منحة البطالة.",
                    items = listOf(
                        "معلومات وشروط الاستفادة",
                        "الوثائق المطلوبة",
                        "إرشادات للمستفيدين",
                        "متابعة المستجدات"
                    )
                )

                "wassit" -> ServiceScreen(
                    modifier = Modifier.padding(padding),
                    title = "فضاء وسيط",
                    description = "خدمات الوكالة الوطنية للتشغيل.",
                    items = listOf(
                        "معلومات التسجيل",
                        "البحث عن عروض العمل",
                        "إرشادات طالبي العمل",
                        "معلومات الخدمات"
                    )
                )

                "profile" -> ServiceScreen(
                    modifier = Modifier.padding(padding),
                    title = "حسابي",
                    description = "مساحتك الشخصية في My Minha.",
                    items = listOf(
                        "الملف الشخصي",
                        "الإعدادات",
                        "حول التطبيق"
                    )
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(25.dp))

        Text(
            "My Minha",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Green
        )

        Text(
            "خدماتك الرقمية في مكان واحد",
            color = Color.Gray,
            fontSize = 15.sp
        )

        Spacer(Modifier.height(30.dp))

        ServiceCard(
            "منحة البطالة",
            "معلومات وإرشادات خدمات المنحة",
            "استكشف الخدمات"
        ) {
            onNavigate("minha")
        }

        Spacer(Modifier.height(16.dp))

        ServiceCard(
            "فضاء وسيط",
            "خدمات التشغيل وعروض العمل",
            "استكشف الخدمات"
        ) {
            onNavigate("wassit")
        }

        Spacer(Modifier.height(16.dp))

        ServiceCard(
            "حسابي",
            "الإعدادات ومعلومات التطبيق",
            "فتح الحساب"
        ) {
            onNavigate("profile")
        }

        Spacer(Modifier.weight(1f))

        Text(
            "My Minha © 2026",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun ServiceScreen(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    items: List<String>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            title,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = Green
        )

        Spacer(Modifier.height(8.dp))

        Text(
            description,
            color = Color.DarkGray
        )

        Spacer(Modifier.height(22.dp))

        items.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    item,
                    modifier = Modifier.padding(18.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        Text(
            "هذه واجهة أولية. ستضاف الخدمات الفعلية بعد تجهيز مصادر البيانات والربط المصرح به.",
            color = Color.Gray,
            fontSize = 13.sp
        )
    }
}

@Composable
fun ServiceCard(
    title: String,
    description: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Green
            )

            Spacer(Modifier.height(8.dp))

            Text(
                description,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(15.dp))

            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green
                )
            ) {
                Text(buttonText)
            }
        }
    }
}
