package com.android.hezi60.hezi_60;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.orhanobut.hawk.Hawk;

public class SplashActivity extends AppCompatActivity implements OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    Hawk.init(this).build();
   //Hawk.delete(getString(R.string.screen)); //to start from beginning

   goToLastScreen();
   goToSiteNum(3);
     //goToHintNum(4);
    //goToDriveNum(1);
    setContentView(R.layout.activity_splash);
    setTitle(R.string.bday60);
    LocalHelper.setLocale(this,"en");
    TextView title = findViewById(R.id.topText);
    Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.turn_scale_anim);
    title.setAnimation(animation);
    title.startAnimation(animation);


    ImageView balloons = findViewById(R.id.balloons);
    Animation animationBalloons = AnimationUtils.loadAnimation(getBaseContext(),R.anim.bottom_up);
    //  animationBalloons.setDuration(1000);
    //  animationBalloons.setRepeatCount(Animation.INFINITE);
    balloons.startAnimation(animationBalloons);
    balloons.bringToFront();
    title.bringToFront();


    Button start  = findViewById(R.id.startButton);
    start.setOnClickListener(this);
  }


  public void goToLastScreen() {
    super.onResume();
    if(Hawk.contains(getString(R.string.screen))){
      String screen = Hawk.get(getString(R.string.screen));
      int hint_num  = Hawk.get(getString(R.string.hint_num));
      if(screen.equals("main")){
        goToHintNum(hint_num);
      }
      else if(screen.equals("site")) {
        goToSiteNum(hint_num);
      }
      else if(screen.equals("drive")) {
        goToDriveNum(hint_num);
      }
    }
  }


  public void showAlertDialog(){
    AlertDialog.Builder alertDialogBuilder;
    alertDialogBuilder = new AlertDialog.Builder(this);
    alertDialogBuilder.setTitle("ברוכים הבאים!");
    alertDialogBuilder.setMessage("לפניך מספר חידות אשר פתרונן יוביל אותך לתחנות שונות בחייך, בכל תחנה יש לצלם סלפי כדי לקבל את החידה הבאה. מוכן להתחיל?");
    alertDialogBuilder
        .setPositiveButton("בהחלט!", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            goToHintNum(1);

          }
        });
    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.show();
  }


  @Override
  public void onClick(View v) {
    showAlertDialog();

  }

  public void goToHintNum(int hint_num){
    Intent i = new Intent();
    i.setClass(this, MainActivity.class);
    i.putExtra(getString(R.string.hint_args),hint_num);
    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(i);
  }

  public void goToSiteNum(int hint_num){
    Intent i = new Intent();
    i.setClass(this, SiteActivity.class);
    i.putExtra(getString(R.string.hint_args),hint_num);
    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(i);
  }
  public void goToDriveNum(int hint_num){
    Intent i = new Intent();
    i.setClass(this, DriveActivity.class);
    i.putExtra(getString(R.string.hint_args),hint_num);
    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(i);
  }


  @Override
  public void onBackPressed() {
    getFragmentManager().popBackStack();
    Hawk.delete(getString(R.string.screen));
    Hawk.delete(getString(R.string.hint_num));
    finish();
  }

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    // getFragmentManager().popBackStack();
    Hawk.delete(getString(R.string.screen));
    Hawk.delete(getString(R.string.hint_num));
    finish();
    return true;
  }
}
