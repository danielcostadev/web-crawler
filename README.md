# WebCrawler ANS - Autor: Daniel Costa - ACZG 6.0

WebCrawler ANS é uma aplicação Groovy que que acessa o portal da Agência Nacional de Saúde Suplementar e realiza algumas interações automatizadas, esta aplicação está sendo desenvolvida como um dos projetos do ACZG 6.0.

## Tecnologias Utilizadas

- **Groovy**: Linguagem de programação principal utilizada no desenvolvimento do projeto.
- **Gradle**: Project Builder
- **Git e GitHub**: Para versionamento e armazenamento do projeto
- **JDK 11**: Versão do Java Development Kit utilizada como base para o projeto Groovy.
- **IntelliJ IDEA**: Ambiente de Desenvolvimento Integrado (IDE) utilizado.

## Bibliotecas Utilizadas
- **Jsoup**: é uma biblioteca em Java para trabalhar com HTML. Ela permite que os desenvolvedores façam scraping de páginas da web, extraiam e manipulem dados de documentos HTML, e realizem operações de análise e modificação de conteúdo.
- **Http-Builder-NG**: é uma biblioteca Groovy para fazer requisições HTTP de maneira simples e eficiente. É uma evolução do HttpBuilder original, oferecendo uma interface mais intuitiva e recursos aprimorados para facilitar o trabalho com APIs REST e web services.
- **OpenCSV**: é uma biblioteca em Java que facilita a leitura e escrita de arquivos no formato CSV (Comma-Separated Values) Valores separados por vírgula.

## Funcionalidades

1. Baixa os aquivos da documentação do padrão TISS (Troca de Informações na Saúde Suplementar), na versão mais recente.
2. Acessa o campo "Histórico das versões dos Componentes do Padrão TISS" e Coleta, na tabela, os dados de competência, publicação e início de vigência a partir da competência de data jan/2016;
3. Acessa o campo "Tabelas relacionadas" e baixa a "Tabela de erros no envio para a ANS".

## Minhas breves considerações

As possibilidaeds "mágicas" que as técnicas WebCrawler e WebScraping nos proporcionam são surreais, tornar procesos rotineiros automatizados é muito gratificante.
Quando assistimos o log do robô acessando as páginas e realizando as tarefas, ou então quando algum erro aparece e debugamos para entender o que de fato está ocorrendo,
o nosso conhecimento e o fascínio sobre as ferramentas aumenta.

O aprendizado adiquirido ao desenvolver essa pequena aplicação será útil para projetos futuros.

## Como Executar

### Pré-requisitos

- Groovy 3.0.13 ou superior instalado.
- IDE como IntelliJ IDEA (opcional, mas recomendado).

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/danielcostadev/web-crawler.git

2. Navegue até a pasta do projeto

   ```bash
   cd web-crawler/

3. Compile o projeto

   ```bash
   gradle clean build

4. Execute o projeto

   ```bash
   java -cp build App

### Download do arquivo JAR v1.0.0

---

- **Link:** [Clique aqui para baixar](https://github.com/danielcostadev/web-crawler/raw/master/app-1.0-SNAPSHOT.jar)
- Após baixar o arquivo RAR, extraia escolhendo a opção "Extrair aqui".
- Com auxílio do seu terminal navegue até a pasta que foi extraída.
- Para executar a aplicação utilize o comando abaixo:

   ```bash
  java -jar app-1.0-SNAPSHOT.jar

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

- **MIT LICENSE:** [Ver licença](https://github.com/danielcostadev/web-crawler/blob/master/LICENSE)


## Contato

Para maiores informações ou dúvidas, entre em contato:

- **Nome:** Daniel Costa
- **LinkedIn:** [DanielCostaDev](https://www.linkedin.com/in/danielcostadev)
