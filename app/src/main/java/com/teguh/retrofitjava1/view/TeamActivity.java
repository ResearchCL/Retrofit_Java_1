package com.teguh.retrofitjava1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.teguh.retrofitjava1.R;
import com.teguh.retrofitjava1.api.SportDbApi;
import com.teguh.retrofitjava1.model.Team;
import com.teguh.retrofitjava1.model.TeamDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamActivity extends AppCompatActivity {

    private ListView lvTeam;
    private SportDbApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        lvTeam = findViewById(R.id.lv_team);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SportDbApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SportDbApi.class);

        getAllTeams();

    }

    private void getAllTeams() {

        Call<Team> call = api.getAllTeams("4331");

        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                Team team = response.body();

                List<TeamDetail> teamList = team.getTeams();

                String[] teams = new String[teamList.size()];

                for (int i = 0; i < teamList.size(); i++) {
                    teams[i] = teamList.get(i).getTeamName();
                }

                lvTeam.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, teams));

            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
