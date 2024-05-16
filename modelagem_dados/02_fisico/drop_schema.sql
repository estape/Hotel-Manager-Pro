-- Comando para limpar todo o SCHEMA public e recriá-lo.
	-- Use com BASTANTE CAUTELA e para teste de modelagem.
	-- Definitivamente não recomendado executar em um banco operante.
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;