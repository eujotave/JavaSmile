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

package aluno.ifpb.edu.br.JavaSmile.Controller;

import com.clinica.home.HomeDash;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import swing.EventLogin;
import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class GoogleAuth {

    private static final String CREDENTIALS_FILE_PATH; // Caminho para o arquivo JSON baixado

    static {
        try {
            CREDENTIALS_FILE_PATH = JsonUtil.getPath() + File.separator + "credentials.json";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/userinfo.profile");
    private static final String TOKENS_DIRECTORY_PATH = "tokens"; // Diretório para armazenar tokens

    private EventLogin event;

    // Construtor para inicializar o event
    public GoogleAuth(EventLogin event) {
        this.event = event;
    }

    // Método de instância para autenticação
    public void authenticateWithGoogle(boolean deletarCredenciais) throws IOException {
        final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();

        // Carregar o arquivo de credenciais
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(CREDENTIALS_FILE_PATH));

        // Configurar o fluxo de autorização
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        // Configura o receptor local para o fluxo de autorização
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        // Autenticação
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        if (credential == null) {
            JOptionPane.showMessageDialog(null, "Falha ao obter credenciais.");
            return;
        }

        // Pega as informações do usuário
        Oauth2 oauth2 = new Oauth2.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("Google-OAuthLoginExample")
                .build();
        Userinfo userInfo = oauth2.userinfo().get().execute();

        // Verifica se o login foi bem-sucedido
        if (userInfo != null && userInfo.getId() != null) {
            // Login com Google foi válido
            if (event != null) {
                System.out.println("Login bem sucedido: " + userInfo.getName());
                HomeDash homeDash = new HomeDash();
                homeDash.setVisible(true);

                if (deletarCredenciais) {
                    File diretorio = new File("tokens");
                    try {
                        Files.delete(Path.of(diretorio + File.separator + "StoredCredential"));
                        System.out.println("Arquivo deletado com Sucesso!");
                    } catch (IOException e) {
                        e.getMessage();
                        System.out.println("Erro ao deletar arquivo!");
                    }
                }
            }
        } else {
            System.out.println("Falha na autenticação do Google.");
        }
    }
}