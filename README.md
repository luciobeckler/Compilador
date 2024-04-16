## Introdução

Compilador da linguagem "Xulambis" desenvolvido durante a materia optativa Compiladores, ministrada pela professora [Kênia Carolina](https://www.linkedin.com/in/k%C3%AAnia-carolina-a8686a280/) e desenvolvida inteiramente em Java, capaz de realizar as análises sintática, lexa e semântica da linguagem.

Este projeto foi um divisor de águas no meu entendimento da linguagem Java e nele pude exercitor alguns conceitos como:

- Lógica de programação: 🔍
- Estrutura de dados: 📊
- Detecção e tratamento de erros e exceções: ⚠️
- Criação de exceções personalizadas: ❗️
- Boas práticas de programação: 🛠️
- Introdução a padrões de projetos: 🏗️




## Descrição da Linguagem:

### Alfabeto:

- Digit: [0-9]
- Digits: (*Digit)*
- Numer: Digits(.Digits)*
- Letter: [A-Za-z]
- Id: Letter (Letter | Digit) *
- symbols: { , } , ; ,  . , < , > , = ,  != ,  ( ,  ) ,  / , + , - , *
- If: if
- While: while
- Float: float
- Bool: bool
- Break: break

### L = [A-Za-z] | [0-9] | {+} | {-} | {*} | {/} | {.} | {;} | {<} | {>} | {=}

## Gramática:

### P→ {0 - 9} | {A - Za - Z}*{0 - 9}* | {symbols} FALTA

## Palavras reservadas:

- int;
- float;
- bool
- true;
- false;
- while;
- if;
- break;
