import java.util.*;
import java.io.*;

public class nyse_interp
{
	public static void main (String args[]) throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		
		boolean cont = true;
		String check = "";
		String in = new String();
		Scanner inputFile = new Scanner("");
		String[] tempValues = new String[2];
		String tempYear = "";
		String tempVolume = "";
		ArrayList<Integer> year = new ArrayList<>();
		Stack<Double> holdingVolume = new Stack<>();
		double tempValue = 0;
		int tempYears = 0;
		ArrayList<Double> yearlyAverage = new ArrayList<>();
		
		while (cont)
		{
			System.out.println("Do you wish to input a file? y/n");
			check = keyboard.nextLine();
			
			if (check.equals("n"))
			{
				cont = false;
				break;
			}
			else if (check.equals("y"))
				cont = true;
			else
			{
				System.out.println("That wasn't quite right...\n");
				continue;
			}
			
			System.out.print("Input full file path and name: ");
			File input = new File(keyboard.nextLine());
			inputFile = new Scanner(input);
			System.out.println();
			
			while (inputFile.hasNextLine())
			{
				in = inputFile.nextLine();
				tempValues = in.split("\t", 2);
				tempYear = tempValues[0].substring(0, 4);
				year.add(Integer.parseInt(tempYear));
				
				if (year.size() > 1)
				{
					if (year.get(year.size() - 1) - year.get(year.size() - 2) == 1)
						{
							tempYears = holdingVolume.size();
							while (holdingVolume.isEmpty() == false)
								tempValue += holdingVolume.pop();
							yearlyAverage.add(tempValue / (double)tempYears);
							
							tempValue = 0;
						}
				}
				
				tempVolume = tempValues[1];
				holdingVolume.add(Double.parseDouble(tempVolume));
			}
			
			if (holdingVolume.isEmpty() == false)
			{
				tempYears = holdingVolume.size();
				while (holdingVolume.isEmpty() == false)
					tempValue += holdingVolume.pop();
				yearlyAverage.add(tempValue / (double)tempYears);
			}
			
			tempValue = 0;
		}
		
		PrintWriter outputFile = new PrintWriter("Output.txt");
		
		for (int x = 0; x < yearlyAverage.size(); x++)
		{
			outputFile.printf("%.1f", yearlyAverage.get(x));
			outputFile.println();
		}
		
		keyboard.close();
		inputFile.close();
		outputFile.close();
	}
}
