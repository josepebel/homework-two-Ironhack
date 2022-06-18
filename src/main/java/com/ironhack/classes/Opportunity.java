package com.ironhack.classes;

import com.ironhack.enums.Product;
import com.ironhack.enums.Status;
import com.ironhack.supportiveClasses.PrintText;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Opportunity {

    private final int id;
    private Product product;
    private int quantity;
    private Contact decisionMaker;
    private Status status;
    private static int count = 0;


    public Opportunity(Product product, int quantity, Contact decisionMaker) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = Status.OPEN;
        this.count ++;
        this.id = count;
    }

    public static int getNumberOfTrucks(){

        Scanner scanner = new Scanner(System.in);
        int numberOfTrucks = 0;
        try {
            String lectorComando = scanner.nextLine();
            numberOfTrucks = Integer.parseInt(lectorComando);
        } catch (InputMismatchException e){
            numberOfTrucks = 0;
        }
        catch (NumberFormatException e){
            numberOfTrucks = 0;
        }
        if (numberOfTrucks < 1) {
            PrintText.wrongNumberOfTrucks();
            numberOfTrucks = 0;
        }
        return numberOfTrucks;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opportunity that = (Opportunity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    ////////////////////////////////GETTERS AND SETTERS//////////////////////

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", decisionMaker=" + decisionMaker +
                ", status=" + status +
                '}';
    }

    public String toStringFormatted() {
        return  "Opportunity ID " + id +
                " | Product: " + product +
                " | Quantity: " + quantity +
                " | Decision Maker: " + decisionMaker.getName() +
                " | Status: " + status;
    }

}
