package repaso01Spring.example.Spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Autos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuto;

    //Not blank solo en texto
    @NotBlank(message = "nombre auto")
    @Size(min = 2,max = 10,message = "entre 2 y 10")
    private String nombreAuto;

    @NotNull(message = "peso auto")
    @Min(value = 5, message = "minimo peso auto 5")
    @Max(value = 500, message = "maximo peso auto 500")
    private Integer pesoAuto;

    @NotNull(message = "Kilotraje auto")
    @DecimalMin( value = "0", message = "kilomtreaje minimo 0")
    @DecimalMax( value = "100", message = "kilomtreaje minimo 0")
    private Float kilometraje_auto;

    private boolean nuevo;


    private LocalDateTime fecha_ingreso_auto;



}
