# Sistema de Gerenciamento da SeTEIC - IFFar-SVS

Este projeto é um sistema web desenvolvido para gerenciar a Semana Tecnológica do Eixo de Informação e Comunicação (SeTEIC) do Instituto Federal Farroupilha - Campus São Vicente do Sul. Ele permite o controle de eventos, oficinas, palestras, inscrições e validação de presença por QRCode.

---

## ✨ Funcionalidades Principais

### 👩‍💻 Perfil Administrador
- Cadastro e gerenciamento de edições da SeTEIC (ex: "17ª Semana Tecnológica").
- Cadastro e edição de subeventos, incluindo:
  - Oficinas
  - Palestras
- Controle das informações dos subeventos:
  - Título
  - Descrição/Conteúdo
  - Ministrante(s)
  - Data e horário
  - Local
  - Número de vagas
- Visualização da lista de inscritos.
- Geração e leitura de QRCodes para validação de presença.

### 👨‍🎓 Perfil Aluno
- Cadastro e login de usuário.
- Visualização das edições da SeTEIC disponíveis.
- Inscrição em oficinas e palestras.
- Geração automática de QRCode pessoal para validação de presença.
- Histórico de participação em eventos.

---

## 💡 Funcionalidades Adicionais

- **Certificação Automática:** Geração de certificados em PDF para alunos com presença validada em atividades.
- **Filtro de Eventos:** Por categoria ou data, facilitando a busca por eventos desejados.
- **Envio de Emails de Confirmação:** Ao se inscrever ou ter presença validada, o aluno recebe um email automático.
- **Exportação de Presenças em CSV:** Para controle e arquivamento externo pelo administrador.
- **Sistema de Avaliação das Atividades:** Ao final do evento, o aluno pode avaliar o conteúdo ministrado.

---

## 🧰 Tecnologias Utilizadas

- Java  
- Spring Boot  
- HTML e CSS  
- Banco de Dados (a definir)  
- ZXing (para geração e leitura de QRCodes)  

---

## 🗃️ Estrutura do Banco de Dados Atualizada

### Evento
- id: serial  
- nome: string  
- ano: int  
- descrição: string  
- created_at: timestamp  
- updated_at: timestamp  
- deleted: boolean  
- deleted_at: timestamp  

### Atividade
- id: serial  
- titulo: string  
- tipo: ENUM [Palestra, Oficina]  
- conteudo: string  
- dataHoraInicio: datetime  
- dataHoraFim: datetime  
- local: string  
- qr_token: string (default null)  
- qr_expiration: timestamp (gerado + 5 minutos após criação)  
- vagas: int  
- evento: FK -> Evento.id  
- created_at: timestamp  
- updated_at: timestamp  
- deleted: boolean  
- deleted_at: timestamp  

### Usuario
- id: serial  
- nome: string  
- email: string (único)  
- senha: string (hash)  
- cpf: string (único)  
- tipo: ENUM [Comum, Admin]  
- created_at: timestamp  
- updated_at: timestamp  
- deleted: boolean  
- deleted_at: timestamp  

### Ministrante
- id: serial  
- nome: string  
- biografia: string  

### Atividade_Ministrante
- atividadeId: FK -> Atividade.id  
- ministranteId: FK -> Ministrante.id  

### Inscricao
- id: serial  
- usuarioId: FK -> Usuario.id  
- atividadeId: FK -> Atividade.id  
- dataInscricao: datetime  
- status: ENUM [Inscrito, Presente]  
- Constraint Unique(usuarioId, atividadeId) — impede inscrição dupla na mesma atividade  
- created_at: timestamp  
- updated_at: timestamp  
- deleted: boolean  
- deleted_at: timestamp  

---

## Desenvolvido por  
[Carolini Bassan Carlé](https://github.com/CarolBassan), [Djonathan Vinicius Briesch dos Santos](https://github.com/Djonathan-Briesch), [Maurício Carvalho Cogo](https://github.com/MauricioCogo) e [Rafael Müller Tischler](https://github.com/rafaelTischler).
