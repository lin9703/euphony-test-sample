package com.sample.euphonytest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import euphony.lib.transmitter.EuTxManager;

public class MainActivityUsingJava extends AppCompatActivity {

    private EuTxManager mTxManager;
    private boolean isSpeakOn = false;
    private Button soundControlButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transmitterSetting();

        soundControlButton = findViewById(R.id.button_sound_control);
        soundControlButton.setOnClickListener(soundButtonClickListener());

        // layout initial setting
        soundOff();

    }

    private void transmitterSetting() {
        mTxManager = new EuTxManager();
    }

    @NotNull
    private View.OnClickListener soundButtonClickListener() {
        return view -> changeSoundStatus();
    }

    private void changeSoundStatus() {
        if (isSpeakOn) {
            soundOff();

        } else {
            soundOn();
        }
    }

    private void soundOn() {
        mTxManager.euInitTransmit("Hello, Euphony"); // To generate acoustic data "Hello, Euphony"
        mTxManager.process(-1); // generate sound infinite.
        soundControlButton.setText("사운드 On");
        isSpeakOn = true;
    }

    private void soundOff() {
        mTxManager.stop();
        soundControlButton.setText("사운드 Off");
        isSpeakOn = false;
    }

}
