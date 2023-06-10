package br.pucminas.svardb.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.pucminas.svardb.R;
import br.pucminas.svardb.activity.SensorDetailsActivity;
import br.pucminas.svardb.model.Sensor;
import br.pucminas.svardb.util.Contract;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder> {

    private final List<Sensor> sensorsMostRecent;
    private final List<Sensor> sensors;

    public SensorAdapter(List<Sensor> sensors) {
        this.sensorsMostRecent = this.mostRecentToEachIdList(sensors);
        this.sensors = sensors;
    }

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_list_sensors, parent, false);
        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        double sensorValue = sensorsMostRecent.get(position).getValue();

        holder.name.setText(sensorsMostRecent.get(position).getName());
        holder.value.setText(sensorValue + "%");

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sensorName = ((TextView) view.findViewById(R.id.txt_sensor_name))
                        .getText().toString();
                Context context = view.getContext();
                Intent intent = new Intent(context, SensorDetailsActivity.class);
                List<Sensor> sensorsIntent = sensors.stream()
                        .filter(sensor -> sensor.getName().equals(sensorName))
                        .collect(Collectors.toList());
                intent.putExtra(Contract.SENSOR_COUNT_EXTRA, sensorsIntent.size());
                for (int i = 0; i < sensorsIntent.size(); i++) {
                    intent.putExtra(Contract.SENSOR_EXTRA + i, sensorsIntent.get(i));
                }
                context.startActivity(intent);
            }
        });
        if (sensorValue >= 60) {
            holder.layout.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }

    @Override
    public int getItemCount() {
        return sensorsMostRecent.size();
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

    private List<Sensor> mostRecentToEachIdList(List<Sensor> sensors) {
        Map<String, LocalDateTime> mostRecentTime = new HashMap<>();
        Map<String, Sensor> mostRecentSensors = new HashMap<>();

        for (Sensor sensor : sensors) {
            if (sensor.getDate() == null) continue;
            if (mostRecentTime.get(sensor.getName()) != null) {
                if (mostRecentTime.get(sensor.getName()).isBefore(sensor.getDate())) {
                    mostRecentTime.put(sensor.getName(), sensor.getDate());
                    mostRecentSensors.put(sensor.getName(), sensor);
                }
            } else {
                mostRecentTime.put(sensor.getName(), sensor.getDate());
                mostRecentSensors.put(sensor.getName(), sensor);
            }
        }

        return new ArrayList<>(mostRecentSensors.values());
    }
}
