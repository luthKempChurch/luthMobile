package com.mobile.luthkemp.luthkemp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Prayer extends AppCompatActivity {
  private EditText txtName;
  private EditText txtSurname;
  private EditText txtEmail;
  private EditText txtCellphone;
  private EditText txtDescription;
  private Button btnSubmit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.prayer);
    //getActionBar().setDisplayHomeAsUpEnabled(true);
  }
private void init(){
  txtCellphone = (EditText) findViewById(R.id.txtphoneNumber);
  txtDescription = (EditText) findViewById(R.id.txtDescription);
  txtName = (EditText) findViewById(R.id.txtName);
  txtSurname = (EditText) findViewById(R.id.txtSurname);
  txtEmail = (EditText) findViewById(R.id.txtEmail);
  btnSubmit = (Button) findViewById(R.id.btnSubmit);

  btnSubmit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
  });
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
