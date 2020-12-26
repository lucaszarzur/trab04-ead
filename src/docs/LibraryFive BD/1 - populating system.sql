use libraryFive;

-- Admin 1
insert into USUARIO(ID_USUARIO, NOME,SENHA,COMPLEMENTO,ADMIN,DATA_NASCIMENTO,CIDADE,EMAIL,BAIRRO,ESTADO,RUA,NUMERO,STATUS,TIPO_USUARIO)
values(123, 'Lucas','123','complemento',true,'1995/08/16','curitiba','admin@ufpr.com.br','bairro','estado','rua',22,
'Ativo','SERVIDOR');

-- Admin 2
insert into USUARIO(ID_USUARIO, NOME,SENHA,COMPLEMENTO,ADMIN,DATA_NASCIMENTO,CIDADE,EMAIL,BAIRRO,ESTADO,RUA,NUMERO,STATUS,TIPO_USUARIO)
values(1234, 'Lucas','123','complemento',true,'1995/08/16','curitiba','admin2@ufpr.com.br','bairro','estado','rua',22,
'Ativo','SERVIDOR');

-- Aluno 1
insert into USUARIO(ID_USUARIO, NOME,SENHA,COMPLEMENTO,ADMIN,DATA_NASCIMENTO,CIDADE,EMAIL,BAIRRO,ESTADO,RUA,NUMERO,STATUS,TIPO_USUARIO)
values(1235, 'Lucas','123','complemento',true,'1995/08/16','curitiba','aluno1@ufpr.com.br','bairro','estado','rua',22,
'Ativo','ALUNO');