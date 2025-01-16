package com.gpt.data.enhancer.helpers;

public class PromptBuilder {

	public static String buildPrompt(String prompt, String structure) {
		if (structure != null) {
			return "Recebi os seguintes dados em texto simples. Gostaria que você quebrasse esses dados "
				+ "de acordo com a estrutura do banco de dados que será informada e, em seguida, "
				+ "enriquecesse as informações conforme necessário. Utilize a estrutuda de dados "
				+ "informada e adicione detalhes enriquecidos, como localização, palavras-chave extraídas "
				+ "da descrição entre outras que julgar interessantes para o contexto. "
				+ "Você pode usar sites externos para complementar se necessário. Por exemplo, se os dados "
				+ "forem sobre vagas de emprego e o banco de dados tiver uma coluna chamada faixa salarial,"
				+ "você pode realizar uma pesquisa(pesquise de sites como glassdoor ou afins) baseada em "
				+ "posição e senioridade. Adicione entre parênteses a fonte das suas pesquisas (exemplo: "
				+ "R$4500,00 (Glassdoor))."
				+ ""
				+ "Estrutura do banco de dados MYSQL:"
				+ structure
				+ ""
				+ "Dados recebidos (formato texto simples):"
				+ ""
				+ prompt
				+ ""
				+ "Solicito que você:"
				+ "1- Quebre os dados conforme a estrutura do banco de dados fornecida."
				+ "2- Enriqueça os dados com informações adicionais pertinentes ao assunto e estrutura do "
				+ "banco de dados."
				+ "Após o enriquecimento, forneça um objeto JSON com os dados de forma adequada para "
				+ "inserção no banco de dados."
				+ "Por favor se abstenha de quaisquer explicações, o output deve ser somente o Objeto JSON com os dados enriquecidos.";
		}

		return "Recebi os seguintes dados em texto simples. Gostaria que você quebrasse aesses dados "
			+ "em uma estrutura para importação em um banco de dados SQL (sugira a melhor estrutura "
			+ "baseada nos dados informados) e, em seguida, "
			+ "enriquecesse as informações conforme necessário. "
			+ "Adicione detalhes enriquecidos, como localização, palavras-chave extraídas "
			+ "da descrição entre outras que julgar interessantes para o contexto. "
			+ "Você pode usar sites externos para complementar se necessário. Por exemplo, se os dados "
			+ "forem sobre vagas de emprego, você pode realizar uma pesquisa(pesquise de sites como glassdoor "
			+ "ou afins) baseada em posição e senioridade. Adicione entre parênteses a fonte das suas pesquisas (exemplo: "
			+ "R$4500,00 (Glassdoor))."
			+ ""
			+ "Dados recebidos (formato texto simples):"
			+ ""
			+ prompt
			+ ""
			+ "Solicito que você:"
			+ "1- Quebre os dados na melhor estrutura para um banco de dados."
			+ "2- Enriqueça os dados com informações adicionais pertinentes ao assunto e estrutura do "
			+ "banco de dados que você apresentar."
			+ "Após o enriquecimento, forneça um objeto JSON com os dados de forma adequada para "
			+ "inserção no banco de dados, sendo que as chaves de cada dado serão as colunas da tabela."
			+ "Por favor se abstenha de quaisquer explicações, o output deve ser somente o Objeto JSON com os dados enriquecidos.";
	}
}
