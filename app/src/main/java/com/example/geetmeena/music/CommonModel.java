package com.example.geetmeena.music;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by Jeetmeena on 1/20/2018.
 */

public class CommonModel {
    private String queryTtile;
    private String queryAlbum;
    private String queryArtist;
    private String queryDuration;
    private String filePath1;
    private String _id;
    private String queryName;
    private String songImage;
    private Uri u;
    String j;
    int numberSong;

    public String getAlbumCoverPath() {
        return albumCoverPath;
    }

    public void setAlbumCoverPath(String albumCoverPath) {
        this.albumCoverPath = albumCoverPath;
    }

    private String albumCoverPath;

    public CommonModel(String queryTtile, String queryAlbum, String queryArtist, String duretion, String filepath,
                       String _id, String name, int coun) {
        queryTtile = queryTtile;
        numberSong = coun;
        queryArtist = queryAlbum;
        queryAlbum = queryAlbum;
        queryDuration = duretion;
        filePath1 = filepath;
        _id = _id;
        queryName = name;
        //  j=jeet;
        // u=uri;
        //songImage=bitmap;


    }

    public void setQueryTtile(String queryTtile) {
        this.queryTtile = queryTtile;
    }

    public void setQueryDuration(String queryDuration) {
        this.queryDuration = queryDuration;
    }

    public String getQueryTtile() {
        return queryTtile;
    }

    public String getQueryAlbum() {
        return queryAlbum;
    }

    public String getQueryArtist() {
        return queryArtist;
    }

    public String getAudioView1() {
        songImage = j;
        return songImage;
    }

    public Uri getU() {
        return u;
    }

    public String getFilePath1() {
        return filePath1;
    }

    public void setFilepath1(String filepath1) {
        this.filePath1 = filepath1;
    }

    public String get_id() {
        return queryArtist;
    }

    public String getName() {
        return queryArtist;
    }

    public String getQueryName() {
        return queryName;
    }

}