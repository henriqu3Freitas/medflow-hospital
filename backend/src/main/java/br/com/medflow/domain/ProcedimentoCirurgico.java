package br.com.medflow.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProcedimentoCirurgico {
    private UUID id = UUID.randomUUID();
    private UUID pacienteId; private UUID cirurgiaoId;
    private String descricao; private String tipoCirurgia;
    private String observacoesPreOperatorias; private String observacoesPosOperatorias;
    private LocalDateTime dataHoraPrevista; private LocalDateTime dataHoraEfetiva;
    private StatusProcedimento status = StatusProcedimento.AGENDADO;

    public void alterarStatus(StatusProcedimento novo) {
        if (novo == StatusProcedimento.CONCLUIDO && status != StatusProcedimento.EM_ANDAMENTO)
            throw new IllegalStateException("Procedimento so pode ser concluido apos ser iniciado");
        if (status == StatusProcedimento.CONCLUIDO || status == StatusProcedimento.CANCELADO)
            throw new IllegalStateException("Procedimento encerrado nao pode ser alterado");
        status = novo;
        if (novo == StatusProcedimento.EM_ANDAMENTO && dataHoraEfetiva == null) dataHoraEfetiva = LocalDateTime.now();
    }
    public UUID getId() { return id; }
    public UUID getPacienteId() { return pacienteId; } public void setPacienteId(UUID v) { pacienteId = v; }
    public UUID getCirurgiaoId() { return cirurgiaoId; } public void setCirurgiaoId(UUID v) { cirurgiaoId = v; }
    public String getDescricao() { return descricao; } public void setDescricao(String v) { descricao = v; }
    public String getTipoCirurgia() { return tipoCirurgia; } public void setTipoCirurgia(String v) { tipoCirurgia = v; }
    public String getObservacoesPreOperatorias() { return observacoesPreOperatorias; } public void setObservacoesPreOperatorias(String v) { observacoesPreOperatorias = v; }
    public String getObservacoesPosOperatorias() { return observacoesPosOperatorias; } public void setObservacoesPosOperatorias(String v) { observacoesPosOperatorias = v; }
    public LocalDateTime getDataHoraPrevista() { return dataHoraPrevista; } public void setDataHoraPrevista(LocalDateTime v) { dataHoraPrevista = v; }
    public LocalDateTime getDataHoraEfetiva() { return dataHoraEfetiva; } public void setDataHoraEfetiva(LocalDateTime v) { dataHoraEfetiva = v; }
    public StatusProcedimento getStatus() { return status; }
}
