package App.dao.interfaces.repository;

import App.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    // Métodos personalizados, si son necesarios
}
