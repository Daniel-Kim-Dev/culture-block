import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BuildingsList extends AppCompatActivity {
    ListView lv;
    ArrayList<String> buildings;
    ArrayList<String> longitude;
    ArrayList<String> latitude;
    String jsonFileContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings_list);
        lv = findViewById(R.id.buildings_list);

        try {
            jsonFileContent = readFile("CULTURAL_BUILDINGS.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            buildings = new ArrayList<>();
            longitude = new ArrayList<>();
            latitude = new ArrayList<>();

            JSONObject jsonArray = new JSONObject(jsonFileContent);
            JSONArray features = jsonArray.getJSONArray("features");

            for (int i = 0; i < features.length(); i++) {
                JSONObject jsonObj = features.getJSONObject(i);
                JSONObject properties = jsonObj.getJSONObject("properties");
                String buildingName = properties.getString("BLDGNAM");
                String streetName = properties.getString("STRNAM");
                String streetNumber = properties.getString("STRNUM");
                String longi = properties.getString("X");
                String lati = properties.getString("Y");

                buildings.add(buildingName + "\n" + streetName + " " + streetNumber);
                longitude.add(longi);
                latitude.add(lati);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> eventsAdapter = new ArrayAdapter<String>(BuildingsList.this, android.R.layout.simple_list_item_1, buildings);
        lv.setAdapter(eventsAdapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(BuildingsList.this, MapActivity.class);

            intent.putExtra("y", latitude.get(i));
            intent.putExtra("x", longitude.get(i));

            startActivity(intent);
        });
    }

    public String readFile(String fileName) throws IOException {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName), "UTF-8"));

        String content = "";
        String line;
        while ((line = reader.readLine()) != null) {
            content += line;
        }

        return content;
    }
}
