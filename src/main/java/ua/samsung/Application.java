package ua.samsung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application 
{
	public static void main(String[] args)
	{
		String[][] expressionsTable = retrieveInputs();
		
		Arrays.stream(expressionsTable).forEach(s -> System.out.println(Arrays.toString(s)));
	}
	
	
	
	public static String[][] retrieveInputs()
	{
		Scanner scanner = new Scanner(System.in);
		
		List<String[]> result = new ArrayList<>();
		int numberOfInputs = Integer.parseInt(scanner.nextLine());
		for(int i = 0; i < numberOfInputs; i++)
		{
			result.add(scanner.nextLine().split(","));
		}
		
		return result.toArray(new String[result.size()][]);
	}
}
