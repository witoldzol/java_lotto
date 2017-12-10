import java.util.Arrays;
import java.util.Random;

public class Lotto{
	//CONSTRUCTOR
	public Lotto(int[] firstSet, int[] secondSet, int[] thirdSet, int howManySeries){

		this.firstSet = firstSet;
		this.secondSet = secondSet;
		this.thirdSet = thirdSet;
		this.howManySeries = howManySeries;
	}

	//VARIABLES
	private int totalPrize, howManySeries;
	private boolean omgItActuallyHappened = false;
    //arrays
    private int[] lottoNumbers = new int[6];
    private int[] firstSet;
    private int[] secondSet;
    private int[] thirdSet;
    private int[] results = {7,7,7};
    //obj
    Random rng = new Random();
    //private ArrayList<int> history;
    //constants
    final int THREE = 9;
    final int FOUR = 54;
    final int FIVE = 1000;
    final String SIX = "OMG,you actually won the lottery!!!!!!!!!!!!!";

    //generate random numbers array
    public void generateLotto(){

    	//loop until we get non duplicate array
    	while(true) {

    			for(int i = 0; i<6;i++){
    			//generate random number from 0-39 plus 1
    			lottoNumbers[i] = rng.nextInt(39) + 1;
    			}
    			//if there are no duplicates, brake the loop
    			//no very efficient! ... but it works
    			if(!testForDuplicates(lottoNumbers)){
    				break;
    			} 

    	}

		//display numbers
	    System.out.println("\nLotto numbers are : " + Arrays.toString(lottoNumbers));
    }	

    //method for checking if there are duplicates in an array
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

    //compare user input to lotto numbers 
    public void calculateLottoResults(){
    	//if there was only one line
    	if(howManySeries > 0){
    		int matched =0;
    		//user input array
    		for(int i = 0; i<6; i++){
    			//lotto numbers array
    			for(int j = 0; j<6; j++){

    				if(firstSet[i] == lottoNumbers[j]){
    					matched++;
				    }
			    }
		    }
		    results[0] = matched;
		    //calculate the winnings for this line
		    calculateWinnings(matched);
		    System.out.println("\tYou matched "+matched+" number(s) in first line");
	    }
	    //second line
	    if(howManySeries > 1){
		    int matched =0;
		    for(int i = 0; i<6; i++){

			    for(int j = 0; j<6; j++){

				    if(secondSet[i] == lottoNumbers[j]){
					    matched++;
				    }
			    }
		    }
		    results[1] = matched;
		    calculateWinnings(matched);
		    System.out.println("\tYou matched "+matched+" number(s) in second line");
	    }
	    //thirds line
	    if(howManySeries == 3){
		    int matched =0;
		    for(int i = 0; i<6; i++){

			    for(int j = 0; j<6; j++){

				    if(thirdSet[i] ==lottoNumbers[j]){
					    matched++;
				    }
			    }
		    }
		    results[2] = matched;

		    calculateWinnings(matched);
		    System.out.println("\tYou matched "+matched+" number(s) in third line");

	    }

	    //print out total winnings

	    //check if user actually wins lotto...
	    if(omgItActuallyHappened){
		    System.out.println(SIX);
	    } else {
		    System.out.println("You won in total: $" + totalPrize);
	    }

    }
    public void calculateWinnings(int matched){

    	switch (matched){
		    case 4:
		    	totalPrize += FOUR;
		    	break;
		    case 5:
		    	totalPrize += FIVE;
		    	break;
		    case 6:
		    	omgItActuallyHappened = true;
			    totalPrize = 100000000;
		    	break;
		    default:
		    	totalPrize = 0;

	    }

    }
    
    //return array with results
	public int[] getResults(){
    	return results;
	}


}
