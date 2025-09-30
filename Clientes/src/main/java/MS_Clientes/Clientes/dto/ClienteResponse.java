package MS_Clientes.Clientes.dto;

import lombok.Data;

@Data
public class ClienteResponse {

    private int id_cliente;
    private String nombre;
    private long telefono;
    private String correo;
    private EstadoRepublicaDTO estado;
}
