package br.edu.vianna.gibiapp.service;

import java.util.concurrent.ExecutionException;

import br.edu.vianna.gibiapp.dto.UserDTO;
import br.edu.vianna.gibiapp.dto.inputLoginDTO;
import br.edu.vianna.gibiapp.service.task.LoginTask;

public class UserService {

    public UserDTO logar(String login, String senha) throws ExecutionException, InterruptedException {

        LoginTask serv = new LoginTask();

        inputLoginDTO log = new inputLoginDTO(login, senha);

        UserDTO userDTO = serv.execute(log).get();

        return userDTO;
    }

}
