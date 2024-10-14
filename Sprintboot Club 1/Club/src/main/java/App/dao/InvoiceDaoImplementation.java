package App.dao;

import App.dao.interfaces.InvoiceDao;
import App.dto.InvoiceDto;
import App.dto.PartnerDto;
import App.dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
@NoArgsConstructor
public class InvoiceDaoImplementation implements InvoiceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Override
    public List<InvoiceDto> findAllInvoices() {
        String query = "SELECT * FROM invoice";

        return jdbcTemplate.query(query, new InvoiceRowMapper());
    }

    private static class InvoiceRowMapper implements RowMapper<InvoiceDto> {

        @Override
        public InvoiceDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            InvoiceDto invoice = new InvoiceDto();

            invoice.setId(rs.getLong("id"));
            invoice.setCreacionDate(rs.getTimestamp("creation_date"));
            invoice.setTotalAmount(rs.getDouble("total_amount"));
            invoice.setStatus(rs.getBoolean("status"));

            long partnerId = rs.getLong("partnerid");
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setId(partnerId);
            invoice.setPartnerId(partnerDto);

            long userId = rs.getLong("userid");
            UserDto userDto = new UserDto();
            userDto.setId(userId);
            invoice.setUserId(userDto);

            return invoice;
        }
    }

    @Override
    public List<InvoiceDto> findInvoicesByPartnerId(long partnerId) {
        String query = "SELECT * FROM invoice WHERE partnerId = ?";
        List<InvoiceDto> invoices = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, partnerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InvoiceDto invoice = new InvoiceDto();
                    invoice.setId(rs.getLong("id"));
                    invoice.setCreacionDate(rs.getTimestamp("creation_date"));
                    invoice.setTotalAmount(rs.getDouble("total_amount"));
                    invoice.setStatus(rs.getBoolean("status"));

                    PartnerDto partnerDto = new PartnerDto();
                    partnerDto.setId(rs.getLong("partnerid"));
                    invoice.setPartnerId(partnerDto);

                    UserDto userDto = new UserDto();
                    userDto.setId(rs.getLong("userid"));
                    invoice.setUserId(userDto);

                    invoices.add(invoice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    @Override
    public List<InvoiceDto> findByGuestId(long guestId) {
        String query = "SELECT * FROM guest WHERE ID = ?";
        List<InvoiceDto> invoices = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, guestId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InvoiceDto invoice = new InvoiceDto();
                    invoice.setId(rs.getLong("id"));
                    invoice.setStatus(rs.getBoolean("status"));

                    PartnerDto partnerDto = new PartnerDto();
                    partnerDto.setId(rs.getLong("partnerid"));
                    invoice.setPartnerId(partnerDto);

                    UserDto userDto = new UserDto();
                    userDto.setId(rs.getLong("userid"));
                    invoice.setUserId(userDto);

                    invoices.add(invoice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invoices;
    }

}
