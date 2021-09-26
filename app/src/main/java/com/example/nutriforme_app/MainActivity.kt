package com.example.nutriforme_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutriforme_app.act.NutriData
import com.example.nutriforme_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass : ActivityMainBinding

    // private var video_field: VideoView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        // video_field = findViewById(R.id.videoView)


        /*  val videoUri = Uri.parse("android.resource://com.example.nutriforme/" + R.raw.background_video );
          video_field?.setVideoURI(videoUri);
          video_field?.start();
          video_field?.setOnCompletionListener {
              video_field?.start();
          }
  */
        bindingClass.btStart.setOnClickListener {
            val i = Intent(this, NutriData::class.java)
            startActivity(i)
        }

    }
}