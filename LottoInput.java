import java.util.Arrays;
import java.util.Scanner;

public class LottoInput{

	//Variables
	private String lottoNumbers;
	private int inputHowManyGames;
	private int howManyLottoNumbersProvided;

	//arrays
	private int[] firstSet;
	private int[] secondSet;
	private int[] thirdSet;

	//Objects
	Scanner scan = new Scanner(System.in);

	//setters

	//getters
	public int getInputHowManyGames(){
		return inputHowManyGames;
	}
	public int getHowManyLottoNumbersProvided(){
		return howManyLottoNumbersProvided;
	}
	public int[] getFirstSet(){
		return firstSet;
	}
	public int[] getSecondSet(){
		return secondSet;
	}
	public int[] getThirdSet(){
		return thirdSet;
	}

	//METHODS


	public void startGame(){

		System.out.println("Welcome to LOTTO GAME.\nHow many sets of numbers would you like to choose? (1-3)");

		//loop until user provides correct input
		while(true) {

			try {

				Scanner scan = new Scanner(System.in);
				inputHowManyGames = scan.nextInt();

				if(inputHowManyGames <=3 && inputHowManyGames >=1){
					System.out.println("Thank you.\n\nYou have 1 in 3,838,380 chance of winning this lottery.\nGOOD LUCK!!!\n");
					break;
				}else{
					System.out.println("Select value between 1 and 3. Try again");
				}

			} catch (Exception e) {

				System.out.println("Ooops, we have encountered an error:\n" + e + "\nDon't be shy, try again");

			}
		}

	}

	public void askForNumbers(){

		System.out.println("Please enter 6 values between 1 and 40.");

		while(true){

			try{
				Scanner scan = new Scanner(System.in);

				lottoNumbers = scan.nextLine();
				//transfer input to int array
				int[] lottoNumbersArray = convertStringToArray(lottoNumbers);

				if(testNumbers(lottoNumbersArray)){
					break;
				}else{
					System.out.println("Try again");
				}

			}catch (Exception e){
				System.out.println("Ooops, we have an error:\n" + e + "\nTry again");
			}


		}

	}
	//INTERNAL METHODS
	private int[] convertStringToArray(String inputString){

		int counter = 0;
		int filterCounter = 0;
		String[] stringArr = inputString.trim().split("\\s*[^0-9.]+");

		//count how many actual digits there are in array
		for (String ele : stringArr) {
			if (!ele.isEmpty()) {
				counter++;
			}
		}

		int[] filteredArr = new int [counter];

		for (String ele : stringArr) {
			if (!ele.isEmpty()) {
				filteredArr[filterCounter] = Integer.parseInt(ele);
				filterCounter++;
			}
		}

		return filteredArr;
	}

	private boolean testNumbers(int[] inputArray){

		int len = inputArray.length;


		//game length
		if(len <6){
			System.out.println("Not enough numbers provided");
			return false;
		}else if(len > 6) {
			System.out.println("Too many numbers provided");
			return false;
		}
		//game for duplicates
		if(testForDuplicates(inputArray)){
			System.out.println("Ahhh Geez, you cannot select same number more than once");
			return false;
		}
		//game if numbers are valid
		if(testForValidNumbers(inputArray)){
			System.out.println("Oh dear, you selected number(s) outside of valid range.\nPick numbers between 1 and 40.");
			return false;
		}
		//after verifying numbers, we can save them
		saveUserLottoNumbers(inputArray);
		return true;
	}

	private boolean testForDuplicates(int[] arr){

		for(int i =0; i<arr.length; i++){

			int counter = 0;

			for(int j = 0; j<arr.length; j++){

				if(arr[i] == arr[j]){

					counter++;

					if(counter == 2){
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean testForValidNumbers(int[] arr){

		for(int ele : arr){
			if(ele > 40 || ele <1 ){
				return true;
			}
		}
		return false;
	}

	private void saveUserLottoNumbers(int[] inputArray){

		if(howManyLottoNumbersProvided<3){

			howManyLottoNumbersProvided++;

			if(howManyLottoNumbersProvided == 1){
				firstSet = inputArray;
			} else if(howManyLottoNumbersProvided == 2){
				secondSet = inputArray;
			} else {
				thirdSet = inputArray;
			}
		}
	}
}
