import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EventsList extends AppCompatActivity {
    ListView lv;
    ArrayList<String> events;
    ArrayList<String> websites;
    ArrayList<String> emails;
    ArrayList<String> phones;
    ArrayList<String> addresses;
    ArrayList<String> descriptions;
    ArrayList<String> longitude;
    ArrayList<String> latitude;
    String jsonFileContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        lv = findViewById(R.id.events_list);

        Bundle extras = getIntent().getExtras();
        String category = extras.getString("category");
        String fileName = "";

        if (category.equals("events")) {
            fileName = "EVENTS.json";
        } else if (category.equals("venues")) {
            fileName = "VENUES.json";
        } else if (category.equals("organizations")) {
            fileName = "CULTURAL_ORGANIZATIONS.json";
        } else if (category.equals("street_art")) {
            fileName = "PUBLIC_ART.json";
        }

        try {
            jsonFileContent = readFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            events = new ArrayList<>();
            websites = new ArrayList<>();
            emails = new ArrayList<>();
            phones = new ArrayList<>();
            addresses = new ArrayList<>();
            descriptions = new ArrayList<>();
            longitude = new ArrayList<>();
            latitude = new ArrayList<>();

            JSONObject jsonArray = new JSONObject(jsonFileContent);
            JSONArray features = jsonArray.getJSONArray("features");

            for (int i = 0; i < features.length(); i++) {
                JSONObject jsonObj = features.getJSONObject(i);
                JSONObject properties = jsonObj.getJSONObject("properties");
                String event = properties.getString("Name");
                String website = properties.getString("website");
                String email = properties.getString("email");
                String phone = properties.getString("phone");
                String address = properties.getString("Address");
                String description = properties.getString("Descriptn");
                String longi = properties.getString("X");
                String lati = properties.getString("Y");

                events.add(event);
                websites.add(website);
                emails.add(email);
                phones.add(phone);
                addresses.add(address);
                descriptions.add(description);
                longitude.add(longi);
                latitude.add(lati);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> eventsAdapter = new ArrayAdapter<String>(EventsList.this, android.R.layout.simple_list_item_1, events);
        lv.setAdapter(eventsAdapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(EventsList.this, EventsDetails.class);
            intent.putExtra("name", events.get(i));
            intent.putExtra("website", websites.get(i));
            intent.putExtra("email", emails.get(i));
            intent.putExtra("phone", phones.get(i));
            intent.putExtra("address", addresses.get(i));
            intent.putExtra("description", descriptions.get(i));
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
