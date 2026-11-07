package br.com.medflow.api;

import br.com.medflow.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/procedimentos-cirurgicos")
public class ProcedimentoController {
    @GetMapping public Collection<Object> listar() { return Store.procedimentos.values(); }
    @GetMapping("/{id}") public ProcedimentoCirurgico buscar(@PathVariable UUID id) { return PacienteController.required(Store.procedimentos, id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ProcedimentoCirurgico criar(@RequestBody ProcedimentoCirurgico p) {
        PacienteController.required(Store.pacientes, p.getPacienteId());
        var cirurgiao = (Profissional) PacienteController.required(Store.profissionais, p.getCirurgiaoId());
        if (cirurgiao.getTipo() != TipoProfissional.CIRURGIAO) throw new IllegalArgumentException("Responsavel deve ser cirurgiao");
        Store.procedimentos.put(p.getId(), p); return p;
    }
    @PatchMapping("/{id}/status") public ProcedimentoCirurgico alterarStatus(@PathVariable UUID id, @RequestParam StatusProcedimento status) {
        var p = buscar(id); p.alterarStatus(status); return p;
    }
}
