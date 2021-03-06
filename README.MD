# Avalia��o T�cnica
- Esta � uma aplica��o web que tem como objetivo realizar o cadastro de ofertas e op��es de compras, efetuar o vinculo entre eles e processar a compra para os mesmos.

## Entradas para a Ofertas e Op��es de Compra
- Ambos consistem em formul�rios contendo as seguintes informa��es de cadastro:
-- Deal(Oferta) -> titleBuyOption, textBuyOption, createDateBuyOption, publishDateBuyOption, endDateBuyOption, urlBuyOption, totalSoldBuyOption, typeBuyOption, buyOptions.
-- Op��o de Compra (BuyOption) -> title, normalPrice, salePrice, percentagelDiscount, quantityCupom, startDate, endDate, 

##### Poss�veis Melhorias
- melhorar valida��o de campos
- melhorar as m�scaras de entrada
- implementar valida��o de campos obrigat�rios
- adicionar pagina��o na lista de resultados.
- adicionar filtros de ordena��o na lista de resultados.
- realizar uma an�lise de UX para verificar e melhorar a experi�ncia do usu�rio tanto na inser��o de dados como na maneira com que se demonstra os resultados.

## M�todo de Associa��o
- Para criticar criar uma associa��o basta selecionar os itens e clicar em associar, caso uma op��o j� esteja associada a uma oferta o sistema remove da primeira oferta e associa a segunda.

##### Poss�veis Melhorias
- Verificar ader�ncia � regra de neg�cio.

## Processamento de compra
- Conforme a documenta��o recebida, esse item ficou muito vago, sendo assim a cada clique o sistema simplesmente incrementa o numero de compras.

##### Poss�veis Melhorias
- Colear melhores especifica��es do fluxo de neg�cio.

## Tecnolog�as Utilizadas
  - Frontend: [Angular5], [Html5], [Bootstrap3]
  - Backend: [Java], [Spring-boot], [Spring-data-rest]
  - Integra��es: [Rest], [Restful], [Hateoas]
  - Documenta��o: [Swagger]

## Arquitetura 
- A solu��o foi feita em tr�s projetos distintos e independentes um espec�fico para o Frontend e outro para o Backend e mais um projeto integrador.

### Backend
- Solu��o desenvolvida em Java utilizando Spring-boot, Spring-data-rest, hibernate, JPA, Lombok 
 - API Restful dispon�vel na porta 8080 contendo documenta��o swagger dos endpoints dispon�vel em `http://localhost:8080/swagger-ui.html`.
  - API para cadastros e consultas das est�o dispon�veis em `http://localhost:8080/api/v1/deal` e `http://localhost:8080/api/v1/buyOption`.
  - API que efetuar� a associa��o e o processamento da venda est�o dispon�veis em `http://localhost:8080/api/v1/deal/associar` e `http://localhost:8080/api/v1/deal/processa/{dealId}/{buyOptionId}`.

### Frontend  
- Solu��o desenvolvida em Angular5, Html5 e CSS3 utilizando Bootstrap3, [Angular CLI](https://github.com/angular/angular-cli) e hospedada em um servidor NGINX. 

##### Melhorias
- melhoria no desacoplamento do c�digo em componentes distintos para que os mesmos possam ser reutilizados em outros lugares da aplica��o.

### Banco de dados
- para esta prova de conceito foi utilizado banco de dados em mem�ria, H2, os dados iniciais s�o populados atrav�s de um script sql na pasta de resources, que est� dispon�vel em http://localhost:8080/h2-console/ o mesmo faz uso da base padr�ojdbc:h2:mem:testdb .

### Build
- esta solu��o � composta por projetos independentes para o server side (backend) dispon�vel por padr�o em `http://localhost:8080` e o client (frontend) dispon�vel por padrao em `http://localhost:4200`, ambos os projetos podem ter o build executados de forma independente. Existe um terceiro projeto integrador capaz de executar o build de ambos os projetos de forma simult�nea.

##### Parent
- esse projeto visa englobar os outros dois como sendo m�dulos de um projeto principal, este projeto pode ser executado atrav�s de Docker Compose ou de forma nativa utilizando maven e node.

##### Parent com Docker Composer
- para subir o servidor dessa aplica��o � poss�vel gerar e executar dois container docker atrav�s do Docker Compose, basta acessar o diret�rio raiz do projeto peixe-parent e executar (REQUESITO: ambas as imagens j� devem estar geradas)
```sh 
$ docker-compose -f docker-compose.yml up -d --force-recreate --build
``` 
- as duas imagens docker previamente geradas chamadas `peixe-server` e `peixe-client` ser�o executadas atrav�s de containers dispon�veis nas portas `8080` (backend) e `4200` (frontend), caso os containers j� existam os mesmos ser�o destru�dos e recriados.

####### Melhorias
adicionar o processo de gerar as imagens ao docker composer.

##### Backend com Docker
- para subir o servidor dessa aplica��o � poss�vel gerar um container docker atrav�s do Dockerfile  basta acessar o diret�rio raiz do projeto `peixe-backend `e executar
```sh 
$ mvn clean package docker:build
``` 
- ser� gerado uma imagem docker chamada `peixe-server-0.1.0` que pode ser executada atrav�s do comando
```sh 
$ docker run -d --name peixe-server -p8080:8080 peixe-server
```
##### Backend com Maven
- para compilar este projeto basta acessar o diret�rio raiz do projeto `peixe-backend` e executar o comando 
```sh 
$ mvn clean package
``` 
- para executar a aplica��o basta acessar o diret�rio raiz do projeto ``peixe-backend e executar o comando 
```sh 
$ mvn spring-boot:run
``` 
##### Frontend com Docker
- para subir a aplica��o � poss�vel gerar um container docker atrav�s do Dockerfile  basta acessar o diret�rio raiz do projeto `peixe-frontend` e executar
```sh 
$ docker build -t peixe-client .
``` 
- ser� gerado uma imagem docker chamada `peixe-client` que pode ser executada atrav�s do comando
```sh 
$ docker run -d --name peixe-client -p4200:4200 peixe-client
```
##### Frontend com Node
- para compilar este projeto basta acessar o diret�rio raiz do projeto `peixe-frontend` e executar o comando 
```sh 
$ npm install
``` 
- para executar a aplica��o basta acessar o diret�rio raiz do projeto `peixe-frontend` e executar o comando 
```sh 
$ ng serve
``` 
## Testes
##### Frontend (n�o foram implementados)
- Testes unit�rios: executar o comando `ng test` para rodar os testes unitarios via [Karma](https://karma-runner.github.io).
- Teste ponta a ponta: executar o comando `ng e2e` para rodar os testes ponta a ponta via [Protractor](http://www.protractortest.org/).

##### Backend
- Testes unit�rios: .
- Teste ponta a ponta: .

## Documenta��o
- esta solu��o possui documenta��o em ambos c�digos fontes e a documenta��o da API Rest est� dispon�vel atrav�s da ferramenta Swagger em /swagger-ui.html

### Observa��es 
> Alguns pontos foram implementados por�m precisam de melhorias, algumas delas j� foram mencionadas nos itens anteriores, mas outras tamb�m se fazem necess�rias como os testes unit�rios e a documenta��o do c�digo fazem que tamb�m fazem parte do escopo do projeto.

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
   
   

