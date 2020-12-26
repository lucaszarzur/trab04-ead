# cadastro de acervo - INICIO ########################################################################

#inserindo autores
insert into biblioteca.autor (nome_autor) values ('Machado de Assis');
insert into biblioteca.autor (nome_autor) values ('José de Alencar');
commit;
select ID_AUTOR, NOME_AUTOR from biblioteca.autor;

#inserindo obra
insert into biblioteca.obra (titulo,ano_publicacao,tipo_obra) 
values ('Dom Casmurro',1899,'Literatura');
insert into biblioteca.autor_obra (id_obra, id_autor)
values (6,7);
insert into biblioteca.obra (titulo,ano_publicacao,tipo_obra) 
values ('Quincas Borba',1891,'Literatura');
insert into biblioteca.autor_obra (id_obra, id_autor)
values (7,7);
insert into biblioteca.obra (titulo,ano_publicacao,tipo_obra) 
values ('Iracema',1865,'Literatura');
insert into biblioteca.autor_obra (id_obra, id_autor)
values (8,8);
insert into biblioteca.obra (titulo,ano_publicacao,tipo_obra) 
values ('Senhora',1857,'Literatura');
insert into biblioteca.autor_obra (id_obra, id_autor)
values (9,8);
commit;
select * from biblioteca.obra;
select * from biblioteca.autor_obra;

insert into biblioteca.exemplar (data_aquisicao, id_obra, situacao) 
values (sysdate(),6,'Disponível');
insert into biblioteca.exemplar (data_aquisicao, id_obra, situacao) 
values (sysdate(),7,'Disponível');
insert into biblioteca.exemplar (data_aquisicao, id_obra, situacao) 
values (sysdate(),8,'Disponível');
insert into biblioteca.exemplar (data_aquisicao, id_obra, situacao) 
values (sysdate(),9,'Disponível');
commit;
select * from biblioteca.exemplar;
# cadastro de acervo - FIM ###########################################################################

# cadastro de usuario - INICIO #######################################################################
insert into biblioteca.usuario (id_usuario, nome, email, tipo_usuario, rua, bairro, numero, complemento,
estado, cidade, data_nascimento, status, senha, admin)
values(534620, 'Maria da Silva', 'maria@gmail.com', 'Aluno', 'Rua Teste', 'Centro', 
34, 'Apto. 200', 'Paraná', 'Curitiba', STR_TO_DATE('17-09-1995','%d-%m-%Y'), 'Ativo', 'teste', 0);
insert into biblioteca.usuario (id_usuario, nome, email, tipo_usuario, rua, bairro, numero, complemento,
estado, cidade, data_nascimento, status, senha, admin)
values(534678, 'José da Silva', 'jose@gmail.com', 'Servidor', 'Rua Teste', 'Centro', 
34, 'Apto. 101', 'Paraná', 'Curitiba', STR_TO_DATE('17-09-1990','%d-%m-%Y'), 'Ativo', 'teste', 1);
insert into biblioteca.usuario_telefone (id_usuario, numero_telefone, principal)
values (534620,41999980102,1);
insert into biblioteca.usuario_telefone (id_usuario, numero_telefone, principal)
values (534678,41999980105,1);
commit;
select * from biblioteca.usuario;
select * from biblioteca.usuario_telefone;
# cadastro de usuario - FIM ##########################################################################

# reserva - INICIO ###################################################################################
insert into biblioteca.reserva (id_usuario, id_exemplar, data_reserva)
values (534678,5,sysdate());
update biblioteca.exemplar set situacao = 'Reservado' where id_exemplar = 5;
commit;
select * from biblioteca.reserva;
select * from biblioteca.exemplar;
# reserva - FIM ######################################################################################

# emprestimo - INICIO ################################################################################
insert into biblioteca.emprestimo (id_usuario, id_exemplar, data_emprestimo, data_prevista_devolucao)
values (534620,2,sysdate(),sysdate()+5);
update biblioteca.exemplar set situacao = 'Emprestado' where id_exemplar = 5;
commit;
select * from biblioteca.emprestimo;
select * from biblioteca.exemplar;
# emprestimo - FIM ###################################################################################

# devolucao - INICIO #################################################################################
insert into biblioteca.devolucao (data_devolucao, id_emprestimo)
values (sysdate(),1);
update biblioteca.exemplar set situacao = 'Devolvido' where id_exemplar = 2;
commit;
select * from biblioteca.devolucao;
select * from biblioteca.exemplar;
# devolucao - FIM ####################################################################################