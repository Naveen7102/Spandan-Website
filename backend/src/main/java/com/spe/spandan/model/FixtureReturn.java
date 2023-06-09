package com.spe.spandan.model;

public class FixtureReturn {

    private Integer id;

    private String sport;

    private String team1;

    private String team2;

    private String winner;

    private String result;

    private String time;

    public FixtureReturn(Integer id, String sport, String team1, String team2, String winner, String result, String time) {
        this.id = id;
        this.sport = sport;
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.result = result;
        this.time = time;
    }

    public FixtureReturn() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
