package com.epam.util;

import com.epam.dao.AbstractRepository;
import com.epam.exception.PaginationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {
    public static Pageable getPageableWithSort(Integer pageNumber, Integer pageSize, String sortType, String sortField, AbstractRepository repository) {
        long countFromDb = repository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest && countFromDb != 0) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize, Sort.by(getSortType(sortType), sortField));
    }

    public static Pageable getPageableWithoutSort(Integer pageNumber, Integer pageSize, AbstractRepository repository) {
        long countFromDb = repository.count();
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest && countFromDb != 0) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize);
    }


    private static Sort.Direction getSortType(String sortType) {
        return sortType.equals(Sort.Direction.DESC.toString()) ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
}
