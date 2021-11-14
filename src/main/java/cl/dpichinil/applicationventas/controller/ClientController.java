package cl.dpichinil.applicationventas.controller;

import cl.dpichinil.applicationventas.dto.ResponseDto;
import cl.dpichinil.applicationventas.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client")
public class ClientController {
    private ClientService clienteService;

    public ClientController(ClientService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("list")
    public ResponseDto listActive(){
        return this.clienteService.getListActive();
    }
}
