package com.epam.util;

import com.epam.exception.PaginationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {
    public static Pageable getPageableWithSort(int pageNumber, int pageSize, String sortType, String sortField, long countFromDb) {
        checkCount(pageNumber, pageSize, countFromDb);

        return PageRequest.of(pageNumber, pageSize, Sort.by(getSortType(sortType), sortField));
    }

    public static Pageable getPageableWithoutSort(int pageNumber, int pageSize, long countFromDb) {
        checkCount(pageNumber, pageSize, countFromDb);

        return PageRequest.of(pageNumber, pageSize);
    }

    private static Sort.Direction getSortType(String sortType) {
        return sortType.equals(Sort.Direction.DESC.toString()) ? Sort.Direction.DESC : Sort.Direction.ASC;
    }

    private static void checkCount(int pageNumber, int pageSize, long countFromDb) {
        long countFromRequest = pageNumber * pageSize;
        if (countFromDb <= countFromRequest && countFromDb != 0) {
            throw new PaginationException("pagination.not.valid.data", pageNumber, pageSize);
        }
    }
}
