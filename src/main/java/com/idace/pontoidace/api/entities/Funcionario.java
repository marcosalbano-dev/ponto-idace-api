package com.idace.pontoidace.api.entities;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.idace.pontoidace.api.enums.PerfilEnum;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(name = "valor_hora", nullable = true)
    private BigDecimal valorHora;
    @Column(name = "qtd_horas_trabalho_dia", nullable = true)
    private Float qtdHorasTrabalhoDia;
    @Column(name = "qtd_horas_almoco", nullable = true)
    private Float qtdHorasAlmoco;
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;
    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;
    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;
    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos;
}
