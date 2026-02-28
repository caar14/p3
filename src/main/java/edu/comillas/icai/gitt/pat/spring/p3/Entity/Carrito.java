package edu.comillas.icai.gitt.pat.spring.p3.Entity;


import jakarta.persistence.*;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;

    @Column(nullable = false) public String usuarioId;
    @Column(nullable = false) public String correo;
    @Column(nullable = true) public Float precio;
}
