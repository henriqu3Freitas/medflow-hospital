package br.com.medflow.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class Consulta {
    private UUID id = UUID.randomUUID();
    @NotNull private UUID pacienteId;
    @NotNull private UUID profissionalId;
    @NotNull private LocalDateTime dataHora;
    @NotBlank private String motivo;
    private String tipo = "ROTINA";
    private String especialidade;
    private String observacoes;
    private StatusConsulta status = StatusConsulta.AGENDADA;

    public void alterarStatus(StatusConsulta novoStatus) {
        if (novoStatus == null || !status.podeIrPara(novoStatus))
            throw new IllegalStateException("Transicao de consulta invalida: " + status + " -> " + novoStatus);
        status = novoStatus;
    }
    public UUID getId() { return id; }
    public UUID getPacienteId() { return pacienteId; } public void setPacienteId(UUID v) { pacienteId = v; }
    public UUID getProfissionalId() { return profissionalId; } public void setProfissionalId(UUID v) { profissionalId = v; }
    public LocalDateTime getDataHora() { return dataHora; } public void setDataHora(LocalDateTime v) { dataHora = v; }
    public String getMotivo() { return motivo; } public void setMotivo(String v) { motivo = v; }
    public String getTipo() { return tipo; } public void setTipo(String v) { tipo = v; }
    public String getEspecialidade() { return especialidade; } public void setEspecialidade(String v) { especialidade = v; }
    public String getObservacoes() { return observacoes; } public void setObservacoes(String v) { observacoes = v; }
    public StatusConsulta getStatus() { return status; }
}
