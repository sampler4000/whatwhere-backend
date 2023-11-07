package ee.spacexy.whatwhere.service.repository;

import ee.spacexy.whatwhere.service.domain.Layer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LayerRepository extends JpaRepository<Layer, UUID> {

}
