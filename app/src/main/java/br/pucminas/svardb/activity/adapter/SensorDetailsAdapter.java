package br.pucminas.svardb.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.pucminas.svardb.R;
import br.pucminas.svardb.model.Sensor;
import lombok.AllArgsConstructor;

public class SensorDetailsAdapter extends RecyclerView.Adapter<SensorDetailsAdapter.SensorDetailsHolder> {

    private final List<Sensor> sensors;

    public SensorDetailsAdapter(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @NonNull
    @Override
    public SensorDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_sensor_details, parent, false);
        return new SensorDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorDetailsHolder holder, int position) {
        holder.value.setText(String.format("%s%%", sensors.get(position).getValue()));

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy \nHH:mm:ss");
        holder.date.setText(format.format(sensors.get(position).getDate()));
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }

    public static class SensorDetailsHolder extends RecyclerView.ViewHolder {
        private final TextView value;
        private final TextView date;

        public SensorDetailsHolder(@NonNull View itemView) {
            super(itemView);
            value  = itemView.findViewById(R.id.txt_sensor_details_value);
            date = itemView.findViewById(R.id.txt_sensor_details_date);
        }
    }
}
