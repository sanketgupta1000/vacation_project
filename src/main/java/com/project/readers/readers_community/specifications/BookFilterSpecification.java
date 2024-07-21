package com.project.readers.readers_community.specifications;

import com.project.readers.readers_community.embeddables.Address;
import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class BookFilterSpecification
{
    public static Specification<Book> filterBooks(String title, String author, String owner, String city, List<Integer> categories, Integer minPageCount, Integer maxPageCount) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if ((title != null) && (!title.isEmpty()) )
            {
                System.out.println(title);
                predicates.add(criteriaBuilder.like(root.get("bookTitle"), "%" + title + "%"));
            }
            if (author != null && !author.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("authorName"), "%" + author + "%"));
            }
            if (owner != null && !owner.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("owner").get("fullName"), "%" + owner + "%"));
                predicates.add(criteriaBuilder.like(root.get("owner").get("email"), "%" + owner + "%"));
            }
            if( city!=null && !city.isEmpty() )
            {
                predicates.add(criteriaBuilder.like(root.get("owner").get("address").get("city"), "%" + city + "%"));
            }

            if (categories != null && !categories.isEmpty()) {
                predicates.add(root.get("category").get("id").in(categories));
            }

            if (minPageCount != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("pageCount"), minPageCount));
            }

            if (maxPageCount != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("pageCount"), maxPageCount));
            }

            predicates.add(criteriaBuilder.equal(root.get("adminApproval"), Approval.APPROVED));

            System.out.println(predicates);
            System.out.println("My debug comment");
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
