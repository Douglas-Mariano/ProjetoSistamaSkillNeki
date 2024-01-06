package br.com.neki.sistemaSkill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.sistemaSkill.model.SkillsUsuario;

public interface SkillsUsuarioRepository extends JpaRepository<SkillsUsuario, Long> {
    
}
