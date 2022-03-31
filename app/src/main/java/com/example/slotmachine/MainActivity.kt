package com.example.slotmachine

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slotmachine.ui.theme.*
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


                // A surface container using the 'background' color from the theme
                    setMachineViews()


        }

    }
}

@Composable
fun SlotBox(name: String, modifier: Modifier, data: String?) {


    Box(modifier = Modifier
        .width(150.dp)
        .height(90.dp)
        .background(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color.Gray,
                    Color.Transparent
                )
            )
        )
        .padding(16.dp)){


        Card( modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .align(Alignment.Center)
            ,
            shape = RoundedCornerShape(16.dp)
        ){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)) {
                Text(modifier = Modifier
                    .align(Alignment.Center),
                    color = Color.Black
                    , text = data?: name,
                    textAlign = TextAlign.Center)

            }


        }

    }



}


@Composable
fun StartButton(name: String, sc: MutableState<Int>, squadName:MutableState<String>, memberName:MutableState<String>, updateVisibility: (Boolean)-> Unit){
    val context = LocalContext.current

    val ctaText = remember {
        mutableStateOf("Start Matching")
    }

    Box(modifier = Modifier
        .width(120.dp)
        .height(120.dp)
        .background(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color.Gray,
                    Color.Transparent
                )
            )
        )
        .padding(16.dp)){

        updateVisibility(
           false

        )


        Card( modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clickable {

                if ( Utils.clickCount == 5) {
                    squadName.value = "Squad"
                    memberName.value = "Member"
                    sc.value = 0
                    Utils.clickCount = 0
                    updateVisibility(false)
                    ctaText.value = "Start Matching"
                    return@clickable
                }

                Utils.clickCount++


                val timer = object : CountDownTimer(1000, 50) {
                    var pair = Pair<Int, Int>(0, 0)
                    override fun onTick(millisUntilFinished: Long) {

                        squadName.value =
                            Utils.squadList[Random.nextInt(0, Utils.squadList.size - 1)]
                        memberName.value =
                            Utils.membersList[Random.nextInt(0, Utils.membersList.size - 1)]

                    }

                    override fun onFinish() {

                        updateVisibility(
                            squadName.value.equals(Utils.teamMap[memberName.value])

                        )



                        if (squadName.value.equals(Utils.teamMap[memberName.value])) {

                            sc.value++
                        }

                        if(Utils.clickCount == 5){
                            ctaText.value = "Reset"
                        } else{
                            ctaText.value = "Start Matching"
                        }
                    }
                }
                timer.start()


            }
            ,
            shape = RoundedCornerShape(100.dp),
            elevation = 16.dp
        ){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)) {
                Text(modifier = Modifier
                    .align(Alignment.Center),
                    color = Color.White
                    , text = ctaText.value,
                    textAlign = TextAlign.Center)

            }


        }

    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun setMachineViews(){


    LaunchedEffect(key1 = true) {
        Utils.teamMap()
    }

    val squadName = remember{
        mutableStateOf("Squad")
    }


    val memberName = remember{
        mutableStateOf("Member")
    }

    val score = remember {
        mutableStateOf(Utils.playerScore)
    }

    val visibilityState = remember {
        mutableStateOf(false)
    }




    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.verticalGradient(
                colors = listOf(
                    Color_343434, Color_020202
                )
            )
        )
      ,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = Color.Green,
                fontSize = 60.sp
            )
            ){
                append("S")
            }
            append("quad")

            withStyle(style = SpanStyle(
                color = Color.Green,
                fontSize = 60.sp
            )
            ){
                append(" G")
            }
            append("ame")
        }, color = Color.White, fontSize = 40.sp)


        Spacer(modifier = Modifier
            .width(24.dp)
            .height(24.dp))

        AnimatedVisibility(visible = visibilityState.value) { Text(text = "It's a Match!!", color = Color.Green) }

        Spacer(modifier = Modifier
            .width(32.dp)
            .height(32.dp))

        Spacer(modifier = Modifier
            .width(32.dp)
            .height(32.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {
            SlotBox(name = "Qubit", modifier = Modifier, squadName.value)
            SlotBox(name = "Sanchit", modifier = Modifier, memberName.value)
        }
        Spacer(modifier = Modifier
            .width(32.dp)
            .height(32.dp))



        StartButton(squadName.value, score, squadName, memberName){
            visibilityState.value = it

        }

        Spacer(modifier = Modifier
            .width(32.dp)
            .height(32.dp))

        Text(text = "Score: ${score.value}/5", color = Color.White)

    }
    


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    setMachineViews()
}