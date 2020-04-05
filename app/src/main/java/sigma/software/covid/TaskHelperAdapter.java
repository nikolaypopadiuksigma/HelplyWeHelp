package sigma.software.covid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskHelperAdapter extends RecyclerView.Adapter<TaskHelperAdapter.PlanetViewHolder> {

    ArrayList<TaskHelper> planetList;

    public TaskHelperAdapter(ArrayList<TaskHelper> planetList, Context context) {
        this.planetList = planetList;
    }

    @Override
    public TaskHelperAdapter.PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_helper, parent, false);
        PlanetViewHolder viewHolder = new PlanetViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskHelperAdapter.PlanetViewHolder holder, int position) {
        holder.text.setText("Message: " + planetList.get(position).getMessage());
        holder.phone.setText("Phone: " + planetList.get(position).getPhone());
        holder.address.setText("Address: " + planetList.get(position).getLocation());
        if (planetList.get(position).isClose()) {
            holder.button.setText("Already Done!");
            holder.button.setEnabled(false);
        } else if (planetList.get(position).getHelperId().isEmpty()) {
            holder.button.setText("Take this!");
            holder.button.setEnabled(true);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Button) v).setText("End Task");
                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((Button) v).setText("Already Done!");
                            v.setEnabled(false);
                        }
                    });
                }
            });
        } else {
            holder.button.setText("End Task");
            holder.button.setEnabled(true);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Button) v).setText("Already Done!");
                    v.setEnabled(false);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }

    public static class PlanetViewHolder extends RecyclerView.ViewHolder {

        protected TextView address;
        protected TextView phone;
        protected TextView text;
        protected Button button;

        public PlanetViewHolder(View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
            text = itemView.findViewById(R.id.text);
            button = itemView.findViewById(R.id.button);

        }
    }
}