package br.com.medflow.domain;

public enum StatusConsulta {
    AGENDADA, CONFIRMADA, EM_ATENDIMENTO, CONCLUIDA, CANCELADA, NAO_COMPARECEU;

    public boolean podeIrPara(StatusConsulta destino) {
        return switch (this) {
            case AGENDADA -> destino == CONFIRMADA || destino == CANCELADA || destino == NAO_COMPARECEU;
            case CONFIRMADA -> destino == EM_ATENDIMENTO || destino == CANCELADA || destino == NAO_COMPARECEU;
            case EM_ATENDIMENTO -> destino == CONCLUIDA || destino == CANCELADA;
            case CONCLUIDA, CANCELADA, NAO_COMPARECEU -> false;
        };
    }
}
