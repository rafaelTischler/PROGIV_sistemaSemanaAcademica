# Sistema de Gerenciamento da SeTEIC - IFFar-SVS

Este projeto é um sistema web desenvolvido para gerenciar a **Semana Tecnológica do Eixo de Informação e Comunicação (SeTEIC)** do **Instituto Federal Farroupilha - Campus São Vicente do Sul**. Ele permite o controle de eventos, oficinas, palestras, inscrições e validação de presença por QRCode.

## ✨ Funcionalidades Principais

### 👩‍💻 Perfil Administrador
- Cadastro e gerenciamento de edições da SeTEIC (ex: "17ª Semana Tecnológica").
- Cadastro e edição de subeventos:
  - Oficinas
  - Palestras
- Controle de informações:
  - Título
  - Descrição/Conteúdo
  - Ministrante(s)
  - Data e horário
  - Local
  - Número de vagas
- Visualização da lista de inscritos
- Geração e leitura de **QRCodes para validação de presença**

### 👨‍🎓 Perfil Aluno
- Cadastro e login de usuário
- Visualização das edições da SeTEIC disponíveis
- Inscrição em oficinas e palestras
- Geração automática de QRCode pessoal para validação de presença
- Histórico de participação em eventos

## 💡 Funcionalidades

1. **Certificação Automática**  
   Geração de certificados em PDF para alunos com presença validada em atividades.

2. **Filtro de Eventos por Categoria ou Data**  
   Interface para facilitar a busca por eventos desejados.

3. **Envio de Emails de Confirmação**  
   Ao se inscrever ou ter presença validada, o aluno recebe um email automático.

4. **Exportação de Presenças em CSV**  
   Para fins de controle e arquivamento externo pelo administrador.

5. **Sistema de Avaliação das Atividades**  
   Ao final do evento, o aluno pode avaliar o conteúdo ministrado.

## 🧰 Tecnologias Utilizadas

- Java
- Spring Boot
- HTML e CSS
- Banco de Dados XXX
- ZXing (geração e leitura de QRCodes)

## 🗃️ Estrutura do Banco de Dados (Exemplo Simplificado)

### 📌 EventoGeral
- `id`
- `nome`
- `ano`
- `descrição`

### 📌 Subevento
- `id`
- `titulo`
- `tipo` (palestra/oficina)
- `conteúdo`
- `dataHoraInicio`
- `dataHoraFim`
- `local`
- `vagas`
- `ministrante`
- `eventoGeral_id` (FK)

### 📌 Usuario (Aluno)
- `id`
- `nome`
- `email`
- `senha` (criptografada)
- `QRCode`

### 📌 Inscricao
- `id`
- `usuario_id` (FK)
- `subevento_id` (FK)
- `dataInscricao`
- `status` (confirmado/presente)

### 📌 Administrador
- `id`
- `nome`
- `email`
- `senha` (criptografada)

Desenvolvido por Carolini Bassan Carlé, Djonathan Vinicius Briesch dos Santos, Maurício Carvalho Cogo e Rafael Müller Tischler.
