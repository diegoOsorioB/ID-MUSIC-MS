package MS_Clientes.Clientes.service;

import MS_Clientes.Clientes.dto.ClienteResponse;
import MS_Clientes.Clientes.dto.EstadoRepublicaDTO;
import MS_Clientes.Clientes.entity.Cliente;
import MS_Clientes.Clientes.excepction.Excepciones;
import MS_Clientes.Clientes.repository.ClienteRepository;
import MS_Clientes.Clientes.repository.EstadoRepublicaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService
{
   private ClienteRepository clienteRepository;
    private RestTemplate restTemplate;

    public ClienteService(ClienteRepository clienteRepository, RestTemplate restTemplate)
    {
       this.restTemplate = restTemplate;
       this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getClientes()
    {

        try {
            return clienteRepository.findAll();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Cliente getClienteById(int id)
    {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new Excepciones.ClienteNotFoundException(id));
    }

    public Cliente crear(Cliente cliente)
    {
        try {
            return clienteRepository.save(cliente);
        }catch (Exception e)
        {
            throw new Excepciones.DatabaseException("Ocurrio un error al registrarse"+e.getMessage());
        }
    }

    public Cliente actualizar(Cliente cliente)
    {
        if (!clienteRepository.existsById(cliente.getId_clientes()))
        {
            throw new Excepciones.ClienteNotFoundException(cliente.getId_clientes());
        }
        try {
            return clienteRepository.save(cliente);
        }catch (Exception e)
        {
            throw new Excepciones.DatabaseException("Ocurrio un error al actualizar"+e.getMessage());
        }
    }



    public void  eliminar(int id)
    {
        if (!clienteRepository.existsById(id))
        {
            throw new Excepciones.ClienteNotFoundException(id);
        }
        try {
            clienteRepository.deleteById(id);
        }catch (Exception e)
        {
            throw new Excepciones.DatabaseException("Ocurrio un error al eliminar"+e.getMessage());
        }
    }

    public ClienteResponse obtenerClienteCompleto(int id)
    {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new Excepciones.ClienteNotFoundException(id));
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId_cliente(cliente.getId_clientes());
        clienteResponse.setNombre(cliente.getNombre());
        clienteResponse.setCorreo(cliente.getCorreo());
        clienteResponse.setTelefono(cliente.getTelefono());

        clienteResponse.setEstado(new EstadoRepublicaDTO()
                                  {{
                                      setId_estado(cliente.getEstado().getId_estado());
                                      setNombre(cliente.getEstado().getNombre());
                                      setStatus(cliente.getEstado().getStatus());
                                  }}
        );

        return clienteResponse;
    }

    public Cliente getClienteByCorreo(String correo) {
        return clienteRepository.findByCorreo(correo)
                .orElseThrow(() -> new Excepciones.ClienteNotFoundException(
                        "No se encontró cliente con el correo: " + correo));
    }

}
