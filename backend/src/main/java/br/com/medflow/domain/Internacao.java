package br.com.medflow.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Internacao {
    private UUID id = UUID.randomUUID(); private UUID pacienteId; private UUID quartoId;
    private LocalDateTime entrada = LocalDateTime.now(); private LocalDateTime alta;
    private String motivo; private boolean ativa = true;
    public void darAlta() { if (!ativa) throw new IllegalStateException("Internacao ja encerrada"); ativa = false; alta = LocalDateTime.now(); }
    public UUID getId() { return id; }
    public UUID getPacienteId() { return pacienteId; } public void setPacienteId(UUID v) { pacienteId = v; }
    public UUID getQuartoId() { return quartoId; } public void setQuartoId(UUID v) { quartoId = v; }
    public LocalDateTime getEntrada() { return entrada; } public void setEntrada(LocalDateTime v) { entrada = v; }
    public LocalDateTime getAlta() { return alta; } public String getMotivo() { return motivo; } public void setMotivo(String v) { motivo = v; }
    public boolean isAtiva() { return ativa; }
}
