use libraryFive;

select * from AUTOR;
select * from AUTOR_OBRA;
select * from DEVOLUCAO;
select * from EMPRESTIMO;
select * from EXEMPLAR;
select * from OBRA;
select * from RESERVA;
select * from USUARIO;
select * from USUARIO_TELEFONE;

-- ------------------------------- USUARIO - INICIO -------------------------------
select * from USUARIO
inner join USUARIO_TELEFONE;
-- ------------------------------- USUARIO - FIM ----------------------------------
--
--
-- ------------------------------- OBRA - INICIO  ---------------------------------
-- Obra 1:n exemplar / Todas as obras que tiverem um exemplar
select * from OBRA as o
inner join EXEMPLAR as e;

-- Obra 1:n exemplar / Todas as obras que tiverem um exemplar e situacao x
select * from OBRA as o
inner join EXEMPLAR as e
where e.SITUACAO = 'Disponível';

select * from OBRA as o 
inner join EXEMPLAR as e
ON e.ID_EXEMPLAR = o.ID_OBRA;
-- ------------------------------- OBRA - FIM  ------------------------------------
--
--
-- ------------------------------- EXEMPLAR - INICIO  -----------------------------
-- Exemplar n:1 obra / Todas as obras que tiverem exemplar
SELECT * FROM EXEMPLAR as e
INNER JOIN OBRA as o;

-- Exemplar n:1 obra / Todas as obras que tiverem um exemplar e  titulo x
SELECT * FROM EXEMPLAR as e
INNER JOIN OBRA as o ON e.ID_EXEMPLAR=o.ID_OBRA
WHERE o.TITULO = 'Titulo 2';

-- Todos os exemplares com o ID x
SELECT * FROM EXEMPLAR as e
WHERE e.ID_OBRA = 15;

-- Exemplar n:1 obra / Todas as obras que tiverem um exemplar e situacao x
select * from EXEMPLAR as e
INNER JOIN OBRA
WHERE e.SITUACAO= 'Disponível';
-- ------------------------------- EXEMPLAR - FIM  --------------------------------
--
--
-- ------------------------------- EMPRESTIMO - INICIO  ---------------------------
select * from EMPRESTIMO as e
inner join USUARIO as u
where u.email = 'lucas12zarzur@gmail.com';
-- ------------------------------- EMPRESTIMO - FIM  ------------------------------
--
--
-- ------------------------------- AUTOR - INICIO  --------------------------------
select * from AUTOR as a
inner join OBRA as o
where o.TITULO = 'Livro teste 3';

select * from AUTOR_OBRA as ao
inner join OBRA as o
where o.TITULO = 'Livro teste 2';

select * from AUTOR_OBRA as ao
inner join AUTOR;
-- ------------------------------- AUTOR - FIM  -----------------------------------
--
--
-- ------------------------------- XXX - INICIO  --------------------------------

-- ------------------------------- XXX - FIM  --------------------------------
