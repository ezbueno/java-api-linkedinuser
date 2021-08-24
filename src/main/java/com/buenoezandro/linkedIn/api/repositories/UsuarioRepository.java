package com.buenoezandro.linkedIn.api.repositories;

import com.buenoezandro.linkedIn.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}