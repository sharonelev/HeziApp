package com.android.hezi60.hezi_60;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.orhanobut.hawk.Hawk;


public class MainActivity extends AppCompatActivity implements OnClickListener {
  boolean returnToStart = false;
  public TextView title_hint_num;
  public TextView topText;
  public ImageView playAudio;
  public View checkLocation;
  public ProgressBar progressBar;
  Button button1;
  ImageView valid1;
  Button button2;
  ImageView valid2;
  Button button3;
  ImageView valid3;
  int validCounter;
  public View hintLayout;
  public int hint_num;
  AlertDialog.Builder alertDialogBuilder;
  EditText hint2Ans1;
  EditText hint2Ans2;
  EditText hint2Ans3;
  EditText hint2Ans4;
  EditText hint2Ans5;
  EditText hint2Ans6;
  EditText hint2Ans7;
  EditText hint2Ans8;
  EditText hint2Ans9;
  EditText hint2Ans10;
  EditText hint4Ans1;
  EditText hint4Ans2;
  EditText hint4Ans3;
  EditText hint4Ans4;
  EditText hint4Ans5;
  EditText hint4Ans6;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setTitle(R.string.bday60);
    LocalHelper.setLocale(this,"en");
    Intent intent = getIntent();
    hint_num = intent.getIntExtra(getString(R.string.hint_args), 1);
    alertDialogBuilder = new AlertDialog.Builder(this);
    returnToStart = false;

