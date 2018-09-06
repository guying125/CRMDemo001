package com.guying.dao;

import java.util.List;

import com.guying.domain.Dict;

public interface DictDao {

	List<Dict> findByCode(String dict_type_code);

}
