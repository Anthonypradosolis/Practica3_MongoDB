package org.example.Datos;

import jakarta.persistence.*;


@Entity
@Table(name = "Equipos")
public class Equipos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Integer id_equipo;

    @Column(name = "nome",length = 100)
    private String nome;

    @Column(name = "cidade",length = 100)
    private String cidade;

    public Equipos() {
    }

    public Equipos(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    public Integer getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


}
