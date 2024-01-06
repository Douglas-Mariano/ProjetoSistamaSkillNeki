package br.com.neki.sistemaSkill.dto.usuario;

import java.util.List;

import br.com.neki.sistemaSkill.dto.skillsUsuario.SkillsUsuarioResponseDTO;

public class UsuarioResponseDTO extends UsuarioBaseDTO {

    private long id;
    
    private List<SkillsUsuarioResponseDTO> skills;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SkillsUsuarioResponseDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsUsuarioResponseDTO> skills) {
        this.skills = skills;
    }

}
