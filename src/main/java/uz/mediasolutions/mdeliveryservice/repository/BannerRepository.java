package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    Page<Banner> findAllByOrderByNumberAsc(Pageable pageable);

}
