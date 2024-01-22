package br.com.neki.sistemaSkill.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.neki.sistemaSkill.dto.skill.SkillRequestDTO;
import br.com.neki.sistemaSkill.dto.skill.SkillResponseDTO;
import br.com.neki.sistemaSkill.dto.usuario.UsuarioResponseDTO;
import br.com.neki.sistemaSkill.model.Skill;
import br.com.neki.sistemaSkill.model.Usuario;
import br.com.neki.sistemaSkill.model.exceptions.ResourceNotFoundException;
import br.com.neki.sistemaSkill.repository.SkillRepository;
import br.com.neki.sistemaSkill.repository.UsuarioRepository;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;

    public List<SkillResponseDTO> obterTodos() {
        List<Skill> skills = skillRepository.findAll();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuarioLogado = authentication.getName();

        Optional<Usuario> optUsuario = usuarioRepository.findByLogin(usuarioLogado);
        if (optUsuario.isEmpty()) {
            throw new BadCredentialsException("Usuário ou senha invalidos");
        }

        UsuarioResponseDTO usuario = usuarioService.obterPorId(optUsuario.get().getId());

        Set<String> nomesSkillsUsuario = usuario.getSkills().stream()
                .map(skillUsuario -> skillUsuario.getSkills().getNome())
                .collect(Collectors.toSet());

        List<Skill> skillsNaoPossuidas = skills.stream()
                .filter(skill -> !nomesSkillsUsuario.contains(skill.getNome()))
                .collect(Collectors.toList());

        return skillsNaoPossuidas.stream()
                .map(skill -> modelMapper.map(skill, SkillResponseDTO.class))
                .collect(Collectors.toList());
    }

    public SkillResponseDTO obterPorId(Long id) {
        Optional<Skill> optSkill = skillRepository.findById(id);

        if (optSkill.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum registro encontardo para o id: " + id);
        }
        return modelMapper.map(optSkill.get(), SkillResponseDTO.class);
    }

    @Transactional
    public SkillResponseDTO adcionar(SkillRequestDTO skillRequest) {

        Skill skill = modelMapper.map(skillRequest, Skill.class);

        skill.setId(0L);

        skill = skillRepository.save(skill);

        return modelMapper.map(skill, SkillResponseDTO.class);
    }

    public SkillResponseDTO atualizar(Long id, SkillRequestDTO skillRequest) {

        obterPorId(id);

        Skill skill = modelMapper.map(skillRequest, Skill.class);
        skill.setId(id);

        return modelMapper.map(skillRepository.save(skill), SkillResponseDTO.class);
    }

    public void deletar(long id) {

        obterPorId(id);

        skillRepository.deleteById(id);

    }

}
