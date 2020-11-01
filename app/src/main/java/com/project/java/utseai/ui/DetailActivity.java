package com.project.java.utseai.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.java.utseai.R;
import com.project.java.utseai.data.Team;
import com.project.java.utseai.data.Teams;
import com.project.java.utseai.network.ApiClient;
import com.project.java.utseai.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private Team team = new Team();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvName = findViewById(R.id.tv_name);
        TextView tvId = findViewById(R.id.tv_id);
        TextView tvYear = findViewById(R.id.tv_year);
        TextView tvStadium = findViewById(R.id.tv_stadium);
        TextView tvDesc = findViewById(R.id.tv_desc);
        ImageView imgPoster = findViewById(R.id.iv_poster);

        team = getIntent().getParcelableExtra("detail");

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Teams> call = service.getTeamDetail(team.getIdTeam());
        call.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse(Call<Teams> call, Response<Teams> response) {
                if (response.isSuccessful()) {
                    Teams teams = response.body();
                    if (teams != null && teams.getTeams() != null) {
                        team = teams.getTeams().get(0);
                        tvName.setText(team.getStrTeam());
                        tvId.setText(team.getIdTeam());
                        tvYear.setText(team.getIntFormedYear());
                        tvStadium.setText(team.getStrStadium());
                        tvDesc.setText(team.getStrDescriptionEN());
                        Glide.with(getApplicationContext())
                                .load(team.getStrTeamBadge())
                                .apply(new RequestOptions())
                                .into(imgPoster);
                    }
                }
            }

            @Override
            public void onFailure(Call<Teams> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Connection Failed" , Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }
}