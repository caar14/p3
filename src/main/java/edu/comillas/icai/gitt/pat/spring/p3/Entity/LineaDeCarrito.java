package edu.comillas.icai.gitt.pat.spring.p3.Entity;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class LineaDeCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne@JoinColumn(name = "carrito_id", referencedColumnName = "id", nullable = false) public Carrito carrito;
    @Column(nullable = false) public Long articuloId;
    @Column(nullable = false) public Float precio_unitario;
    @Column(nullable = false) public Integer unidades;
    @Column(nullable = false) public Float coste_total;




}
