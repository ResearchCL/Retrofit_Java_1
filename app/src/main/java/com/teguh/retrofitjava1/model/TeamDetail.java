package com.teguh.retrofitjava1.model;

import com.google.gson.annotations.SerializedName;

public class TeamDetail {
    @SerializedName("idTeam")
    private String teamId;
    @SerializedName("strTeam")
    private String teamName;
    @SerializedName("strTeamBadge")
    private String teamBadge;

    public String getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamBadge() {
        return teamBadge;
    }
}
