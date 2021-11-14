package cl.dpichinil.applicationventas.dao;

import cl.dpichinil.applicationventas.dto.ClientDto;
import cl.dpichinil.applicationventas.dto.ProductDto;
import cl.dpichinil.applicationventas.dto.SaleDetailDto;
import cl.dpichinil.applicationventas.dto.SaleDto;
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
public class SaleDao {
    private final String INS_SALE = "CALL sp_ins_sale(?,?,?,?)";
    private final String INS_SALE_DETAIL = "CALL sp_ins_sale_detail(?, ?, ?, ?)";

    private final String SEL_SALE_BY_ID = "CALL sp_sel_sale_by_id(?)";
    private final String SEL_SALE_LIST = "CALL sp_sel_sale_list()";
    private final String SEL_SALE_DETAIL_BY_ID_SALE = "CALL sp_sel_sale_detail_by_id_sale(?)";

    private JdbcTemplate jdbcTemplate;

    public SaleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertSale(SaleDto dto){
        String sql = this.INS_SALE;

        Integer id = this.jdbcTemplate.query( new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                //:p_fecha,:p_descuento,:p_iva,:p_total
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, dto.getDate());
                ps.setInt(2, dto.getDiscount());
                ps.setInt(3, dto.getIva());
                ps.setInt(4, dto.getTotal());
                return ps;
            }
        } , new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Integer id = 0;
                    id = rs.getInt("id");
                    return id;
                }else {
                    return null;
                }
            }
        });
        return id;
    }

    public int insertDetailSale(SaleDetailDto dto) {
        String sql = this.INS_SALE_DETAIL;

        Integer id = this.jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                //:p_venta_id,:p_producto_id,:p_cantidad,:p_subtotal
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, dto.getSaleId());
                ps.setInt(3, dto.getProductId());
                ps.setInt(2, dto.getAmount());
                ps.setInt(4, dto.getSubtotal());
                return ps;
            }
        } , new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Integer id = 0;
                    id = rs.getInt("id");
                    return id;
                }else {
                    return null;
                }
            }
        });
        return id;
    }

    public SaleDto getById(int id) {
        String sql = this.SEL_SALE_BY_ID;

        SaleDto dto = this.jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                return ps;
            }
        } , new ResultSetExtractor<SaleDto>() {
            @Override
            public SaleDto extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    SaleDto e = new SaleDto();
                    e.setId(rs.getInt("id"));
                    e.setDate(rs.getString("date"));
                    e.setDiscount(rs.getInt("discount"));
                    e.setIva(rs.getInt("iva"));
                    e.setTotal(rs.getInt("total"));
                    List<SaleDetailDto> list = getDetailById(e.getId());
                    e.setDetails(list);
                    return e;
                }else{
                    return null;
                }
            }
        });
        return dto;
    }

    public List<SaleDetailDto> getDetailById(int id) {
        String sql = this.SEL_SALE_DETAIL_BY_ID_SALE;
        List<SaleDetailDto> list = this.jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                return ps;
            }
        } , new ResultSetExtractor<List<SaleDetailDto>>() {
            @Override
            public List<SaleDetailDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<SaleDetailDto> list = new ArrayList<SaleDetailDto>();
                while(rs.next()) {
                    SaleDetailDto e = new SaleDetailDto();
                    e.setId(rs.getInt("id"));
                    e.setSaleId(rs.getInt("sale_id"));
                    e.setProductId(rs.getInt("product_id"));
                    e.setAmount(rs.getInt("amount"));
                    e.setSubtotal(rs.getInt("subtotal"));
                    list.add(e);
                }
                return list;
            }
        });
        return list;
    }

    public List<SaleDto> getListSale() {
        String sql = this.SEL_SALE_LIST;

        List<SaleDto> list = this.jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                return ps;
            }
        } , new ResultSetExtractor<List<SaleDto>>() {
            @Override
            public List<SaleDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<SaleDto> list = new ArrayList<SaleDto>();
                SaleDto e = null;
                while(rs.next()) {
                    e = new SaleDto();
                    e.setId(rs.getInt("id"));
                    e.setDate(rs.getString("date"));
                    e.setDiscount(rs.getInt("discount"));
                    e.setIva(rs.getInt("iva"));
                    e.setTotal(rs.getInt("total"));
                    list.add(e);
                }
                return list;
            }
        });
        return list;
    }
}
