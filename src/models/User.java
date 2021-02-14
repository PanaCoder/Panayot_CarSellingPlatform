package models;

public class User {
    private String uid; /* This is user id for Server!!! It can be seen by client*/
    private String username;
    private String pass;
    private String fullName;
    private int age;
    private String city;
    private String sex;
    private UserType userType;
    private String phoneNumber;

    public User(String username, String pass, String fullName, int age, String city, String sex, String phoneNumber) {
        this.username = username;
        this.pass = pass;
        this.fullName = fullName;
        this.age = age;
        this.city = city;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.userType = UserType.REGULAR;
    }

    public String toString() {
        return this.fullName + " на " + this.age + " години от " + this.city;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
