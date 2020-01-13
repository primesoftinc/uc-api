package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.UserFavourites;

@Repository
public interface UserFavouritesRepo extends JpaRepository<UserFavourites, UUID>{

	@Transactional
	@Modifying
	@Query(value = "delete from user_favourites where branch_id = ?1",nativeQuery =true)
	int deleteByBranchId(UUID id);

	@Query("select uf from UserFavourites uf join fetch uf.user u where u.id = ?1")
	List<UserFavourites> getUserFavourites (UUID userId);
}
