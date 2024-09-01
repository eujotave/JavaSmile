package aluno.ifpb.edu.br.JavaSmile.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
* - Essa classe representa o Assistente, ou basicamente o usuário principal
* - Responsável por criar, editar e remover Pacientes, Procedimentos, Consultas e Dentistas
*/
@SuperBuilder
@Data
@NoArgsConstructor
public class Assistente extends Funcionario {

}

