package org.daemon.model;

import org.daemon.utils.UserInterface;

import com.mongodb.client.*;
import org.bson.Document;
import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.table.DefaultTableModel;

public class Model extends UserInterface {
    Dotenv DOTENV;
    MongoDatabase passwordManager;
    MongoCollection<Document> entries;
    Document entry;
    FindIterable<Document> data;

    public Model() {
        super();
        DOTENV = Dotenv.load();
    }

    public void newEntry(String password, String account, String username) {
        try(MongoClient client = MongoClients.create(DOTENV.get("MONGODB_URI"))) {
            passwordManager = client.getDatabase("password_manager");

            System.out.println("Database connected...");

            entries = passwordManager.getCollection("entries");
            entry = new Document().append("password", password).append("account", account).append("username", username);

            entries.insertOne(entry);

            System.out.println("New entry...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getEntries() {
        String[] columns = {"id", "Account", "Username", "Password"};

        try(MongoClient client = MongoClients.create(DOTENV.get("MONGODB_URI"))) {
            passwordManager = client.getDatabase("password_manager");

            System.out.println("Database connected... METHOD::getEntries()");

            entries = passwordManager.getCollection("entries");
            data = entries.find();
            model = new DefaultTableModel(columns, 0);

            for(Document d : data) {
                model.addRow(new Object[]{d.getObjectId("_id").toHexString(), d.getString("account"), d.getString("username"), d.getString("password")});
            }

            // display password

            setViewPasswordFrame();

            System.out.println("Collection retrieved...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
