import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        ListView list_foods = findViewById(R.id.categories);

        list_foods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                if(i == 0) {
                    String sessionID = "events";
                    Intent intent = new Intent(Categories.this, EventsList.class);
                    intent.putExtra("category", sessionID);
                    startActivity(intent);
                }
                if(i == 1) {
                    String sessionID = "venues";
                    Intent intent = new Intent(Categories.this, EventsList.class);
                    intent.putExtra("category", sessionID);
                    startActivity(intent);
                }

                if(i == 2) {
                    String sessionID = "organizations";
                    Intent intent = new Intent(Categories.this, EventsList.class);
                    intent.putExtra("category", sessionID);
                    startActivity(intent);
                }

                if(i == 3) {
                    Intent intent = new Intent(Categories.this, BuildingsList.class);
                    startActivity(intent);
                }

                if(i == 4) {
                    String sessionID = "street_art";
                    Intent intent = new Intent(Categories.this, EventsList.class);
                    intent.putExtra("category", sessionID);
                    startActivity(intent);
                }
            }
        });
    }
}
