package org.example.MongoDB.Metodos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MetodosMongo {

    public void importarJSON(String filePath, MongoCollection<Document> collection){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Map<String, Object>[] jsonObject = mapper.readValue(new File(filePath), Map[].class);

            for(Map<String, Object> doc : jsonObject){
                collection.insertOne(new Document(doc));
            }
            System.out.println("Datos importados correctamente");
        } catch (IOException e){
            System.out.println("Error al importar los documentos: " + e.getMessage());
        }

    }

    public void leerDatos(MongoCollection<Document> collection){

        try(MongoCursor<Document> cursor = collection.find().iterator()){
            System.out.println("Documentos en la colección:");
            while(cursor.hasNext()){
                Document doc = cursor.next();
                System.out.println(doc.toJson());
            }
        }catch (Exception e){
            System.err.println("Error al leer los documentos: " + e.getMessage());
        }
    }

}
