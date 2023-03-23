package com.example.composable

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.example.composable.ui.theme.ComposableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent  {
                ComposableTheme () {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,

                ) {
//                    Greeting(Message("Android" , "Hey, Take a look ayt Jetpack Compose, It's Great!"))

                }
            }
        }
    }
}


@Composable
fun Greeting(message: Message) {


    Row (modifier = Modifier.padding(all = 8.dp) ) {
        Image(
           painter = painterResource(R.drawable.profile ) , contentDescription = "Logo"  ,
           modifier = Modifier
               .size(40.dp)
               .clip(CircleShape)
               .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
           )

        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember { mutableStateOf(false) }

        Column (modifier = Modifier.clickable { isExpanded = !isExpanded } ,  ) {
            Text(
                text = "${message.Author}!" ,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.size(4.dp))
            androidx.compose.material.Surface( shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = "${message.Message}!",
                    modifier = Modifier.padding(all = 4.dp),
                    color = if (isExpanded) MaterialTheme.colors.primary else Color.Black,

                    maxLines = if (isExpanded) Int.MAX_VALUE else 1 ,
                    style = MaterialTheme.typography.body2)
            }
        }
    }

}


@Composable
fun Conversation(messages : List<Message>){

    LazyColumn( ){
        items(messages){
            message -> Greeting(message = message )
        }
    }
}


@Preview(name = "Light Mode",   showBackground = true ,)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode" ,
    showBackground = true ,
)
@Composable
fun DefaultPreview() {
    ComposableTheme {
//         A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.background
        ) {
//            Greeting(Message("Android" , "Hey, Take a look ayt Jetpack Compose, It's Great!"))
            Conversation(messages = SampleData.ConversationSample)
        }
    }
}