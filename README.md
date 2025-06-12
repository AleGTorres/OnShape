# 💪OnShape

Nosso projeto é um aplicativo de treino focado em musculação, projetado para ajudar usuários a criar, acompanhar e registrar suas rotinas de treino de forma eficiente. O app permite que o usuário monte seus próprios treinos, registre cargas e repetições a cada sessão e visualize seu progresso ao longo do tempo, mantendo um histórico detalhado de suas atividades.

  

## 📌Alunos do Grupo ##

- Alexandre Torres Gonçalves

- Amanda Caroline de Gois

- João Augusto Antonow

  

## 👥Papéis e Usuários

#### Usuário Atleta:  
- Pessoa que se cadastra no aplicativo para gerenciar e registrar seus treinos

#### Visitante:
- Qualquer pessoa que abre o app sem fazer login



## 📝Requisitos Funcionais
  
### RF01: Cadastro e Login de Usuário

-   **Descrição:** O sistema deve permitir que novos usuários se cadastrem com nome, e-mail, senha e uma foto. Usuários existentes devem poder realizar o login para acessar seus dados.
    
-   **Entradas:** Nome (texto), e-mail (texto), senha (texto), foto do perfil (imagem).
    
-   **Processamento:**
    -   Validar que campos obrigatórios, como e-mail e senha, não estejam vazios.
        
    -   Verificar se o e-mail já está em uso.
    -   Gerar e armazenar o hash da senha para segurança.
        
    -   Armazenar a foto do usuário.
        
    -   Para o login, o sistema deve comparar o e-mail e o hash da senha fornecida com os dados armazenados.
-   **Saídas:** Acesso à tela principal do aplicativo ou mensagens de erro claras (ex: "E-mail já cadastrado", "Usuário ou senha inválidos").

  
### RF02: Gerenciamento de Rotinas de Treino

-   **Descrição:** O usuário deve ser capaz de criar, visualizar, editar e excluir suas próprias rotinas de treino.
-   **Entradas:** Nome da rotina (ex: "Treino A - Peito e Tríceps"), lista de exercícios, número de séries e repetições para cada exercício.
-   **Processamento:** O aplicativo deve salvar as rotinas de treino no banco de dados, associando cada rotina ao usuário que a criou. As operações de edição e exclusão devem atualizar ou remover os dados correspondentes.
    
-   **Saídas:** Exibição da lista de rotinas salvas. Confirmação visual quando uma rotina é criada, alterada ou removida.



### RF03: Visualização de Histórico e Progresso

-   **Descrição:** O usuário deve poder visualizar um histórico de todos os treinos que já realizou e ver relatórios de progresso.
-   **Entradas:** Acesso do usuário à tela de "Histórico" ou "Progresso".
-   **Processamento:** O aplicativo busca no banco de dados todas as sessões de treino registradas pelo usuário e as organiza (ex: por data). Pode processar esses dados para gerar gráficos de evolução de carga para um exercício específico.
-   **Saídas:** Exibição de uma lista com os treinos passados e/ou gráficos mostrando o progresso ao longo do tempo. Este é um dos "relatórios e saídas" do seu app.


### RF04: Biblioteca de Exercícios

-   **Descrição:** O aplicativo deve fornecer uma biblioteca de exercícios pré-cadastrados que o usuário pode consultar e adicionar às suas rotinas.
-   **Entradas:** Filtro de busca por nome do exercício ou por grupo muscular (ex: Peito, Costas, Perna).
-   **Processamento:** O sistema busca na sua base de dados de exercícios e exibe uma lista com os resultados. Cada exercício pode conter uma imagem/gif demonstrativo e uma descrição do movimento.
-   **Saídas:** Exibição da lista de exercícios filtrada para o usuário.
