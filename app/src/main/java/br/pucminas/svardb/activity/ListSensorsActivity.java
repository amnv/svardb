package br.pucminas.svardb.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.pucminas.svardb.R;
import br.pucminas.svardb.activity.adapter.SensorAdapter;
import br.pucminas.svardb.model.Sensor;

public class ListSensorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensors);

        List<Sensor> sensors = List.of(new Sensor("sensor 1", 30L),
                new Sensor("sensor 2", 40L),
                new Sensor("sensor 3", 50L),
                new Sensor("sensor 4", 60L),
                new Sensor("sensor 5", 70L));
        RecyclerView view = findViewById(R.id.recyclerView);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(new SensorAdapter(sensors));
    }
}
