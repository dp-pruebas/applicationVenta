package cl.dpichinil.applicationventas.controller;

import cl.dpichinil.applicationventas.dto.ResponseDto;
import cl.dpichinil.applicationventas.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;

public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("list")
    public ResponseDto listActive(){
        return this.clienteService.getListActive();
    }
}
