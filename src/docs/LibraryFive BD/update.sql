use libraryFive;

-- Setar uma data prevista para devolucao diferente para um emprestimo com ID x
update EMPRESTIMO set DATA_PREVISTA_DEVOLUCAO = '2020/05/19' where ID_EMPRESTIMO = 9;

-- Setar uma data de emprestimo diferente para um emprestimo com ID x
update EMPRESTIMO set DATA_EMPRESTIMO = '2020/05/10' where ID_EMPRESTIMO = 9;

update EXEMPLAR set SITUACAO = 'Disponivel' where ID_EXEMPLAR = 2;

update USUARIO set EMAIL = 'admin@utfpr.edu' where ID_USUARIO = 123;