# Projeto Restaurante

**Descrição do problema**

O restaurante BomMacarrao está atualmente com duas filiais na cidade e precisa de um sistema integrado para gerenciamento de cardápios e comandas.

Visando atender a possível expansão, o sistema deve permitir o cadastro de múltiplas filiais, com cardápios diferentes para cada uma das filiais. O sistema também deve permitir o controle de comandas por filiais.
  
---
  
**Implementação** 

Após a coleta de requisitos junto ao cliente, foram definidos os testes em `RestauranteServiceTest.java` que validam as regras de negócios do problema proposto.

Sua função é realizar a codificação dos métodos da classe `RestauranteService.java`, de modo que atendam aos requisitos e aos testes da `RestauranteServiceTest.java`.
  
---
  
**Requisitos**
  * O sistema deve permitir o cadastro de filiais; 
  * O sistema deve permitir a inserção de produtos e cardápios para cada filial;
  * O sistema deve permitir a abertura de comandas a partir do código da filial, controlando para que seja gerado um código unico para cada comanda;
  * O sistema deve permitir a inclusão de novos itens na comanda a partir do código do produto, quantidade e código da comanda;
  * O sistema deve permitir a remoção de itens da comanda a partir do código do produto, quantidade e código da comanda;
  * O sistema deve totalizar a comanda a partir do seu código;
  * O sistema deve permitir o fechamento da comanda, impedindo a inserção de itens em uma comanda fechada;

---
  
**Observações**
  * Por questões de simplificação **NÃO** é necessário a persistência dos dados em banco de dados. Utilize `List, ArrayList, Set, Map...` ou a estrutura de dados que julgar mais conveniente para armazenar os dados temporariamente.
  * **NÃO** se faz necessária a implementação de nenhuma interface gráfica para comunicação com o usuário, os testes são suficientes para avaliar os resultados.
  * Sinta-se livre para escrever outros testes que julgar necessário, porém você não deve remover nenhum dos testes inicialmente implementados na classe `RestauranteServiceTest.java`.
  * Sinta-se livre para inserir novos métodos na classe `RestauranteService.java`, porém não exclua nem altere a declaração de nenhum dos métodos que já estão lá. Para resolver o problema proposto você deverá escrever o corpo dos métodos que já estão previamente declarados.
  * Espera-se 100% de aceitação nos testes da classe `RestauranteServiceTest.java`, porém isso não garante a nota máxima no trabalho. Para avaliação será considerado a solução implementada e os códigos fontes produzidos.
  * Será atribuído nota **ZERO** caso seja indentificada a presença de plágio do código fonte.
  
---
  
**Realize o exercício com calma, aplicando os conceitos que estudamos em sala de aula e obterá sucesso.**
