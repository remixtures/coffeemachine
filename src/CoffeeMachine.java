import java.util.Scanner;

public class CoffeeMachine {

    private int water;
    private int milk;
    private int coffeeBeans;
    private int disposableCups;
    private int money;
    private String input;
    private State state = State.READY;

    CoffeeMachine(int water, int milk, int coffeeBeans, int disposableCups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.disposableCups = disposableCups;
        this.money = money;
    }

    State getState() {
        return this.state;
    }

    void start() {
        ready();
    }

    void stop() {
        this.state = State.SHUTDOWN;
    }

    private void ready() {
        this.state = State.READY;
        System.out.println();
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
    }

    void processInput(String input) {
        this.input = input;

        switch (this.state) {
            case READY:
                actionOptions();
                break;
            case WATER_INPUT:
            case MILK_INPUT:
            case BEANS_INPUT:
            case CUPS_INPUT:
                fill();
                break;
            case BUY_OPTIONS:
                buyCoffee();
                break;
            default:
                System.out.println("Unknown input state");
                ready();
                break;
        }
    }

    private void actionOptions() {
        System.out.println();
        switch (input) {
            case "buy":
                buyCoffee();
                break;
            case "fill":
                fill();
                break;
            case "take":
                takeMoney();
                break;
            case "remaining":
                printStatus();
                break;
            case "exit":
                stop();
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
    }

    private void buyCoffee() {
        switch (this.state) {
            case READY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                        "back - to main menu: ");
                this.state = State.BUY_OPTIONS;
                break;
            case BUY_OPTIONS:

                switch (this.input) {
                    case "1": // espresso
                        espresso();
                        break;
                    case "2": // latte
                        latte();
                        break;
                    case "3": // cappuccino
                        cappuccino();
                        break;
                    case "back":
                        break;
                    default:
                        System.out.println("Unknown buy command");
                        break;
                }
                ready();
                break;
            default:
                System.out.println("Unknown buy state");
                ready();
                break;
        }
    }

    private void espresso() {
        if (coffeeBeans < 16) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (water < 250) {
            System.out.println("Sorry, not enough water!");
        } else if (disposableCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            coffeeBeans -= 16;
            water -= 250;
            disposableCups -= 1;
            money += 4;
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    private void latte() {
        if (coffeeBeans < 20) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (water < 350) {
            System.out.println("Sorry, not enough water!");
        } else if (disposableCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else if (milk < 75) {
            System.out.println("Sorry, not enough milk!");
        } else {
            coffeeBeans -= 20;
            water -=350;
            disposableCups -= 1;
            milk -= 75;
            money += 7;
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    private void cappuccino () {
        if (coffeeBeans < 12) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (water < 200) {
            System.out.println("Sorry, not enough water!");
        } else if (disposableCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else if (milk < 100) {
            System.out.println("Sorry, not enough milk!");
        } else {
            coffeeBeans -= 12;
            water -=200;
            disposableCups -= 1;
            milk -= 100;
            money += 6;
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    private void takeMoney() {
        System.out.println("I gave you $" + money + "\n");
        money = 0;
        ready();
    }

    private void fill() {
        switch (this.state) {
            case READY:
                System.out.println("Write how many ml of water do you want to add: ");
                this.state = State.WATER_INPUT;
                break;
            case WATER_INPUT:
                this.water += Integer.parseInt(this.input);
                System.out.println("Write how many ml of milk do you want to add: ");
                this.state = State.MILK_INPUT;
                break;
            case MILK_INPUT:
                this.milk += Integer.parseInt(this.input);
                System.out.println("Write how many grams of coffee beans do you want to add: ");
                this.state = State.BEANS_INPUT;
                break;
            case BEANS_INPUT:
                this.coffeeBeans += Integer.parseInt(this.input);
                System.out.println("Write how many disposable cups of coffee do you want to add: ");
                this.state = State.CUPS_INPUT;
                break;
            case CUPS_INPUT:
                this.disposableCups += Integer.parseInt(this.input);
                ready();
                break;
            default:
                System.out.println("Unknown fill state");
                ready();
                break;
        }
    }

    private void printStatus() {
        System.out.println("The coffee machine has:\n" +
                water +  " of water\n" +
                milk + " of milk\n" +
                coffeeBeans + " of coffee beans\n" +
                disposableCups + " of disposable cups\n" +
                money + " of money");
        ready();
    }
}