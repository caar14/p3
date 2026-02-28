package edu.comillas.icai.gitt.pat.spring.p3.Repositorio;

import edu.comillas.icai.gitt.pat.spring.p3.Entity.Carrito;
import org.springframework.data.repository.CrudRepository;

public interface RepoCarritos extends CrudRepository<Carrito,Long> {


    Carrito findByUsuarioId(String usuarioId);
}
