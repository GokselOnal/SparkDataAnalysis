package Spark;

import java.io.Serializable;

public class GroupedData implements Serializable {
    String team;
    int win_count;

    public GroupedData(String team, int win_count) {
        this.team = team;
        this.win_count = win_count;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getWin_count() {
        return win_count;
    }

    public void setWin_count(int win_count) {
        this.win_count = win_count;
    }
}
