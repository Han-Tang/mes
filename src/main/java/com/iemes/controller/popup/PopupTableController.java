package com.iemes.controller.popup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iemes.controller.index.BaseController;
import com.iemes.mapper.BaseExtMapper;
import com.iemes.plugin.PageView;

@Controller
@RequestMapping("/PopupTable/")
public class PopupTableController extends BaseController{
	
	@Inject
	protected BaseExtMapper baseExtMapper;

	/**
	 * 获取已接收但未使用的SFC列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("assemble_sfclist")
	public PageView getAssembleSfcList(String pageNow,
			String pageSize) {
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		String itemNo = getPara("itemNo");
		
		//查询已接收，但未被使用的SFC列表
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site", site);
		map.put("itemNo", itemNo);
		List<Map<String, Object>> list = baseExtMapper.getAssembleSfcList(map);
		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setQueryResult(1, list);
		return pageView;		
	}
}
