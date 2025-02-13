package org.example.Hibernate.Metodos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.Datos.Equipos;
import org.example.Datos.Xogadores;
import org.example.Hibernate.Utilidad.Utilidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MetodosEnConjunto {

    public void insertarEquipos(Equipos equipo){

        try(Session session = Utilidad.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
                session.save(equipo);
                transaction.commit();
                System.out.println("Equipo insertado correctamente");
        } catch (Exception e){
            System.out.println("Error al insertar equipo "+e.getMessage());
        }
    }

    public void insertarXogadores(String nome, String apelido, String posicion, Date data_nacemento, String nacionalidade, int idequipo){
        try(Session session = Utilidad.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

                Equipos equipo = session.get(Equipos.class, idequipo);

                if(equipo !=null){
                    Xogadores xogadores = new Xogadores();
                    xogadores.setNome(nome);
                    xogadores.setApellidos(apelido);
                    xogadores.setPosicion(posicion);
                    xogadores.setData_nacemento(data_nacemento);
                    xogadores.setNacionalidade(nacionalidade);
                    xogadores.setIdequipo(equipo);
                    session.save(xogadores);
                    transaction.commit();

                }
                System.out.println("Xogador insertado correctamente");

        } catch (Exception e){
            System.out.println("Error al insertar xogador "+e.getMessage());
        }
    }

    public void borrarTablas(){
        try(Session session = Utilidad.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from Xogadores").executeUpdate();
            session.createQuery("delete from Equipos").executeUpdate();
            NativeQuery<?> query = session.createNativeQuery("ALTER SEQUENCE equipos_id_equipo_seq RESTART WITH 1");
            NativeQuery<?> query2 = session.createNativeQuery("ALTER SEQUENCE xogadores_id_xogador_seq RESTART WITH 1");
            query.executeUpdate();
            query2.executeUpdate();
            transaction.commit();
            System.out.println("Tablas borradas correctamente");
        } catch (Exception e){
            System.out.println("Error al borrar tablas "+e.getMessage());
        }
    }

    public List<Equipos> listarEquipos(){
        List<Equipos> equipos = null;
        try(Session session = Utilidad.getSessionFactory().openSession()){
            Query<Equipos> query = session.createQuery("from Equipos", Equipos.class);
            equipos = query.list();
            equipos.forEach(System.out::println);
        } catch (Exception e){
            System.out.println("Error al listar equipos "+e.getMessage());
        }
        return equipos;
    }

    public List<Xogadores> listarXogadores(){
        List<Xogadores> xogadores = null;
        try(Session session = Utilidad.getSessionFactory().openSession()){
            Query<Xogadores> query = session.createQuery("from Xogadores", Xogadores.class);
            xogadores = query.list();
            xogadores.forEach(System.out::println);
        } catch (Exception e){
            System.out.println("Error al listar xogadores "+e.getMessage());
        }
        return xogadores;
    }

    public void exportarEquiposAJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try (Session session = Utilidad.getSessionFactory().openSession()) {
            List<Equipos> equipos = session.createQuery("from Equipos", Equipos.class).list();
            objectMapper.writeValue(new File(filePath), equipos);

            System.out.println("Equipos exportados correctamente a " + filePath);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al exportar equipos: " + e.getMessage());
        }
    }


    public void exportarXogadoresAJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd"));
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try (Session session = Utilidad.getSessionFactory().openSession()) {
            List<Xogadores> xogadores = session.createQuery("from Xogadores", Xogadores.class).list();

            // Convertir a una estructura adecuada para MongoDB
            List<Map<String, Object>> xogadoresJson = xogadores.stream().map(x -> {
                Map<String, Object> xogadorMap = new java.util.HashMap<>();
                xogadorMap.put("id_xogador", x.getId_xogador());
                xogadorMap.put("nome", x.getNome());
                xogadorMap.put("apellidos", x.getApellidos());
                xogadorMap.put("posicion", x.getPosicion());
                xogadorMap.put("data_nacemento", x.getData_nacemento().toString()); // Fecha como String
                xogadorMap.put("nacionalidade", x.getNacionalidade());
                xogadorMap.put("idEquipo", x.getIdequipo().getId_equipo()); // Solo el ID del equipo

                return xogadorMap;
            }).toList();

            objectMapper.writeValue(new File(filePath), xogadoresJson);
            System.out.println("Xogadores exportados correctamente a " + filePath);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al exportar xogadores: " + e.getMessage());
        }
    }


    public <T> void leerJson(String filePath, Class<T[]> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            T[] objetos = objectMapper.readValue(new File(filePath), clazz);
            List<T> lista = Arrays.asList(objetos);
            Arrays.stream(objetos).forEach(System.out::println);

            System.out.println("Datos leídos correctamente desde " + filePath);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }


}
