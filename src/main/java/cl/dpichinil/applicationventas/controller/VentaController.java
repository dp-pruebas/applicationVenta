package cl.dpichinil.applicationventas.controller;

import cl.dpichinil.applicationventas.dto.ResponseDto;
import cl.dpichinil.applicationventas.dto.VentaDto;
import cl.dpichinil.applicationventas.service.VentaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class VentaController {
    private VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("insert")
    public ResponseDto insert(@RequestBody VentaDto dto){
        return ventaService.ingresarVenta(dto);
    }

    @GetMapping("get/{id}")
    public ResponseDto get(@PathVariable("id") int id){
        return ventaService.getById(id);
    }

    @GetMapping("list")
    public ResponseDto list(){
        return ventaService.getList();
    }
}
