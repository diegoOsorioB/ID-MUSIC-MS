package MS_Clientes.Clientes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_clientes;
    private String nombre;
    private String correo;
    private long telefono;
    @ManyToOne
    @JoinColumn(name = "id_estado",referencedColumnName = "id_estado")
    public EstadoRepublica estado;


}
