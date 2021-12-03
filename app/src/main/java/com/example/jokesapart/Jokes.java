package com.example.jokesapart;

public class Jokes {
    private final String jokeID;
    private final String jokeType;
    private final String jokeDes;
    private final String jokePunchline;


    public Jokes(String jokeID,String jokeType, String jokeDes, String jokePunchline) {
        this.jokeID = jokeID;
    this.jokeType = jokeType;
        this.jokeDes = jokeDes;
        this.jokePunchline = jokePunchline;
    }

    public String getJokeID() {
        return jokeID;
    }

    public String getJokeType() {
        return jokeType;
    }

    public String getJokeDes() {
        return jokeDes;
    }

    public String getJokePunchline() {
        return jokePunchline;
    }
}
