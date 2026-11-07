package br.com.medflow.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class Profissional {
    private UUID id = UUID.randomUUID();
    @NotBlank private String nome;
    @NotBlank private String registroProfissional;
    private String telefone;
    @Email private String email;
    private TipoProfissional tipo;
    private String especialidade;
    private String areaCirurgica;

    public Profissional() {}
    public Profissional(String nome, String registro, String telefone, String email, TipoProfissional tipo,
                         String especialidade, String areaCirurgica) {
        this.nome = nome; this.registroProfissional = registro; this.telefone = telefone; this.email = email;
        this.tipo = tipo; this.especialidade = especialidade; this.areaCirurgica = areaCirurgica;
        validar();
    }
    public void validar() {
        if (tipo == null) throw new IllegalArgumentException("Tipo do profissional e obrigatorio");
        if (tipo == TipoProfissional.ESPECIALISTA && (especialidade == null || especialidade.isBlank()))
            throw new IllegalArgumentException("Especialista deve informar a especialidade");
        if (tipo == TipoProfissional.CIRURGIAO && (areaCirurgica == null || areaCirurgica.isBlank()))
            throw new IllegalArgumentException("Cirurgiao deve informar a area cirurgica");
    }
    public UUID getId() { return id; }
    public String getNome() { return nome; } public void setNome(String v) { nome = v; }
    public String getRegistroProfissional() { return registroProfissional; } public void setRegistroProfissional(String v) { registroProfissional = v; }
    public String getTelefone() { return telefone; } public void setTelefone(String v) { telefone = v; }
    public String getEmail() { return email; } public void setEmail(String v) { email = v; }
    public TipoProfissional getTipo() { return tipo; } public void setTipo(TipoProfissional v) { tipo = v; }
    public String getEspecialidade() { return especialidade; } public void setEspecialidade(String v) { especialidade = v; }
    public String getAreaCirurgica() { return areaCirurgica; } public void setAreaCirurgica(String v) { areaCirurgica = v; }
}
