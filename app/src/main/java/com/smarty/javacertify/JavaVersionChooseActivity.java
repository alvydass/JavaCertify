package com.smarty.javacertify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class JavaVersionChooseActivity extends AppCompatActivity {

    public static final String QUIZ_CODE = "QUIZ_CODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_version_choose);

        CardView oca8 = findViewById(R.id.oca_8);
        CardView ocp8 = findViewById(R.id.ocp_8);
        CardView oca11 = findViewById(R.id.oca_11);
        CardView ocp11 = findViewById(R.id.ocp_11);
        CardView oca17 = findViewById(R.id.oca_17);
        CardView ocp17 = findViewById(R.id.ocp_17);

        ocp8.setOnClickListener(v -> {
            Intent intent = new Intent(JavaVersionChooseActivity.this, MainActivity.class);
            intent.putExtra(QUIZ_CODE, QuizType.JAVA_8_OCP_QUIZ.name());
            startActivity(intent);
        });

        oca8.setOnClickListener(v -> {
            Intent intent = new Intent(JavaVersionChooseActivity.this, MainActivity.class);
            intent.putExtra(QUIZ_CODE, QuizType.JAVA_8_OCA_QUIZ.name());
            startActivity(intent);
        });

        ocp11.setOnClickListener(v -> {
            Intent intent = new Intent(JavaVersionChooseActivity.this, MainActivity.class);
            intent.putExtra(QUIZ_CODE, QuizType.JAVA_11_OCP_QUIZ.name());
            startActivity(intent);
        });

        oca11.setOnClickListener(v -> {
            Intent intent = new Intent(JavaVersionChooseActivity.this, MainActivity.class);
            intent.putExtra(QUIZ_CODE, QuizType.JAVA_11_OCA_QUIZ.name());
            startActivity(intent);
        });

        ocp17.setOnClickListener(v -> {
            Intent intent = new Intent(JavaVersionChooseActivity.this, MainActivity.class);
            intent.putExtra(QUIZ_CODE, QuizType.JAVA_17_OCP_QUIZ.name());
            startActivity(intent);
        });

        oca17.setOnClickListener(v -> {
            Intent intent = new Intent(JavaVersionChooseActivity.this, MainActivity.class);
            intent.putExtra(QUIZ_CODE, QuizType.JAVA_11_OCA_QUIZ.name());
            startActivity(intent);
        });
    }
}