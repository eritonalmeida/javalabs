package app.config;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MongodbConfig {

    private static final Morphia morphia = new Morphia();

    public static Datastore getDatastore(String dbStore) {
        switch (dbStore) {

            case "production":
                return morphia.createDatastore(new MongoClient("192.168.1.10"), "production");

            default:
                return morphia.createDatastore(new MongoClient("localhost"), "morphia_test");
        }
    }
}
