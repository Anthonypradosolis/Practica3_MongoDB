package org.example.MongoDB.BaseDatosMongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {

    // URI de la base de datos de MongoDB
    private static final String URI = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "PracticaMongoDB3";
    private MongoDatabase database;
    private MongoClient mongoClient;

    /**
     * Establece la conexión con la base de datos de MongoDB
     * @return Base de datos de MongoDB
     */
    public MongoDatabase connect(){
        MongoClient client = MongoClients.create(URI);
        this.database = client.getDatabase(DATABASE_NAME);
        return this.database;
    }

    /**
     * Devuelve una colección de MongoDB
     * @param collectionName Nombre de la colección
     * @return Colección de MongoDB
     */
    public MongoCollection<Document> getCollection(String collectionName){
        if(database == null){
            throw new IllegalStateException("La conexion no esta establecida");
        }
        return this.database.getCollection(collectionName);
    }

    /**
     * Cierra la conexión con la base de datos de MongoDB
     */
    public void close(){
        if(database != null){
            mongoClient.close();
            System.out.println("Conexion cerrada con MongoDB");
        }
    }
}
