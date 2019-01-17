# Avaliação Técnica
- Esta é uma aplicação web que tem como objetivo realizar o cadastro de ofertas e opções de compras, efetuar o vinculo entre eles e processar a compra para os mesmos.

## Entradas para a Ofertas e Opções de Compra
- Ambos consistem em formulários contendo as seguintes informações de cadastro:
-- Deal(Oferta) -> titleBuyOption, textBuyOption, createDateBuyOption, publishDateBuyOption, endDateBuyOption, urlBuyOption, totalSoldBuyOption, typeBuyOption, buyOptions.
-- Opção de Compra (BuyOption) -> title, normalPrice, salePrice, percentagelDiscount, quantityCupom, startDate, endDate, 

##### Possíveis Melhorias
- melhorar validação de campos
- melhorar as máscaras de entrada
- implementar validação de campos obrigatórios
- adicionar paginação na lista de resultados.
- adicionar filtros de ordenação na lista de resultados.
- realizar uma análise de UX para verificar e melhorar a experiência do usuário tanto na inserção de dados como na maneira com que se demonstra os resultados.

## Método de Associação
- Para criticar criar uma associação basta selecionar os itens e clicar em associar, caso uma opção já esteja associada a uma oferta o sistema remove da primeira oferta e associa a segunda.

##### Possíveis Melhorias
- Verificar aderência à regra de negócio.

## Processamento de compra
- Conforme a documentação recebida, esse item ficou muito vago, sendo assim a cada clique o sistema simplesmente incrementa o numero de compras.

##### Possíveis Melhorias
- Colear melhores especificações do fluxo de negócio.

## Tecnologías Utilizadas
  - Frontend: [Angular5], [Html5], [Bootstrap3]
  - Backend: [Java], [Spring-boot], [Spring-data-rest]
  - Integrações: [Rest], [Restful], [Hateoas]
  - Documentação: [Swagger]

## Arquitetura 
- A solução foi feita em três projetos distintos e independentes um específico para o Frontend e outro para o Backend e mais um projeto integrador.

### Backend
- Solução desenvolvida em Java utilizando Spring-boot, Spring-data-rest, hibernate, JPA, Lombok 
 - API Restful disponível na porta 8080 contendo documentação swagger dos endpoints disponível em `http://localhost:8080/swagger-ui.html`.
  - API para cadastros e consultas das estão disponíveis em `http://localhost:8080/api/v1/deal` e `http://localhost:8080/api/v1/buyOption`.
  - API que efetuará a associação e o processamento da venda estão disponíveis em `http://localhost:8080/api/v1/deal/associar` e `http://localhost:8080/api/v1/deal/processa/{dealId}/{buyOptionId}`.

### Frontend  
- Solução desenvolvida em Angular5, Html5 e CSS3 utilizando Bootstrap3, [Angular CLI](https://github.com/angular/angular-cli) e hospedada em um servidor NGINX. 

##### Melhorias
- melhoria no desacoplamento do código em componentes distintos para que os mesmos possam ser reutilizados em outros lugares da aplicação.

### Banco de dados
- para esta prova de conceito foi utilizado banco de dados em memória, H2, os dados iniciais são populados através de um script sql na pasta de resources, que está disponível em http://localhost:8080/h2-console/ o mesmo faz uso da base padrãojdbc:h2:mem:testdb .

### Build
- esta solução é composta por projetos independentes para o server side (backend) disponível por padrão em `http://localhost:8080` e o client (frontend) disponível por padrao em `http://localhost:4200`, ambos os projetos podem ter o build executados de forma independente. Existe um terceiro projeto integrador capaz de executar o build de ambos os projetos de forma simultânea.

##### Parent
- esse projeto visa englobar os outros dois como sendo módulos de um projeto principal, este projeto pode ser executado através de Docker Compose ou de forma nativa utilizando maven e node.

##### Parent com Docker Composer
- para subir o servidor dessa aplicação é possível gerar e executar dois container docker através do Docker Compose, basta acessar o diretório raiz do projeto peixe-parent e executar (REQUESITO: ambas as imagens já devem estar geradas)
```sh 
$ docker-compose -f docker-compose.yml up -d --force-recreate --build
``` 
- as duas imagens docker previamente geradas chamadas `peixe-server` e `peixe-client` serão executadas através de containers disponíveis nas portas `8080` (backend) e `4200` (frontend), caso os containers já existam os mesmos serão destruídos e recriados.

####### Melhorias
adicionar o processo de gerar as imagens ao docker composer.

##### Backend com Docker
- para subir o servidor dessa aplicação é possível gerar um container docker através do Dockerfile  basta acessar o diretório raiz do projeto `peixe-backend `e executar
```sh 
$ mvn clean package docker:build
``` 
- será gerado uma imagem docker chamada `peixe-server-0.1.0` que pode ser executada através do comando
```sh 
$ docker run -d --name peixe-server -p8080:8080 peixe-server
```
##### Backend com Maven
- para compilar este projeto basta acessar o diretório raiz do projeto `peixe-backend` e executar o comando 
```sh 
$ mvn clean package
``` 
- para executar a aplicação basta acessar o diretório raiz do projeto ``peixe-backend e executar o comando 
```sh 
$ mvn spring-boot:run
``` 
##### Frontend com Docker
- para subir a aplicação é possível gerar um container docker através do Dockerfile  basta acessar o diretório raiz do projeto `peixe-frontend` e executar
```sh 
$ docker build -t peixe-client .
``` 
- será gerado uma imagem docker chamada `peixe-client` que pode ser executada através do comando
```sh 
$ docker run -d --name peixe-client -p4200:4200 peixe-client
```
##### Frontend com Node
- para compilar este projeto basta acessar o diretório raiz do projeto `peixe-frontend` e executar o comando 
```sh 
$ npm install
``` 
- para executar a aplicação basta acessar o diretório raiz do projeto `peixe-frontend` e executar o comando 
```sh 
$ ng serve
``` 
## Testes
##### Frontend (não foram implementados)
- Testes unitários: executar o comando `ng test` para rodar os testes unitarios via [Karma](https://karma-runner.github.io).
- Teste ponta a ponta: executar o comando `ng e2e` para rodar os testes ponta a ponta via [Protractor](http://www.protractortest.org/).

##### Backend
- Testes unitários: .
- Teste ponta a ponta: .

## Documentação
- esta solução possui documentação em ambos códigos fontes e a documentação da API Rest está disponível através da ferramenta Swagger em /swagger-ui.html

### Observações 
> Alguns pontos foram implementados porém precisam de melhorias, algumas delas já foram mencionadas nos itens anteriores, mas outras também se fazem necessárias como os testes unitários e a documentação do código fazem que também fazem parte do escopo do projeto.

   [AngularJS]: <http://angularjs.org>	
   [Html5]: <https://www.w3.org/TR/html5/>
   [Bootstrap]: <http://getbootstrap.com/>
   [Java]: <http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html>
   [Spring-boot]: <https://projects.spring.io/spring-boot/>
   [Rest]: <https://www.w3.org/2001/sw/wiki/REST>
   [Swagger]: <https://swagger.io/>  
   [Angular5]: <https://blog.angular.io/version-5-0-0-of-angular-now-available-37e414935ced>
   [Bootstrap3]: <https://getbootstrap.com/docs/3.3/>
   [Spring-data-rest]: <https://spring.io/projects/spring-data-rest>
   [Restful]:<https://www.devmedia.com.br/introducao-a-web-services-restful/37387>
   [Hateoas]: <https://spring.io/understanding/HATEOAS>
   
   

