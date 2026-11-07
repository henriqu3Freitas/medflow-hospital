package br.com.medflow.domain;

import java.util.UUID;

public class Quarto {
    private UUID id = UUID.randomUUID(); private String numero; private TipoQuarto tipo;
    private boolean disponivel = true;
    public UUID getId() { return id; }
    public String getNumero() { return numero; } public void setNumero(String v) { numero = v; }
    public TipoQuarto getTipo() { return tipo; } public void setTipo(TipoQuarto v) { tipo = v; }
    public boolean isDisponivel() { return disponivel; } public void setDisponivel(boolean v) { disponivel = v; }
}
