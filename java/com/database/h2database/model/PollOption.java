package com.database.h2database.model;

public class PollOption {
    private String option;
    private int votes;

    public PollOption(String option, int votes) {
        this.option = option;
        this.votes = votes;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "PollOption{" +
                "option='" + option + '\'' +
                ", votes=" + votes +
                '}';
    }
}
