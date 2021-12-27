package machine;

import java.util.Objects;
import java.util.Scanner;

public class Machine {
    private int milk = 540;
    private int water = 400;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;

    public void makeOrder(){
//        System.out.println("Starting to make a coffee\n" +
//                "Grinding coffee beans\n" +
//                "Boiling water\n" +
//                "Mixing boiled water with crushed coffee beans\n" +
//                "Pouring coffee into the cup\n" +
//                "Pouring some milk into the cup\n" +
//                "Coffee is ready!");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.next();
            System.out.println();
            switch (action){
                case "buy":
                    buyCoffee();
                    break;
                case "fill":
                    fillMachine();
                    break;
                case "take":
                    takeMoney();
                    break;
                case "remaining":
                    showState();
                    break;
                case "exit":
                    return;
                default:
                    break;
            }
            System.out.println();
        }
    }

    private void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void fillMachine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add: ");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        beans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        cups += scanner.nextInt();
    }

    private void buyCoffee(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");

        switch(scanner.next()){
            case "1":
                if (canWeMakeOrder(1)) {
                    money += 4;
                    water -= 250;
                    beans -= 16;
                    cups--;
                }
                break;
            case "2":
                if (canWeMakeOrder(2)) {
                    money += 7;
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    cups--;
                }
                break;
            case "3":
                if (canWeMakeOrder(3)) {
                    money += 6;
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    cups--;
                }
                break;
            case "back":
                return;
            default:
                break;
        }
    }

    public void setIngredients(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has: ");
        water = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        beans = scanner.nextInt();
    }
    private boolean canWeMakeOrder(int order){
        switch (order) {
            case 1:
                if (water < 250){
                    System.out.println("Sorry, not enough water!");
                }else if (beans < 16){
                    System.out.println("Sorry, not enough beans!");
                }else{
                    System.out.println("I have enough resources, making you a coffee!");
                    return true;
                }
                break;
            case 2:
                if (water < 350){
                    System.out.println("Sorry, not enough water!");
                }else if (beans < 20){
                    System.out.println("Sorry, not enough beans!");
                }else if (milk < 75){
                    System.out.println("Sorry, not enough milk!");
                }else {
                    System.out.println("I have enough resources, making you a coffee!");
                    return true;
                }
                break;
            case 3:
                if (water < 200){
                    System.out.println("Sorry, not enough water!");
                }else if (beans < 12){
                    System.out.println("Sorry, not enough beans!");
                }else if (milk < 100){
                    System.out.println("Sorry, not enough milk!");
                }else {
                    System.out.println("I have enough resources, making you a coffee!");
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    public void setOrderSize(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need:");
        int order = scanner.nextInt();
        canWeMakeOrder(order);
    }
    public void showWeights(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need:");
        int cups = scanner.nextInt();
        System.out.println("For " + cups + " cups of coffee you will need:");
        int water = cups*200;
        int milk = cups*50;
        int beans = cups*15;
        System.out.println(
                water +" ml of water\n" +
                        milk  +" ml of milk\n" +
                        beans +" g of coffee beans"
        );
    }
    public void showState(){
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
    }

}
