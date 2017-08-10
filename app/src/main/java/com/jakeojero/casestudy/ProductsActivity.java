package com.jakeojero.casestudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.jakeojero.casestudy.adapters.ProductsAdapter;
import com.jakeojero.casestudy.models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<Product> products = new ArrayList<>();
    private String brand;
    RecyclerView rv;
    ProductsAdapter radapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        rv = (RecyclerView) findViewById(R.id.productsRV);

        radapter = new ProductsAdapter(this, products);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rv);

        rv.setAdapter(radapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);
        //rv.setLayoutManager(new LinearLayoutManager(this));

        Spinner spinner = (Spinner) findViewById(R.id.brandSpinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.brands_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        brand = parent.getItemAtPosition(position).toString();

        String url = "http://casestudyjo.azurewebsites.net/api/GetProductsByBrand/" + brand;
        // get products
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, res, error) {
            @Override
            public String getBodyContentType() {return "application/json; charset=UTF-8";  }
        };

        queue.add(req);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        brand = parent.getItemAtPosition(0).toString();
        Log.i("PRODTEST", "onItemSelected: brand  " + brand);
    }

    Response.Listener<JSONArray> res = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("PRODTESTS", "onResponse: " + response);
                try {
                    products.clear();
                    for(int i=0; i < response.length(); ++i) {
                        Product p = new Product(
                                response.getJSONObject(i).getString("Id"),
                                response.getJSONObject(i).getInt("BrandId"),
                                response.getJSONObject(i).getString("ProductName"),
                                response.getJSONObject(i).getString("GraphicName"),
                                response.getJSONObject(i).getDouble("CostPrice"),
                                response.getJSONObject(i).getDouble("MSRP"),
                                response.getJSONObject(i).getInt("QtyOnHand"),
                                response.getJSONObject(i).getInt("QtyOnBackOrder"),
                                response.getJSONObject(i).getString("Description"));
                        products.add(p);
                    }
                    radapter.dataChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
    };

    Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("PRODTEST", "onErrorResponse: " + error);
                error.printStackTrace();
            }
    };


}
