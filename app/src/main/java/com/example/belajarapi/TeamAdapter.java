package com.example.belajarapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

// Adapter untuk RecyclerView, khusus menangani list data Team
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private Context context;            // menyimpan context dari activity atau fragment
    private List<Team> teamList;       // menyimpan daftar tim yang akan ditampilkan

    // Constructor: menerima context dan daftar tim
    public TeamAdapter(Context context, List<Team> teamList) {
        this.context = context;
        this.teamList = teamList;
    }

    // Dipanggil ketika RecyclerView butuh membuat item baru (inflate layout)
    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate layout item_team.xml untuk tiap item
        View view = LayoutInflater.from(context).inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }

    // Dipanggil untuk mengisi data ke dalam ViewHolder di posisi tertentu
    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        // Ambil data Team di posisi saat ini
        Team team = teamList.get(position);

        // Set nama tim dan nama stadion ke TextView
        holder.txtTeam.setText(team.getStrTeam());
        holder.txtStadium.setText(team.getStrStadium());

        // Muat gambar logo tim menggunakan Picasso
        Glide.with(holder.itemView.getContext())
                .load(team.getBadge())
                .into(holder.imgLogo);            // pasang gambar ke ImageView
    }

    // Mengembalikan jumlah item yang ada dalam list
    @Override
    public int getItemCount() {
        return teamList.size();
    }

    // ViewHolder: memegang view yang digunakan untuk menampilkan item
    public class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView txtTeam, txtStadium;
        ImageView imgLogo;

        public TeamViewHolder(View itemView) {
            super(itemView);
            // Hubungkan variabel dengan elemen di layout item_team.xml
            txtTeam = itemView.findViewById(R.id.txtTeam);          // TextView untuk nama tim
            txtStadium = itemView.findViewById(R.id.txtStadium);    // TextView untuk nama stadion
            imgLogo = itemView.findViewById(R.id.imgLogo);          // ImageView untuk logo tim
        }
    }
}
