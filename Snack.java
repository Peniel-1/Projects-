package com.techelevator;

public class Snack {

    //properties//////////////////////////////////
    protected double purchasePrice;
    protected int quantity;
    protected String slot;
    protected String name;
    protected String sound;




    //////////////////getters///////////////////
    public double getPurchasePrice() {
        return purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }
    public String getSound() { return sound;}

    //constructor///////////////////////////////////////////////
    public Snack() {

    }

    public Snack(String slot, String name, double purchasePrice){
        this.quantity = 5;
    }

    //sold out/////////////////////////////////////////////////////
    public boolean soldOut(){
        System.out.println("");
        System.out.println("****************************");
        System.out.println("SOLD OUT!");
        System.out.println("****************************");
        return this.quantity==0;
    }

    //dispense///////////////////////////////////////////////////
    public boolean dispenseSnack( ){
        if(quantity>0) {
            quantity--;
            System.out.println("****************************");
            System.out.println(getName());
            System.out.println("Cost: $" + getPurchasePrice());
            System.out.println(getSound());
            return true;
        }
        return false;
    }
}
