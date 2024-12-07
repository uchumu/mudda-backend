package org.example.mudda.repository;


import org.example.mudda.entity.Capsule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CapsuleRepository extends JpaRepository<Capsule, Long> {


    Optional<Capsule> findById(Long id);

//    @Query("SELECT b FROM Book b JOIN b.category c WHERE b.name LIKE %:bookName% AND c.name like %:categoryName% AND b.author LIKE %:author%")
//    List<Book> findByBookNameContainingAndCategory_CategoryNameAndAuthorContaining(@Param("bookName") String bookName, @Param("categoryName") String categoryName, @Param("author") String author);




}
