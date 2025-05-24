package com.seuusuario.seuprojeto.model;

import jakarta.persistence.*;

@Entity
public class AgenteIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String versao;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getVersao() { return versao; }
    public void setVersao(String versao) { this.versao = versao; }
}
