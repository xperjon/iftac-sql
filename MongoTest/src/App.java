import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import se.xperjon.domain.Person;

import static com.mongodb.client.model.Filters.*;

import java.time.LocalDate;
import java.util.Arrays;

public class App {

	public static void main(String[] args) throws JsonProcessingException {
		MongoClientURI connectionStr = new MongoClientURI(
				"mongodb://admin:FRFdvn99235@node69630-mongodb.jls-sto2.elastx.net:11045");
		MongoClient mongoClient = new MongoClient(connectionStr);
		MongoDatabase iftac = mongoClient.getDatabase("iftac");
		MongoCollection<Document> collection = iftac.getCollection("javaoop");
		
		System.out.println("number of documents in db: " + collection.count());

		// Create document
		Document doc = new Document("name", "java2017")
				.append("type", "YH")
				.append("desc","Under vår 2-åriga utbildning Javautvecklare (420 Yh-poäng) får du bland annat specialisera dig inom design, arkitektur och konstruktion av databaser för att bli en spjutspets inom området. Utbildningen innefattar två LIA-perioder (totalt 22 veckor) som förbereder dig för att direkt kunna gå från utbildning till arbete.")
				.append("attendants", Arrays.asList("Emil", "Patrik", "Kristoffer", "Alex"));


		//Skapa java objekt
		Person person = new Person();
		person.setAge(38);
		person.setFirstName("Jon-Erik");
		person.setLastName("Liw");
		person.setBirtDate(LocalDate.of(1979, 04, 8));
		
		//Konvertera till json
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
		//System.out.println(json);
		
		
		Document doc2 = Document.parse(json);
		// collection.insertOne(doc);
		collection.insertOne(doc2);


		collection.updateOne(regex("firstName", "Jon"), new Document("$set", new Document("age",25)));
		
		//collection.deleteOne(eq("firstName","Jon-Erik"));
		
		Document first = collection.find(regex("firstName", "Jon")).first();
		String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(first);
		System.out.println(output);
		
		mongoClient.close();	
	}
}
