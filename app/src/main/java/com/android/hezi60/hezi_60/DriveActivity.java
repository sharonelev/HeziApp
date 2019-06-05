package com.android.hezi60.hezi_60;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.orhanobut.hawk.Hawk;

public class DriveActivity extends AppCompatActivity implements OnClickListener {
  boolean returnToStart = false;
  public TextView title_hint_num;
  public TextView topText;
  public View checkLocation;
  public ProgressBar progressBar;
  public Button inLocation;
  public int hint_num;
  AlertDialog.Builder alertDialogBuilder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drive);
    setTitle(R.string.bday60);

    LocalHelper.setLocale(this,"en");

    Intent intent = getIntent();
    hint_num = intent.getIntExtra(getString(R.string.hint_args), 1);
    alertDialogBuilder = new AlertDialog.Builder(this);

    returnToStart = false;
    inLocation = findViewById(R.id.inLoc);
    inLocation.setOnClickListener(this);

    checkLocation = findViewById(R.id.checkLocation);
    progressBar = findViewById(R.id.progressBar);

    title_hint_num = findViewById(R.id.titleText);

    Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.from_right);

    title_hint_num.setText(String.format(getString(R.string.hint_title), String.valueOf(hint_num)));

    ImageView car =  findViewById(R.id.car);
    car.startAnimation(animation);



  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_start, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {


    int id = item.getItemId();

    if (id == R.id.action_start) {
      Intent i = new Intent();
      i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      i.setClass(this, SplashActivity.class);
      //    i.putExtra(getString(R.string.hint_args),hint_num);
     returnToStart = true;
      startActivity(i);

      return true;
    }
    return super.onOptionsItemSelected(item);
  }


  @Override
  protected void onPause() {
    super.onPause();
    super.onStop();

    if(returnToStart)
    {
      //getFragmentManager().popBackStack();
      Hawk.delete(getString(R.string.screen)); //to start from beginning
      Hawk.delete(getString(R.string.hint_num)); //to start from beginning
      finish();
    }
    else {
      Hawk.put(getString(R.string.screen), "drive");
      Hawk.put(getString(R.string.hint_num), hint_num);
    }
  }


  @Override
  public void onClick(View v) {
    switch (v.getId()) {

      case R.id.inLoc:
        if(hint_num == 1) {
          //check GPS - open dialog
          findViewById(R.id.progressDialog).setVisibility(View.VISIBLE);
          checkLocation.setVisibility(View.VISIBLE);
          progressBar.setVisibility(View.VISIBLE);
          inLocation.setOnClickListener(null);

          final Handler handler = new Handler();
          handler.postDelayed(new Runnable() {
            public void run() {

              progressBar.setVisibility(View.GONE);
              TextView text = findViewById(R.id.progressText);
              text.setText(R.string.no_location);

              findViewById(R.id.continueToNext).setVisibility(View.VISIBLE);
              findViewById(R.id.continueToNext).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                  Intent i = new Intent();
                  i.setClass(getBaseContext(), SiteActivity.class);
                  i.putExtra(getString(R.string.hint_args), hint_num);
                  startActivity(i);
                }
              });

            }
          }, 5000);

        }

        else
        {
          Intent i = new Intent();
          i.setClass(getBaseContext(), SiteActivity.class);
          i.putExtra(getString(R.string.hint_args), hint_num);
          startActivity(i);
        }

        break;


    }

  }

}

