package br.com.utfpr.libraryfive.dao;

import br.com.utfpr.libraryfive.model.CollectionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionCustomDao {

    List<CollectionModel> findAllAvailableCollection();

    boolean isAvailable(Integer collectionId, Integer quantity);
}
