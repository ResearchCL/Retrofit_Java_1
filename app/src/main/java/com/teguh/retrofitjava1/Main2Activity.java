package com.teguh.retrofitjava1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    private ListView lvLeagues;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lvLeagues = findViewById(R.id.lv_leagues);

        // instansiasi retrofit objek
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Di sini kita menggunakan GsonConverterFactory untuk secara langsung mengkonversi data json ke objek
                .build();

        // buat api interface
        api = retrofit.create(Api.class);

        getAllLeagues(); //buat method

    }

    private void getAllLeagues() {
        //creating the call object
        //gunakan method api yang kita buat di dalam Api.interface
        Call<League> call = api.getAllLeagues();

        call.enqueue(new Callback<League>() {
            @Override
            public void onResponse(Call<League> call, Response<League> response) {
                League league = response.body();

                //dapatkan data league ke dalam bentuk list
                List<LeagueDetail> leagueList = league.getLeague();

                //instansiasi leagues dengan type String array
                String[] leagues = new String[leagueList.size()];

                //pindahkan leagueList ke bentuk data String array
                for (int i = 0; i < leagueList.size(); i++) {
                    leagues[i] = leagueList.get(i).getLeagueName();
                }

                //tampilkan String array ke ListView
                lvLeagues.setAdapter(new ArrayAdapter<String>
                        (getApplicationContext(), android.R.layout.simple_list_item_1, leagues));

            }

            @Override
            public void onFailure(Call<League> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
