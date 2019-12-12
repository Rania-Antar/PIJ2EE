package tn.esprit.pitwin.utilities;

import java.io.Serializable;

public enum Gender implements Serializable {
    MALE("Male"), FEMALE("Female");

    private final String value;

    private Gender(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
