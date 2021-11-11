package cl.dpichinil.applicationventas.controller;

import cl.dpichinil.applicationventas.dto.ResponseDto;
import cl.dpichinil.applicationventas.service.DetalleVentaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("api")
public class DetalleVentaController {
    private DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping("get/{id}")
    public ResponseDto getId(@PathVariable("id") int id){
        return detalleVentaService.getById(id);
    }
}
