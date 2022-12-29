package com.examen.abm.repository;

import com.examen.abm.enums.TipoDocumento;
import com.examen.abm.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT p FROM Person p WHERE (UPPER(CONCAT(p.perNombre,' ',p.perApellido) ) LIKE UPPER(CONCAT('%',:#{#nombre},'%'))) AND p.perTipoDocumento = :#{#tipodoc}")
    List<Person> findPersonaWithPartOfNameAndTipoDoc(@Param("nombre") String nombre, @Param("tipodoc") TipoDocumento tipoDocumento);

    @Query(value = "SELECT p FROM Person p WHERE (UPPER(CONCAT(p.perNombre,' ',p.perApellido) ) LIKE UPPER(CONCAT('%',:#{#nombre},'%')))")
    List<Person> findPersonaWithPartOfName(@Param("nombre") String nombre);

}
