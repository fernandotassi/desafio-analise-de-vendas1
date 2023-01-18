package application;

import java.io.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import entities.Sale;

public class Program
{
   public static void main(String[] args) 
	{
		Locale.setDefault(Locale.US); 
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Entre o caminho do arquivo: ");
		String path = scan.nextLine();
		
		System.out.println();
		try(BufferedReader br = new BufferedReader(new FileReader(path)))
		{
			List<Sale> list = new ArrayList<>();
			String line = br.readLine();
			while(line != null)
			{
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]) , Integer.parseInt(fields[1]), fields[2],
						  Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}	
			
			 Comparator<Sale> comp = (p1, p2) -> p1.averagePrice().compareTo(p2.averagePrice());
			 
			 List<Sale> stream = list.stream().filter(p -> p.getYear() == 2016)						 
					    .sorted(comp.reversed())	
					    .limit(5)
					    .collect(Collectors.toList());
			 
		     stream.forEach(System.out :: println);	    
		     
		     Double avg = list.stream().filter(lista -> lista.getSeller().equals("Logan") && (lista.getMonth() == 1 || lista.getMonth() == 7))	 		    		                  
		                              .map(p -> p.getTotal())		                            
		                              .reduce(0.0, (x, y) -> x + y) ;
		   
		     System.out.println();                                               
		     System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = " + avg);
		}
		catch(IOException e)
		{System.out.println("Error: " + path + " (O sistema não pode encontrar o arquivo especificado)");}
		
		scan.close();
	} 
}
