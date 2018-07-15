import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class MySql {
	String url, dbName, driver, username, password;
	Connection conn;
	
	public MySql(String url, String dbName, String driver, String username, String password){
		this.url = url;
		this.dbName = dbName;
		this.driver = driver;
		this.username = username;
		this.password = password;
		try{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(this.url + this.dbName, this.username, this.password);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Successful");
	}
	
	public void print(Object x){System.out.println(x);};
	
	public boolean sequencing(){
		print("Demonstrating sequencing by creating a new table,\nadding new values and finally,\ndropping it.");
		Statement st;
		try{
			st = this.conn.createStatement();
		}
		catch( Exception e){
			e.printStackTrace();
			return false;
		}
		try{
			int output = st.executeUpdate("CREATE TABLE mytable(id int not null auto_increment key, name varchar(20))");
			int output2 = st.executeUpdate("INSERT INTO mytable(name) values('Aditya')");
			int output3 = st.executeUpdate("INSERT INTO mytable(name) values('Amey')");
			int output4 = st.executeUpdate("INSERT INTO mytable(name) values('Mandar')");
			int output5 = st.executeUpdate("INSERT INTO mytable(name) values('Pratik')");
			
			ResultSet rs = st.executeQuery("SELECT * FROM mytable");
			
			while(rs.next()){
				System.out.println("ID: " + rs.getInt("id") + " Name: " + rs.getString("name"));
			}
			
			int Output = st.executeUpdate("DROP TABLE mytable");
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean indexing(){
		String menu = "1.Unique Index\n2.Compund Index\n3.Show index\n4. Select index";
		String choice = "y";
		int option;
		Scanner sc = new Scanner(System.in);
		Statement st;
		try{
			st = this.conn.createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		try{
			while(choice.equalsIgnoreCase("y")){
				print(menu);
				print("Enter your option: ");
				option = sc.nextInt();
				switch(option){
				case 1:
					print("Enter index name: ");
					String indexName = sc.next();
					int option9 =st.executeUpdate("CREATE UNIQUE INDEX " + indexName + " ON book(isbn)");
					print("Successful");
					break;
				case 2:
					print("Enter index name: ");
					String indexName2 = sc.next();
					int output1 = st.executeUpdate("CREATE INDEX " + indexName2 + " ON order1(isbn, date1)");
					String compIndex = sc.next();
					break;
					
				case 3:
					print("Enter table name: ");
					String tname2 = sc.next();
					ResultSet rs = st.executeQuery("SHOW INDEX FROM " + tname2);
					while(rs.next()){
						System.out.println(rs.getString("Column_name") + " : " + rs.getString("Key_name"));
					}
					break;
					
				case 4:
					print("Selecting index");
					ResultSet rs1 = st.executeQuery("SELECT * FROM book USE INDEX(price_index)");
					while(rs1.next()){
						System.out.println(rs1.getString("title") + " : " + rs1.getInt("price"));
					}
					break;
				}
				print("Want to repeat(y or n)? :");
				choice = sc.next();
			}
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean viewMethods(){
		String menu = "1.Create view\n2.Update View\n3.Drop view\n4.Display\n5.Complex View";
		String choice = "y";
		Statement st;
		try{
			st = this.conn.createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		Scanner sc = new Scanner(System.in);
		try{
			while(choice.equalsIgnoreCase("Y")){
				print(menu);
				print("Enter you options: ");
				int option = sc.nextInt();
				switch( option ){
				case 1:
					print("Enter view name: ");
					String viewName = sc.next();
					int output = st.executeUpdate("CREATE VIEW " + viewName + " AS SELECT aname, country from author");
					print("Successful");
					break;
					
				case 2:
					print("Enter view name: ");
					String viewName1 = sc.next();
					try{
						int output1 = st.executeUpdate("UPDATE " + viewName1 + " SET aname = 'HELLO' WHERE country = 'USA'");
						print("Successful");
					}
					catch(Exception e){
						e.printStackTrace();
						return false;
					}
					break;
					
				case 3:
					print("Enter view name: ");
					String viewName2 = sc.next();
					try{
						int output2 = st.executeUpdate("DROP VIEW " + viewName2 );
						System.out.println("Successful");
					}
					catch( Exception e){
						e.printStackTrace();
						return false;
					}
					break;
				
				case 4:
					print("Enter view name to be displayed: ");
					String viewName3 = sc.next();
					try{
						ResultSet res = st.executeQuery("SELECT * FROM " + viewName3);
						while(res.next()){
							System.out.println(res.getString("aname") + " : " + res.getString("country"));
						}
					}
					catch( Exception e){
						e.printStackTrace();
					}
					break;
					
				case 5:
					print("Complex view name: ");
					String compview = sc.next();
					try{
						int output3 = st.executeUpdate("CREATE VIEW " + compview + " AS SELECT book.isbn, author.aname FROM book natural join author");
						ResultSet rs = st.executeQuery("SELECT * FROM " + compview);
						while(rs.next()){
							System.out.println(rs.getInt("isbn") + " : " + rs.getString("aname"));
						}
						print("Successful");
					}
					catch (Exception e){
						e.printStackTrace();
					}
					break;
				
				}
				print("Enter y to repeat else n: ");
				choice = sc.next();
			
			}
		}
		catch( Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void main(String args[]){
		String url = "jdbc:mysql://10.10.15.202:3306/";
		String dbName = "te3149db";
		String driver = "com.mysql.jdbc.Driver";
		String username = "te3149";
		String password = "te3149";
		
		// 
		
		MySql ob1 = new MySql(url, dbName, driver, username, password);
		boolean output = true;
		String mainMenu = "1.View\n2.Index\n3.Sequence";
		Scanner sc = new Scanner(System.in);
		int choice;
		String repeat = "y";
		while(repeat.equalsIgnoreCase("y")){
			System.out.println(mainMenu);
			choice = sc.nextInt();
			switch(choice){
			case 1:
				ob1.viewMethods();
				break;
			case 2:
				ob1.indexing();
				break;
			case 3:
				ob1.sequencing();
				break;
			default:
				System.out.println("Wrong choice");
			}
			System.out.println("Repeat? [Y/n]: ");
			repeat = sc.next();
		}
		
	}
}
