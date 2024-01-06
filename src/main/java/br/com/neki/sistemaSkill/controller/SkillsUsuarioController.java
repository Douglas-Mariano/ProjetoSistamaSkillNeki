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

import br.com.neki.sistemaSkill.dto.skillsUsuario.SkillsUsuarioRequestDTO;
import br.com.neki.sistemaSkill.dto.skillsUsuario.SkillsUsuarioResponseDTO;
import br.com.neki.sistemaSkill.service.SkillsUsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/skillsUsuario")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
public class SkillsUsuarioController {
    
    @Autowired
    private SkillsUsuarioService skillsUsuarioService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<SkillsUsuarioResponseDTO>> obterTodos() {
        return ResponseEntity.ok(skillsUsuarioService.obterTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<SkillsUsuarioResponseDTO> obterPorId(@PathVariable long id) {
        return ResponseEntity.ok(skillsUsuarioService.obterPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<SkillsUsuarioResponseDTO> adicionar(@RequestBody SkillsUsuarioRequestDTO skillsUsuario) {
        SkillsUsuarioResponseDTO skillsUsuarioAdicionado = skillsUsuarioService.adcionar(skillsUsuario);

        return ResponseEntity
                .status(201)
                .body(skillsUsuarioAdicionado);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<SkillsUsuarioResponseDTO> atualizar(@PathVariable long id, @RequestBody SkillsUsuarioRequestDTO skillsUsuario) {
        SkillsUsuarioResponseDTO skillsUsuarioAtualizado = skillsUsuarioService.atualizar(id, skillsUsuario);

        return ResponseEntity
                .status(201)
                .body(skillsUsuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        skillsUsuarioService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }
}
