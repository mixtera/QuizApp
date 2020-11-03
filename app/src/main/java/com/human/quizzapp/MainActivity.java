package com.human.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    int mIndex;  // 4Fetching
    int mQuestion;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    int mScore;


    private TrueFalse[] mQuestionBank = new TrueFalse[]{    //array
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true),


    };
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("Score Key");
            mIndex = savedInstanceState.getInt("Index Key");

        } else {
            mScore = 0;
            mIndex = 0;


        }


        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.initial_score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mScoreTextView.setText("Score " + mScore + "/" +mQuestionBank.length);

         mQuestion = mQuestionBank[mIndex].getQuestionID(); //fetch

        mQuestionTextView.setText(mQuestion);//fetch


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateQuestion(); //fetch
                checkAnswer(true);


            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateQuestion(); //fetch
                checkAnswer(false);


            }
        });


    }

    private void updateQuestion() {

        mIndex = (mIndex + 1) % mQuestionBank.length;

        if (mIndex == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("GAME OVER");
            alert.setCancelable(false);
            alert.setMessage("U SCORED " + mScore + " POINTS!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();

        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);

        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("SCORE" + mScore + "/" + mQuestionBank.length);

        //fetch


    }


    private void checkAnswer(boolean userSelection) {

        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();

        if (userSelection == correctAnswer) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore = mScore + 1;


        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();


        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("Score Key", mScore);
        outState.putInt("Index Key", mIndex);
    }
}