package com.rv198510.englishinsound

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.graphics.drawable.Drawable
import android.inputmethodservice.Keyboard.Row
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.coroutines.delay
import java.security.AccessController.getContext
import java.sql.Time
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var btnPlay: ImageButton? = null
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        mediaPlayer = MediaPlayer()

        btnPlay = findViewById<ImageButton>(R.id.btnPlay)

        val items = JsonHelper.getItemsFromJson("item.json", this)
        var itemsIndex = 0
        replaceFragment(items, itemsIndex)



        val prevButton = findViewById<ImageButton>(R.id.prevButton)
        val nextButton = findViewById<ImageButton>(R.id.nextButton)


        prevButton.isEnabled = false

        prevButton.setOnClickListener {
            if (itemsIndex > 0) {
                itemsIndex--
                replaceFragment(items, itemsIndex)

                if (itemsIndex == 0) {
                    prevButton.isEnabled = false
                }
                if (itemsIndex == items.size - 2) {
                    nextButton.isEnabled = true
                }
            }
        }

        nextButton.setOnClickListener {
            if (itemsIndex < items.size - 1) {
                itemsIndex++
                replaceFragment(items, itemsIndex)

                if (itemsIndex == items.size - 1) {
                    nextButton.isEnabled = false

                }
                if (itemsIndex == 1) {
                    prevButton.isEnabled = true

                }
            }
        }

        btnPlay?.setOnClickListener {

            val soundName = items[itemsIndex].soundId
            val soundUrl = "${resources.getIdentifier("$soundName", "raw", packageName)}"
            loadSound(soundUrl)

//            try {
//
//                val soundName = items[itemsIndex].soundId
//                val soundUrl = "${resources.getIdentifier("$soundName", "raw", packageName)}"
//
//                mediaPlayer = MediaPlayer.create(this,soundUrl.toInt())
//                mediaPlayer?.setOnCompletionListener{
//                    btnPlay?.setImageResource(R.drawable.play)
//                    mediaPlayer.reset()
//                mediaPlayer.release()
//
//                }
//                    if(mediaPlayer.isPlaying ){
//                        mediaPlayer.pause()
//                        btnPlay?.setImageResource(R.drawable.play)
//
//                    }else{
//                        mediaPlayer.start()
//                        btnPlay?.setImageResource(R.drawable.pause)
//                    }
//                } catch (e: Exception) {
//
//                    e.printStackTrace()
//
//                }
        }

    }
    private fun loadSound(soundUrl: String){
        try {
            mediaPlayer = MediaPlayer.create(this,soundUrl.toInt())
            mediaPlayer?.setOnCompletionListener{
                btnPlay?.setImageResource(R.drawable.play)
                mediaPlayer.reset()
                mediaPlayer.release()

            }
            if(mediaPlayer.isPlaying ){
                mediaPlayer.pause()
                btnPlay?.setImageResource(R.drawable.play)

            }else{
                mediaPlayer.start()
                btnPlay?.setImageResource(R.drawable.pause)
            }
        } catch (e: Exception) {

            e.printStackTrace()

        }
    }
    private fun replaceFragment(items: ArrayList<EnglishData>, itemIndex: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, EnglishFragment.newInstance(items[itemIndex]))
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}