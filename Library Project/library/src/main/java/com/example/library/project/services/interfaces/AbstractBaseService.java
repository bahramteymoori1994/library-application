package com.example.library.project.services.interfaces;

import java.util.List;

public interface AbstractBaseService<Q,S> {

    /*
    * S ----> Response Dto
    * Q -----> Request Dto
     */

    S save(Q q) throws Exception;
    S update(Q q) throws Exception;
    S findById(Long id) throws Exception;
    List<S> findAll();
}