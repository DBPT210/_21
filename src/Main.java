import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("==========");
        System.out.println("###_21####");
        System.out.println("==========");
        System.out.println("");
        System.out.printf("\u001B[36m%s\u001B[0m", "\n> start - Starts the game. Good luck!\n> tutorial - Know how to play the game\n> exit - Exits the program.\n");
        System.out.printf("\u001B[32m%s\u001B[0m", "\n> ");
        String input = userInput.next();
        //System.out.println(input);
        //if (input.equals("hi")){
        //    System.out.println("true");
        //}else {
        //    System.out.println("false");
        //}
        switch (input){
            case "start":
                System.out.println("Starting game...");
                new playgame();
                break;
            case "tutorial":
                System.out.println("This game is a very simplified version of the popular casino card game 'blackjack'.");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Your objective is to get more points than the dealer within the limit of 21 points.");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("If you get 21 points, you automaticly win, but if you go over it, you immediatly lose. It also applies to the dealer.");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("You start with 100$ to bet with, if you lose it all you are out.");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Feeling lucky? Let's find out.");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("");
                main(new String[]{""});
            case "exit":
                System.out.println("See you soon!");
                TimeUnit.SECONDS.sleep(5);
                break;
            default:
                System.out.println("Invalid input");
                main(new String[]{""});

        }
    }
}