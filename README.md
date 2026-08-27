# BookShelf App

> Template de projeto Android usado no curso **Arquitetura no Desenvolvimento Android Moderno**, do [CAMP](https://androidmoderno.com.br).

Este repositório é o **ponto de partida** para os alunos do curso. Ele não é um app completo nem um exemplo de "arquitetura ideal" pronta — é propositalmente simples, e cresce em complexidade ao longo das aulas, aula após aula, conforme novas necessidades reais do app forçam novas decisões arquiteturais.

## Sobre o curso

**Arquitetura no Desenvolvimento Android Moderno** ensina como decisões arquiteturais realmente surgem durante a evolução de um aplicativo Android — não como uma lista de padrões para decorar e aplicar de antemão.

A premissa do curso é simples: **evitar abstrações prematuras**. Em vez de começar com Clean Architecture, camadas de domínio, `Repository`, `UseCase`, injeção de dependência e testes de tudo já no primeiro commit, partimos de um código propositalmente incompleto e vamos evoluindo o projeto *à medida que os problemas reais aparecem* — exatamente como aconteceria em um app de verdade. Cada abstração introduzida no curso é uma resposta a uma dor concreta do código, não uma antecipação teórica.

Saiba mais e inscreva-se em **[androidmoderno.com.br](https://androidmoderno.com.br)**.

## O que é o BookShelf

O BookShelf é um app de catálogo e biblioteca pessoal de livros, consumindo a [Open Library API](https://openlibrary.org/developers/api). O objetivo do app em si é secundário — ele existe apenas como veículo para discutir arquitetura. O aluno vai:

- pesquisar livros pela Open Library;
- visualizar uma lista de resultados;
- abrir os detalhes de uma obra;
- (futuramente) salvar livros em uma biblioteca pessoal e acompanhar status de leitura.

### O que já vem pronto neste template

Este ponto de partida contém **apenas** a camada de apresentação e navegação, para que as aulas comecem direto na parte interessante:

- **Design System** completo (`core/ui/designsystem`) — tema claro/escuro, cores, tipografia, shapes, espaçamentos e componentes reutilizáveis (`BookCover`, `BookListItem`, `BookSearchField`, `EmptyContent`, `ErrorContent`, `ReadingStatusChip`, etc.), todos *stateless*.
- **Telas de demonstração** (`feature/discover`, `feature/library`, `feature/bookdetails`) já usando os componentes do design system, mas sem lógica real — apenas estados vazios/estáticos.
- **Navegação** com Jetpack Navigation 3, incluindo back stack independente por aba da bottom bar.
- Um `HttpClient` Ktor básico (`network/OpenLibraryHttpClient.kt`) já apontando para a Open Library — a única peça de infraestrutura de rede que existe até agora.

### O que **não** existe ainda (de propósito)

Nada disto está implementado — e é exatamente aí que o curso começa:

- Busca de livros de verdade (a `DiscoverScreen` não chama nenhuma API ainda);
- `ViewModel` / gerenciamento de estado;
- `Repository`, `UseCase` ou qualquer camada de domínio;
- Persistência local (Room ou similar);
- Injeção de dependência;
- Testes automatizados além dos exemplos padrão do Android Studio.

Se você chegou aqui fora do curso: isso não é um esquecimento, é intencional.

## Stack técnica

| Categoria | Tecnologia |
|---|---|
| Linguagem | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Navegação | Navigation 3 (Compose) |
| Rede | Ktor Client |
| Serialização | Kotlinx Serialization |
| Imagens | Coil 3 |
| API de dados | [Open Library](https://openlibrary.org/developers/api) |
| Build | Gradle (Kotlin DSL), AGP 9.3.1 |

**Requisitos mínimos:** `compileSdk`/`targetSdk` 37, `minSdk` 30, JVM 11.

## Como começar

Este projeto é um **template do GitHub** — você não deve dar fork nem clonar este repositório diretamente.

1. No topo desta página, clique em **[Use this template → Create a new repository](../../generate)**.
2. Dê um nome ao seu repositório (ex: `bookshelf-app-meu-nome`) e crie-o na sua própria conta.
3. Clone **o seu repositório recém-criado**, não este:
   ```bash
   git clone https://github.com/<seu-usuario>/<seu-repositorio>.git
   ```
4. Abra o projeto no **Android Studio** (versão mais recente recomendada) e deixe o Gradle sincronizar.
5. Rode o app em um emulador ou dispositivo com Android 11 (API 30) ou superior.
6. Acompanhe as aulas em **[androidmoderno.com.br](https://androidmoderno.com.br)** e vá evoluindo o seu repositório junto comigo, aula a aula.

> Dica: crie uma branch ou uma tag antes de cada aula começar a mexer no código, para sempre poder comparar o "antes" e o "depois" da sua própria evolução.

## Estrutura do projeto

```
app/src/main/java/br/com/androidmoderno/bookshelf/
├── core/ui/designsystem/   # tema, componentes visuais reutilizáveis, previews
├── feature/discover/       # tela de descoberta/pesquisa de livros
├── feature/library/        # tela da biblioteca pessoal
├── feature/bookdetails/    # tela de detalhes de um livro
├── navigation/             # destinos e estado de navegação (Navigation 3)
├── network/                # client Ktor para a Open Library
├── BookShelfApp.kt         # composable raiz, monta navegação + bottom bar
└── MainActivity.kt
```

## Créditos e dados

Os dados de livros são fornecidos pela [Open Library](https://openlibrary.org/developers/api), um projeto do Internet Archive. Este é um projeto educacional sem fins comerciais.

## Licença

<!-- TODO: definir a licença deste template (ex: MIT) -->
A definir. Enquanto isso, considere este código "todos os direitos reservados" para fins de uso fora do escopo do curso.

## Autor

Feito por **Douglas Motta**, criador da CAMP.

- Site do curso: [androidmoderno.com.br](https://androidmoderno.com.br)
- YouTube: [@DouglasMotta](https://www.youtube.com/@DouglasMotta)
- Instagram: [@dmotta91](https://www.instagram.com/dmotta91/)
