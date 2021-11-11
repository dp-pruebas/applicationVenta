package cl.dpichinil.applicationventas.service;

import cl.dpichinil.applicationventas.dto.ResponseDto;
import cl.dpichinil.applicationventas.dto.VentaDto;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    public ResponseDto ingresarVenta(VentaDto dto) {
        ResponseDto response = null;
        try {
            response = new ResponseDto();
        }catch(Exception e){

        }
        return response;
    }

    public ResponseDto getById(int id) {
        ResponseDto response = null;
        try {
            response = new ResponseDto();
        }catch(Exception e){

        }
        return response;
    }

    public ResponseDto getList() {
        ResponseDto response = null;
        try {
            response = new ResponseDto();
        }catch(Exception e){

        }
        return response;
    }
}
