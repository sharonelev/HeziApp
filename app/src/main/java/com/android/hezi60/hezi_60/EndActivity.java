package com.android.hezi60.hezi_60;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.orhanobut.hawk.Hawk;

public class EndActivity extends AppCompatActivity implements OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_end);
    setTitle(R.string.bday60);
    LocalHelper.setLocale(this,"en");

    findViewById(R.id.theSite).bringToFront();
    TextView title = findViewById(R.id.topText);

    ImageView balloons = findViewById(R.id.balloons);
    Animation animationBalloons = AnimationUtils.loadAnimation(getBaseContext(),R.anim.bottom_up);
    //  animationBalloons.setDuration(1000);
    //  animationBalloons.setRepeatCount(Animation.INFINITE);
    balloons.startAnimation(animationBalloons);
    balloons.bringToFront();
    title.bringToFront();

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
      i.setClass(this, SplashActivity.class);
      //    i.putExtra(getString(R.string.hint_args),hint_num);
      Hawk.delete(getString(R.string.screen)); //to start from beginning
      startActivity(i);

      return true;
    }
    return super.onOptionsItemSelected(item);
  }



  @Override
  public void onClick(View v) {

  }

}
