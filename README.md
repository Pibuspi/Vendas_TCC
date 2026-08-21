Vendas_TCC - Módulo de Vendas ERP (Etec Guarulhos)

Repositório oficial do TCC da Etec Guarulhos. Este projeto implementa o Módulo de Vendas.

Integrantes e Matriz de Responsabilidades por Camada

Divisão do que cada integrante deve fazer:

| Integrante | Módulo / Tela | O que fazer em SQL & DAO | O que fazer em Model | O que fazer em Controller | O que fazer em View |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Eúde** | Cadastro de Clientes e Regras de Venda | Criar tabela `clientes` no PostgreSQL; implementar `ClienteDAO.java` (CRUD completo). | Estruturar `Cliente.java` com validações de CNPJ/CPF e limites. | Validar dados cadastrais e regras de crédito. | Desenvolver `CadastroClienteView.java` (formulários e tabelas). |
| **Rafael** | Digitação de Pedidos de Venda | Criar tabelas `pedidos_venda` e `itens_pedido`; implementar `PedidoDAO.java`. | Estruturar `PedidoVenda.java` e `ItemPedido.java`. | `PedidoController.java` para controle de estoque e subtotais. | Desenvolver `DigitacaoPedidoView.java` (lançamento de SKUs). |
| **Matheus** | Motor de Preços & Alçadas | Estruturar tabela `alcadas_comerciais`; implementar `AlcadaDAO.java`. | Modelar regras de desconto e margem mínima. | `PricingEngineController.java` para cálculo de margem e Hard Stops. | Desenvolver `PricingEngineView.java` (painel de governança). |
| **Lucas** | Liberação Comercial | Atualizar status na tabela `pedidos_venda`; usar `PedidoDAO.java`. | Manipular status de liberação do pedido. | Lógica de aprovação gerencial e consolidação de faturamento. | Desenvolver `LiberacaoComercialView.java` (fila de aprovações). |
| **Pietro** | Gestão de Devoluções | Criar tabela `devolucoes`; implementar `DevolucaoDAO.java`. | Estruturar `Devolucao.java` (motivos e estornos). | `DevolucaoController.java` para processamento de notas de crédito. | Desenvolver `GestaoDevolucoesView.java` (pós-venda e estornos). |
| **Eduardo** | Tela de Balcão (TOTVS) | Atualizar para ter uma forma de pagamento| Controlar fluxo de PDV, atalhos e fechamento de caixa. | Desenvolver `TelaPagamentoView.java` (interface inspirada na TOTVS). |

