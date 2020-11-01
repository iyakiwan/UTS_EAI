package com.project.java.utseai.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.java.utseai.R;
import com.project.java.utseai.data.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>{
    private List<Team> teams = new ArrayList<>();

    public void setData (List<Team> teams) {
        this.teams.clear();
        this.teams = teams;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        holder.bind(teams.get(position));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("detail", teams.get(position));
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDesc;
        private ImageView imgPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            imgPoster = itemView.findViewById(R.id.img_photo);
        }

        public void bind(Team team) {
            tvName.setText(team.getStrTeam());
            tvDesc.setText(team.getStrDescriptionEN());
            Glide.with(itemView.getContext())
                    .load(team.getStrTeamBadge())
                    .apply(new RequestOptions())
                    .into(imgPoster);
        }
    }
}
