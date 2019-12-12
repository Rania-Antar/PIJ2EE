package tn.esprit.pitwin.utilities;

public enum QuestionType {
    CHECKBOX("checkbox"), RADIOBOX("radiobox");

    private final String value;

    private QuestionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
