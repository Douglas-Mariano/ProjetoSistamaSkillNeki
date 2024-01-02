package br.com.neki.sistemaSkill.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistemaSkill.dto.skill.SkillRequestDTO;
import br.com.neki.sistemaSkill.dto.skill.SkillResponseDTO;
import br.com.neki.sistemaSkill.service.SkillService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/skills")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<SkillResponseDTO>> obterTodos() {
        return ResponseEntity.ok(skillService.obterTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<SkillResponseDTO> obterPorId(@PathVariable long id) {
        return ResponseEntity.ok(skillService.obterPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<SkillResponseDTO> adicionar(@RequestBody SkillRequestDTO skill) {
        SkillResponseDTO skillAdicionado = skillService.adcionar(skill);

        return ResponseEntity
                .status(201)
                .body(skillAdicionado);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<SkillResponseDTO> atualizar(@PathVariable long id, @RequestBody SkillRequestDTO skill) {
        SkillResponseDTO skillAtualizado = skillService.atualizar(id, skill);

        return ResponseEntity
                .status(201)
                .body(skillAtualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        skillService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

}
