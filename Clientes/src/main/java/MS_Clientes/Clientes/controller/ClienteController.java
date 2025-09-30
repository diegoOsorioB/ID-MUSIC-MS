package MS_Clientes.Clientes.controller;

import MS_Clientes.Clientes.dto.ClienteResponse;
import MS_Clientes.Clientes.entity.Cliente;
import MS_Clientes.Clientes.repository.ClienteRepository;
import MS_Clientes.Clientes.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.ok(clienteService.getClientes());
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable int id) {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }
*/
    @GetMapping("/{correo}")
    public ResponseEntity<Cliente> getClienteByCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(clienteService.getClienteByCorreo(correo));
    }


    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.crear(cliente));
    }

    @PutMapping
    public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizar(cliente));
    }

    @DeleteMapping("/borrar/{id}")
    public void deleteById(@PathVariable int id) {
        clienteService.eliminar(id);
    }

    @GetMapping("/todo/todo")
    public ClienteResponse obtenerClienteResponseById(@PathVariable int id) {
        return clienteService.obtenerClienteCompleto(id);
    }
}
