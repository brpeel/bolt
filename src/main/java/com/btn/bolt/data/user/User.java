package com.btn.bolt.data.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class User {

    @JsonProperty("id")
    @NotNull
    long id;

    @JsonProperty("email")
    @NotNull
    String email;

    @JsonProperty("first_name")
    @NotNull
    String firstName;

    @JsonProperty("last_name")
    @NotNull
    String lastName;

    @JsonProperty("points")
    @NotNull
    long points;

    public User() {
    }

    public User(long id, String email, String firstName, String lastName, long points) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public long getPoints() {
        return points;
    }
}

