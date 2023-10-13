<h3 align="center">
  API REST COM ELASTICSEARCH NO PADRÃO DE ARQUITETURA LIMPA

### Contexto

Uma `API REST` implementando um `CRUD` básico com persistência no elasticsearch, seguindo o padrão Clean Architecture.

Nessa `API`, foram aplicados os princípios da Clean Architecture para estruturar o projeto de forma organizada, escalável e altamente testável. A arquitetura é dividida em camadas, como a camada de `entrypoints` (responsável por receber as requisições HTTP e retornar as respostas), a camada de `dataproviders` (responsável pela interação com o banco de dados ou outras fontes de dados externas) e a camada `core` (onde são definidas os casos de uso e as regras de negócio).

O objetivo principal dessa API é oferecer operações básicas de criação, atualização e exclusão de recursos e busca avançada por termos no elastisearch.

<h4>Tecnologias</h4>
<ul>
  <li> Java 17
  <li> Spring Boot 
  <li> Maven
  <li> Lombok
  <li> Docker
  <li> Elasticsearch
  <li> Swagger
</ul>

### Requisitos

Para rodar esta aplicação, você deve ter instalado em seu computador:

<ul>  
   <li><a href="https://docs.docker.com/get-started/" target="_blank">Docker</a>
</ul>

### Configuração do Elaticsearch

Para configurar de maneira eficiente o seu índice no elasticsearch você deve realizar os seguintes passos:

1. Execute o comando docker-compose para iniciar o contêiner do Elasticsearch. Após o contêiner ser iniciado com sucesso, abra o seu terminal e digite os seguintes comandos: 

```
curl -X PUT "http://localhost:9200/product-index" -H "Content-Type: application/json" -d '{
  "settings": {
    "index": {
      "max_result_window": 10000
    },
    "analysis": {
      "analyzer": {
        "edge_ngram_analyzer": {
          "type": "custom",
          "tokenizer": "edge_ngram_tokenizer"
        }
      },
      "tokenizer": {
        "edge_ngram_tokenizer": {
          "type": "edge_ngram",
          "min_gram": 3,
          "max_gram": 20,
          "token_chars": ["letter", "digit"]
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "id": {
        "type": "keyword"
      },
      "name": {
        "type": "text",
        "fields": {
          "keyword": {
            "type": "keyword"
          }
        },
        "analyzer": "edge_ngram_analyzer",
        "search_analyzer": "standard"
      },
      "description": {
        "type": "text",
        "fields": {
          "keyword": {
            "type": "keyword"
          }
        },
        "analyzer": "edge_ngram_analyzer",
        "search_analyzer": "standard"
      },
      "price": {
        "type": "double",
        "fields": {
          "keyword": {
            "type": "keyword"
          }
        }
      }
    }
  }
}'
```
Este código basicamente configura um novo índice no Elasticsearch chamado `product-index` com as seguintes definições:

- Limita o número máximo de resultados em uma pesquisa para 10.000.
- Define um analisador personalizado chamado "edge_ngram_analyzer" que ajuda na pesquisa com correspondência de prefixo.
- Mapeia os campos `id`, `name`, `description`, e `price`, onde `name` e `description` são configurados para pesquisas de correspondência de prefixo.
- Adiciona campos `keyword` para os campos `name` e `description` para permitir pesquisas exatas e de prefixo.

Essa configuração é comum em cenários onde você precisa pesquisar e indexar dados, como em um sistema de comércio eletrônico, especialmente quando deseja realizar pesquisas eficazes em nomes e descrições de produtos.

### Executando o projeto

1. Clone ou baixe o projeto do repositório para o seu `Computador`.

2. Navegue até a raíz do diretório do projeto, abra o `terminal e execute o comando:`
<ul>
   <li> sudo docker-compose up -d
</ul>

3. Após a execução dos processos anteriores, estarão disponíveis para acesso em seu browser os seguintes `endpoints` para teste:

<ul>
   <li> Swagger
</ul>
<a href="http://localhost:8887/v1/api/swagger-ui/index.html" target="_blank" title="Clique e navegue!">
<img align="center" src="https://raw.githubusercontent.com/RodrigoAntonioCruz/assets/main/swagger-prod.png" /></a>

<ul>
   <li> Kibana
</ul>
<a href="http://localhost:5601/app/management/data/index_management/indices" target="_blank" title="Clique e navegue!">
<img align="center" src="https://raw.githubusercontent.com/RodrigoAntonioCruz/assets/main/kibana.png" /></a>

