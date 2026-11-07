package br.com.medflow.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

public class Paciente {
    private UUID id = UUID.randomUUID();
    @NotBlank private String nome;
    @NotBlank private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    @Email private String email;

    public Paciente() {}
    public Paciente(String nome, String cpf, LocalDate dataNascimento, String telefone, String email) {
        this.nome = nome; this.cpf = cpf; this.dataNascimento = dataNascimento; this.telefone = telefone; this.email = email;
    }
    public UUID getId() { return id; }
    public String getNome() { return nome; } public void setNome(String v) { nome = v; }
    public String getCpf() { return cpf; } public void setCpf(String v) { cpf = v; }
    public LocalDate getDataNascimento() { return dataNascimento; } public void setDataNascimento(LocalDate v) { dataNascimento = v; }
    public String getTelefone() { return telefone; } public void setTelefone(String v) { telefone = v; }
    public String getEmail() { return email; } public void setEmail(String v) { email = v; }
}
