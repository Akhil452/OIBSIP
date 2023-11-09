import java.util.*;
public class NumberGuess
{
	public static void main(String[] args) {
	    Random r=new Random();
	    Scanner sc= new Scanner(System.in);
	    int h=100,l=1,guess,limit=10;
		char ch='y';
		while(ch=='y')
		{
	    	int n=r.nextInt(h-l)+l;
			int count=0;
		    while(limit!=count)
	    	{
	        	System.out.println("Guess the number(1 to 100): ");
	       	 	guess= sc.nextInt();
	        	if(guess==n)
	            {
	                System.out.println("Your Guess is right!!");
	                count+=1;
	                System.out.println("Score: "+ count);
	                break;
	            }
	    	    else
	            {
					count++;
	                System.out.println("Your Guess is wrong("+(limit-count)+" tries left)");
					if(count == limit)
	                {
	                    System.out.println("!!!GAME OVER!!!");
						break;
	                }
					if(n>guess)
						System.out.println("Go for bigger number");
					else
	                	System.out.println("Go for smaller number");
	            }
	   		}

			System.out.println("Want to play another round?(y/n): ");
			ch= sc.next().charAt(0);
		}	
	}
}
