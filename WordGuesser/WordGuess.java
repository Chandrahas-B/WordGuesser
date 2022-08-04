
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
class WordGuess
{
    public static void main(String[] args)
    {
    	Scanner sc =new Scanner(System.in);

        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("\033[38;5;206m "); 
        // System.out.println("\033[0;95m");
        printTitle();
        System.out.println("--------------------------------------------------------------------------------\n\n");
        String correct_word = getRandomWord();
        System.out.println("Enter your name:");
        String name=sc.nextLine();
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        System.out.println("--------------------------------------------------------------------------------\n\n");
        System.out.println("Hello "+name+"!!\n\n");
        printWelcome();
        //rules for the game.
        printRules();
        System.out.println("\nAre you ready to play?\n\nPress any key to continue\n");
        System.out.println(correct_word);
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}
        
        int correct_length=correct_word.length();
        int chance=7;
        int chance_check=0;
        String prev_user_word="";
        ArrayList<Character> used_letters=new ArrayList<>(correct_length+7);
        int check_length=0;
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        String user_word="";
        System.out.println("Length of your word: "+correct_length+"\n\n");
        for(int i=0; i<correct_length; i++)
        	user_word+= '-';
        prev_user_word=user_word;
        while(chance>0)
        {
            printScoreArt(7-chance);
        	chance_check+=1;
        	System.out.println("Guess No. "+chance_check);
        	System.out.println("\nYour current progress: \n");
        	System.out.println(user_word);
        	System.out.println("\n"+chance+" guesses left");
        	System.out.println("Enter the character: ");
            char input_letter;
            //check for the number of input letters(ask them again for input)
            String user_input=sc.next();
            if(user_input.length() > 1)
            {
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println("You have entered more than 1 character,\nPlease follow the rules and try again!\n\n\n");
                chance_check-=1;
                continue;
            }
            else
                input_letter=user_input.charAt(0);
            //check if the letter is repeating or not
            used_letters=repeating_check(used_letters,input_letter);
            if(check_length==used_letters.size())
            {
                chance_check-=1;
                continue;
            }
            check_length+=1;
            //filling the blanks
        	user_word=check_for_letter(correct_word,user_word,input_letter);
        	if(user_word.equals(correct_word))
        		break;
        	if(user_word.equals(prev_user_word))
        		chance-=1;
        	prev_user_word=user_word;
        }
        if (! (user_word.equals(correct_word)))
        {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            printScoreArt(7);
            printLose();
        	System.out.println("You did not guess the word!\n\n");
        	System.out.println("Better luck next time!\n");
        	System.out.println("------------------------------------------------\n\n");
            System.out.println("The correct word is: "+correct_word.toUpperCase());
        }
        else 
        {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            printScoreArt(7-chance);
            printWin();
        	System.out.println("You guessed the word!!\n\n");
            System.out.println("------------------------------------------------\n\n");
        	System.out.println("The correct word is: "+correct_word.toUpperCase());
        }
        System.out.println("------------------------------------------------\n\n");
        System.out.println("\nPress any key to exit\n");
    	try
        {
            System.in.read();
            System.in.read();
            System.in.read();
        }  
        catch(Exception e)
        {}
    	
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        printTY();
        System.out.println("\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tProject done by:");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t- Bhavith PB Rao\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t- Chandrahas B\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t- Rivana S\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t- Samarth AP");
        try
        {
            System.in.read();
            System.in.read();
        }  
        catch(Exception e)
        {}
    	System.out.println("\033[31;0m");
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.exit(0);
        sc.close();
    }

    public static void printTitle()
    {
        System.out.println();
        System.out.println("██████████████████████████████████▀██████████████████████████████████████");
        System.out.println("█▄─█▀▀▀█─▄█─▄▄─█▄─▄▄▀█▄─▄▄▀███─▄▄▄▄█▄─██─▄█▄─▄▄─█─▄▄▄▄█─▄▄▄▄█▄─▄▄─█▄─▄▄▀█");
        System.out.println("██─█─█─█─██─██─██─▄─▄██─██─███─██▄─██─██─███─▄█▀█▄▄▄▄─█▄▄▄▄─██─▄█▀██─▄─▄█");
        System.out.println("▀▀▄▄▄▀▄▄▄▀▀▄▄▄▄▀▄▄▀▄▄▀▄▄▄▄▀▀▀▀▄▄▄▄▄▀▀▄▄▄▄▀▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▀▄▄▀");
        
        System.out.println();
    }
    
    public static void printRules()
    {
        System.out.println();
        System.out.println(" █▀█ █░█ █░░ █▀▀ █▀");
        System.out.println(" █▀▄ █▄█ █▄▄ ██▄ ▄█");
        System.out.println();
        System.out.println("--> A random word is chosen from the English dictionary for you to guess.");
        System.out.println("--> The aim of the game is to guess all letters of the word correctly before you run out of chances.");
        System.out.println("--> You get 7 chances to guess the word correctly.");
        System.out.println("--> You can only guess 1 letter at a time. Numbers and Special Characters will be considered as incorrect.");
        System.out.println("--> Each incorrect guess will reduce the number of chances by 1.");
        System.out.println("--> Each correct guess will fill in all occurrences of the letter in the word.");
        System.out.println("--> If you guess all letters of the word correctly, you win!");
        System.out.println("--> If you run out of chances, then you lose!");
    }

    public static void printWelcome()
    {

        System.out.println("░██╗░░░░░░░██╗███████╗██╗░░░░░░█████╗░░█████╗░███╗░░░███╗███████╗");
        System.out.println("░██║░░██╗░░██║██╔════╝██║░░░░░██╔══██╗██╔══██╗████╗░████║██╔════╝");
        System.out.println("░╚██╗████╗██╔╝█████╗░░██║░░░░░██║░░╚═╝██║░░██║██╔████╔██║█████╗░░");
        System.out.println("░░████╔═████║░██╔══╝░░██║░░░░░██║░░██╗██║░░██║██║╚██╔╝██║██╔══╝░░");
        System.out.println("░░╚██╔╝░╚██╔╝░███████╗███████╗╚█████╔╝╚█████╔╝██║░╚═╝░██║███████╗");
        System.out.println("░░░╚═╝░░░╚═╝░░╚══════╝╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚══════╝");
    }

    public static void printScoreArt(int n)
    {
        String[] HANGMANPICS = { 
            " +---+\n" +
            "     |\n" +
            "     |\n" +
            "     |\n" +
            "     |\n" +
            "     |\n" +
            "=========\n", 
            " +---+\n" +
            " |   |\n" +
            "     |\n" +
            "     |\n" +
            "     |\n" +
            "     |\n" +
            "==========\n", 
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "     |\n" +
            "     |\n" +
            "     |\n" +
            "===========\n" , 
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            " |   |\n" +
            "     |\n" +
            "     |\n" +
            "===========\n" , 
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|   |\n" +
            "     |\n" +
            "     |\n" +
            "===========\n" , 
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" +
            "     |\n" +
            "     |\n" +
            "=========\n" , 
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" +
            "/    |\n" +
            "     |\n" +
            "=========\n" , 
            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" +
            "/ \\  |\n" +
            "     |\n" +
            "========= \n"
        };
    
            System.out.println(HANGMANPICS[n]);
    
    }

    public static void printWin()
    {
        System.out.println();
        System.out.println("█████████████████████████████████████████████████████████████████████████████████████████████████████████████████████");
        System.out.println("█░░░░░░░░██░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░████░░░░░░██████████░░░░░░█░░░░░░░░░░█░░░░░░██████████░░░░░░█░░░░░░█");
        System.out.println("█░░▄▀▄▀░░██░░▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░████░░▄▀░░██████████░░▄▀░░█░░▄▀▄▀▄▀░░█░░▄▀░░░░░███████░░▄▀░░█░░▄▀░░█");
        System.out.println("█░░░░▄▀░░██░░▄▀░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░████░░▄▀░░██████████░░▄▀░░█░░░░▄▀░░░░█░░▄▀▄▀░░░░██████░░▄▀░░█░░▄▀░░█");
        System.out.println("███░░▄▀▄▀░░▄▀▄▀░░███░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░████░░▄▀░░██████████░░▄▀░░███░░▄▀░░███░░▄▀░░▄▀░░░░████░░▄▀░░█░░▄▀░░█");
        System.out.println("███░░░░▄▀▄▀▄▀░░░░███░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░████░░▄▀░░██░░░░░░██░░▄▀░░███░░▄▀░░███░░▄▀░░░░▄▀░░░░██░░▄▀░░█░░▄▀░░█");
        System.out.println("█████░░░░▄▀░░░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░████░░▄▀░░██░░▄▀░░██░░▄▀░░███░░▄▀░░███░░▄▀░░░░░░▄▀░░░░░░▄▀░░█░░▄▀░░█");
        System.out.println("███████░░▄▀░░███████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░████░░▄▀░░██░░▄▀░░██░░▄▀░░███░░▄▀░░███░░▄▀░░██░░░░▄▀░░░░▄▀░░█░░░░░░█");
        System.out.println("███████░░▄▀░░███████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░████░░▄▀░░░░░░▄▀░░░░░░▄▀░░███░░▄▀░░███░░▄▀░░████░░░░▄▀░░▄▀░░████████");
        System.out.println("███████░░▄▀░░███████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░▄▀░░████░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀░░█░░░░▄▀░░░░█░░▄▀░░██████░░▄▀▄▀▄▀░░█░░░░░░█");
        System.out.println("███████░░▄▀░░███████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░░░░░▄▀░░░░░░▄▀░░█░░▄▀▄▀▄▀░░█░░▄▀░░██████░░░░░░▄▀░░█░░▄▀░░█");
        System.out.println("███████░░░░░░███████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░████░░░░░░██░░░░░░██░░░░░░█░░░░░░░░░░█░░░░░░██████████░░░░░░█░░░░░░█");
        System.out.println("█████████████████████████████████████████████████████████████████████████████████████████████████████████████████████");
        System.out.println();
    }

    public static void printLose()
    {
        System.out.println("\n\n");
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("─████████──████████─██████████████─██████──██████────██████─────────██████████████─██████████████─██████████████─██████─");
        System.out.println("─██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░██──██░░██────██░░██─────────██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░██─");
        System.out.println("─████░░██──██░░████─██░░██████░░██─██░░██──██░░██────██░░██─────────██░░██████░░██─██░░██████████─██░░██████████─██░░██─");
        System.out.println("───██░░░░██░░░░██───██░░██──██░░██─██░░██──██░░██────██░░██─────────██░░██──██░░██─██░░██─────────██░░██─────────██░░██─");
        System.out.println("───████░░░░░░████───██░░██──██░░██─██░░██──██░░██────██░░██─────────██░░██──██░░██─██░░██████████─██░░██████████─██░░██─");
        System.out.println("─────████░░████─────██░░██──██░░██─██░░██──██░░██────██░░██─────────██░░██──██░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░██─");
        System.out.println("───────██░░██───────██░░██──██░░██─██░░██──██░░██────██░░██─────────██░░██──██░░██─██████████░░██─██░░██████████─██████─");
        System.out.println("───────██░░██───────██░░██──██░░██─██░░██──██░░██────██░░██─────────██░░██──██░░██─────────██░░██─██░░██────────────────");
        System.out.println("───────██░░██───────██░░██████░░██─██░░██████░░██────██░░██████████─██░░██████░░██─██████████░░██─██░░██████████─██████─");
        System.out.println("───────██░░██───────██░░░░░░░░░░██─██░░░░░░░░░░██────██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░██─");
        System.out.println("───────██████───────██████████████─██████████████────██████████████─██████████████─██████████████─██████████████─██████─");
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println();
    }

    public static void printTY()
    {

        System.out.println("████████╗██╗░░██╗░█████╗░███╗░░██╗██╗░░██╗░██████╗  ███████╗░█████╗░██████╗░  ██████╗░██╗░░░░░░█████╗░██╗░░░██╗██╗███╗░░██╗░██████╗░");
        System.out.println("╚══██╔══╝██║░░██║██╔══██╗████╗░██║██║░██╔╝██╔════╝  ██╔════╝██╔══██╗██╔══██╗  ██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██║████╗░██║██╔════╝░");
        System.out.println("░░░██║░░░███████║███████║██╔██╗██║█████═╝░╚█████╗░  █████╗░░██║░░██║██████╔╝  ██████╔╝██║░░░░░███████║░╚████╔╝░██║██╔██╗██║██║░░██╗░");
        System.out.println("░░░██║░░░██╔══██║██╔══██║██║╚████║██╔═██╗░░╚═══██╗  ██╔══╝░░██║░░██║██╔══██╗  ██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██║██║╚████║██║░░╚██╗");
        System.out.println("░░░██║░░░██║░░██║██║░░██║██║░╚███║██║░╚██╗██████╔╝  ██║░░░░░╚█████╔╝██║░░██║  ██║░░░░░███████╗██║░░██║░░░██║░░░██║██║░╚███║╚██████╔╝");
        System.out.println("░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝╚═════╝░  ╚═╝░░░░░░╚════╝░╚═╝░░╚═╝  ╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝╚═╝░░╚══╝░╚═════╝░");

    }

    public static ArrayList<Character> repeating_check(ArrayList<Character> used_letters, char input_letter)
    {
        for(char check_letter:used_letters)
        {
            if(check_letter==input_letter)
            {
                System.out.print("\033[H\033[2J");  
                System.out.flush();
                System.out.println("Your previous guess: "+input_letter);
                System.out.println("You have already tried this letter\nTry something else\n\n");
                return used_letters;
            }
        }
        used_letters.add(input_letter);
        return used_letters;
    }
    public static String check_for_letter(String c_word, String u_word, char letter)
    {
    	String new_user_word="";
    	int flag=0;
    	for(int i=0;i<c_word.length();i++)
    	{
    		if (c_word.charAt(i)==letter)
    		{
    			flag=1;
    			new_user_word+=letter;
    		}
    		else
    			new_user_word+=u_word.charAt(i);
    	}
    	if(flag==1)
	    	System.out.println("You have guessed a correct letter!! \n\n");
    	else
    		System.out.println("You have guessed an incorrect letter!!\n\n");
    	
    	System.out.println(new_user_word);
    	System.out.println("\nPress any key to continue");
    	try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}
    	System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("Your previous guess: "+letter);
    	return new_user_word;
    }
    
    public static String getRandomWord()
    {
        String word  = "";
        int n = (int)(Math.random()*127142);
        try{
            Scanner sc =new Scanner(new File("dictionary.txt"));
            for(int i=0;i<n;i++)
                sc.nextLine();
            word = sc.nextLine();
            sc.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        return word;
    }
}