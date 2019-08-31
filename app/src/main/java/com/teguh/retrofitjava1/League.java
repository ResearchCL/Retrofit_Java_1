package com.teguh.retrofitjava1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class League {

    @SerializedName("leagues")
    private List<LeagueDetail> leagues;

    public League(List<LeagueDetail> leagueDetail) {
        this.leagues = leagueDetail;
    }

    public List<LeagueDetail> getLeague() {
        return leagues;
    }
}
