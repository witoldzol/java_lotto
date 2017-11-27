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

    //private ArrayList<int> history;
    //constants
    final int THREE = 9;
    final int FOUR = 54;
    final int FIVE = 1000;
    final String SIX = "OMG,you actually won the lottery!!!!!!!!!!!!!";


    //METHODS
	
    //generate random numbers array
    public void generateLotto(){

	    Random rng = new Random();

		for(int i = 0; i<6;i++){
		//generate random number from 0-39 plus 1
		lottoNumbers[i] = rng.nextInt(39) + 1;
		}
	    System.out.println("Lotto numbers are : " + Arrays.toString(lottoNumbers));
    }

    public void calculateLottoResults(){

    	if(howManySeries > 0){
    		int matched =0;
    		for(int i = 0; i<6; i++){

    			for(int j = 0; j<6; j++){

    				if(firstSet[i] == lottoNumbers[j]){
    					matched++;
				    }
			    }
		    }
		    results[0] = matched;

		    calculateWinnings(matched);
		    System.out.println("\tYou matched "+matched+" number(s) in first line");
	    }

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
