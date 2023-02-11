package com.epam.hateoas.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collection;
import java.util.List;

public interface CollectionModelAssembler<T extends RepresentationModel<T>> {
    List<Link> getCollectionLinks(int pageNumber, int pageSize, String sortType, String sortField);

    default CollectionModel<T> toCollectionModel(Collection<T> collection,
                                                 int pageNumber,
                                                 int pageSize,
                                                 String sortType,
                                                 String sortField) {
        CollectionModel<T> collectionModel = CollectionModel.empty();
        if (collection != null && !collection.isEmpty()) {
            collectionModel = CollectionModel.of(collection, getCollectionLinks(pageNumber, pageSize, sortType, sortField));
        }
        return collectionModel;
    }


}
