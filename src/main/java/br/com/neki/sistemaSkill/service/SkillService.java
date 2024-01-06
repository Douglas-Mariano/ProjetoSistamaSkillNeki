package br.com.neki.sistemaSkill.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.sistemaSkill.dto.skill.SkillRequestDTO;
import br.com.neki.sistemaSkill.dto.skill.SkillResponseDTO;
import br.com.neki.sistemaSkill.model.Skill;
import br.com.neki.sistemaSkill.model.exceptions.ResourceNotFoundException;
import br.com.neki.sistemaSkill.repository.SkillRepository;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SkillResponseDTO> obterTodos() {
        List<Skill> skills = skillRepository.findAll();

        return skills.stream()
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
