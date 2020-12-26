use libraryFive;

select * from USUARIO;
insert into USUARIO(ID_USUARIO, NOME,SENHA,COMPLEMENTO,ADMIN,DATA_NASCIMENTO,CIDADE,EMAIL,BAIRRO,ESTADO,RUA,NUMERO,STATUS,TIPO_USUARIO)
values(123, 'Lucas','123','complemento',true,'1995/08/16','curitiba','admin@ufpr.com.br','bairro','estado','rua',22,
'Ativo','ALUNO');

insert into OBRA (TITULO, ANO_PUBLICACAO, TIPO_OBRA)
values('JAVA use a cabeça', '2008', 'Outros');

-- Autor pode ser criado sozinho
select * from AUTOR;
insert into AUTOR (NOME)
values ('Leonardo da Vinci');

-- Obra pode ser criada sozinha
select * from OBRA;
insert into OBRA (TITULO, ANO_PUBLICACAO, TIPO_OBRA)
values('Titulo 2', '2020', 'Outros');

-- Quando criar essa?
insert into AUTOR_OBRA (ID_OBRA, ID_AUTOR)
values(1, 1);

-- Precisa que a obra esteja criada
select * from EXEMPLAR;
insert into EXEMPLAR (DATA_AQUISICAO, SITUACAO, ID_OBRA)
values('2001-01-01', 'Emprestado', 1);

-- Precisa que o usuario e o exemplar estejam criados
select * from EMPRESTIMO;
insert into EMPRESTIMO (DATA_EMPRESTIMO, DATA_PREVISTA_DEVOLUCAO, ID_USUARIO, ID_EXEMPLAR)
values ('2020-04-06', '2020-04-13', 123, 2);

-- Precisa que o usuario esteja criado
insert into USUARIO_TELEFONE (NUMERO_TELEFONE,PRINCIPAL,ID_USUARIO)
values ('41997241970',true, 11);