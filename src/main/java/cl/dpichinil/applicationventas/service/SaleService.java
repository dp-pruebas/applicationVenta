package cl.dpichinil.applicationventas.service;

import cl.dpichinil.applicationventas.dao.SaleDao;
import cl.dpichinil.applicationventas.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    private SaleDao dao;

    public SaleService(SaleDao dao) {
        this.dao = dao;
    }

    public ResponseDto insertSale(SaleDto dto) {
        ResponseDto response = null;
        try {
            int id = dao.insertSale(dto);
            if(id > 0) {
                dto.setId(id);
                for (SaleDetailDto detail: dto.getDetails()){
                    detail.setSaleId(id);
                    int idDetail = dao.insertDetailSale(detail);
                }
                response = new ResponseDto(0,"Data Generada", id);
            } else {
                response = new ResponseDto(1001,"Error al obtener la data");
            }
        }catch(Exception e){
            response = new ResponseDto(1000,"Error al generar la data");
        }
        return response;
    }

    public ResponseDto getById(int id) {
        ResponseDto response = null;
        try {
            response = new ResponseDto();
            SaleDto dto = dao.getById(id);
            if(dto != null){
                response = new ResponseDto(0,"Data Generada", dto);
            }else{
                response = new ResponseDto(1001,"Error al obtener la data");
            }
        }catch(Exception e){
            response = new ResponseDto(1000,"Error al generar la data");
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
