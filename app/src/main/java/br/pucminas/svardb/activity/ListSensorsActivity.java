package br.pucminas.svardb.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.pucminas.svardb.R;
import br.pucminas.svardb.activity.adapter.SensorAdapter;
import br.pucminas.svardb.model.Sensor;
import br.pucminas.svardb.worker.DynamoBDConnect;

public class ListSensorsActivity extends AppCompatActivity {

    private List<Sensor> sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensors);

        new GetAllItemsAsyncTask().execute();
    }

    private void populateMemoList(List<Map<String, AttributeValue>> documents) {
        sensors = new ArrayList<>();
        for (Map<String, AttributeValue> document : documents) {
            try {
                Sensor sensor = new Sensor(document.get("device_id").getS(),
                        Long.parseLong(document.get("device_data").getM().get("humidity").getN()),
                        LocalDateTime.ofInstant(new Timestamp(Long.parseLong(document.get("sample_time")
                                .getN())).toInstant(), ZoneId.systemDefault()));
                sensors.add(sensor);
            } catch (NullPointerException e) {
                Log.e("ListSensorsActivity", "populateMemoList error: ", e);
            }
        }

        RecyclerView view = findViewById(R.id.rv_list_sensors);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(new SensorAdapter(sensors));
    }

    private class GetAllItemsAsyncTask extends AsyncTask<Void, Void, List<Map<String, AttributeValue>>> {
        @Override
        protected List<Map<String, AttributeValue>> doInBackground(Void... params) {
            DynamoBDConnect databaseAccess = DynamoBDConnect.getInstance(ListSensorsActivity.this.getBaseContext());
            return databaseAccess.getAllMemos();
        }

        @Override
        protected void onPostExecute(List<Map<String, AttributeValue>> documents) {
            if (documents != null && documents.size() > 0) {
                populateMemoList(documents);
            }
        }
    }

}
