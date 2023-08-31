package com.roberyGame;

public class Building {

    private String name;
    private Item[] items;

    public Building(String name, Item[] items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public Item[] getItems() {
        return items;
    }

    public static int devide(int numri, int pjestusi) {
        try {
            return numri / pjestusi;
        } catch (ArithmeticException error) {
            System.out.println("Nuk mund te pjestohet me 0");
            return 0;
        } finally {
            System.out.println("puna u mbarua");
        }
    }

}
