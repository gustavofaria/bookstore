CREATE DATABASE Livraria;
USE Livraria;

CREATE TABLE livro (
  `livro_id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(128) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `preco` float NOT NULL,
  `quantidade` float NOT NULL,
  PRIMARY KEY (`livro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE genero (
  `genero_id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(128) NOT NULL,
  PRIMARY KEY (`genero_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE livro_has_genero (
  `genero_id` int(11) NOT NULL ,
  `livro_id` int(11) NOT NULL ,
  PRIMARY KEY (`genero_id`,`livro_id`),
  FOREIGN KEY (`genero_id`) REFERENCES genero(`genero_id`),
  FOREIGN KEY (`livro_id`) REFERENCES livro(`livro_id`)
) ;

CREATE TABLE pessoa(
  `pessoa_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(128) NOT NULL,
  `endereco` varchar(128) NOT NULL,
  `num_cartao` varchar(64) NOT NULL,
  `cvv` varchar(10) not null,
  `validade_cartao` integer not null,
  PRIMARY KEY (`pessoa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE compra(
  `compra_id` int(11) NOT NULL AUTO_INCREMENT ,
  `pessoa_id` int(11) NOT NULL,
  `livro_id` int(11) NOT NULL, 
  `quantidade` int(11) NOT NULL, 
  PRIMARY KEY (`compra_id`),
  FOREIGN KEY (`pessoa_id`) REFERENCES pessoa(`pessoa_id`),
  FOREIGN KEY (`livro_id`) REFERENCES livro(`livro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- INSERT INTO livro (titulo, autor, preco) VALUES ('titulo inicial', 'primeiro autor', 0);
-- INSERT INTO genero (genero_id,descricao) VALUES (1, 'romance');
--
-- INSERT INTO livro_has_genero(genero_id,livro_id) VALUES (1,1);

