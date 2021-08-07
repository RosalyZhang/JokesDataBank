package com.example.jokesdatabank.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Jokes {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String type;

    @NotNull
    @Size(min = 1, max = 200)
    private String setup;

    @NotNull
    @Size(min = 1, max = 200)
    private String punchline;

    public Jokes() {
    }

    public Jokes(String type, String setup, String punchline) {
        this.type = type;
        this.setup = setup;
        this.punchline = punchline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }
}
