package MS_Clientes.Clientes.controller;

import MS_Clientes.Clientes.entity.EstadoRepublica;
import MS_Clientes.Clientes.repository.EstadoRepublicaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
@CrossOrigin("*")
public class EstadoRepublicaController {

    private final EstadoRepublicaRepository estadoRepublicaRepository;

    public EstadoRepublicaController(EstadoRepublicaRepository estadoRepublicaRepository) {
        this.estadoRepublicaRepository = estadoRepublicaRepository;
    }

    @GetMapping
    public List<EstadoRepublica>  obtenerEstados(){
        return estadoRepublicaRepository.findAll();
    }

    @GetMapping("/{id}")
    public EstadoRepublica obtenerEstadoRepublicaById(@PathVariable int id){
        return estadoRepublicaRepository.findById(id).get();
    }

    @PostMapping
    public EstadoRepublica crearEstadoRepublica(@PathVariable EstadoRepublica estadoRepublica)
    {
        return estadoRepublicaRepository.save(estadoRepublica);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrarEstadoRepublica(@PathVariable int id){
        estadoRepublicaRepository.deleteById(id);
    }
}
