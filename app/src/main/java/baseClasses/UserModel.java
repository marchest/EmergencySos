package baseClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by marchest on 30.11.2016.
 */
public class UserModel implements Serializable {
    @SerializedName("returned_username")
    public String name;
    @SerializedName("returned_surName")
    public String surName;
    @SerializedName("returned_gender")
    public String gender;
    @SerializedName("returned_phoneNumber")
    public String phoneNumber;
    @SerializedName("returned_email")
    public String email;
    @SerializedName("response_code")
    private int response_code;
    @SerializedName("message")
    private String message;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModel(String name, String surName, String gender, String phoneNumber) {
        this.name = name;
        this.surName = surName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
