/*
 * ==============================================================================
 * SISTEMA ERP - MÓDULO DE VENDAS (ETEC GUARULHOS - TCC)
 * Script de Criação do Banco de Dados Relacional (PostgreSQL)
 * ==============================================================================
 * Descrição: Este script define a estrutura relacional para suporte ao ciclo 
 * Order-to-Cash, incluindo tabelas de Clientes, Produtos, Pedidos de Venda, 
 * Itens de Pedido, Alçadas Comerciais, Devoluções e Tabela Fiscal.
 */

-- Tabela de Clientes
CREATE TABLE clientes (
    id_cliente SERIAL PRIMARY KEY,
    razao_social VARCHAR(150) NOT NULL,
    cnpj_cpf VARCHAR(20) UNIQUE NOT NULL,
    ie_rg VARCHAR(30),
    endereco VARCHAR(200) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    limite_credito NUMERIC(12,2) DEFAULT 0.00,
    status_credito VARCHAR(20) DEFAULT 'LIBERADO', -- LIBERADO, BLOQUEADO
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Produtos (SKUs)
CREATE TABLE produtos (
    id_produto SERIAL PRIMARY KEY,
    codigo_sku VARCHAR(50) UNIQUE NOT NULL,
    descricao VARCHAR(150) NOT NULL,
    preco_tabela NUMERIC(12,2) NOT NULL,
    custo_unitario NUMERIC(12,2) NOT NULL,
    estoque_atual INT NOT NULL,
    ncm VARCHAR(10) NOT NULL
);

-- Tabela de Pedidos de Venda (Sales Order Entry)
CREATE TABLE pedidos_venda (
    id_pedido SERIAL PRIMARY KEY,
    id_cliente INT REFERENCES clientes(id_cliente),
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valor_total NUMERIC(12,2) NOT NULL,
    status_pedido VARCHAR(30) DEFAULT 'DIGITACAO', -- DIGITACAO, AGUARDANDO_APROVACAO, APROVADO, FATURADO, CANCELADO
    margem_lucro_percentual NUMERIC(5,2),
    observacoes TEXT
);

-- Tabela de Itens do Pedido
CREATE TABLE itens_pedido (
    id_item SERIAL PRIMARY KEY,
    id_pedido INT REFERENCES pedidos_venda(id_pedido) ON DELETE CASCADE,
    id_produto INT REFERENCES produtos(id_produto),
    quantidade INT NOT NULL,
    preco_praticado NUMERIC(12,2) NOT NULL,
    desconto_aplicado NUMERIC(5,2) DEFAULT 0.00,
    subtotal NUMERIC(12,2) NOT NULL
);

-- Tabela de Alçadas Comerciais (Pricing Engine & Hard Stops)
CREATE TABLE alcadas_comerciais (
    id_alcada SERIAL PRIMARY KEY,
    id_pedido INT REFERENCES pedidos_venda(id_pedido),
    nivel_aprovacao VARCHAR(50) NOT NULL, -- GERENTE, DIRETOR, FINANCEIRO
    status_aprovacao VARCHAR(20) DEFAULT 'PENDENTE', -- PENDENTE, APROVADO, REJEITADO
    motivo_bloqueio TEXT,
    data_analise TIMESTAMP
);

-- Tabela de Gestão de Devoluções (Return Orders)
CREATE TABLE devolucoes (
    id_devolucao SERIAL PRIMARY KEY,
    id_pedido INT REFERENCES pedidos_venda(id_pedido),
    motivo_devolucao TEXT NOT NULL,
    status_devolucao VARCHAR(30) DEFAULT 'SOLICITADA', -- SOLICITADA, EM_ANALISE, APROVADA, CONCLUIDA
    valor_estornado NUMERIC(12,2) NOT NULL,
    data_solicitacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
