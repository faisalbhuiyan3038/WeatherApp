package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        tv = findViewById(R.id.tv);
    }

    public void get(View v){
        String apikey = "f861ccfaf12c919b8adcffa9ed4691c9";
        String lat = et1.getText().toString();
        String lon = et2.getText().toString();
        String url = "https://api.openweathermap.org/data/2.5/air_pollution?lat="+lat+"&lon="+lon+"&appid=f861ccfaf12c919b8adcffa9ed4691c9";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("components");
                    String CarbonMonoxide = object.getString("co");
                    tv.setText(CarbonMonoxide);
                    String NitrogenMonoxide = object.getString("no");
                    tv.setText(NitrogenMonoxide);
                    String NitrogenDioxide = object.getString("no2");
                    tv.setText(NitrogenDioxide);
                    String Ozone = object.getString("o3");
                    tv.setText(Ozone);
                    String SulphurDioxide = object.getString("so2");
                    tv.setText(SulphurDioxide);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}