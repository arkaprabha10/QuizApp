package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.String;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  final String SCORE_KEY="SCORE";
    private  final String INDEX_KEY="SCORE";
    int user_progress=33;
    private TextView t1;
    private TextView t2;
    private Button b1;
    private Button b2;
    private ProgressBar p1;
    private int index=0;
    private int mQuizQuestion;
    private String quiz_answer;
    private int score=0;
    private QuizModel [] collection = new QuizModel[]
            {
                    new QuizModel(R.string.q1,"Typewriter"),
                    new QuizModel(R.string.q2,"576 MP"),
                    new QuizModel(R.string.q3,"Nathan")

            };
    private String set1 []={
      "Typewriter","480 MP"  , "Nathan"
    };
    private String set2 []={
            "Armadillo","576 MP" , "A forest in South America"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null)
        {
            score = savedInstanceState.getInt(SCORE_KEY);
            index = savedInstanceState.getInt(INDEX_KEY);
        }
        else
        {
            index =0;
            score=0;
        }

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        final QuizModel q1 = collection[index];
        mQuizQuestion = q1.getMq();
        quiz_answer = q1.getAns();
        t1.setText(mQuizQuestion);
        p1 = findViewById(R.id.p1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer(b1.getText().toString());
                changeQuestion();
                b1.setText(set1[index]);
                b2.setText(set2[index]);
                t2.setText("Your Score is " + score);


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer(b2.getText().toString());
                changeQuestion();
                b1.setText(set1[index]);
                b2.setText(set2[index]);
                t2.setText("Your Score is " + score);

            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY,score);
        outState.putInt(INDEX_KEY,index);
    }

    private void changeQuestion()
    {   index=(index+1)%3;
        if(index==0) {
            //score = 0;
            //user_progress=33;
            //p1.setProgress(user_progress);
            AlertDialog.Builder a1 = new AlertDialog.Builder(this);
            a1.setTitle("The quiz is finished");
            a1.setMessage("Your Score is " + score);
            a1.setCancelable(false);

            a1.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            a1.show();

        }
        mQuizQuestion = collection[index].getMq();
        t1.setText(mQuizQuestion);
        p1.incrementProgressBy(user_progress);

    }


    private void answer(String guess)
    {
        String answer1= collection[index].getAns();
        if(guess ==  answer1)
        {   score=score+1;
            Toast.makeText(getApplicationContext(),R.string.correct_text, Toast.LENGTH_SHORT).show();
            Log.i("CHECK", guess);

        }
        else
        {
            Toast.makeText(getApplicationContext(),R.string.incorrect_text, Toast.LENGTH_SHORT).show();
            Log.i("CHECK",answer1);
            Log.i("CHECK",guess);
        }

    }

}
