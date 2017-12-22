package com.iemes.controller.kanban;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iemes.controller.index.BaseController;
import com.iemes.mapper.KanbanMapper;
import com.iemes.plugin.PageView;
import com.iemes.util.Common;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/kanban/")
public class KanbanController extends BaseController {

	@Inject
	private KanbanMapper kanbanMapper;
	/**
	 * 跳转到物料消耗看板页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("kanbanUI")
	public String listUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/kanban/material_consumption_list";
	}

	/**
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getMaterialConsumptionDataFindByPage")
	public PageView getMaterialConsumptionDataFindByPage(String pageNow,String pageSize) throws Exception{
		Session session = SecurityUtils.getSubject().getSession();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("site", session.getAttribute("site").toString());

		// 查询物料消耗信息
		List<Map<String, Object>> data = null;
		try {
			data =  kanbanMapper.findKanBanData(map);
		} catch (Exception e) {
			System.out.println(e);
		}

		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setQueryResult(data.size(), data);
		return pageView;
	}
}