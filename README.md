AgilStore - Sistema de Gerenciamento de Produtos
Este projeto é um sistema simples de gerenciamento de produtos. Ele permite adicionar, listar, atualizar, excluir e buscar produtos. Os dados dos produtos são armazenados em um arquivo JSON, facilitando a persistência e leitura.

Tecnologias Usadas
Java 8+: A linguagem de programação usada para implementar o sistema.
Jackson: Biblioteca usada para realizar a serialização e desserialização de objetos Java em JSON e vice-versa.
Scanner: Usado para leitura de dados do usuário no terminal.
Funcionalidades
Adicionar Produto: Permite adicionar um novo produto, incluindo nome, categoria, quantidade e preço.
Listar Produtos: Exibe todos os produtos cadastrados ou permite filtrar por categoria, nome, quantidade ou preço.
Atualizar Produto: Permite atualizar as informações de um produto existente, como nome, categoria, quantidade ou preço.
Excluir Produto: Exclui um produto com base no ID.
Buscar Produto: Permite buscar um produto por ID ou nome.
Como Rodar
Pré-requisitos
Antes de rodar o código, verifique se você tem as seguintes dependências instaladas:

Java 8+: Caso não tenha o Java instalado, você pode baixar e instalar a partir do site oficial do Java.
IDE Java: Você pode usar qualquer IDE Java como IntelliJ IDEA, Eclipse ou NetBeans.
Jackson: A biblioteca Jackson é usada para manipulação de JSON. Caso ainda não tenha, ela pode ser adicionada ao seu projeto.
Passos para Executar
Baixar o código:

Clone este repositório ou baixe os arquivos manualmente.
Configuração do Projeto (se necessário):

Caso esteja utilizando uma ferramenta de gerenciamento de dependências como o Maven, adicione a dependência do Jackson no arquivo pom.xml:

xml
Copiar código
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.12.3</version>
</dependency>
Se estiver usando Gradle, adicione no seu build.gradle:

gradle
Copiar código
implementation 'com.fasterxml.jackson.core:jackson-databind:2.12.3'
Executar o código:

Abra o arquivo GerenciaProdutos.java na sua IDE.
Execute o método menu() para começar a interação com o sistema de gerenciamento de produtos.
Interação no terminal:

O sistema vai exibir um menu no terminal com as opções de adicionar, listar, atualizar, excluir e buscar produtos.
Utilize os números correspondentes para selecionar as opções desejadas.
Exemplo de Execução
O programa pode ser iniciado chamando o método menu() dentro do GerenciaProdutos.java.
Após rodar, você verá um menu com opções como:
arduino
Copiar código
Escolha uma das opções abaixo:
1 - Adicionar produto;
2 - Listar produto;
3 - Atualizar produto;
4 - Excluir produto;
5 - Buscar produto;
0 - Sair do sistema.
Digite o número correspondente à opção desejada. Por exemplo, para adicionar um produto, digite 1 e siga as instruções.
Exemplo de Adição de Produto
Quando você escolher a opção de adicionar um produto, o sistema pedirá para informar:

Nome do produto
Categoria
Quantidade em estoque
Preço
Após inserir as informações, o produto será adicionado à lista e salvo no arquivo produtos.json.

Arquivo de Dados
Os dados dos produtos são armazenados no arquivo produtos.json.
Se o arquivo não existir, ele será criado automaticamente.
Estrutura do Projeto
bash
Copiar código
AgilStore/
│
├── com/
│   └── mycompany/
│       └── agilstore/
│           ├── Produto.java      # Definição da classe Produto
│           └── GerenciaProdutos.java  # Lógica do gerenciamento de produtos
└── produtos.json                # Arquivo de dados onde os produtos são armazenados
Contribuições
Sinta-se à vontade para contribuir para este projeto! Se você encontrou um bug ou deseja adicionar um recurso, crie uma issue ou um pull request.
