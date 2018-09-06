package com.guying.service;

import java.util.List;

import com.guying.domain.Dict;

public interface DictService {

	List<Dict> findByCode(String dict_type_code);

}
