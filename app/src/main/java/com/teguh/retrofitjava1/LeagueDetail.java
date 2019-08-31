package com.teguh.retrofitjava1;

import com.google.gson.annotations.SerializedName;

public class LeagueDetail {

    @SerializedName("idLeague")
    private String leagueId;
    @SerializedName("strLeague")
    private String leagueName;
    @SerializedName("strSport")
    private String leagueSport;
    @SerializedName("strLeagueAlternate")
    private String leagueNameAlternate;

    public String getLeagueId() {
        return leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeagueSport() {
        return leagueSport;
    }

    public String getLeagueNameAlternate() {
        return leagueNameAlternate;
    }
}
