package com.example.maciej.bootcampbeer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class BeerListActivity extends ActionBarActivity {

    private ListView list;
    private BeerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);

        initialeList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void initialeList() {
        list = (ListView) findViewById(R.id.beer_list);
        adapter = new BeerAdapter(this);
        list.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_beer_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_beer) {
            addBeer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addBeer() {
        Intent i = new Intent(this, AddBeerActivity.class);
        startActivity(i);
    }
}
