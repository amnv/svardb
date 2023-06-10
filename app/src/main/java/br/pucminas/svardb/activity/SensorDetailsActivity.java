package br.pucminas.svardb.activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.pucminas.svardb.R;
import br.pucminas.svardb.activity.adapter.SensorDetailsAdapter;
import br.pucminas.svardb.model.Sensor;
import br.pucminas.svardb.util.Contract;

public class SensorDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_details);

        List<Sensor> sensors = getSensors();

        RecyclerView view = findViewById(R.id.rv_sensor_details);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(new SensorDetailsAdapter(sensors));
    }

    private List<Sensor> getSensors() {
        List<Sensor> sensors = new ArrayList<>();
        int size = getIntent().getIntExtra(Contract.SENSOR_COUNT_EXTRA, 0);
        for (int i = 0; i < size; i++) {
            sensors.add((Sensor) getIntent().getSerializableExtra(Contract.SENSOR_EXTRA + i));
        }
        return sensors;
    }
}
