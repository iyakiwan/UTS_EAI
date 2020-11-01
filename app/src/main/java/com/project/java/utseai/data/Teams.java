package com.project.java.utseai.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Teams {
    @SerializedName("teams")
    private List<Team> teams;

    public Teams() {
    }

    public Teams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
