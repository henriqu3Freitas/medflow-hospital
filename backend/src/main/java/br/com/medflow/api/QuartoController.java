package br.com.medflow.api;

import br.com.medflow.domain.Quarto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController @RequestMapping("/quartos")
public class QuartoController {
    @GetMapping public Collection<Object> listar(@RequestParam(required=false) Boolean disponivel) {
        return Store.quartos.values().stream().filter(q -> disponivel == null || ((Quarto)q).isDisponivel() == disponivel).collect(Collectors.toList());
    }
    @GetMapping("/{id}") public Quarto buscar(@PathVariable UUID id) { return PacienteController.required(Store.quartos, id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Quarto criar(@RequestBody Quarto quarto) {
        if (quarto.getTipo() == null || quarto.getNumero() == null || quarto.getNumero().isBlank()) throw new IllegalArgumentException("Numero e tipo do quarto sao obrigatorios");
        Store.quartos.put(quarto.getId(), quarto); return quarto;
    }
    @PatchMapping("/{id}/disponibilidade") public Quarto disponibilidade(@PathVariable UUID id, @RequestParam boolean disponivel) {
        var quarto = buscar(id); quarto.setDisponivel(disponivel); return quarto;
    }
}
