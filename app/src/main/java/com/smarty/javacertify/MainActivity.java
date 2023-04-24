package com.smarty.javacertify;

import static com.smarty.javacertify.JavaVersionChooseActivity.QUIZ_CODE;
import static com.smarty.javacertify.QuizActivity.PERCENTAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;

    private int highscore;

    private String quizTypeString;

    private QuizDbHelper dbHelper;

    private static final Map<String, Integer> map = Map.ofEntries(Map.entry("A", 1),
            Map.entry("B", 2), Map.entry("C", 3), Map.entry("D", 4), Map.entry("E", 5), Map.entry("F", 6));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        quizTypeString = intent.getStringExtra(QUIZ_CODE);
        TextView quizType = findViewById(R.id.quiz_type_text);
        quizType.setText(QuizType.valueOf(quizTypeString).getPrettyName());

        textViewHighscore = findViewById(R.id.text_view_highscore);
        loadHighscore(quizTypeString);

        dbHelper = new QuizDbHelper(this);
        List<Question> allQuestions = dbHelper.getAllQuestions(QuizType.valueOf(quizTypeString));
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        Button addQuestion = findViewById(R.id.button_add_question);
        if (allQuestions.isEmpty()) {
            buttonStartQuiz.setEnabled(false);
            TextView noQuestions = findViewById(R.id.no_questions);
            noQuestions.setVisibility(View.VISIBLE);
        } else {
            buttonStartQuiz.setOnClickListener(v -> startQuiz(quizTypeString));
        }

        addQuestion.setOnClickListener(v -> showCustomDialog());
    }

    private void startQuiz(String quizType) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra(QUIZ_CODE, quizType);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                double percentage = data.getDoubleExtra(PERCENTAGE, 0);
                if (score > highscore) {
                    updateHighscore(score, quizTypeString, percentage);
                }
            }
        }
    }

    private void loadHighscore(String quizType) {
        SharedPreferences prefs = getSharedPreferences(quizType, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        float percentage = prefs.getFloat(PERCENTAGE, 0f);
        textViewHighscore.setText("Highscore: " + highscore + ", Percentage: " + percentage + "%");
    }

    private void updateHighscore(int highscoreNew, String quizType, double percentage) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore + ", Percentage: " + percentage + "%");

        SharedPreferences prefs = getSharedPreferences(quizType, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.putFloat(PERCENTAGE, (float) percentage);
        editor.apply();
    }

    void showCustomDialog() {

        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(true);

        dialog.setContentView(R.layout.activity_add_question);

        Spinner spinnerLanguages = dialog.findViewById(R.id.spinneris);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.answers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
        spinnerLanguages.setSelection(0);


        final EditText questionInputText = dialog.findViewById(R.id.question_input_text);
        final EditText explanationInputText = dialog.findViewById(R.id.question_explanation);
        final EditText optionAInputText = dialog.findViewById(R.id.option_a);
        final EditText optionBInputText = dialog.findViewById(R.id.option_b);
        final EditText optionCInputText = dialog.findViewById(R.id.option_c);
        final EditText optionDInputText = dialog.findViewById(R.id.option_d);
        final EditText optionEInputText = dialog.findViewById(R.id.option_e);
        final EditText optionFInputText = dialog.findViewById(R.id.option_f);

        Button submitButton = dialog.findViewById(R.id.submit_button);
        Button cancelButton = dialog.findViewById(R.id.cancel_button);


        submitButton.setOnClickListener(v -> {
           if (TextUtils.isEmpty(questionInputText.getText().toString())) {
               questionInputText.setError("Required");
               return;
           }

            if (TextUtils.isEmpty(explanationInputText.getText().toString())) {
                explanationInputText.setError("Required");
                return;
            }

            if (TextUtils.isEmpty(optionAInputText.getText().toString())) {
                questionInputText.setError("Required");
                return;
            }

            if (TextUtils.isEmpty(optionBInputText.getText().toString())) {
                questionInputText.setError("Required");
                return;
            }

            if (TextUtils.isEmpty(optionCInputText.getText().toString())) {
                questionInputText.setError("Required");
                return;
            }

            if (TextUtils.isEmpty(optionDInputText.getText().toString())) {
                questionInputText.setError("Required");
                return;
            }

            if (TextUtils.isEmpty(optionEInputText.getText().toString())) {
                questionInputText.setError("Required");
                return;
            }

            if (TextUtils.isEmpty(optionFInputText.getText().toString())) {
                questionInputText.setError("Required");
                return;
            }

            if (TextUtils.isEmpty(explanationInputText.getText().toString())) {
                questionInputText.setError("Required");
                return;
            }

            Question question = new Question();
            question.setQuestion(questionInputText.getText().toString());
            question.setOption1(optionAInputText.getText().toString());
            question.setOption2(optionBInputText.getText().toString());
            question.setOption3(optionCInputText.getText().toString());
            question.setOption4(optionDInputText.getText().toString());
            question.setOption5(optionEInputText.getText().toString());
            question.setOption6(optionFInputText.getText().toString());
            question.setExplanation(explanationInputText.getText().toString());
            String s = spinnerLanguages.getSelectedItem().toString();
            question.setAnswerNr(map.get(s));
            question.setQuizType(QuizType.valueOf(quizTypeString));
            dbHelper.saveQuestion(question);

            finish();
            startActivity(getIntent());
        });

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

}