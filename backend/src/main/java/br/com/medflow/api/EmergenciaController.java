package br.com.medflow.api;

import br.com.medflow.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController @RequestMapping("/emergencias")
public class EmergenciaController {
    @GetMapping public Collection<Object> listar(@RequestParam(required=false) StatusAtendimento status) {
        return Store.emergencias.values().stream().filter(e -> status == null || ((AtendimentoEmergencia)e).getStatus() == status).collect(Collectors.toList());
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public AtendimentoEmergencia criar(@RequestBody AtendimentoEmergencia e) {
        PacienteController.required(Store.pacientes, e.getPacienteId());
        if (e.getPrioridade() == null) throw new IllegalArgumentException("Prioridade e obrigatoria");
        Store.emergencias.put(e.getId(), e); return e;
    }
    @PatchMapping("/{id}/iniciar") public AtendimentoEmergencia iniciar(@PathVariable UUID id) { var e = buscar(id); e.iniciar(); return e; }
    @PatchMapping("/{id}/concluir") public AtendimentoEmergencia concluir(@PathVariable UUID id) { var e = buscar(id); e.concluir(); return e; }
    private AtendimentoEmergencia buscar(UUID id) { return PacienteController.required(Store.emergencias, id); }
}
