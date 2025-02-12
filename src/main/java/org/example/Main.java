package org.example;

import org.example.Datos.Equipos;
import org.example.Datos.Xogadores;
import org.example.Hibernate.Metodos.MetodosEnConjunto;

public class Main {
    public static void main(String[] args) {

        MetodosEnConjunto metodosEnConjunto = new MetodosEnConjunto();

        Equipos equipo1 = new Equipos("Real Madrid", "Madrid");
        Equipos equipo2 = new Equipos("FCB Barcelona", "Barcelona");
        Equipos equipo3 = new Equipos("Atletico de Madrid", "Madrid");
        Equipos equipos4 = new Equipos("Manchester United", "Manchester");
        Equipos equipos5 = new Equipos("Chelsea", "Londres");
        Equipos equipos6 = new Equipos("Liverpool", "Liverpool");

        /**
        metodosEnConjunto.insertarEquipos(equipo1);
        metodosEnConjunto.insertarEquipos(equipo2);
        metodosEnConjunto.insertarEquipos(equipo3);
        metodosEnConjunto.insertarEquipos(equipos4);
        metodosEnConjunto.insertarEquipos(equipos5);
        metodosEnConjunto.insertarEquipos(equipos6);
        **/

        /**
        metodosEnConjunto.insertarXogadores("Cristiano", "Ronaldo", "Delantero",  Date.valueOf("1985-02-05"), "Portugal", 1);
        metodosEnConjunto.insertarXogadores("Lionel", "Messi", "Delantero",Date.valueOf("1987-05-24"), "Argentina", 2);
        metodosEnConjunto.insertarXogadores("Luis", "Suarez", "Delantero", Date.valueOf("1987-01-24"), "Uruguay", 3);
        metodosEnConjunto.insertarXogadores("David", "De Gea", "Portero", Date.valueOf("1990-11-07"), "España", 4);
        metodosEnConjunto.insertarXogadores("Eden", "Hazard", "Delantero", Date.valueOf("1991-01-07"), "Belgica", 5);
        metodosEnConjunto.insertarXogadores("Mohamed", "Salah", "Delantero", Date.valueOf("1992-06-15"), "Egipto", 6);
        **/

        /**
        metodosEnConjunto.borrarTablas();
        **/

        metodosEnConjunto.listarEquipos();
        metodosEnConjunto.listarXogadores();

        /**
        metodosEnConjunto.exportarEquiposAJson("equipos.json");
        metodosEnConjunto.exportarXogadoresAJson("xogadores.json");
        **/

        String filePathEquipos = "equipos.json";
        String filePathXogadores = "xogadores.json";
        metodosEnConjunto.leerJson(filePathEquipos,Equipos[].class);
        metodosEnConjunto.leerJson(filePathXogadores, Xogadores[].class);



    }
}