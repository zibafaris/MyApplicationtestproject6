package com.example.myapplicationweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyWeatherViewAdapter.ItemClickListener {
    ArrayList<String> MoviesArry =new ArrayList<>();
    MyWeatherViewAdapter adapterLeft;
    com.arlib.floatingsearchview.FloatingSearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchView =findViewById(R.id.floating_search_view);
        final SQLiteOpenHelper helper = new SQLiteOpenHelper(MainActivity.this, "Sematec", null, 1);
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
            }

            @Override
            public void onSearchAction(String currentQuery) {
                int CountTest = helper.CheckItemifSaved(currentQuery);
                if (CountTest == 0) {
                    helper.insertMovies(currentQuery);
                }
                Intent intent = new Intent(MainActivity.this, DetailWeatherPro.class);
                intent.putExtra("CityName", currentQuery);
                startActivity(intent);
                LoadItems(helper);
            }
        });
        LoadItems(helper);
    }
    public void LoadItems(SQLiteOpenHelper helper)
    {
        MoviesArry = helper.getAllStudentsName();
        String Zizi = String.valueOf(MoviesArry.size());
        RecyclerView recyclerView = findViewById(R.id.rvContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapterLeft = new MyWeatherViewAdapter(MainActivity.this, MoviesArry);
        adapterLeft.setClickListener(MainActivity.this);
        recyclerView.setAdapter(adapterLeft);
    }
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, DetailWeatherPro.class);
        intent.putExtra("CityName", MoviesArry.get(position));
        startActivity(intent);
    }
}
