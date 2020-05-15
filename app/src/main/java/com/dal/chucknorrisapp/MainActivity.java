package com.dal.chucknorrisapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private filleradapter filleradapter;


    List<filler> fillerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//loading the activity_main xml file
        fillerList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getJokes();//will fetch the jokes
        filleradapter =new filleradapter(this,fillerList);
        recyclerView.setAdapter(filleradapter);//set the adapter
    }

    public void getJokes() {
        final String url =  "http://api.icndb.com/jokes/random/10";//url for getting 10 random jokes


        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Success getting data", Toast.LENGTH_LONG).show();
                        try {   JSONArray jsonArray = response.getJSONArray("value");
                            for (int i=0; i< jsonArray.length(); i++){
                                //Parsing json array for getting joke
                                String jokes = jsonArray.getJSONObject(i).optString("joke");
                                fillerList.add(new filler(jokes, R.drawable.chuck_norris));//setting in the list
                            }
                            filleradapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error retrieving data", Toast.LENGTH_LONG).show();
            }
        }
        );
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);



    }
}
