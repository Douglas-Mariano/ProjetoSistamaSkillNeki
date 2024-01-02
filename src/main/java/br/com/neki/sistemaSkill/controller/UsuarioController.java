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

import br.com.neki.sistemaSkill.dto.usuario.UsuarioLoginRequestDTO;
import br.com.neki.sistemaSkill.dto.usuario.UsuarioLoginResponseDTO;
import br.com.neki.sistemaSkill.dto.usuario.UsuarioRequestDTO;
import br.com.neki.sistemaSkill.dto.usuario.UsuarioResponseDTO;
import br.com.neki.sistemaSkill.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> obterTodos() {
        return ResponseEntity.ok(usuarioService.obterTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable long id) {
        return ResponseEntity.ok(usuarioService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO usuarioAdicionado = usuarioService.adcionar(usuario);

        return ResponseEntity
                .status(201)
                .body(usuarioAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable long id, @RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(id, usuario);

        return ResponseEntity
                .status(201)
                .body(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        usuarioService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponseDTO> logar(@RequestBody UsuarioLoginRequestDTO usuariologinRequest) {

        UsuarioLoginResponseDTO usuarioLogado = usuarioService.logar(usuariologinRequest.getLogin(),
                usuariologinRequest.getSenha());

        return ResponseEntity
                .status(200)
                .body(usuarioLogado);
    }

}
