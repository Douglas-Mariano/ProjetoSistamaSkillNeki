package br.com.neki.sistemaSkill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.sistemaSkill.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

}