    switch (hint_num) {
      case 1:
        hintLayout = findViewById(R.id.hint_1);
        button1 = findViewById(R.id.checkAnsPerson1);
        button2 = findViewById(R.id.checkAnsPerson2);
        button3 = findViewById(R.id.checkAnsPerson3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        valid1 = findViewById(R.id.valid1);
        valid2 = findViewById(R.id.valid2);
        valid3 = findViewById(R.id.valid3);
        validCounter = 0;
        break;

      case 2:
        hintLayout = findViewById(R.id.hint_2);
        findViewById(R.id.checkAns2).setOnClickListener(this);
        hint2Ans1 = findViewById(R.id.ans1);
        hint2Ans2 = findViewById(R.id.ans2);
        hint2Ans3 = findViewById(R.id.ans3);
        hint2Ans4 = findViewById(R.id.ans4);
        hint2Ans5 = findViewById(R.id.ans5);
        hint2Ans6 = findViewById(R.id.ans6);
        hint2Ans7 = findViewById(R.id.ans7);
        hint2Ans8 = findViewById(R.id.ans8);
        hint2Ans9 = findViewById(R.id.ans9);
        hint2Ans10 = findViewById(R.id.ans10);

        hint2Ans1.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint2Ans2.requestFocus();
            return false;
          }
        });
        hint2Ans2.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint2Ans3.requestFocus();
            return false;
          }
        });
        hint2Ans3.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint2Ans4.requestFocus();
            return false;
          }
        });
        hint2Ans4.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint2Ans5.requestFocus();
            return false;
          }
        });
        hint2Ans5.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint2Ans6.requestFocus();
            return false;
          }
        });
        hint2Ans6.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint2Ans7.requestFocus();
            return false;
          }
        });
        hint2Ans7.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint2Ans8.requestFocus();
            return false;
          }
        });
        hint2Ans8.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint2Ans9.requestFocus();
            return false;
          }
        });
        hint2Ans9.setOnKeyListener(new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
          hint2Ans10.requestFocus();
          return false;
        }
      });

        break;

      case 3:
        hintLayout = findViewById(R.id.hint_3);
        findViewById(R.id.checkAns3).setOnClickListener(this);

        break;

      case 4:
        hintLayout = findViewById(R.id.hint_4);
        findViewById(R.id.checkAns4).setOnClickListener(this);
        hint4Ans1 = findViewById(R.id.letter1);
        hint4Ans2 = findViewById(R.id.letter2);
        hint4Ans3 = findViewById(R.id.letter3);
        hint4Ans4 = findViewById(R.id.letter4);
        hint4Ans5 = findViewById(R.id.letter5);
        hint4Ans6 = findViewById(R.id.letter6);


        hint4Ans6.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint4Ans5.requestFocus();
            return false;
          }
        });
        hint4Ans5.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint4Ans4.requestFocus();
            return false;
          }
        });
        hint4Ans4.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint4Ans3.requestFocus();
            return false;
          }
        });
        hint4Ans3.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint4Ans2.requestFocus();
            return false;
          }
        });
        hint4Ans2.setOnKeyListener(new OnKeyListener() {
          @Override
          public boolean onKey(View v, int keyCode, KeyEvent event) {
            hint4Ans1.requestFocus();
            return false;
          }
        });
        break;
    }


    title_hint_num = findViewById(R.id.titleText);

    Animation animation = AnimationUtils.loadAnimation(this, R.anim.from_left);
    animation.setAnimationListener(new AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {

      }

      @Override
      public void onAnimationEnd(Animation animation) {

        hintLayout.setVisibility(View.VISIBLE);
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });
    title_hint_num.setText(String.format(getString(R.string.hint_title), String.valueOf(hint_num)));
    title_hint_num.startAnimation(animation);


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
      Hawk.delete(getString(R.string.screen)); //to start from beginning
      Hawk.delete(getString(R.string.hint_num));
      finish();
    }
    else {
      Hawk.put(getString(R.string.screen), "main");
      Hawk.put(getString(R.string.hint_num), hint_num);
    }

  }


  @Override
  public void onClick(View v) {
    switch (v.getId()) {


      case R.id.checkAnsPerson1:
        if( checkAns1((EditText) findViewById(R.id.person1))) {
          button1.setVisibility(View.INVISIBLE);
          valid1.setImageResource(R.drawable.correct);
          valid1.setVisibility(View.VISIBLE);
          validCounter = validCounter + 1;
          findViewById(R.id.person2).requestFocus();


          if(validCounter == 3){
            showAlertDialog(true, null, "יפה, עכשיו חשוב מה המקום המשותף לשלושת הדמויות וסע לשם!", "צא לדרך");
          }
        }
        else {
          valid1.setImageResource(R.drawable.wrong);
          valid1.setVisibility(View.VISIBLE);
        }
        break;
      case R.id.checkAnsPerson2:
        if(checkAns1((EditText) findViewById(R.id.person2))){
          button2.setVisibility(View.INVISIBLE);
          valid2.setImageResource(R.drawable.correct);
          valid2.setVisibility(View.VISIBLE);
          validCounter = validCounter + 1;
          findViewById(R.id.person3).requestFocus();

          if(validCounter == 3){
            showAlertDialog(true, null, "יפה, עכשיו חשוב מה המקום המשותף לשלושת הדמויות וסע לשם!", "צא לדרך");
          }
        }
        else {
          valid2.setImageResource(R.drawable.wrong);
          valid2.setVisibility(View.VISIBLE);
        }
        break;
      case R.id.checkAnsPerson3:
        if(checkAns1((EditText) findViewById(R.id.person3))){
          button3.setVisibility(View.INVISIBLE);
          valid3.setImageResource(R.drawable.correct);
          valid3.setVisibility(View.VISIBLE);
          validCounter = validCounter + 1;

          if(validCounter == 3){
            showAlertDialog(true, null, "יפה, עכשיו חשוב מה המקום המשותף לשלושת הדמויות וסע לשם!", "צא לדרך");
          }
        }
        else {
          valid3.setImageResource(R.drawable.wrong);
          valid3.setVisibility(View.VISIBLE);
        }
        break;


      case R.id.checkAns2:

        if (checkAns2()) {
          showAlertDialog(true, "052-7748039", getString(R.string.correct), "מתקשר ונוסע ליעד");

        } else {

          showAlertDialog(false, null, getString(R.string.wrong), "נסה שוב");

        }


        break;


      case R.id.checkAns3:
        findViewById(R.id.topText3).setVisibility(View.GONE);
        findViewById(R.id.topText2).setVisibility(View.VISIBLE);
        findViewById(R.id.quiz3).setVisibility(View.GONE);
        findViewById(R.id.quiz3_gimatria).setVisibility(View.VISIBLE);
        findViewById(R.id.checkAns3_1).setVisibility(View.VISIBLE);
        findViewById(R.id.checkAns3_1).setOnClickListener(this);
        findViewById(R.id.insert_technion).setVisibility(View.VISIBLE);
        findViewById(R.id.checkAns3).setVisibility(View.GONE);

        break;
      case R.id.checkAns3_1:
        alertDialogBuilder = new AlertDialog.Builder(this);

        if (checkAns3()) {
          showAlertDialog(true, "כל הכבוד!!!", null, "צא ליעד");

        } else {

          showAlertDialog(false,"טעות בידך" ,null, "נסה שוב");
        }



        break;

      case R.id.checkAns4:
        if (checkAns4()) {

          Intent i = new Intent();
          i.setClass(getBaseContext(), EndActivity.class);
          i.putExtra(getString(R.string.hint_args), hint_num);
          startActivity(i);
        } else {
          showAlertDialog(false, "בזזזזזזט תשובה שגויה",null,"נסה שוב" );

        }
        break;

    }

  }


  public boolean checkAns1(EditText person){
    String input = person.getText().toString().trim();
    if(input.equals(("הרב צפניה דרורי").trim())
        ||input.equals(("צפניה דרורי").trim())
        ||input.equals(("הרב דרורי").trim())
        ||input.equals(("ג'קי לוי").trim())
        ||input.equals(("גקי לוי").trim())
        ||input.equals(("מוטי יוגב").trim())
        ||input.equals(("מרדכי יוגב").trim())
        )
      return true;
    else return false;
  }

  public boolean checkAns2() {

    if (hint2Ans1.getText().toString().trim().equals("0") &&
        hint2Ans2.getText().toString().trim().equals("5") &&
        hint2Ans3.getText().toString().trim().equals("2") &&
        hint2Ans4.getText().toString().trim().equals("7") &&
        hint2Ans5.getText().toString().trim().equals("7") &&
        hint2Ans6.getText().toString().trim().equals("4") &&
        hint2Ans7.getText().toString().trim().equals("8") &&
        hint2Ans8.getText().toString().trim().equals("0") &&
        hint2Ans9.getText().toString().trim().equals("3") &&
        hint2Ans10.getText().toString().trim().equals("9") ) {
      return true;
    }
    return false;
  }

  public boolean checkAns3() {
    EditText ans1 = findViewById(R.id.insert_technion);
    if (ans1.getText().toString().trim().equals("טכניון")){
      return true;
    }
    return false;
  }

  public boolean checkAns4() {
    if (hint4Ans6.getText().toString().trim().equals("ד") &&
        hint4Ans5.getText().toString().trim().equals("ן") &&
        hint4Ans4.getText().toString().trim().equals("ח") &&
        hint4Ans3.getText().toString().trim().equals("י") &&
        hint4Ans2.getText().toString().trim().equals("פ") &&
        hint4Ans1.getText().toString().trim().equals("ה")){
      return true;
    }
    return false;
  }


  public void showAlertDialog(final boolean success, String title, String msg, String positive){
    alertDialogBuilder = new AlertDialog.Builder(this);
    alertDialogBuilder.setTitle(title);
    alertDialogBuilder.setMessage(msg);
    alertDialogBuilder
        .setPositiveButton(positive, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();

            if(success) {
              Intent i = new Intent();
              i.setClass(getBaseContext(), DriveActivity.class);
              i.putExtra(getString(R.string.hint_args), hint_num);
              startActivity(i);
            }
          }
        });
    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.show();
  }

  @Override
  public void onBackPressed() {
super.onBackPressed();
  }
}
