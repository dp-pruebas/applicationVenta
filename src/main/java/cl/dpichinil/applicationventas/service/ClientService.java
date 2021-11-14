package cl.dpichinil.applicationventas.service;

import cl.dpichinil.applicationventas.dao.ClientDao;
import cl.dpichinil.applicationventas.dto.ClientDto;
import cl.dpichinil.applicationventas.dto.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientDao dao;

    public ClientService(ClientDao dao) {
        this.dao = dao;
    }

    public ResponseDto getListActive(){
        ResponseDto response = null;
        try{
            List<ClientDto> list = dao.getActiveClientList();
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
