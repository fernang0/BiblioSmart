package cl.bibliosmart.bibliosmart.modules.catalogo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "libro")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autores")
    private String autores;

    @Column(name = "editorial")
    private String editorial;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "motivo_baja")
    private String motivoBaja;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name= "fecha_baja")
    private Date fechaBaja;}
