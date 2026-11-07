package br.com.medflow.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class AtendimentoEmergencia {
    private UUID id = UUID.randomUUID(); private UUID pacienteId; private UUID profissionalId;
    private LocalDateTime chegada = LocalDateTime.now(); private PrioridadeEmergencia prioridade;
    private StatusAtendimento status = StatusAtendimento.AGUARDANDO; private String observacoes;
    public void iniciar() { if (status != StatusAtendimento.AGUARDANDO) throw new IllegalStateException("Emergencia nao esta aguardando"); status = StatusAtendimento.EM_ATENDIMENTO; }
    public void concluir() { if (status != StatusAtendimento.EM_ATENDIMENTO) throw new IllegalStateException("Emergencia precisa estar em atendimento"); status = StatusAtendimento.CONCLUIDO; }
    public UUID getId() { return id; } public UUID getPacienteId() { return pacienteId; } public void setPacienteId(UUID v) { pacienteId = v; }
    public UUID getProfissionalId() { return profissionalId; } public void setProfissionalId(UUID v) { profissionalId = v; }
    public LocalDateTime getChegada() { return chegada; } public PrioridadeEmergencia getPrioridade() { return prioridade; } public void setPrioridade(PrioridadeEmergencia v) { prioridade = v; }
    public StatusAtendimento getStatus() { return status; } public String getObservacoes() { return observacoes; } public void setObservacoes(String v) { observacoes = v; }
}
