
package dz.myminha.app

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

private val Green = Color(0xFF16834A)
private val LightGreen = Color(0xFFEAF5EE)
private val Red = Color(0xFFD83232)

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MyMinhaHome()
            }
        }
    }
}

@Composable
fun MyMinhaHome() {
    var selectedUrl by remember { mutableStateOf<String?>(null) }
    var webView: WebView? by remember { mutableStateOf(null) }

    BackHandler(enabled = selectedUrl != null) {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            selectedUrl = null
        }
    }

    if (selectedUrl != null) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    webViewClient = WebViewClient()
                    webChromeClient = WebChromeClient()
                    loadUrl(selectedUrl!!)
                    webView = this
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F9F7))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "My Minha",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Green
            )

            Text(
                "خدماتك الرقمية في مكان واحد",
                color = Color.DarkGray
            )

            Spacer(Modifier.height(35.dp))

            ServiceCard(
                "منحة البطالة",
                "متابعة خدمات منحة البطالة",
                "الدخول إلى منصة منحة"
            ) {
                selectedUrl = "https://minha.anem.dz/"
            }

            Spacer(Modifier.height(18.dp))

            ServiceCard(
                "فضاء وسيط",
                "خدمات الوكالة الوطنية للتشغيل",
                "الدخول إلى منصة وسيط"
            ) {
                selectedUrl = "https://wassitonline.anem.dz/"
            }

            Spacer(Modifier.weight(1f))

            Text(
                "My Minha © 2026",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
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

            Text(description, color = Color.DarkGray)

            Spacer(Modifier.height(15.dp))

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(buttonText)
            }
        }
    }
}
