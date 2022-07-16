package com.techelevator;

public class Drink extends Snack{

    public String getSound() {
        return "Cheers Glug, Glug!";
    }

    @Override
    public double getPurchasePrice() {
        return super.getPurchasePrice();
    }

    @Override
    public int getQuantity() {
        return super.getQuantity();
    }

    @Override
    public String getSlot() {
        return super.getSlot();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public Drink() {
        super();
    }

    public Drink(String slot, String name, double purchasePrice) {
        super(slot, name, purchasePrice);
        this.slot = slot;
        this.name = name;
        this.purchasePrice = purchasePrice;

    }

    @Override
    public boolean soldOut() {
        return super.soldOut();
    }

    @Override
    public boolean dispenseSnack() {
        return super.dispenseSnack();
    }
}
