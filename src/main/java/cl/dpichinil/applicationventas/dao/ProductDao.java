package cl.dpichinil.applicationventas.dao;

import cl.dpichinil.applicationventas.dto.ProductDto;
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
public class ProductDao {
    private final String SEL_PRODUCT_LIST_WITH_STOCK = "CALL sp_sel_list_products_with_stock()";
    private JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductDto> getProductListWithStock(){
        String sql = this.SEL_PRODUCT_LIST_WITH_STOCK;

        List<ProductDto> list = this.jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                return ps;
            }
        } , new ResultSetExtractor<List<ProductDto>>() {
            @Override
            public List<ProductDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ProductDto> list = new ArrayList<ProductDto>();
                while(rs.next()) {
                    ProductDto e = new ProductDto();
                    e.setId(rs.getInt("id"));
                    e.setName(rs.getString("name"));
                    e.setAmount(rs.getInt("stock"));
                    e.setPrice(rs.getInt("price"));
                    list.add(e);
                }
                return list;
            }
        });
        return list;
    }
}
