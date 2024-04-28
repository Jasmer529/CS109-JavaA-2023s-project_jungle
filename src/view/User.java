package view;

import java.util.ArrayList;

public class User {
    public String name;
    public String password;
    static ArrayList<User> list=new ArrayList<>();
    static {
        list.add(new User("Jasmer","12345"));
        list.add(new User("ff","123"));
        list.add(new User("Xybmy","1234"));
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
