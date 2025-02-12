package org.example.Datos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Xogadores")
public class Xogadores{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_xogador")
    private int id_xogador;

    @Column(name = "nome",length = 100)
    private String nome;

    @Column(name = "apelidos",length = 100)
    private String apellidos;

    @Column(name = "posicion", length = 100)
    private String posicion;

    @Column(name = "data_nacemento")
    private Date data_nacemento;

    @Column(name = "nacionalidade", length = 100)
    private String nacionalidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_equipo",foreignKey = @ForeignKey(name = "xogadores_id_equipo_fkey"))
    @JsonProperty("equipo")
    @JsonBackReference
    private Equipos idequipo;


    public Xogadores() {
    }

    public Xogadores(String nome, String apellidos, String posicion, Date data_nacemento, String nacionalidade, Equipos id_equipo) {
        this.nome = nome;
        this.apellidos = apellidos;
        this.posicion = posicion;
        this.data_nacemento = data_nacemento;
        this.nacionalidade = nacionalidade;
        this.idequipo = id_equipo;
    }

    public int getId_xogador() {
        return id_xogador;
    }

    public void setId_xogador(int id_xogador) {
        this.id_xogador = id_xogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Date getData_nacemento() {
        return data_nacemento;
    }

    public void setData_nacemento(Date data_nacemento) {
        this.data_nacemento = data_nacemento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Equipos getIdequipo() {
        return idequipo;
    }

    public void setIdequipo(Equipos idequipo) {
        this.idequipo = idequipo;
    }
}
