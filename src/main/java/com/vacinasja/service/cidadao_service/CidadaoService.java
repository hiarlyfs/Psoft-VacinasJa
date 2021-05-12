package com.vacinasja.service.cidadao_service;

import com.vacinasja.dto.cidadao.InsertCidadaoDto;
import com.vacinasja.dto.cidadao.UpdateCidadaoDto;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCartaoSus;
import com.vacinasja.error.cidadao_error.CidadaoNaoEncontradoCpf;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.LoginCidadao;

import java.text.ParseException;

public interface CidadaoService {
    LoginCidadao save(InsertCidadaoDto insertCidadaoDto) throws ParseException, TipoLoginInvalido;
    Cidadao update(String cpf, UpdateCidadaoDto updateCidadaoDto) throws CidadaoNaoEncontradoCartaoSus;
    String listaEstagioCidadao(String cpf) throws CidadaoNaoEncontradoCartaoSus;
    Cidadao findByCpf(String cpf) throws CidadaoNaoEncontradoCpf;
    Cidadao findByCartaoSus(String cartaoSus) throws CidadaoNaoEncontradoCartaoSus;
}
