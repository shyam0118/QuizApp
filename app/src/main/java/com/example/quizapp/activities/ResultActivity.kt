package com.example.quizapp.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.quizapp.R
import com.example.quizapp.activities.models.Question
import com.example.quizapp.activities.models.Quiz
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setUpViews()
    }

    private fun setUpViews() {

        val quizData: String? = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)

        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for(entry : MutableMap.MutableEntry<String, Question> in quiz.questions.entries){
            val question : Question = entry.value

            builder.append("<font color'#1806F'><b>Question: ${question.description}</b><font><br/><br/>")
            builder.append("<font color= '#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        }else{
            txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }

    private fun calculateScore() {
        var score = 0

        for(entry: MutableMap.MutableEntry<String, Question> in quiz.questions.entries){
            val question: Question = entry.value
            if(question.answer == question.userAnswer){
                score += 10
            }
        }
        txtScore.text = "Your score: $score"
    }
}