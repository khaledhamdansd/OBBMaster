package com.master.demo.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.master.demo.R;

import java.io.File;
import java.util.Objects;

public class VideoFragment extends Fragment implements View.OnClickListener {


    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dialog_video, null, false);
        videoView = view.findViewById(R.id.videoView);



        //after that you can use it any time by this code:
        String filename2 = "OBBMaster/video/vidDemo.mp4"; // suppose you store put your file in directory in .obb
        String uriString2 = "content://com.master.demo" +  File.separator + filename2;
        Uri uri2 = Uri.parse(uriString2);
        videoView.setVideoURI(uri2);
        videoView.requestFocus();
        videoView.start();


        ImageButton ivClose = view.findViewById(R.id.ivClose);

        ivClose.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {


        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().remove(this).commit();


    }


}
