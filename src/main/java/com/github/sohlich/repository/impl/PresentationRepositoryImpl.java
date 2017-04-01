package com.github.sohlich.repository.impl;

import com.github.sohlich.model.PresentationDocument;
import com.github.sohlich.repository.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Radomir Sohlich on 26/03/2017.
 */
@Service
public class PresentationRepositoryImpl implements PresentationRepository {

    private final String KEY = "DOCUMENT";


    private RedisTemplate<String,PresentationDocument> redisTemplate;
    private HashOperations hashOps;

    @Autowired
    public PresentationRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init(){
        hashOps = redisTemplate.opsForHash();
    }

    @Override
    public void save(PresentationDocument document) {
        hashOps.put(KEY,document.getHex(),document);
    }

    @Override
    public PresentationDocument findById(String id) {
        return (PresentationDocument) hashOps.get(KEY, id);
    }
}
