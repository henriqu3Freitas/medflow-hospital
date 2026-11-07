package br.com.medflow.api;

import br.com.medflow.domain.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController @RequestMapping("/profissionais")
public class ProfissionalController {
    @GetMapping public Collection<Object> listar(@RequestParam(required=false) TipoProfissional tipo,
                                                  @RequestParam(required=false) String especialidade) {
        return Store.profissionais.values().stream().filter(p -> {
            var profissional = (Profissional) p;
            return (tipo == null || profissional.getTipo() == tipo)
                    && (especialidade == null || especialidade.equalsIgnoreCase(profissional.getEspecialidade()));
        }).collect(Collectors.toList());
    }
    @GetMapping("/{id}") public Profissional buscar(@PathVariable UUID id) { return PacienteController.required(Store.profissionais, id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Profissional criar(@Valid @RequestBody Profissional profissional) {
        profissional.validar();
        if (Store.profissionais.values().stream().map(p -> (Profissional)p)
                .anyMatch(p -> p.getRegistroProfissional().equalsIgnoreCase(profissional.getRegistroProfissional())))
            throw new IllegalArgumentException("Registro profissional ja cadastrado");
        Store.profissionais.put(profissional.getId(), profissional); return profissional;
    }
    @PutMapping("/{id}") public Profissional atualizar(@PathVariable UUID id, @Valid @RequestBody Profissional profissional) {
        PacienteController.required(Store.profissionais, id); profissional.validar(); Store.profissionais.put(id, profissional); return profissional;
    }
}
