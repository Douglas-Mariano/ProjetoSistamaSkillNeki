
package br.com.neki.sistemaSkill.dto.usuario;

import java.util.List;

import br.com.neki.sistemaSkill.dto.skill.SkillResponseDTO;

public class UsuarioResponseDTO extends UsuarioBaseDTO {

    private long id;
    private List<SkillResponseDTO> skills;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SkillResponseDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillResponseDTO> skills) {
        this.skills = skills;
    }

}
