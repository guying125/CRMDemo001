package com.guying.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.guying.domain.Dict;
import com.guying.service.DictService;
import com.guying.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 字典控制器
 * @author QJYang
 *
 */
public class DictAction extends ActionSupport implements  ModelDriven<Dict>{

	private static final long serialVersionUID = -6314740729380913628L;

	private Dict dict = new Dict();
	public Dict getModel() {
		return dict;
	}
	
	private DictService dictService;
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	
	/**
	 * 通过字段的dict_type_code值，查询客户级别或客户来源
	 * @return
	 */
	public String findByCode() {
		
		// 调用业务层，需要的参数，已经通过模型驱动封装到了dict对象中
		List<Dict> list = dictService.findByCode(dict.getDict_type_code());
//		List<Dict> list = dictService.findByCode("006");
		
		// 使用fastJson把list转换成json字符串
		String jsonString = FastJsonUtil.toJSONString(list);
		
		// 把json字符串写到浏览器
		HttpServletResponse response = ServletActionContext.getResponse();
		
		// 输出
		FastJsonUtil.write_json(response, jsonString);
		
//		System.out.println(jsonString);
		
		return NONE;
	}
	
	

}
