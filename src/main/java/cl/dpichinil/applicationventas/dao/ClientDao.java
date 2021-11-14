package cl.dpichinil.applicationventas.dao;

import cl.dpichinil.applicationventas.dto.ClientDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDao {
    private final String SEL_ACTIVE_CLIENT_LIST = "CALL sp_sel_list_active_client()";
    private JdbcTemplate jdbcTemplate;

    public ClientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClientDto> getActiveClientList(){
        String sql = this.SEL_ACTIVE_CLIENT_LIST;

        List<ClientDto> list = this.jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                return ps;
            }
        } , new ResultSetExtractor<List<ClientDto>>() {
            @Override
            public List<ClientDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ClientDto> list = new ArrayList<ClientDto>();
                while(rs.next()) {
                    ClientDto e = new ClientDto();
                    e.setId(rs.getInt("id"));
                    e.setName(rs.getString("name"));
                    e.setStatus(rs.getString("status").equals("ACTIVE"));
                    list.add(e);
                }
                return list;
            }
        });
        return list;
    }
}
