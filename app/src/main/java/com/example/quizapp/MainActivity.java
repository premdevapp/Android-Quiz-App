package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   private Button falseButton , trueButton;
   private ImageButton nextButton, prevButton;
   private TextView questionTextView;
   private int currentQuestionIndex = 0;
    private Question[] question = new Question[]{
            new Question(R.string.test_question, true),
            new Question(R.string.test_question1, true),
            new Question(R.string.test_question2, true),
            new Question(R.string.test_question3, true),
            new Question(R.string.test_question4, false),
            new Question(R.string.test_question5, true)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.textView);
        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);

        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.false_button:
                userChoice(false);
                break;
            case R.id.true_button:
                userChoice(true);
                break;
            case R.id.next_button:
                currentQuestionIndex = (currentQuestionIndex+1) % question.length;
                updateQuestion();
                break;
            case R.id.prev_button:
                if(currentQuestionIndex > 0){
                currentQuestionIndex = (currentQuestionIndex-1) % question.length;
                updateQuestion();
                }else {
                    currentQuestionIndex = question.length;
                }
                break;

        }
    }

    private void updateQuestion(){
        questionTextView.setText(question[currentQuestionIndex].getAnswerResId());
    }

    private void userChoice(boolean userInput){
        boolean anwserIsTrue = question[currentQuestionIndex].isAnswer();
        int toastMessageId;
        if( userInput == anwserIsTrue){
            //Log.d("true", "userChoice: true");
            toastMessageId = R.string.correct_ans;
        }else {
            //Log.d("false", "userChoice: false");
            toastMessageId = R.string.wrong_ans;

        }
        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
        }
    }