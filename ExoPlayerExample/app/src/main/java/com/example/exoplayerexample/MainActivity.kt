package com.example.exoplayerexample

import android.drm.DrmStore
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exoplayerexample.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.Extractor
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util


class MainActivity : AppCompatActivity() {

    private val SAMPLE_URL : String = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    private lateinit var binding : ActivityMainBinding
    private var player : SimpleExoPlayer? = null

    private var playWhenReady : Boolean = true;
    private var currentWindow : Int = 0
    private var playbackPosition : Long = 0    // 플레이 시점
    private var playbackParameters : Float = 0f //배속

    private lateinit var mediaSource: MediaSource

    //------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializePlayer()
        setMediaSource()
        if(player != null) player!!.prepare(mediaSource)



        binding.btnStart.setOnClickListener {
            player?.playWhenReady = playWhenReady
        }

        binding.btnFast.setOnClickListener {
            playbackParameters = playbackParameters + 0.1f
            player?.playbackParameters = PlaybackParameters(playbackParameters)
        }

        binding.btnSlow.setOnClickListener {
            playbackParameters = playbackParameters - 0.1f
            player?.playbackParameters = PlaybackParameters(playbackParameters)
        }


    }//onCreate()

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer() //onCreate에서 init해주었으므로 onDestroy에서 release
    }


    //LifeCycle
    //------------------------------------------------------------------------------------

    private fun initializePlayer(){
        if(player == null){
            player = ExoPlayerFactory.newSimpleInstance(this.applicationContext)
            binding.exoPlayerView.player = player   //connect player
        }

    }//initializePlayer()

    private fun setMediaSource(){

        mediaSource = buildMediaSource(Uri.parse(SAMPLE_URL))
    }//setMediaSource()

    private fun buildMediaSource(uri : Uri) : MediaSource{
        val userAgent = Util.getUserAgent(this, this.applicationInfo.name)

        if(uri.getLastPathSegment()!!.contains("mp3") || uri.getLastPathSegment()!!.contains("mp4")){
            return ExtractorMediaSource.Factory(DefaultHttpDataSourceFactory(userAgent))
                .createMediaSource(uri)
        }else{
            return ExtractorMediaSource.Factory(DefaultDataSourceFactory(this, userAgent))
                .createMediaSource(uri)
        }

    }//buildMediaSource

    private fun releasePlayer(){
        /*
            비디오 플레이어 해제
            비디오 플레이어 초기화 & 해제는 맞물려 있어야 한다 -> (onCrate - onDestroy) , (onStart - onStop), (onResume - onPause)
         */
        if(player != null){
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            player = null
        }
    }//releasePlayer()








}