package com.github.sohlich.repository;

import com.github.sohlich.model.PresentationDocument;

/**
 * Created by Radomir Sohlich on 26/03/2017.
 */
public interface PresentationRepository {


    public void save(PresentationDocument document);

    public PresentationDocument findById(String id);

}
