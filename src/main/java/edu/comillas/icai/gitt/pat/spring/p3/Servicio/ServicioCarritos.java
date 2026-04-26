package edu.comillas.icai.gitt.pat.spring.p3.Servicio;

import edu.comillas.icai.gitt.pat.spring.p3.Entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.p3.Entity.LineaDeCarrito;
import edu.comillas.icai.gitt.pat.spring.p3.Repositorio.RepoCarritos;
import edu.comillas.icai.gitt.pat.spring.p3.Repositorio.RepoLinea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioCarritos {

    @Autowired
    RepoCarritos repoCarritos;

    @Autowired
    RepoLinea repoLinea;

    Logger log =  LoggerFactory.getLogger(ServicioCarritos.class);

    public Carrito crea(Carrito carritoNuevo) {

        if (repoCarritos.findByUsuarioId(carritoNuevo.usuarioId) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El carrito ya existe");
        }
        carritoNuevo.precio = 0f;
        repoCarritos.save(carritoNuevo);
        return carritoNuevo;

    }

    public Carrito lee(String usuarioId) {
        Carrito carritoBuscado = repoCarritos.findByUsuarioId(usuarioId);
        if (carritoBuscado == null) {
            log.info("Servicio carritos: Carrito no encontrado");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return (carritoBuscado);
    }

    public Carrito linea(Carrito carrito, LineaDeCarrito linea){

        Carrito carritoBuscado = repoCarritos.findByUsuarioId(carrito.usuarioId);
        LineaDeCarrito lineaCreada = repoLinea.findByArticuloIdAndCarrito(linea.articuloId, carritoBuscado);
        linea.coste_total = linea.precio_unitario*linea.unidades;
            if (carritoBuscado == null) {
                log.info("Servicio carritos: Carrito no encontrado");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            }else if (lineaCreada != null) {

                lineaCreada.unidades += linea.unidades;
                lineaCreada.coste_total += linea.unidades*linea.precio_unitario;
                repoLinea.save(lineaCreada);

            }else{

                linea.carrito = carrito;
                repoLinea.save(linea);

            }

            carritoBuscado.precio += linea.coste_total;
            repoCarritos.save(carritoBuscado);
            return (carritoBuscado);

    }

    public Carrito borraLinea(Carrito carrito, Long articuloId){

        Carrito carritoBuscado = repoCarritos.findByUsuarioId(carrito.usuarioId);
        LineaDeCarrito lineaCreada = repoLinea.findByArticuloIdAndCarrito(articuloId, carritoBuscado);
        if (carritoBuscado == null) {
            log.info("Servicio carritos: Carrito no encontrado");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }else if (lineaCreada != null) {

            log.info(String.valueOf(lineaCreada.coste_total));
            log.info(String.valueOf(carritoBuscado.precio));
            carritoBuscado.precio -= lineaCreada.coste_total;


            repoLinea.delete(lineaCreada);
            repoCarritos.save(carritoBuscado);

        }else{

            log.info("Servicio carritos: Linea no encontrada");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }


        return (carritoBuscado);

    }



    public List<LineaDeCarrito> getLineas(Carrito carrito){

        Iterable<LineaDeCarrito> todas = repoLinea.findAll(); //CRUD devuelve iterable no List
        List<LineaDeCarrito> resultado = new ArrayList<>();

        for (LineaDeCarrito linea: todas){
            if (linea.carrito == carrito){
                resultado.add(linea);
            }
        }

        return resultado;


    }




    public void borra(Carrito carrito) throws Exception {
        try{
            repoCarritos.delete(carrito);
        }catch(Exception e){throw new Exception("Error al realizar la operación de borrado", e);}


    }






}
