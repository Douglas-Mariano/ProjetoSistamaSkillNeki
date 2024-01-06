package br.com.neki.sistemaSkill.dto.skillsUsuario;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.neki.sistemaSkill.dto.skill.SkillResponseDTO;
import br.com.neki.sistemaSkill.dto.usuario.UsuarioResponseDTO;

public class SkillsUsuarioRequestDTO {

    private Integer level;

    @JsonIgnore
    private UsuarioResponseDTO usuario;

    private SkillResponseDTO skill;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }

    public SkillResponseDTO getSkills() {
        return skill;
    }

    public void setSkills(SkillResponseDTO skill) {
        this.skill = skill;
    }

}
