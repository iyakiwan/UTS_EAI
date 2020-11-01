package com.project.java.utseai.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.project.java.utseai.R;
import com.project.java.utseai.data.Teams;
import com.project.java.utseai.network.ApiClient;
import com.project.java.utseai.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_team);
        adapter = new TeamAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getDataTeams();
    }

    private void getDataTeams() {
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Teams> call = service.getTeams("4329");
        call.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse(Call<Teams> call, Response<Teams> response) {
                if (response.isSuccessful()) {
                    Teams teams = response.body();
                    if (teams != null && teams.getTeams() != null) {
                        adapter.setData(teams.getTeams());
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