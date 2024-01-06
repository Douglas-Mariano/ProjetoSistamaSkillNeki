package br.com.neki.sistemaSkill.dto.usuario;

import java.util.Set;

import br.com.neki.sistemaSkill.dto.skillsUsuario.SkillsUsuarioResponseDTO;

public class UsuarioResponseDTO extends UsuarioBaseDTO {

    private long id;
    
    private Set<SkillsUsuarioResponseDTO> skills;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<SkillsUsuarioResponseDTO> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillsUsuarioResponseDTO> skills) {
        this.skills = skills;
    }

}
