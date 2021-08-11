package com.sample.euphonytest

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import euphony.lib.transmitter.EuTxManager

class MainActivityUsingKotlin : AppCompatActivity() {

    private val mTxManager: EuTxManager by lazy {
        EuTxManager()
    }

    private var isSpeakOn = false
    private lateinit var soundControlButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundControlButton = findViewById(R.id.button_sound_control)
        soundControlButton.setOnClickListener(soundButtonClickListener())

        // layout initial setting
        soundOff()
    }


    private fun soundButtonClickListener(): View.OnClickListener {
        return View.OnClickListener { changeSoundStatus() }
    }

    private fun changeSoundStatus() {
        when (isSpeakOn) {
            true -> soundOff()
            false -> soundOn()
        }
    }

    private fun soundOn() {
        mTxManager.euInitTransmit("Hello, Euphony") // To generate acoustic data "Hello, Euphony"
        mTxManager.process(-1) // generate sound infinite.
        soundControlButton.text = "사운드 On"
        isSpeakOn = true
    }

    private fun soundOff() {
        mTxManager.stop()
        soundControlButton.text = "사운드 Off"
        isSpeakOn = false
    }
}