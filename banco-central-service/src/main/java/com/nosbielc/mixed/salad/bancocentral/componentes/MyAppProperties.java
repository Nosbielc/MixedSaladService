//package com.nosbielc.mixed.salad.bancocentral.componentes;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.StringJoiner;
//import java.util.UUID;
//
//@Component
//@ConfigurationProperties("app")
//public class MyAppProperties {
//
//    private int locationX;
//    private int locationY;
//    private int userAge;
//    private int maxUsers;
//    private long refreshRateMilli;
//    private long initialDelayMilli;
//    private String userPassword;
//    private UUID instanceId;
//
//    public int getLocationX() {
//        return locationX;
//    }
//
//    public int getLocationY() {
//        return locationY;
//    }
//
//    public int getUserAge() {
//        return userAge;
//    }
//
//    public int getMaxUsers() {
//        return maxUsers;
//    }
//
//    public long getRefreshRateMilli() {
//        return refreshRateMilli;
//    }
//
//    public long getInitialDelayMilli() {
//        return initialDelayMilli;
//    }
//
//    public String getUserPassword() {
//        return userPassword;
//    }
//
//    public UUID getInstanceId() {
//        return instanceId;
//    }
//
//    @Override
//    public String toString() {
//        return new StringJoiner(", ", MyAppProperties.class.getSimpleName() + "[", "]")
//                .add("locationX=" + locationX)
//                .add("locationY=" + locationY)
//                .add("userAge=" + userAge)
//                .add("maxUsers=" + maxUsers)
//                .add("refreshRateMilli=" + refreshRateMilli)
//                .add("initialDelayMilli=" + initialDelayMilli)
//                .add("userPassword='" + userPassword + "'")
//                .add("instanceId=" + instanceId)
//                .toString();
//    }
//}
