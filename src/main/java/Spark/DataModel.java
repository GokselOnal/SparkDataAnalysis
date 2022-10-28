package Spark;

import java.io.Serializable;

public class DataModel implements Serializable {
    private String date;
    private String home_team;
    private String away_team;
    private String winner;

    public DataModel(String date, String home_team, String away_team, String winner) {
        this.date = date;
        this.home_team = home_team;
        this.away_team = away_team;
        this.winner = winner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
