package repaso01Spring.example.Spring.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class administrador {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idAdmin;

    @NotBlank(message = "Ingrese el nombre")
    @Size ( min = 1 , max = 50, message = "El nombre es entre 1 - 50 caracteres")
    private String nombreAdmin;

    @NotBlank(message = "Ingrese su peso")
    @DecimalMin( value = "5" , message = "peso minimo 5")
    @DecimalMax(value = "300", message = " peso maximo 300")
    private Float pedoAdmin;

    @NotBlank(message = "Debe ingresar su altura")
    @Min( value = 5, message = "altura minima 5")
    @Max(value = 180 , message = "altura maxima 180")
    private Integer alturaAdmin;

}
