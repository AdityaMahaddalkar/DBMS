import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;


public class MainFile {
	public static void main(String[] args) {
		// Creating a Mongo client 
	      MongoClient mongo = new MongoClient( "10.10.15.202" , 27017 ); 
	   
	      // Creating Credentials 
	      MongoCredential credential; 
	      credential = MongoCredential.createCredential("te3149", "te3149db", 
	         "te3149".toCharArray()); 
	      System.out.println("Connected to the database successfully");  
	      
	      // Accessing the database 
	      MongoDatabase database = mongo.getDatabase("te3149db"); 
	      //System.out.println("Credentials ::"+ credential);
	      
	      /*
	      MongoCollection<Document> collection = database.getCollection("WeatherData"); 
	      System.out.println("Collection myCollection selected successfully");
	      
	   // Getting the iterable object 
	      FindIterable<Document> iterDoc = collection.find(); 
	      int i = 1; 

	      // Getting the iterator 
	      Iterator it = iterDoc.iterator(); 
	    
	      while (it.hasNext()) {  
	         System.out.println(it.next());  
	         i++; 
	      }
	      */
	      
	    //Creating a collection 
	      database.createCollection("sampleCollection"); 
	      System.out.println("Collection created successfully");
	      MongoCollection<Document> collection = database.getCollection("sampleCollection");
	      
	      Document document = new Document("title", "MongoDB") 
	      .append("id", 1)
	      .append("description", "database") 
	      .append("likes", 100) 
	      .append("url", "http://www.tutorialspoint.com/mongodb/") 
	      .append("by", "tutorials point");  
	      collection.insertOne(document); 
	      System.out.println("Document inserted successfully");  
	      
	      FindIterable<Document> iterDoc = collection.find(); 
	      int i = 1; 

	      // Getting the iterator 
	      Iterator it = iterDoc.iterator(); 
	    
	      while (it.hasNext()) {  
	         System.out.println(it.next());  
	         i++; 
	      }
	      
	      // Update the document
	      collection.updateOne(Filters.eq("id", 1), Updates.set("likes", 150));       
	      System.out.println("Document update successfully...");
	      
	      iterDoc = collection.find(); 
	      i = 1; 

	      // Getting the iterator 
	      it = iterDoc.iterator(); 
	    
	      while (it.hasNext()) {  
	         System.out.println(it.next());  
	         i++; 
	      }
	      
	      //Deleting the documents 
	      collection.deleteOne(Filters.eq("id", 1)); 
	      System.out.println("Document deleted successfully...");  
	      
	      iterDoc = collection.find(); 
	      i = 1; 

	      // Getting the iterator 
	      it = iterDoc.iterator(); 
	    
	      while (it.hasNext()) {  
	         System.out.println(it.next());  
	         i++; 
	      }
	}
}
