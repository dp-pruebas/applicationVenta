package cl.dpichinil.applicationventas.controller;

import cl.dpichinil.applicationventas.dto.ResponseDto;
import cl.dpichinil.applicationventas.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private ProductService productoService;

    public ProductController(ProductService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("list")
    public ResponseDto list(){
        return productoService.getListActive();
    }
}
