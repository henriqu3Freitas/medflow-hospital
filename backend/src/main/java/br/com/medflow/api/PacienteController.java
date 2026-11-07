package br.com.medflow.api;

import br.com.medflow.domain.Paciente;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/pacientes")
public class PacienteController {
    @GetMapping public Collection<Object> listar() { return Store.pacientes.values(); }
    @GetMapping("/{id}") public Object buscar(@PathVariable UUID id) { return required(Store.pacientes, id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Paciente criar(@Valid @RequestBody Paciente paciente) { Store.pacientes.put(paciente.getId(), paciente); return paciente; }
    @PutMapping("/{id}") public Paciente atualizar(@PathVariable UUID id, @Valid @RequestBody Paciente paciente) {
        required(Store.pacientes, id); Store.pacientes.put(id, paciente); return paciente;
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void remover(@PathVariable UUID id) {
        if (Store.pacientes.remove(id) == null) throw new NoSuchElementException("Paciente nao encontrado");
    }
    static <T> T required(Map<UUID, Object> store, UUID id) {
        @SuppressWarnings("unchecked") T value = (T) store.get(id);
        if (value == null) throw new NoSuchElementException("Registro nao encontrado: " + id);
        return value;
    }
}
