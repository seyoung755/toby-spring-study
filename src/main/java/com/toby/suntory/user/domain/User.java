package com.toby.suntory.user.domain;

public class User {
    String id;
    String name;
    String password;
    Level level;
    int login;
    int recommend;

    public User() {
    }

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String id, String name, String password, Level level, int login, int recommend) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.level = level;
        this.login = login;
        this.recommend = recommend;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Level getLevel() {
        return level;
    }

    public int getLogin() {
        return login;
    }

    public int getRecommend() {
        return recommend;
    }

    public void upgradeLevel() {
        Level nextLevel = this.level.getNext();
        if (nextLevel == null) {
            throw new IllegalStateException(this.level + "은 업그레이드가 불가능합니다.");
        }
        this.level = nextLevel;
    }
}
