package campbell.ca.hw;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        EditText country = (EditText) findViewById(R.id.locationMap);
        String value = getIntent().getExtras().getString("country");
        if (value == null || value.isEmpty()) {
            country.setText(R.string.noData);
            country.setTextColor(Color.RED);
        } else {
            MainActivity.logIt("country" + value);
            country.setText(value);
            country.setTextColor(Color.MAGENTA);
        }
    }
    public void showMap(View v) {
        EditText country = (EditText) findViewById(R.id.locationMap);
        Uri geoLocation = Uri.parse("geo:0,0?q=" + Uri.encode(country.getText().toString()));

        Intent geoIntent = new Intent(Intent.ACTION_VIEW);
        geoIntent.setData(geoLocation);
        if (geoIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(geoIntent);
        } else {
            country.setText(R.string.error_no_geo);
        }

    }
    public void searchWeb(View v) {
        EditText country = (EditText) findViewById(R.id.locationMap);
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, country.getText().toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
