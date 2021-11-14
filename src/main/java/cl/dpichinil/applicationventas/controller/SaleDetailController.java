package cl.dpichinil.applicationventas.controller;

import cl.dpichinil.applicationventas.dto.ResponseDto;
import cl.dpichinil.applicationventas.service.SaleDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/saleDetail")
public class SaleDetailController {
    private SaleDetailService detalleVentaService;

    public SaleDetailController(SaleDetailService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping("get/{id}")
    public ResponseDto getId(@PathVariable("id") int id){
        return detalleVentaService.getById(id);
    }
}
