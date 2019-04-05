package ru.holyway.ficustracker.security;

public class UserService {

    private static final UserService instance = new UserService();

    private String userName;

    private String userPassword;

    private String userToken;


    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserToken() {

        return userToken;
    }

    public void registerUser(String userName, String password, String token) {
        this.userName = userName;
        this.userPassword = password;
        this.userToken = token;
    }

    public static UserService getInstance() {
        return instance;
    }
}
