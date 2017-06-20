package ex.rxretro.rxretrofit2.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by panji on 6/19/2017.
 */

public class User {
    @SerializedName("token")
    private String token;
    @SerializedName("id")
    private String id;
    @SerializedName("nik")
    private String nik;
    @SerializedName("email")
    private String email;
    @SerializedName("no_hp")
    private String mobileNo;
    @SerializedName("firstname")
    private String firstName;
    @SerializedName("lastname")
    private String lastName;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("address")
    private String address;
    @SerializedName("password")
    private String password;
    @SerializedName("url_avatar")
    private String urlAvatar;
    @SerializedName("url_cover")
    private String urlCover;

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }

    public String getNik() {
        return nik;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public String getUrlCover() {
        return urlCover;
    }

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", id='" + id + '\'' +
                ", nik='" + nik + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", urlAvatar='" + urlAvatar + '\'' +
                ", urlCover='" + urlCover + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (token != null ? !token.equals(user.token) : user.token != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (nik != null ? !nik.equals(user.nik) : user.nik != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (mobileNo != null ? !mobileNo.equals(user.mobileNo) : user.mobileNo != null)
            return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null)
            return false;
        if (displayName != null ? !displayName.equals(user.displayName) : user.displayName != null)
            return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null)
            return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        if (urlAvatar != null ? !urlAvatar.equals(user.urlAvatar) : user.urlAvatar != null)
            return false;
        return urlCover != null ? urlCover.equals(user.urlCover) : user.urlCover == null;

    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (nik != null ? nik.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobileNo != null ? mobileNo.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (urlAvatar != null ? urlAvatar.hashCode() : 0);
        result = 31 * result + (urlCover != null ? urlCover.hashCode() : 0);
        return result;
    }
}
