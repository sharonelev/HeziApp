package com.android.hezi60.hezi_60;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.orhanobut.hawk.Hawk;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import com.alexfu.countdownview.CountDownView;


public class SiteActivity extends YouTubeBaseActivity implements OnClickListener {

  boolean returnToStart = false;
  int hint_num;
  //CountDownView countDownView;
  CountDownTimer countDownTimer;
  TextView timerText;
  Button startTimer;
  Button stopTimer;
  int CAMERA_PIC_REQUEST = 200;
  String titlePlace = "";
  Bitmap image;
  List<YoutubeVid> youtubeVidList;
  RecyclerView videoRecyclerView;
  YouTubeAdapter youTubeAdapter;
  LinearLayoutManager linearLayoutManager;
  ImageView topPic;
  MediaPlayer mp;//= new MediaPlayer();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_at_site);
    setTitle(R.string.at_site);
    LocalHelper.setLocale(this, "en");
    Intent intent = getIntent();
    hint_num = intent.getIntExtra(getString(R.string.hint_args), 1);
    timerText = findViewById(R.id.textViewTime);
    startTimer = findViewById(R.id.buttonStart);
    stopTimer = findViewById(R.id.buttonStop);
    startTimer.bringToFront();
    returnToStart = false;
    startTimer.setOnClickListener(this);
    stopTimer.setOnClickListener(this);
    countDownTimer = new CountDownTimer(300000, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        timerText.setText(hmsTimeFormatter(millisUntilFinished));
      }

