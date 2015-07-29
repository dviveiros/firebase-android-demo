package com.danielviveiros.firebasedemo;

/**
 * Created by dviveiros on 29/07/15.
 */
public class Tweet {

    private String author;
    private String message;

    public Tweet() {

    }

    public Tweet( String author, String message ) {
        this.author = author;
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
