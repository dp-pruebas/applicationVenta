package cl.dpichinil.applicationventas.controller;

import cl.dpichinil.applicationventas.dto.ResponseDto;
import cl.dpichinil.applicationventas.dto.SaleDto;
import cl.dpichinil.applicationventas.service.SaleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sale")
public class SaleController {
    private SaleService ventaService;

    public SaleController(SaleService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("insert")
    public ResponseDto insert(@RequestBody SaleDto dto){
        return ventaService.insertSale(dto);
    }

    @GetMapping("get/{id}")
    public ResponseDto get(@PathVariable("id") int id){
        return ventaService.getById(id);
    }

    @GetMapping("list")
    public ResponseDto list(){
        return ventaService.getList();
    }

    @GetMapping("detail/{id}")
    public ResponseDto getDetail(@PathVariable("id") int id){
        return ventaService.getDetailById(id);
    }

    @GetMapping("delete/{id}")
    public ResponseDto getDelete(@PathVariable("id") int id){
        return ventaService.deleteById(id);
    }
}
