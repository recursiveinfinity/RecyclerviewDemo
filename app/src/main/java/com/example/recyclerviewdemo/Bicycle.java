package com.example.recyclerviewdemo;

public class Bicycle {

    private String color;
    private int numberOfWheels;
    private static boolean needsHelmet;

    public Bicycle(String color, int numberOfWheels) {
        this.color = color;
        this.numberOfWheels = numberOfWheels;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static void setNeedsHelmet(boolean needsHelmet) {
        Bicycle.needsHelmet = needsHelmet;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public String getColor() {
        if (needsHelmet) {
            return "Pink";
        }
        return color;
    }

    public void test() {
        Bicycle.setNeedsHelmet(false);
        Bicycle bikeOne = new Bicycle("Green", 2);
        bikeOne.getColor(); //Green

        Bicycle bikeTwo = new Bicycle("Red", 3);
        bikeTwo.getColor(); //Red
    }
}
