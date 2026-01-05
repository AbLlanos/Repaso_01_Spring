package repaso01Spring.example.Spring.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class cliente {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //texto y unique
    @NotBlank(message = "Cédula es requerida")
    @Size(min = 10, max = 10, message = "Cédula debe tener 10 dígitos")
    @Column(unique = true)
    private String cedula;
    //texto
    @NotBlank(message = "Nombre es requerido")
    @Size(min = 2, max = 50, message = "Nombre entre 2-50 caracteres")
    private String nombre;
    //texto con pattern
    @NotBlank(message = "Celular es requerido")
    @Size(min = 10, max = 10, message = "Celular debe tener exactamente 10 dígitos")
    @Pattern(regexp = "^[0-9]{10}$", message = "Celular solo números, 10 dígitos")
    private String celular;
    //Decimal - value entre comillas
    @NotNull(message = "El peso es obligatorio")
    @DecimalMin(value = "5.0", message = "Peso mínimo 5 kg")
    @DecimalMax(value = "200.0", message = "Peso máximo 200 kg")
    private Float peso;
    //Entero
    @NotNull(message = "Altura es obligatoria")
    @Min(value = 50, message = "Altura mínima 50 cm")
    @Max(value = 250, message = "Altura máxima 250 cm")
    private Integer altura;
    //Boolean
    @NotNull(message = "Decisión es obligatoria")
    private Boolean desicion;

    //Fecha - LocalDateTime
    private LocalDateTime fechaActivacion;

}
