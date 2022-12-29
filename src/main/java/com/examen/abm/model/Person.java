package com.examen.abm.model;

import com.examen.abm.enums.TipoDocumento;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persona")
@EntityListeners(AuditingEntityListener.class)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "apellido", nullable = false)
    private String perApellido;

    @Column(name = "nombre", nullable = false)
    private String perNombre;

    @Column(name = "tipo_documento", nullable = false)
    private TipoDocumento perTipoDocumento;

    @Column(name = "numero_documento", nullable = false)
    private Long perNumeroDocumento;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date perFechaNacimiento;

    public Person() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerApellido() {
        return perApellido;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public TipoDocumento getPerTipoDocumento() {
        return perTipoDocumento;
    }

    public void setPerTipoDocumento(TipoDocumento perTipoDocumento) {
        this.perTipoDocumento = perTipoDocumento;
    }

    public Long getPerNumeroDocumento() {
        return perNumeroDocumento;
    }

    public void setPerNumeroDocumento(Long perNumeroDocumento) {
        this.perNumeroDocumento = perNumeroDocumento;
    }

    public Date getPerFechaNacimiento() {
        return perFechaNacimiento;
    }

    public void setPerFechaNacimiento(Date perFechaNacimiento) {
        this.perFechaNacimiento = perFechaNacimiento;
    }
}
