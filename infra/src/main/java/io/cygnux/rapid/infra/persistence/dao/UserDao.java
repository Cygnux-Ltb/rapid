package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * User Repository
 *
 * @author yellow013
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {

    /**
     * @param username String
     * @param email    String
     * @param phone    String
     * @return SUserEntity
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.username = :username OR e.email = :email OR e.phone = :phone)
            """)
    UserEntity queryBy(@Param("username") String username,
                       @Param("email") String email,
                       @Param("phone") String phone);

    @Modifying
    @Transactional
    int deleteByUsername(String username);

}
