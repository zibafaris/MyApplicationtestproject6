package com.example.myapplicationweatherapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import cz.msebera.android.httpclient.Header;

public class DetailWeatherPro extends AppCompatActivity {

    MyWeatherDetailViewAdapter adapterLeft;
    ArrayList<DetailWeather> mData;
    int dayOfWeek;
    LinearLayout _boxplace;
    private DrawerLayout mLayout;
    TextView _txtdegOrg,_txtrain,_txtbaran,PlceCity;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_weather_pro);
        AsyncHttpClient Zirclient = new AsyncHttpClient();
        Intent intent = getIntent();
        _txtdegOrg = findViewById(R.id.txtdegOrg);
        _txtrain = findViewById(R.id.txtrain);
        _txtbaran = findViewById(R.id.txtbaran);
        Calendar calendar = Calendar.getInstance();
        PlceCity = findViewById(R.id.PlceCity);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        _boxplace = findViewById(R.id.boxplace);
//        mLayout = (DrawerLayout) findViewById(R.id.sliding_layout);
        ImageButton button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.openDrawer(GravityCompat.START);
            }

//            @Override
//            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
//
//            }
//
//            @Override
//            public void onDrawerOpened(@NonNull View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerClosed(@NonNull View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//                if (newState.toString() == "COLLAPSED") {
//                    _boxplace.setBackgroundColor(Color.parseColor("#ffffff"));
//                }
//                else{
//                    _boxplace.setBackgroundColor(Color.TRANSPARENT);
//                }

//            }
//            @Override
//            public void onPanelStateChanged(View panel, DrawerLayout.PanelState previousState, DrawerLayout.PanelState newState) {
//                if (newState.toString() == "COLLAPSED") {
//                    _boxplace.setBackgroundColor(Color.parseColor("#ffffff"));
//                }
//                else{
//                    _boxplace.setBackgroundColor(Color.TRANSPARENT);
//                }
//            }
        });


        String _CityName = intent.getStringExtra("CityName");

        PlceCity.setText(_CityName.toUpperCase());

        String Url = "https://api.openweathermap.org/data/2.5/forecast/daily?q="+_CityName+",IR&appid=141a44d6c157c2a60e5e70551c399aba";// + intent.getStringExtra("ItemID");
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Url, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                JSONObject jsonobject = null;
                try {
                    jsonobject = new JSONObject(responseString);
                    JSONArray jsonoArray = new JSONArray(jsonobject.getString("list"));
                    int dayof = dayOfWeek;
                    mData = new ArrayList<>();
                    for (int i = 0; i < jsonoArray.length(); i++) {
                        JSONObject jsonSub = jsonoArray.getJSONObject(i);
                        JSONArray weatherArray = new JSONArray(jsonSub.getString("weather"));
                        JSONObject jsonWSub = weatherArray.getJSONObject(0);//icon
                        if (i==0)
                        {
                            _txtrain.setText(jsonSub.getString("humidity") + "%");
                            _txtdegOrg.setText(jsonSub.getString("deg") + "°C");
                            _txtbaran.setText(jsonSub.getString("speed") + "m/s");
                        }
                        else if (i != 1)
                        {
                            mData.add(new DetailWeather(GetCurrentday(dayof), jsonWSub.getString("icon"), jsonWSub.getString("description"), jsonSub.getString("deg") + "°"));
                        }
                        else if (i == 1)
                        {
                            mData.add(new DetailWeather("Tommorow",jsonWSub.getString("icon"), jsonWSub.getString("description"), jsonSub.getString("deg") + "°"));
                        }

                        if (dayof != 7)
                        {
                            dayof = dayof + 1;
                        }
                        else
                        {
                            dayof = 1;
                        }
                    }
                    RecyclerView recyclerView = findViewById(R.id.rvshowitem);
                    recyclerView.setLayoutManager(new LinearLayoutManager(DetailWeatherPro.this));
                    adapterLeft = new MyWeatherDetailViewAdapter(DetailWeatherPro.this,mData);
                    recyclerView.setAdapter(adapterLeft);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public String  GetCurrentday(int dayOfW)
    {
        String weekDay = "";

        if (Calendar.MONDAY == dayOfW) {
            weekDay = "Monday";
        } else if (Calendar.TUESDAY == dayOfW) {
            weekDay = "Tuesday";
        } else if (Calendar.WEDNESDAY == dayOfW) {
            weekDay = "Wednesday";
        } else if (Calendar.THURSDAY == dayOfW) {
            weekDay = "Thursday";
        } else if (Calendar.FRIDAY == dayOfW) {
            weekDay = "Friday";
        } else if (Calendar.SATURDAY == dayOfW) {
            weekDay = "Saturday";
        } else if (Calendar.SUNDAY == dayOfW) {
            weekDay = "Sunday";
        }
        return  weekDay;
    }
}