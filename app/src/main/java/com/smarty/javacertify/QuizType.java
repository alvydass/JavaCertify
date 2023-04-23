package com.smarty.javacertify;

public enum QuizType {

    JAVA_8_OCA_QUIZ("oca8", 0, "JAVA 8 OCA QUIZ"),
    JAVA_8_OCP_QUIZ("ocp8", 0, "JAVA 8 OCP QUIZ"),
    JAVA_11_OCA_QUIZ("oca11", 0, "JAVA 11 OCA QUIZ"),
    JAVA_11_OCP_QUIZ("ocp11", R.raw.ocp11, "JAVA 11 OCP QUIZ"),
    JAVA_17_OCA_QUIZ("oca17", 0, "JAVA 17 OCA QUIZ"),
    JAVA_17_OCP_QUIZ("ocp17", 0, "JAVA 17 OCP QUIZ");

    private final int file;
    private final String quiz;

    private final String prettyName;

    QuizType(String quiz, int file, String prettyName) {
        this.quiz = quiz;
        this.file = file;
        this.prettyName = prettyName;
    }

    public String getQuiz() {
        return quiz;
    }

    public int getFile() {
        return file;
    }

    public String getPrettyName() {
        return prettyName;
    }
}
