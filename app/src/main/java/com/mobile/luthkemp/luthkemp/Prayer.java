package com.mobile.luthkemp.luthkemp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.mobile.luthkemp.luthkemp.helper.PrayerView;

public class Prayer extends BaseEntry {
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
    init();
  }

  private void init() {
    txtCellphone = (EditText) findViewById(R.id.txtphoneNumber);
    txtDescription = (EditText) findViewById(R.id.txtDescription);
    txtName = (EditText) findViewById(R.id.txtName);
    txtSurname = (EditText) findViewById(R.id.txtSurname);
    txtEmail = (EditText) findViewById(R.id.txtEmail);
    btnSubmit = (Button) findViewById(R.id.btnSubmit);
    btnSubmit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PostPrayer();
      }
    });
  }

  private void PostPrayer(){
    doOnUIThread(action_SHOW_PROGRESS_DIALOG,"requesting prayer");
    PrayerView prayer = new PrayerView(txtName.getText().toString(),txtSurname.getText().toString(),txtEmail.getText().toString(),txtCellphone.getText().toString(),txtDescription.getText().toString());
    POST(prayer,"prayer/post");
  }

  private void doOnUIThread(final int action, final String txt) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        //ProgressDialog progressDialog = new ProgressDialog(Event.this);
        switch (action) {
          case action_SHOW_PROGRESS_DIALOG:
            progressDialog = new ProgressDialog(Prayer.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(txt);
            // Show it
            progressDialog.show();
            break;
          case action_HIDE_PROGRESS_DIALOG:
            if (progressDialog != null) {
              progressDialog.dismiss();
            }
            break;
          default:
            break;
        }
      }
    });
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

  @Override
  public void OnGetDataSucces(String responseBody) {
    doOnUIThread(action_HIDE_PROGRESS_DIALOG,"");
  }

  @Override
  public void OnGetDataFailed(String ResponseBody) {
    doOnUIThread(action_HIDE_PROGRESS_DIALOG,"");
  }
}
