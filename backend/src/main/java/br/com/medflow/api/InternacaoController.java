package br.com.medflow.api;

import br.com.medflow.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/internacoes")
public class InternacaoController {
    @GetMapping public Collection<Object> listar() { return Store.internacoes.values(); }
    @GetMapping("/{id}") public Internacao buscar(@PathVariable UUID id) { return PacienteController.required(Store.internacoes, id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Internacao criar(@RequestBody Internacao internacao) {
        PacienteController.required(Store.pacientes, internacao.getPacienteId());
        var quarto = (Quarto) PacienteController.required(Store.quartos, internacao.getQuartoId());
        if (!quarto.isDisponivel()) throw new IllegalStateException("Quarto indisponivel");
        quarto.setDisponivel(false); Store.internacoes.put(internacao.getId(), internacao); return internacao;
    }
    @PatchMapping("/{id}/alta") public Internacao alta(@PathVariable UUID id) {
        var internacao = buscar(id); internacao.darAlta();
        var quarto = (Quarto) PacienteController.required(Store.quartos, internacao.getQuartoId()); quarto.setDisponivel(true);
        return internacao;
    }
}
