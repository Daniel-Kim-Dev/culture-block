import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_details);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        String website = extras.getString("website");
        String email = extras.getString("email");
        String phone = extras.getString("phone");
        String address = extras.getString("address");
        String description = extras.getString("description");
        String x = extras.getString("x");
        String y = extras.getString("y");

        TextView textName = findViewById(R.id.text_name);
        TextView textWebsite = findViewById(R.id.text_website);
        TextView textEmail = findViewById(R.id.text_email);
        TextView textPhone = findViewById(R.id.text_phone);
        TextView textAddress = findViewById(R.id.text_address);
        TextView textDescription = findViewById(R.id.text_description);

        if (website.trim().length() == 0) {
            website = "N/A";
        }

        if (email.trim().length() == 0) {
            email = "N/A";
        }

        if (phone.trim().length() == 0) {
            phone = "N/A";
        }

        if (address.trim().length() == 0) {
            address = "N/A";
        }

        textName.setText(name);
        textWebsite.setText("Website: " + website);
        textEmail.setText("Email: " + email);
        textPhone.setText("Phone #: " + phone);
        textAddress.setText("Address: " + address);
        textDescription.setText("Description: " + description);

        Button button = findViewById(R.id.button_map);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EventsDetails.this, MapActivity.class);
                intent.putExtra("x", x);
                intent.putExtra("y", y);
                startActivity(intent);
            }
        });
    }
}
