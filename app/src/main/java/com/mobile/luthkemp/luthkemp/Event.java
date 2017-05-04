package com.mobile.luthkemp.luthkemp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.mobile.luthkemp.luthkemp.adapters.EventsAdapter;
import com.mobile.luthkemp.luthkemp.helper.EventView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Event extends AppCompatActivity {
private EventsAdapter adapter;
  private ListView listView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.event);
    initViews();
   adapterTest();
    //getActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void initViews() {
    listView = (ListView) findViewById(R.id.listviewEvents);
  }

  private void adapterTest(){
    List<EventView> events = new ArrayList<>();
    events.add(new EventView(1,new Date(),"Prayer","ewewewe","jabu","week 2","jhasjha",true));
    events.add(new EventView(2,new Date(),"soccer","ewewewe","jabu","week 4","jhasjha",true));
    events.add(new EventView(3,new Date(),"Career","ewewewe","jabu","week 1","jhasjha",true));
    events.add(new EventView(4,new Date(),"Bits","ewewewe","jabu","week 2","jhasjha",true));
   adapter = new EventsAdapter(Event.this,events);
    listView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
