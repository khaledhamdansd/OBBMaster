package com.master.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.master.demo.dialogs.AudioDialog;
import com.master.demo.fragment.VideoFragment;
import com.master.demo.dialogs.imageDialog;

import java.io.File;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    // The shared path to all app expansion files
    private final static String EXP_PATH = "/Android/obb/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View Mapping
        Button btnAudio = findViewById(R.id.btnAudio);
        Button btnVideo = findViewById(R.id.btnVideo);
        Button btnImage = findViewById(R.id.btnImage);

        //Setting click listener
        btnImage.setOnClickListener(this);
        btnVideo.setOnClickListener(this);
        btnAudio.setOnClickListener(this);


    }

    /**
     * Here's a method you can use in your app to get an array containing the complete path to both your expansion filesS
     */
    static String[] getAPKExpansionFiles(Context ctx) {

        String packageName = ctx.getPackageName();
        Vector<String> ret = new Vector<>();

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // Build the full path to the app's expansion files
            File root = Environment.getExternalStorageDirectory();
            File expPath = new File(root.toString() + EXP_PATH + packageName);

            // Check that expansion file path exists
            if (expPath.exists()) {
                {
                    String strMainPath = expPath + File.separator + "main." +
                            1 + "." + packageName + ".obb";
                    File main = new File(strMainPath);
                    if (main.isFile()) {
                        ret.add(strMainPath);

                    }
                }
            } else {
                Toast.makeText(ctx, "path not exist ", Toast.LENGTH_SHORT).show();
            }
        }
        String[] retArray = new String[ret.size()];
        ret.toArray(retArray);
        return retArray;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnAudio:
                AudioDialog audioDialog = new AudioDialog(MainActivity.this);
                audioDialog.show();
                break;
            case R.id.btnImage:
                imageDialog imageDialog = new imageDialog(MainActivity.this);
                imageDialog.show();
                break;
            case R.id.btnVideo:
                VideoFragment dialog = new VideoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("pathg", getAPKExpansionFiles(MainActivity.this)[0]);
                dialog.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_contaire, dialog).commit();

                break;
        }

    }
}
