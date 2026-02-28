package edu.comillas.icai.gitt.pat.spring.p3.Repositorio;

import edu.comillas.icai.gitt.pat.spring.p3.Entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.p3.Entity.LineaDeCarrito;
import org.springframework.data.repository.CrudRepository;

public interface RepoLinea extends CrudRepository<LineaDeCarrito,Long> {

    LineaDeCarrito findByArticuloIdAndCarrito(Long articuloId, Carrito carrito);
}
