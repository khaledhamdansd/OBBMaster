package com.master.demo.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.master.demo.R;
import com.master.demo.zipfile.APKExpansionSupport;
import com.master.demo.zipfile.ZipResourceFile;

import java.io.IOException;
import java.io.InputStream;

public class imageDialog extends Dialog implements View.OnClickListener {

    public imageDialog(@NonNull Context context) {
        super(context);
    }

    private ImageView ivView;
    private ImageButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image_dialog);
        ivView = findViewById(R.id.ivDIalog);
        btnClose = findViewById(R.id.ivClose);

        btnClose.setOnClickListener(this);


// Get a ZipResourceFile representing a merger of both the main and patch files
        ZipResourceFile expansionFile =
                null;
        try {
            expansionFile = APKExpansionSupport.getAPKExpansionZipFile(getContext(), 1, 0);
            // Get an input stream for a known file inside the expansion file ZIPs
            InputStream fileStream = expansionFile.getInputStream("OBBMaster/img/demo1.jpg");
            //show Image
            BitmapFactory.Options bfo = new BitmapFactory.Options();
            bfo.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap b = BitmapFactory.decodeStream(fileStream, null, bfo);
            ivView.setImageBitmap(b);

        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    @Override
    public void onClick(View view) {
        dismiss();
    }
}
