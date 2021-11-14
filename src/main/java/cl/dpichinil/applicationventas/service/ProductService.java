package cl.dpichinil.applicationventas.service;

import cl.dpichinil.applicationventas.dao.ProductDao;
import cl.dpichinil.applicationventas.dto.ClientDto;
import cl.dpichinil.applicationventas.dto.ProductDto;
import cl.dpichinil.applicationventas.dto.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class ProductService {
    private ProductDao dao;
    public ResponseDto getListActive(){
        ResponseDto response = null;
        try{
            List<ProductDto> list = dao.getProductListWithStock();
            if(list!=null){
                response = new ResponseDto(0,"Data Generada",list);
            }else{
                response = new ResponseDto(1001,"Error al obtener la data");
            }
        }catch(Exception e){
            response = new ResponseDto(1000,"Error al generar la data");
        }
        return response;
    }
}
