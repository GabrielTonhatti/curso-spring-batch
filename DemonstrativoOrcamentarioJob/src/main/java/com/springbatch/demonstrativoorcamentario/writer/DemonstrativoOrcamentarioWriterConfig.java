package com.springbatch.demonstrativoorcamentario.writer;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.demonstrativoorcamentario.dominio.GrupoLancamento;
import com.springbatch.demonstrativoorcamentario.dominio.Lancamento;

@Configuration
public class DemonstrativoOrcamentarioWriterConfig {
	@Bean
	public ItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter() {
		return itens -> {
			System.out.println("---- Demonstrativo orçamentário ----");
			for (GrupoLancamento grupo : itens) {
				System.out.printf("[%d] %s - %s%n", grupo.getCodigoNaturezaDespesa(),
						grupo.getDescricaoNaturezaDespesa(),
						NumberFormat.getCurrencyInstance().format(grupo.getTotal()));
				for (Lancamento lancamento : grupo.getLancamentos()) {
					System.out.printf("\t [%s] %s - %s%n", new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()), lancamento.getDescricao(),
							NumberFormat.getCurrencyInstance().format(lancamento.getValor()));
				}
			}
		};
	}
}
