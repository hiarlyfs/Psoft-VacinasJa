package com.vacinasja.dto.agendamento;

import com.vacinasja.model.LocalVacinacao;

public class AgendamentoDto {
	private String data;
	private String horario;
	private LocalVacinacao local;
	
	public AgendamentoDto() {
	}

	public AgendamentoDto(String data, String horario, LocalVacinacao local) {
		super();
		this.data = data;
		this.horario = horario;
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public String getHorario() {
		return horario;
	}

	public LocalVacinacao getLocal() {
		return local;
	}
	
	
}
