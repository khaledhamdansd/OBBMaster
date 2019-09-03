package com.master.demo.provider;


import com.master.demo.zipfile.APEZProvider;

public class ZipFileContentProvider extends APEZProvider {

    @Override
    public String getAuthority() {
        return "com.master.demo";
    }
}