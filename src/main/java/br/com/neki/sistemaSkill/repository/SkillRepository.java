package br.com.neki.sistemaSkill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.sistemaSkill.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    
}
