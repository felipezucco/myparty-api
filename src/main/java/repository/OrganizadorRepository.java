package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tendelfc.entity.Organizador;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Long>{

}
