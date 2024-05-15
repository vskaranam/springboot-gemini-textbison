package com.genai.repossitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.genai.entity.Dataset;

@Repository
public interface DatasetRepository extends CrudRepository<Dataset, Long>{}
