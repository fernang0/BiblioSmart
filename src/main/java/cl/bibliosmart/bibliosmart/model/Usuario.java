package cl.bibliosmart.bibliosmart.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rut")
    private String rut;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasena")
    private String contrasena;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol;


    @Column(name= "estado_cuenta")
    private String estadoCuenta;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name= "fecha_registro")
    private Date fechaRegistro;
    
    @Column(name = "deuda")
    private Integer deuda;

}
