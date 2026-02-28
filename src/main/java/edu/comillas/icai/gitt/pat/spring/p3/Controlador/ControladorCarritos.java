package edu.comillas.icai.gitt.pat.spring.p3.Controlador;


import edu.comillas.icai.gitt.pat.spring.p3.Entity.Carrito;
import edu.comillas.icai.gitt.pat.spring.p3.Entity.LineaDeCarrito;
import edu.comillas.icai.gitt.pat.spring.p3.Servicio.ServicioCarritos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ControladorCarritos {

    @Autowired
    ServicioCarritos servicioCarritos;



    @PostMapping("/api/carritos") @ResponseStatus(HttpStatus.CREATED)
    public Carrito crea(@RequestBody Carrito carrito) {

       servicioCarritos.crea(carrito);
       return carrito;

    }


    @GetMapping("/api/carritos/{usuarioId}")
    public Carrito lee(@PathVariable String usuarioId) {
        //Usuario user =  servicioContadores.autentica(credenciales);
        return servicioCarritos.lee(usuarioId);

    }

    @PutMapping("/api/carritos/{nombre}/linea")
    public Carrito addlinea(@RequestBody LineaDeCarrito lineaNueva, @PathVariable String nombre){


        Carrito carrito = servicioCarritos.lee(nombre);
        return servicioCarritos.linea(carrito,lineaNueva);

    }


    @DeleteMapping("/api/carritos/{nombre}")
    public void borra(@PathVariable String nombre) {


        Carrito carrito = servicioCarritos.lee(nombre);
        try {
            servicioCarritos.borra(carrito);
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }


    }
}


