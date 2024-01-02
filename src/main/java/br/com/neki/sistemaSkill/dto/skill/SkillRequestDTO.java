package br.com.neki.sistemaSkill.dto.skill;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.neki.sistemaSkill.dto.usuario.UsuarioResponseDTO;

public class SkillRequestDTO {

    private String nome;
    private String descricao;
    private Integer level;
    private String imagem;
    @JsonIgnore
    private UsuarioResponseDTO usuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }

}
