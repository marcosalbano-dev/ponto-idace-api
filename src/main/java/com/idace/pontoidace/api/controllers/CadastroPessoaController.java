package com.idace.pontoidace.api.controllers;

import com.idace.pontoidace.api.dtos.CadastroPessoaDTO;
import com.idace.pontoidace.api.entities.Funcionario;
import com.idace.pontoidace.api.entities.Setor;
import com.idace.pontoidace.api.enums.PerfilEnum;
import com.idace.pontoidace.api.response.Response;
import com.idace.pontoidace.api.services.FuncionarioService;
import com.idace.pontoidace.api.services.SetorService;
import com.idace.pontoidace.api.utils.PasswordUtils;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequestMapping("/api/cadastrar-pessoa")
@CrossOrigin(origins = "*")
public class CadastroPessoaController {

    private static final Logger log = LoggerFactory.getLogger(CadastroPessoaController.class);

    @Autowired
    private SetorService setorService;

    @Autowired
    private FuncionarioService funcionarioService;

    public CadastroPessoaController() {
    }

    /**
     * Cadastra um funcionário pessoa física no sistema.
     *
     * @param cadastroPessoaDTO
     * @param result
     * @return ResponseEntity<Response<CadastroPFDto>>
     * @throws NoSuchAlgorithmException
     */
    @PostMapping
    public ResponseEntity<Response<CadastroPessoaDTO>> cadastrar(@Valid @RequestBody CadastroPessoaDTO cadastroPessoaDTO,
                                                                 BindingResult result) throws NoSuchAlgorithmException {
        log.info("Cadastrando Pessoa: {}", cadastroPessoaDTO.toString());
        Response<CadastroPessoaDTO> response = new Response<CadastroPessoaDTO>();

        validarDadosExistentes(cadastroPessoaDTO, result);
        Funcionario funcionario = this.converterDTOParaFuncionario(cadastroPessoaDTO, result);

        if (result.hasErrors()) {
            log.error("Erro validando dados de cadastro Pessoa: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Setor> setor = this.setorService.buscarPorNomeSetor(cadastroPessoaDTO.getNome());
        setor.ifPresent(s -> funcionario.setSetor(s));
        this.funcionarioService.persistir(funcionario);

        response.setData(this.converterCadastroPessoaDTO(funcionario));
        return ResponseEntity.ok(response);
    }

    /**
     * Verifica se a empresa está cadastrada e se o funcionário não existe na base de dados.
     *
     * @param cadastroPessoaDTO
     * @param result
     */
    private void validarDadosExistentes(CadastroPessoaDTO cadastroPessoaDTO, BindingResult result) {
        Optional<Setor> setor = this.setorService.buscarPorNomeSetor(cadastroPessoaDTO.getNome());
        if (!setor.isPresent()) {
            result.addError(new ObjectError("setor", "Setor não cadastrada."));
        }

        this.funcionarioService.buscarPorCpf(cadastroPessoaDTO.getCpf())
                .ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

        this.funcionarioService.buscarPorEmail(cadastroPessoaDTO.getEmail())
                .ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
    }

    /**
     * Converte os dados do DTO para funcionário.
     *
     * @param cadastroPessoaDTO
     * @param result
     * @return Funcionario
     * @throws NoSuchAlgorithmException
     */
    private Funcionario converterDTOParaFuncionario(CadastroPessoaDTO cadastroPessoaDTO, BindingResult result)
            throws NoSuchAlgorithmException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(cadastroPessoaDTO.getNome());
        funcionario.setEmail(cadastroPessoaDTO.getEmail());
        funcionario.setCpf(cadastroPessoaDTO.getCpf());
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(PasswordUtils.gerarBCrypt(cadastroPessoaDTO.getSenha()));
        cadastroPessoaDTO.getQtdHorasAlmoco()
                .ifPresent(qtdHorasAlmoco -> funcionario.setQtdHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));
        cadastroPessoaDTO.getQtdHorasTrabalhoDia()
                .ifPresent(qtdHorasTrabDia -> funcionario.setQtdHorasTrabalhoDia(Float.valueOf(qtdHorasTrabDia)));
        cadastroPessoaDTO.getValorHora().ifPresent(valorHora -> funcionario.setValorHora(new BigDecimal(valorHora)));

        return funcionario;
    }

    /**
     * Popula o DTO de cadastro com os dados do funcionário e empresa.
     *
     * @param funcionario
     * @return CadastroPFDto
     */
    private CadastroPessoaDTO converterCadastroPessoaDTO(Funcionario funcionario) {
        CadastroPessoaDTO cadastroPessoaDTO = new CadastroPessoaDTO();
        cadastroPessoaDTO.setId(funcionario.getId());
        cadastroPessoaDTO.setNome(funcionario.getNome());
        cadastroPessoaDTO.setEmail(funcionario.getEmail());
        cadastroPessoaDTO.setCpf(funcionario.getCpf());
        funcionario.getQtdHorasAlmocoOpt().ifPresent(qtdHorasAlmoco -> cadastroPessoaDTO
                .setQtdHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
        funcionario.getQtdHorasTrabalhoDiaOpt().ifPresent(
                qtdHorasTrabDia -> cadastroPessoaDTO.setQtdHorasTrabalhoDia(Optional.of(Float.toString(qtdHorasTrabDia))));
        funcionario.getValorHoraOpt()
                .ifPresent(valorHora -> cadastroPessoaDTO.setValorHora(Optional.of(valorHora.toString())));

        return cadastroPessoaDTO;
    }
}
