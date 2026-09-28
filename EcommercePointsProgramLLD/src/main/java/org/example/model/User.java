package org.example.model;

public class User {
    String userId;
    String name;
    Integer orderCount;
    Double points;
    LevelService levelService;

    public User(String userId, String name, Integer orderCount, Double points, LevelService levelService) {
        this.userId = userId;
        this.name = name;
        this.orderCount = orderCount;
        this.points = points;
        this.levelService = levelService;
    }

    public Double addPoints(Double points) {
        this.points += points;
        this.updateLevel();
        return points;
    }

    public Double removePoints(Double points) {
        if (this.points < points) {
            throw new IllegalArgumentException("User doesn't have enough points.");
        }
        this.points -= points;
        this.updateLevel();
        return points;
    }

    public Integer addOrderCount() {
        this.orderCount += 1;
        return this.orderCount;
    }

    public void updateLevel() {
        if (0 <= this.points && this.points <= 499) {
            this.levelService = new BronzeLevelService();
        } else if (500 <= this.points && this.points <= 999) {
            this.levelService = new SilverLevelService();
        } else if (1000 <= this.points && this.points <= 99999) {
            this.levelService = new GoldLevelService();
        } else {
            this.levelService = new BronzeLevelService();
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public LevelService getLevelService() {
        return levelService;
    }

    public void setLevelService(LevelService levelService) {
        this.levelService = levelService;
    }
}