      @Override
      public void onFinish() {
        afterTimer();
        audioPlayer();
      }
    };

    findViewById(R.id.bottomButton).setOnClickListener(this);

    topPic = findViewById(R.id.topPic);

    youtubeVidList = new ArrayList<>();
    YoutubeVid youtubeVid;

    switch (hint_num) {
      case 1:
        titlePlace = "ישיבת כפר הראה";
        topPic.setImageResource(R.drawable.kfar_haroe);
        youtubeVid = new YoutubeVid("73ABsqJ4frQ", R.drawable.hispin1);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("hf37f7hHPdY", R.drawable.pt1);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("9R_M0wCnvEY", R.drawable.pnina);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("XcOztGmyGTI", R.drawable.china3);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("dbgZzPHtCr0", R.drawable.elta1);
        youtubeVidList.add(youtubeVid);
        break;
      case 2:
        titlePlace = "הבית בקרית אתא";
        topPic.setImageResource(R.drawable.kiryat_ata_house);
        youtubeVid = new YoutubeVid("sAbj9uO4_tg", R.drawable.kiryat_ata1);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("p5ybWyYfOc4", R.drawable.haya);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("zwZDtuHummA", R.drawable.china1);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("8rUGsruW4KQ", R.drawable.matang);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("663aTqYS-74", R.drawable.hispin3);
        youtubeVidList.add(youtubeVid);
        break;
      case 3:
        titlePlace = "הטכניון";
        topPic.setImageResource(R.drawable.technion);
        youtubeVid = new YoutubeVid("FanGXKD9hew", R.drawable.technion1);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("iO1eMk61CPU", R.drawable.kids1);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("jOLMKcQKGNU", R.drawable.hispin2);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("7XTxI1Lifdw", R.drawable.hani);
        youtubeVidList.add(youtubeVid);
        youtubeVid = new YoutubeVid("i13ddSIM_28", R.drawable.china2);
        youtubeVidList.add(youtubeVid);
        break;
      case 4:
        titlePlace = "מלון דן";
        break;
    }

    TextView theSite = findViewById(R.id.theSite);
    theSite.setText(titlePlace);

    theSite.bringToFront();

    // YouTube adapter

    videoRecyclerView = findViewById(R.id.videoRecyclerView);
    linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    videoRecyclerView.setLayoutManager(linearLayoutManager);
    youTubeAdapter = new YouTubeAdapter(this, youtubeVidList);
    videoRecyclerView.setAdapter(youTubeAdapter);

  }


  public void afterTimer() {

    findViewById(R.id.bottomText).setVisibility(View.VISIBLE);
    findViewById(R.id.bottomButton).setVisibility(View.VISIBLE);
    //   findViewById(R.id.buttonStop).setVisibility(View.INVISIBLE);
    findViewById(R.id.wordsTitle).setVisibility(View.VISIBLE);
    findViewById(R.id.videoRecyclerView).setVisibility(View.VISIBLE);
    findViewById(R.id.timerLayout).setVisibility(View.GONE);
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
    if (returnToStart) {
    //  getFragmentManager().popBackStack();
      Hawk.delete(getString(R.string.screen)); //to start from beginning
      Hawk.delete(getString(R.string.hint_num)); //to start from beginning
      finish();
    } else {
      Hawk.put(getString(R.string.screen), "site");
      Hawk.put(getString(R.string.hint_num), hint_num);
    }

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.buttonStart:
        countDownTimer.start();
        startTimer.setVisibility(View.GONE);
        stopTimer.setVisibility(View.VISIBLE);


        break;

      case R.id.buttonStop:
        countDownTimer.cancel();
        afterTimer();
        break;

      case R.id.bottomButton:
        Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
        break;

    }
  }


  public void audioPlayer() {
    //set up MediaPlayer

    try {
      MediaPlayer.create(this, R.raw.times_up_audio_file_5sec).start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {
      image = (Bitmap) data.getExtras().get("data");
      ImageView imageview = (ImageView) findViewById(R.id.selfiePic); //sets imageview as the bitmap
      findViewById(R.id.selfieFrame).setVisibility(View.VISIBLE);
      imageview.setImageBitmap(image);
      if (checkPermission()) {

        store(image, "selfie_" + String.valueOf(hint_num) + ".png");
      }
      findViewById(R.id.keepOn).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent i = new Intent();
          i.setClass(getBaseContext(), MainActivity.class);
          int next_hint = hint_num + 1;
          i.putExtra(getString(R.string.hint_args), next_hint);
          startActivity(i);


        }
      });
    }
  }


  private String hmsTimeFormatter(long milliSeconds) {

    String hms = String.format("%02d:%02d:%02d",
        TimeUnit.MILLISECONDS.toHours(milliSeconds),
        TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS
            .toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
        TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES
            .toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

    return hms;


  }


  public File store(Bitmap bm, String fileName) {
    final String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
        + "/hezi_bday";//"/Pictures/Screenshots";
    File dir = new File(dirPath);
    if (!dir.exists()) {
      dir.mkdirs();
      Log.i("ExternalStorage", "created folder");
    }
    File file = new File(dirPath, fileName);

    try {
      FileOutputStream fOut = new FileOutputStream(file);
      bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
      fOut.flush();
      fOut.close();
      Toast.makeText(this, "Photo saved in your phone gallery", Toast.LENGTH_SHORT).show();

    } catch (Exception e) {
      e.printStackTrace();
      return null;

    }

    // Tell the media scanner about the new file so that it is
    // immediately available to the user.
    MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
        new MediaScannerConnection.OnScanCompletedListener() {
          public void onScanCompleted(String path, Uri uri) {
            Log.i("ExternalStorage", "Scanned " + path + ":");
            Log.i("ExternalStorage", "-> uri=" + uri);
          }
        });
    return file;
  }


  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    if (requestCode == 789) {
      if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        store(image, "selfie_" + String.valueOf(hint_num) + ".png");

      } else {
        Toast.makeText(this, "Permission required to store pic", Toast.LENGTH_SHORT).show();

      }
    }
  }


  private boolean checkPermission() {
    if (Build.VERSION.SDK_INT >= 23) {
      if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
          == PackageManager.PERMISSION_GRANTED) {
        Log.v("permission", "Permission is granted");
        //File write logic here
        return true;
      } else {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 789);

        return false;
      }
    } else {
      return true;
    }
  }


}