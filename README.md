# Vendas_TCC - Módulo de Vendas ERP (Etec Guarulhos)

Repositório oficial do Trabalho de Conclusão de Curso (TCC) da Etec Guarulhos (2º Semestre / 2024 - 2026). Este projeto implementa o **Módulo de Vendas (Order-to-Cash)** para um ERP corporativo completo, integrando cadastro de clientes, digitação de pedidos, motor de preços (*Pricing Engine*), alçadas comerciais, gestão de devoluções e tela de balcão (inspirada no modelo TOTVS).

## Integrantes do Grupo
- Eduardo Yuri de Holanda Lima
- Eúde Emanuel de Souza Paris
- Lucas de Lima Francisco
- Matheus Godoy
- Pietro Rainone Bruneli
- Rafael Pimenta de Souza

---

## Estrutura do Projeto (Padrão Eclipse / Java)

O projeto está estruturado no formato tradicional Java para importação direta no Eclipse IDE:
- `src/`: Contém os códigos-fonte divididos em pacotes (MVC):
  - `model/`: Regras de negócio, entidades e persistência de dados.
  - `view/`: Telas e interfaces de usuário (Wireframes, Balcão, Gestão).
  - `controller/`: Controladores de fluxo, validações e lógicas de transação.
  - `util/`: Classes utilitárias, conexões de banco e formatadores fiscais.
- `bin/`: Diretório de arquivos compilados (gerado pelo Eclipse).
- `banco/`: Scripts SQL de criação do banco de dados relacional (PostgreSQL) e diagramas de relacionamento (ERD).

---

## Guia de Implementação (Classes e Descrições)

Cada arquivo dentro da pasta `src/` possui comentários e descrições detalhadas do que deve ser implementado por cada membro da equipe, seguindo rigorosamente a monografia e as normas da ABNT.
