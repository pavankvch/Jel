package com.jelsat.sociallogin.user;

public class SmartUser {
    private String birthday;
    private String email;
    private String firstName;
    private String gender;
    private String lastName;
    private String middleName;
    private String photoUrl;
    private String profileLink;
    private String userId;
    private String username;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String str) {
        this.middleName = str;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public String getProfileLink() {
        return this.profileLink;
    }

    public void setProfileLink(String str) {
        this.profileLink = str;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String str) {
        this.photoUrl = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("SmartUser{userId='");
        stringBuilder.append(this.userId);
        stringBuilder.append('\'');
        stringBuilder.append(", username='");
        stringBuilder.append(this.username);
        stringBuilder.append('\'');
        stringBuilder.append(", firstName='");
        stringBuilder.append(this.firstName);
        stringBuilder.append('\'');
        stringBuilder.append(", middleName='");
        stringBuilder.append(this.middleName);
        stringBuilder.append('\'');
        stringBuilder.append(", lastName='");
        stringBuilder.append(this.lastName);
        stringBuilder.append('\'');
        stringBuilder.append(", email='");
        stringBuilder.append(this.email);
        stringBuilder.append('\'');
        stringBuilder.append(", birthday='");
        stringBuilder.append(this.birthday);
        stringBuilder.append('\'');
        stringBuilder.append(", gender=");
        stringBuilder.append(this.gender);
        stringBuilder.append(", profileLink='");
        stringBuilder.append(this.profileLink);
        stringBuilder.append('\'');
        stringBuilder.append(", photoUrl='");
        stringBuilder.append(this.photoUrl);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
