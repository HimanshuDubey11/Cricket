package project.himanshu.com.cricket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    RecyclerView recyclerView;
    ArrayList<Data> dataArrayList;
    ProgressDialog progressBar;
    String url = "http://cricapi.com/api/matchCalendar/ob242roZWFSsuZkkdNjjCUFefms1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = new ProgressDialog(MainActivity.this);
        progressBar.setMessage("Loading...");
        progressBar.show();
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        recyclerView = findViewById(R.id.myrecyclerview);
        dataArrayList = new ArrayList<Data>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.dismiss();

                try {
                    JSONArray array = response.getJSONArray("data");

                    for (int i = 0; i < array.length() ; i++) {
                        JSONObject obj = array.getJSONObject(i);

                        String name=obj.getString("name");
                        String date=obj.getString("date");

                        Data mData=new Data();
                        mData.setName(name);
                        mData.setDate(date);

                        dataArrayList.add(mData);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                (MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new DataAdapter(dataArrayList));

    }
}