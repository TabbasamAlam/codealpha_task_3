import android.widget.RadioGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.flashcard.Fleshcard
import com.example.flashcard.backButton
import com.example.flashcard.stylesButtton
import com.example.flashcard.textfield


@Composable
fun StylizedCard(
    onDelete: () -> Unit,
    onAnswerSubmit: (String, String) -> Unit
) {
    var question by remember { mutableStateOf("") }
    var option1 by remember { mutableStateOf("") }
    var option2 by remember { mutableStateOf("") }
    var option3 by remember { mutableStateOf("") }
    var option4 by remember { mutableStateOf("") }
    var correctAnswer by remember { mutableStateOf("") }
    var userAnswer by remember { mutableStateOf("") }

    var hideAnswer by remember { mutableStateOf(true) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Delete Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    // isSnackbarVisible = true
                    onDelete()
                },
                modifier = Modifier.background(Color.Red)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // TextFields
            TextField(value =question ,
                onValueChange = { question = it },
                label = { Text("Enter Question") })

            TextField(value = option1,
                onValueChange = { option1 = it },
                label = { Text("Option1") })

            TextField(value = option2,
                onValueChange = { option2 = it },
                label = { Text("Optiion2") })

            TextField(value = option3,
                onValueChange = { option3 = it },
                label = { Text("Option3") })

            TextField(value = option4,
                onValueChange = { option4 = it },
                label = { Text("Option4") })

            if (hideAnswer == true) {
                TextField(value = correctAnswer,
                    onValueChange = { correctAnswer = it },
                    label = { Text("Correct Answer") })
            }
            if (correctAnswer != "") {
                Button(onClick = { hideAnswer = !hideAnswer }) {
                    if (hideAnswer==true)
                    {
                        Text(text = "Hide Answer")
                    }
                    else
                    {
                        Text(text = "Show Answer")
                    }
                }
            }

            if (!hideAnswer) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Select Your Answer:")
                RadioGroup(
                    options = listOf(option1, option2, option3, option4),
                    onSelected = { selectedAnswer ->
                        userAnswer = selectedAnswer
                        onAnswerSubmit(question, selectedAnswer)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Handle quiz submission here
                    onAnswerSubmit(question, userAnswer)
                }
            ) {
                Text("Submit Answer")
            }
        }
    }
}

@Composable
fun Card1() {
      var cards by remember { mutableStateOf(emptyList<@Composable () -> Unit>()) }
    var quiz by remember { mutableStateOf(false) }
    var userAnswers by remember { mutableStateOf(mutableMapOf<String, String>()) }
    var back by remember{ mutableStateOf(false) }
    if (quiz == true) {

        Column {
            backButton {
                back=true
            }
            for ((question, userAnswer) in userAnswers) {
                Text("Question: $question, Your Answer: $userAnswer")
            }

            // Add your logic to calculate and display the overall result
        }

    }
    else if(back==true) {
        Fleshcard()
    } else
     {
        Column {
            Row() {
                Text(text = "Click ->", fontSize = 20.sp, modifier = Modifier.padding(10.dp))
                Spacer(modifier = Modifier.padding(100.dp))
                IconButton(
                    onClick = {
                        val index = cards.size
                        cards = cards + {
                            StylizedCard(onDelete =
                            {
                                cards = cards.filterIndexed { idx, _ -> idx != index }
                            },
                                onAnswerSubmit = { question, userAnswer ->
                                    userAnswers[question] = userAnswer
                                }
                            )
                        }
                    }
                )
                {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }

                Spacer(modifier = Modifier.padding(10.dp))
                Button(onClick = { quiz = true }) {
                    Text("Start Quiz")
                }
            }
            LazyColumn()
            {
                items(cards) { card ->
                    card()
                }
            }
        }
    }
}
@Composable
fun RadioGroup(options: List<String>, onSelected: (String) -> Unit) {
    var selectedOption by remember { mutableStateOf("") }

    options.forEach { option ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = option)
            RadioButton(
                selected = selectedOption == option,
                onClick = {
                    selectedOption = option
                    onSelected(option)
                }
            )
        }
    }
}
//@Composable
//fun SigleCard(
//    Question:String,
//    Option1:String,
//    Option2:String,
//    Option3:String,
//    Option4:String,
//    CorrectAnswer:String
//) {
//    Card(modifier = Modifier.fillMaxWidth()) {
//        var question by remember { mutableStateOf(Question) }
//        var option1 by remember { mutableStateOf(Option1) }
//        var option2 by remember { mutableStateOf(Option2) }
//        var option3 by remember { mutableStateOf(Option3) }
//        var option4 by remember { mutableStateOf(Option4) }
//        var correctAnswer by remember { mutableStateOf(CorrectAnswer) }
//        var hideAnswer by remember { mutableStateOf(true) }
//
//        Column {
//            textfield(txt = question, onvalueChange = { question = it }) {
//                Text(text = "Enter Question")
//            }
//
//            textfield(txt = option1, onvalueChange = { option1 = it }) {
//                Text(text = "Option1")
//            }
//
//            textfield(txt = option2, onvalueChange = { option2 = it }) {
//                Text(text = "Option2")
//            }
//
//            textfield(txt = option3, onvalueChange = { option3 = it }) {
//                Text(text = "Option3")
//            }
//
//            textfield(txt = option4, onvalueChange = { option4 = it }) {
//                Text(text = "Option4")
//            }
//            if (hideAnswer == true) {
//                textfield(txt = correctAnswer, onvalueChange = { correctAnswer = it }) {
//                    Text(text = "Correct Answer")
//                }
//            }
//            if (correctAnswer != "") {
//                Button(onClick = { hideAnswer = !hideAnswer }) {
//                    if (hideAnswer == true) {
//                        Text(text = "Hide Answer")
//                    } else {
//                        Text(text = "Show Answer")
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun listofCards() {
//    Spacer(modifier = Modifier.height(100.dp))
//    var question by remember { mutableStateOf("") }
//    var option1 by remember { mutableStateOf("") }
//    var option2 by remember { mutableStateOf("") }
//    var option3 by remember { mutableStateOf("") }
//    var option4 by remember { mutableStateOf("") }
//    var correctAnswer by remember { mutableStateOf("") }
//    var On by remember { mutableStateOf(false) }
//
//    var item =SigleCard(
//        Question =question,
//        Option1 =option1,
//        Option2 =option2,
//        Option3 =option3,
//        Option4 =option4,
//        CorrectAnswer =correctAnswer
//    )
//    var cards by remember { mutableStateOf(listOf(item)) }
//    IconButton(onClick = {
//        cards=cards+item
//    }) {
//        Icon(imageVector = Icons.Default.Add, contentDescription = "Add card",
//        )
//    }
//                LazyColumn()
//            {
//                items(cards) { card ->
//                    item
//                }
//            }
//}
//@Preview(showBackground = true)
//@Composable
//fun pe() {
//    listofCards()
//}
