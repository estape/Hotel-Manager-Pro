# Hotel Manager Pro

## Descrição

O Hotel Manager Pro é um sistema de gerenciamento de hotel desenvolvido como parte de um trabalho acadêmico. Ele oferece funcionalidades para facilitar o gerenciamento de hóspedes, funcionários, reservas, quartos de reserva e tipos de quarto em um hotel. A interação do usuário é realizada por linha de comando, seguindo a metodologia Model-View-Controller (MVC).

## Funcionalidades

O sistema Hotel Manager Pro oferece as seguintes funcionalidades:

1. **Gerenciamento de Hóspedes**: Os funcionários podem inserir, atualizar e excluir informações dos hóspedes, incluindo detalhes como nome, contato e informações de pagamento.
2. **Gerenciamento de Funcionários**: Os funcionários podem inserir, atualizar e excluir informações dos funcionários, como nome, cargo, salário e dados de contato.
3. **Gerenciamento de Reservas**: Os funcionários podem inserir novas reservas, atualizar o status das reservas (como confirmadas, canceladas, etc.) e excluir reservas.
4. **Gerenciamento de Quartos da Reserva**: Os funcionários podem inserir, atualizar e excluir quartos de uma reserva, associando quartos específicos a uma reserva e modificando essa associação conforme necessário.
5. **Gerenciamento de Tipos de Quarto**: Os funcionários podem inserir, atualizar e excluir tipos de quarto, definindo características como capacidade de ocupação, preço por noite e disponibilidade de comodidades.

## Tecnologias Utilizadas

O sistema Hotel Manager Pro foi desenvolvido utilizando as seguintes tecnologias:

- Linguagem de Programação: Java
- Framework: Spring CLI
- Gerenciador de Dependências: Maven

## Metodologia

O Hotel Manager Pro segue a metodologia Model-View-Controller (MVC) para organizar e estruturar o código-fonte. Nesta abordagem:

- O **Model** representa a estrutura de dados do sistema, incluindo classes para hóspedes, funcionários, reservas, quartos e tipos de quarto.
- A **View** corresponde à camada de interação com o usuário por linha de comando, onde são exibidos menus e prompts para entrada de dados.
- O **Controller** é responsável por receber e processar as solicitações do usuário, interagindo com o modelo e atualizando a visualização conforme necessário.

## Instalação e Uso

Para instalar e utilizar o Hotel Manager Pro, siga os passos abaixo:

1. Clone o repositório para sua máquina local:
git clone https://github.com/seu-usuario/hotel-manager-pro.git
