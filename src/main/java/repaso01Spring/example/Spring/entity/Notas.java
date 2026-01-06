package repaso01Spring.example.Spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Notas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNota;

    @NotBlank (message = "Debe ir nota 1")
    @DecimalMin( value = "0", message = "nota minima 0")
    @DecimalMax( value = "10", message = "nota maxima 10")
    private Float nota1;

    @NotBlank (message = "Debe ir nota 2")
    @DecimalMin( value = "0", message = "nota minima 0")
    @DecimalMax( value = "10", message = "nota maxima 10")
    private Float nota2;

    @NotBlank ( message = "Nota de comportamiento 1 - 10 ")
    @Min(value = 0, message = "comporamientmeino minimo 1")
    @Max(value = 10, message = "Comporamtineto maximo 10")
    private Integer notaCom;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente clienteDatos;



    private LocalDateTime fechaIngresoNota;


}
