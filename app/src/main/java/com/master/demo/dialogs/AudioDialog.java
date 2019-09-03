package com.master.demo.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.master.demo.R;
import com.master.demo.zipfile.APKExpansionSupport;
import com.master.demo.zipfile.ZipResourceFile;

import java.io.IOException;

public class AudioDialog extends Dialog implements View.OnClickListener {

    private MediaPlayer mediaPlayer;

    public AudioDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_audio);
        setTitle(R.string.dialog_audio);

        mediaPlayer = new MediaPlayer();

        //mapping Viwes
        ImageButton ivClose = findViewById(R.id.ivClose);
        ImageButton ivPlay = findViewById(R.id.ivPlay);
        ImageButton ivStop = findViewById(R.id.ivStop);

        //set listeners
        ivClose.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivStop.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ivClose:
                dismiss();
                break;
            case R.id.ivPlay:
                if (!(mediaPlayer.isPlaying())) {


                    mediaPlayer.reset();
                    getAudio();
                }

                break;
            case R.id.ivStop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                break;


        }

    }

    private void getAudio() {

        ZipResourceFile expansionFile;

        try {
            expansionFile = APKExpansionSupport.getAPKExpansionZipFile(getContext(), 1, 0);

            AssetFileDescriptor fd = expansionFile.getAssetFileDescriptor("OBBMaster/voice/my_music.mp3");

            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
