import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class playgame {
    static Random random = new Random();
    static Scanner userInput = new Scanner(System.in);
    static int minRand = 1;
    static int maxRand = 11;
    static int dealerRand;
    static String input;
    static int playerPoints = 0;
    static int dealerPoints = 0;
    static int playerMoney = 100;
    static int card = random.nextInt(maxRand - minRand + 1) + minRand;
    static int roundCounter = 1;
    static int moneyBet;

    public playgame() throws InterruptedException {       //main process (aka constructor)
        //System.out.println("runplay");
        statsPrint();
    }
    static void statsPrint() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        if (playerMoney == 0){
            System.out.printf("\u001B[91m%s\u001B[0m","\nYou went backrupt.\n");
            TimeUnit.SECONDS.sleep(5);
            System.exit(0);
        }
        playerPoints = 0;
        dealerPoints = 0;
        //roundCounter += 1;
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(" ");
        System.out.println("Round " + roundCounter);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Current money: " + playerMoney + "$");
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("\u001B[36m%s\u001B[0m", "\nAvailable commands:\n> play - Play the next round;\n> leave - Leave the game with your money;\n");
        System.out.printf("\u001B[32m%s\u001B[0m", "\n> ");
        input = userInput.next();
        switch (input){
            case "play":
                System.out.printf("\u001B[36m%s\u001B[0m", "\nHow much money will you bet?\n");
                System.out.printf("\u001B[32m%s\u001B[0m", "\n> ");
                input = userInput.next();
                try {
                    moneyBet = Integer.parseInt(input);
                } catch (Exception e){
                    System.out.printf("\u001B[91m%s\u001B[0m","\nInvalid money value\n");
                    statsPrint();
                }
                if (moneyBet > playerMoney){
                    System.out.printf("\u001B[33m%s\u001B[0m","\nYou betted more money than you have.\n");
                    statsPrint();
                }
                if (moneyBet <= 0){
                    System.out.printf("\u001B[91m%s\u001B[0m","\nInvalid money value\n");
                    statsPrint();
                }
                gameloop();
                break;
            case "leave":
                System.out.println("You left the game with " + playerMoney + "$.");
                TimeUnit.SECONDS.sleep(5);
                System.exit(1);
                break;
            default:
                System.out.printf("\u001B[91m%s\u001B[0m","\nInvalid input!\n");
                statsPrint();
        }

    }
    static void gameloop() throws InterruptedException {
        //System.out.println("This is the gameloop");
        System.out.println("Currently you have " + playerPoints + " points.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("What are you going to do?");
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("\u001B[36m%s\u001B[0m", "\n> card - Take a card\n> bet - Bet if you think you got the most points\n");
        System.out.printf("\u001B[32m%s\u001B[0m", "\n> ");
        input = userInput.next();
        switch (input){
            case "card":
                card = random.nextInt(maxRand - minRand + 1) + minRand;
                playerPoints += card;
                System.out.println("You got " + card + " points.");
                TimeUnit.SECONDS.sleep(1);
                if (playerPoints > 21){
                    System.out.printf("\u001B[91m%s\u001B[0m","\nYou got more than 21 points. You lose!\n");
                    TimeUnit.SECONDS.sleep(1);
                    lose();
                }
                if (playerPoints == 21){
                    System.out.printf("\u001B[32m%s\u001B[0m","\nYou got exactly 21 points! You win!\n");
                    TimeUnit.SECONDS.sleep(2);
                    win();
                }
                dealerTurn();
                break;
            case "bet":
                bet();
                break;
            default:
                System.out.printf("\u001B[91m%s\u001B[0m","\nInvalid input!\n");
                gameloop();
        }
    }
    static void dealerDeals() throws InterruptedException {
        card = random.nextInt(maxRand - minRand + 1) + minRand;
        dealerPoints += card;
        System.out.println("The dealer gets a card.");
        TimeUnit.SECONDS.sleep(2);
        if (dealerPoints > 21){
            System.out.printf("\u001B[32m%s\u001B[0m","\nDealer got more than 21 points. You win!\n");
            TimeUnit.SECONDS.sleep(3);
            win();
        }
        if (dealerPoints == 21){
            System.out.printf("\u001B[91m%s\u001B[0m","\nThe dealer got exactly 21 points! You lose!\n");
            TimeUnit.SECONDS.sleep(2);
            lose();
        }
        gameloop();
    }
    static void dealerTurn() throws InterruptedException {
        System.out.println("Dealer's turn...");
        TimeUnit.SECONDS.sleep(2);
        switch (dealerPoints){
            case 0:case 1: case 2: case 3: case 4: case 5:
            case 6:
                dealerDeals();
                break;
             case 7: case 8: case 9: case 10:
            case 11:
                dealerRand = random.nextInt(50 - 1 + 1) + 1;
                if (dealerRand == 25){
                    dealerNoDeal();
                    break;
                } else {
                    dealerDeals();
                }
            case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
                dealerRand = random.nextInt(2 - 1 + 1) + 1;
                if (dealerRand == 2){
                    dealerDeals();
                }else {
                    dealerNoDeal();
                    break;
                }
            case 20:
                dealerNoDeal();
                break;
        }
    }
    static void dealerNoDeal() throws InterruptedException {
        System.out.println("Dealer bets he has the most points.");
        TimeUnit.SECONDS.sleep(2);
        bet();
    }
    static void win() throws InterruptedException {
        playerMoney += moneyBet;
        roundCounter += 1;
        statsPrint();
    }
    static void lose() throws InterruptedException {
        playerMoney -= moneyBet;
        roundCounter += 1;
        statsPrint();
    }
    static void bet() throws InterruptedException {
        System.out.println("You have " + playerPoints + " points");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("The dealer has " + dealerPoints + " points");
        TimeUnit.SECONDS.sleep(2);
        if (playerPoints > dealerPoints){
            System.out.printf("\u001B[32m%s\u001B[0m","\nYou got the most points. You win!\n");
            TimeUnit.SECONDS.sleep(3);
            win();
        }
        if (dealerPoints > playerPoints){
            System.out.printf("\u001B[91m%s\u001B[0m","\nThe dealer got the most points. You lose!\n");
            TimeUnit.SECONDS.sleep(3);
            lose();
        }
        if (dealerPoints == playerPoints){
            System.out.printf("\u001B[33m%s\u001B[0m","\nYou and the dealer have the same amount of points. It's a tie!\n");
            TimeUnit.SECONDS.sleep(3);
            roundCounter += 1;
            statsPrint();
        }
    }
}