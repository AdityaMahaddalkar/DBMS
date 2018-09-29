
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main{
	
	
	static void decodeObjects() throws IOException, ParseException
	{
		BufferedReader br = new BufferedReader(new FileReader("myFile.json"));
		
		String str;
		JSONObject ob;
		int i = 1;
		while( ( str = br.readLine() ) != null)
		{
			
			System.out.println("Documnet no. :\t" + i + "\n");
			
			ob = (JSONObject)new  JSONParser().parse(str);
			
			Long roll = (Long)ob.get("Roll");
			System.out.println("Roll no:\t" + roll.toString());
			
			JSONObject name = (JSONObject) ob.get("Name");
			
			String Name = (String)name.get("Name");
			System.out.println("Name:\t" + Name);
			
			JSONArray arr = (JSONArray) ob.get("Cities");
			
			System.out.println("Cities     :");
			
			for(int j = 0 ; j < arr.size() ; j++)
			{
				String city = (String) arr.get(j);
				
				System.out.println(city);
			}
			
			System.out.println("\n");
			i+=1;
		}
		
		br.close();
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static void addObject(Integer roll , String Name) throws IOException
	{
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("myFile.json",true));
		
		JSONObject obj = new JSONObject();
		
		obj.put("Roll", roll);
		
		Map name = new HashMap<String , String>();
		name.put("Name", Name);
		
		obj.put("Name", name);
		
		Random rand = new Random();
		
		String [] cities = {"Pune", "Mumbai", "Delhi", "Jaipur"};
		
		JSONArray ar = new JSONArray();
		
		for(int i=0 ; i < 4 ; i++)
		{
			ar.add(cities[i]);
		}
		
		obj.put("Cities", ar);
		
		System.out.println("\n" + obj.toJSONString());
		bw.write(obj.toJSONString());
		bw.newLine();
		System.out.println("Success");
		bw.close();
		
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		
		int choice;
		Scanner sc = new Scanner(System.in);
		
		String Name;
		Integer roll;
		
		while(true)
		{
			System.out.print("\n\nEnter Choice :\n1: Encode Object\n2: Decode Objects\n3: Exit\n>");
			choice = sc.nextInt();
			
			if(choice == 1)
			{
				System.out.print("Enter RollNO      : ");
				roll = Integer.parseInt(sc.next());
				System.out.print("Enter Name  : ");
				Name = sc.next();
				Main.addObject(roll, Name);
			}
			else if(choice == 2)
			{
				Main.decodeObjects();
			}
			else
			{
				sc.close();
				break;
			}
		}
	}
	
}