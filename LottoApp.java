import java.util.ArrayList;
import java.util.Scanner;

public class LottoApp {

	
	public static void main(String[] args) {
		//because we don't know how many times user will play
		//we use arraylist as it has no predefined length
		ArrayList<Integer> resultHistory = new ArrayList<Integer>();
		int[] results;

		//obj with the actual game
		Process process = new Process();

		

		//loop until user decides to stop
		while(true){

			results = process.game();
			//save results
			process.saveResults(results,resultHistory);

			//if user decides to stop, break loop
			if(!playAgain()){

				//display game history so far
				printHistory(resultHistory);
				break;
			}

		}

	}

	//method that asks user to play again
	//can break loop
	private static boolean playAgain(){
		//obj
		Scanner scan = new Scanner(System.in);

		System.out.println("Would you like to play again? Type 'y' if you do");

		String answer = scan.next();

		//if user enters anything other than 'y' or 'yes', end the game
		if( answer.equals("y") || answer.equals("yes")){
			System.out.println("you pressed yes");
			return true;
		} else{
			System.out.println("\nThank you for playing, see you next time!\nHere are your results from all your games:\n");
			return false;

		}

	}
	//method for printing out the history of played games
	private static void printHistory(ArrayList<Integer> list){
		int len = list.size();
		int total = 0;
		int index = 1;
		boolean wonLotto = false;

		System.out.println("You have entered " + len + " line(s) in total:\n");

		//loop over list and print out number of matches for each line
		//since lists have no indexes, we track index manually
		for( int ele : list){
			System.out.println("\tYou guessed " + ele + " number(s) on line number " + index);
			index++;
			//check if user won lotto
			if(ele == 6){
				wonLotto = true;
			}
			//
			total = calculateWinnings(total, ele);
		}
		if(wonLotto){
			System.out.println("\nOMG,you actually won the lottery!!!!!!!!!!!!!\n");
		}
		System.out.println("\nYou have won in total $"+total);
	}

	//method for calculating winnings
	private static int calculateWinnings(int total, int matched ){
		final int THREE = 9;
		final int FOUR = 54;
		final int FIVE = 1000;

		switch (matched){
			case 4:
				total += FOUR;
				break;
			case 5:
				total += FIVE;
				break;
			default:
				total = 0;
		}
		return total;
	}

}

//class with actual game
class Process{

	public int[] game(){

		int[] results;
		//variables
		int numberOfGames;
		int numbersProvided;

		//objects
		LottoInput inputObj = new LottoInput();

		inputObj.startGame();

		numberOfGames = inputObj.getInputHowManyGames();

		//loop until we get all the required lotto numbers
		while(true) {

			inputObj.askForNumbers();
			numbersProvided = inputObj.getHowManyLottoNumbersProvided();
			if(numberOfGames == numbersProvided){
				break;
			}
		}

		//instantiate object with values we generated previously
		Lotto processObj = new Lotto(

				inputObj.getFirstSet(),
				inputObj.getSecondSet(),
				inputObj.getThirdSet(),
				inputObj.getHowManyLottoNumbersProvided()
		);

		//generate random lotto numbers
		processObj.generateLotto();

		//calculate results
		processObj.calculateLottoResults();

		results = processObj.getResults();
		return results;
	}
	//method for saving results
	public void saveResults(int[] arr, ArrayList<Integer> list){
		//loop through each element in array with results
		for( int ele : arr){
			//if they are not valid number add to list with results
			if(ele < 7 ){

				list.add(ele);
			}

		}

	}

}
