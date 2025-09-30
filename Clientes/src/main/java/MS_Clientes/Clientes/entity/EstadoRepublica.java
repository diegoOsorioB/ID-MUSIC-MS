package MS_Clientes.Clientes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estados_republica")
public class EstadoRepublica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_estado;
    private String nombre;
    private String status;

}
