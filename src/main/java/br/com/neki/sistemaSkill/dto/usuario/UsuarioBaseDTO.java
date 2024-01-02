package br.com.neki.sistemaSkill.dto.usuario;

import br.com.neki.sistemaSkill.enums.Perfil;

public abstract class UsuarioBaseDTO {

    private String nome;
    private Perfil perfil;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

}
