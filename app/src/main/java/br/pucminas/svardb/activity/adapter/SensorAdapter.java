package br.pucminas.svardb.activity.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.pucminas.svardb.R;
import br.pucminas.svardb.model.Sensor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder> {

    private List<Sensor> sensors;

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_list_sensors, parent, false);
        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        double sensorValue = sensors.get(position).getValue();

        holder.name.setText(sensors.get(position).getName());
        holder.value.setText(sensorValue + "%");

        if (sensorValue >= 60) {
            holder.layout.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }

    public static class SensorViewHolder  extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView value;

        private LinearLayout layout;
        public SensorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_sensor_name);
            value = itemView.findViewById(R.id.txt_sensor_value);
            layout = itemView.findViewById(R.id.layout_cardview);
        }
    }
}
