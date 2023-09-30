#Desafio 1

## Como executar a Aplicação

    . É recomendado a utilização da IDE `IntelliJ Idea`, visto que a mesma foi utilizada para a 
    criação do projeto as dependencias foram geradas através do Maven, porem o mesmo deixou de 
    atualizar e buscar novas dependencias automaticamente no meio do projeto

    . Ao inicializar a aplicação é possivel acessar a documentação de Api via interface do 
    `Swagger` através da URL <http://localhost:8080/swagger-ui.html>. Nesta documentação se 
    encontra o CRUD requisitado neste primeiro desáfio

## Como Executar os testes

    . Utilizando o `IntelliJ Idea`, é possivel rodar os testes da aplicação com o botão 
    `More Actions` utilizando a opção  `Run CieloBcFinalApplication with Coverage`

## Principais Decisões

    . A Lingua base selecionada para o usuário final foi o `Inglês` devido a Cielo ser uma 
    empresa multinacional
    . Código desenhado e mantido na estrutura Single-responsability Principle, do SOLID para
    facilitar testes injeções de dependencias e mocks
    . Banco em memória para desenvolvimento mais ágil
    . Desacoplamento dos diferentes tipos de clientes para maior agilidade da requisição
    . Alteração da linha de REGEXP pedida no desafio: Resolvi utilizar um padrão que já conhecia
    após perceber que o REGEX proposto se encontrava incorreto
