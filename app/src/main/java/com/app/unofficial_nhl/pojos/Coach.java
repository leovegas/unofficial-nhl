package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coach {

    @SerializedName("person")
    @Expose
    private Person__45 person;

    public Person__45 getPerson() {
        return person;
    }

    public void setPerson(Person__45 person) {
        this.person = person;
    }


}
