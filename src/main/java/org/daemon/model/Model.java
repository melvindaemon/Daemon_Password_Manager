package org.daemon.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import io.github.cdimascio.dotenv.Dotenv;

public class Model {
    final Dotenv dotenv;
    public Model(String password, String account, String username) {
        dotenv = Dotenv.load();

        try(MongoClient client = MongoClients.create(dotenv.get("MONGODB_URI"))) {
            MongoDatabase passwordManager = client.getDatabase("password_manager");

            System.out.println("Database connected...");

            MongoCollection<Document> entries = passwordManager.getCollection("entries");
            Document entry = new Document().append("password", password).append("account", account).append("username", username);

            entries.insertOne(entry);

            System.out.println("New entry...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
