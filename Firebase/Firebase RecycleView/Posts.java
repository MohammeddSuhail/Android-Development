package com.example.meetup.model;

public class Posts {
    private String datePost,postDec,postImageUrl,userProfileImage,username,userId;

    public Posts() {
    }

    public Posts(String datePost, String postDec, String postImageUrl, String userProfileImage, String username, String userId) {
        this.datePost = datePost;
        this.postDec = postDec;
        this.postImageUrl = postImageUrl;
        this.userProfileImage = userProfileImage;
        this.username = username;
        this.userId=userId;
    }


    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getPostDec() {
        return postDec;
    }

    public void setPostDec(String postDec) {
        this.postDec = postDec;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId(String key) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
