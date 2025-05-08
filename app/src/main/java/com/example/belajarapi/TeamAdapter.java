package com.example.belajarapi;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
public class TeamAdapter  extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    private Context context;
    private List<Team> teamList;

    public TeamAdapter(Context context, List<Team> teamList) {
        this.context = context;
        this.teamList = teamList;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.txtTeam.setText(team.getStrTeam());
        holder.txtStadium.setText(team.getStrStadium());
        Picasso.get().load(team.getStrTeamBadge()).into(holder.imgLogo);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView txtTeam, txtStadium;
        ImageView imgLogo;

        public TeamViewHolder(View itemView) {
            super(itemView);
            txtTeam = itemView.findViewById(R.id.txtTeam);
            txtStadium = itemView.findViewById(R.id.txtStadium);
            imgLogo = itemView.findViewById(R.id.imgLogo);
        }
    }
}
