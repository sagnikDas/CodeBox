package com.example.android.codebox;

/**
 * Created by sagnik on 12/01/17.
 */

public class Post {

    private String postTitle;

    private String postSubTitle;

    private String postSubTitle2;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostSubTitle() {
        return postSubTitle;
    }

    public void setPostSubTitle(String postSubTitle) {
        this.postSubTitle = postSubTitle;
    }

    public String getPostSubTitle2(){
        return postSubTitle2;
    }

    public void setPostSubTitle2(String postSubTitle2){
        this.postSubTitle2 = postSubTitle2;
    }

    public Post(String postTitle, String postSubTitle, String postSubTitle2) {
        this.postTitle = postTitle;
        this.postSubTitle = postSubTitle;
        this.postSubTitle2 = postSubTitle2;
    }
}