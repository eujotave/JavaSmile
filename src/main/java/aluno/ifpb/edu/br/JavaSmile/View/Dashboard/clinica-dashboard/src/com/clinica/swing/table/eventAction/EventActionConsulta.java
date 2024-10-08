/*
 * Copyright 2024 Pedro Paulo Delgado Alves e João Victor Moura Barbosa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The software licensed under the Apache License, Version 2.0 is further
 * subject to the Commons Clause License Condition v1.0.
 */

package com.clinica.swing.table.eventAction;

import aluno.ifpb.edu.br.JavaSmile.Model.Consulta;
import java.io.IOException;

//define as ações que podem ser realizadas em uma consulta.
//interface implementada para permitir que as ações de edição e
// deleção sejam disparadas pelos botões criados na classe ActionConsulta.
public interface EventActionConsulta {
        
    public void delete(Consulta paciente) throws IOException;
    
    public void update(Consulta paciente);
    
}
