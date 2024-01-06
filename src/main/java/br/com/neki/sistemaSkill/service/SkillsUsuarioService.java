package br.com.neki.sistemaSkill.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.neki.sistemaSkill.dto.skillsUsuario.SkillsUsuarioRequestDTO;
import br.com.neki.sistemaSkill.dto.skillsUsuario.SkillsUsuarioResponseDTO;
import br.com.neki.sistemaSkill.model.SkillsUsuario;
import br.com.neki.sistemaSkill.model.Usuario;
import br.com.neki.sistemaSkill.model.exceptions.ResourceNotFoundException;
import br.com.neki.sistemaSkill.repository.SkillsUsuarioRepository;
import br.com.neki.sistemaSkill.repository.UsuarioRepository;

@Service
public class SkillsUsuarioService {

    @Autowired
    private SkillsUsuarioRepository skillsUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SkillsUsuarioResponseDTO> obterTodos() {
        List<SkillsUsuario> skillsUsuarios = skillsUsuarioRepository.findAll();

        return skillsUsuarios.stream()
                .map(skillsUsuario -> modelMapper.map(skillsUsuario, SkillsUsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    public SkillsUsuarioResponseDTO obterPorId(Long id) {
        Optional<SkillsUsuario> optSkillsUsuario = skillsUsuarioRepository.findById(id);

        if (optSkillsUsuario.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum registro encontardo para o id: " + id);
        }
        return modelMapper.map(optSkillsUsuario.get(), SkillsUsuarioResponseDTO.class);
    }

    @Transactional
    public SkillsUsuarioResponseDTO adcionar(SkillsUsuarioRequestDTO skillsUsuarioRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuarioLogado = authentication.getName();

        Optional<Usuario> optUsuario = usuarioRepository.findByLogin(usuarioLogado);
        if (optUsuario.isEmpty()) {
            throw new BadCredentialsException("Usuário ou senha invalidos");
        }

        Usuario usuario = optUsuario.get();

        SkillsUsuario skillsUsuario = modelMapper.map(skillsUsuarioRequest, SkillsUsuario.class);

        skillsUsuario.setId(0L);

        skillsUsuario.setUsuario(usuario);

        skillsUsuario = skillsUsuarioRepository.save(skillsUsuario);

        return modelMapper.map(skillsUsuario, SkillsUsuarioResponseDTO.class);
    }

    public SkillsUsuarioResponseDTO atualizar(Long id, SkillsUsuarioRequestDTO skillsUsuarioRequest) {

        obterPorId(id);

        SkillsUsuario skillsUsuario = modelMapper.map(skillsUsuarioRequest, SkillsUsuario.class);
        skillsUsuario.setId(id);

        return modelMapper.map(skillsUsuarioRepository.save(skillsUsuario), SkillsUsuarioResponseDTO.class);
    }

    public void deletar(long id) {

        obterPorId(id);

        skillsUsuarioRepository.deleteById(id);

    }

}
