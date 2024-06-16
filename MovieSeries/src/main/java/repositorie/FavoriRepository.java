package repositorie;

import com.msproje.movieSerieApp.model.Favori;
import com.msproje.movieSerieApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriRepository extends JpaRepository<Favori, Long> {
    List<Favori> findByUser(User user);
}
