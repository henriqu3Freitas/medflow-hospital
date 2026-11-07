package br.com.medflow.api;

import br.com.medflow.domain.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController @RequestMapping("/consultas")
public class ConsultaController {
    @GetMapping public Collection<Object> listar(@RequestParam(required=false) StatusConsulta status) {
        return Store.consultas.values().stream().filter(c -> status == null || ((Consulta)c).getStatus() == status).collect(Collectors.toList());
    }
    @GetMapping("/{id}") public Consulta buscar(@PathVariable UUID id) { return PacienteController.required(Store.consultas, id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Consulta criar(@Valid @RequestBody Consulta consulta) {
        PacienteController.required(Store.pacientes, consulta.getPacienteId());
        var profissional = (Profissional) PacienteController.required(Store.profissionais, consulta.getProfissionalId());
        validarModalidade(consulta, profissional);
        Store.consultas.put(consulta.getId(), consulta); return consulta;
    }
    @PatchMapping("/{id}/status") public Consulta alterarStatus(@PathVariable UUID id, @RequestParam StatusConsulta status) {
        var consulta = buscar(id); consulta.alterarStatus(status); return consulta;
    }
    private void validarModalidade(Consulta c, Profissional p) {
        if ("ESPECIALIZADA".equalsIgnoreCase(c.getTipo())) {
            if (c.getEspecialidade() == null || !c.getEspecialidade().equalsIgnoreCase(p.getEspecialidade()))
                throw new IllegalArgumentException("A especialidade da consulta nao e compativel com o profissional");
        } else if (!"ROTINA".equalsIgnoreCase(c.getTipo())) {
            throw new IllegalArgumentException("Tipo de consulta deve ser ROTINA ou ESPECIALIZADA");
        }
    }
}
