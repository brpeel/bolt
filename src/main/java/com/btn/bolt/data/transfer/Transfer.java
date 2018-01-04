package com.btn.bolt.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Transfer {

    @JsonProperty("id")
    @NotNull
    long id;

    @JsonProperty("user_id")
    @NotNull
    long userId;

    @JsonProperty("points")
    @NotNull
    long points;

    public Transfer(long id, long userId, long points) {
        this.id = id;
        this.userId = userId;
        this.points = points;
    }

    public Transfer() {

    }

    public long getId() {
        return id;
    }

    public Transfer setId(long id) {
        this.id = id;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public Transfer setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getPoints() {
        return points;
    }

    public Transfer setPoints(long points) {
        this.points = points;
        return this;
    }
}
