package com.iemes.controller.workshop;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iemes.annotation.SystemLog;
import com.iemes.controller.base.ConstantController;
import com.iemes.controller.index.BaseController;
import com.iemes.entity.ItemFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.plugin.PageView;
import com.iemes.score.GradeCalculateService;
import com.iemes.util.Common;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/item_resource/")
public class ItemController extends BaseController {

	@Inject
	private GradeCalculateService gradeCalculateService;

	@RequestMapping("item_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/item_resource/item/item_list";
	}

	/**
	 * 查询原物料
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getItemChildFindByPage")
	public PageView getItemChildFindByPage(String pageNow,String pageSize) throws Exception{

		//查询原物料
		Session session = SecurityUtils.getSubject().getSession();
		ItemFormMap itemFormMap = new ItemFormMap();
		itemFormMap.put("site", session.getAttribute("site").toString());
		itemFormMap.put("item_type", "purchase");
		List<ItemFormMap> list = baseMapper.findByNames(itemFormMap);
		
		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setQueryResult(list.size(), list);
		return pageView;
	}
	
	/**
	 * 查询主物料
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getItemMainFindByPage")
	public PageView getItemMainFindByPage(String pageNow,String pageSize) throws Exception{

		//查询主物料
		Session session = SecurityUtils.getSubject().getSession();
		ItemFormMap itemFormMap = new ItemFormMap();
		itemFormMap.put("site", session.getAttribute("site").toString());
		itemFormMap.put("item_type", "machining");
		List<ItemFormMap> list = baseMapper.findByNames(itemFormMap);
		
		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setQueryResult(list.size(), list);
		return pageView;
	}
	
	/**
	 * 查询所有物料
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getItemFindByPage")
	public PageView getItemFindByPage(String pageNow,String pageSize) throws Exception{

		//查询原物料
		Session session = SecurityUtils.getSubject().getSession();
		ItemFormMap itemFormMap = new ItemFormMap();
		itemFormMap.put("site", session.getAttribute("site").toString());
		//itemFormMap.put("item_type", "purchase");
		List<ItemFormMap> list = baseMapper.findByNames(itemFormMap);
		
		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setQueryResult(list.size(), list);
		return pageView;
	}

	@ResponseBody
	@RequestMapping("item_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		pageView = new PageView();
		Session session = SecurityUtils.getSubject().getSession();
		ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
		itemFormMap.put("site", session.getAttribute("site"));
		itemFormMap=toFormMap(itemFormMap, pageNow, pageSize,itemFormMap.getStr("orderby"));
		pageView.setRecords(baseMapper.findByPage(itemFormMap));
		return pageView;
	}

	@RequestMapping("item_addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/item_resource/item/item_add";
	}

	@ResponseBody
	@RequestMapping("item_addEntity")
	//	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产物料控制",methods="物料维护-新增物料")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {

		JSONObject json = new JSONObject();

		ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
		Session session = SecurityUtils.getSubject().getSession();
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		itemFormMap.put("by_user", userFormMap.getStr("accountName"));
		itemFormMap.put("site", session.getAttribute("site"));

		/*根据物料编号判断校验物料是否已经存在*/
		String itemNo = itemFormMap.getStr("item_no");
		List<ItemFormMap> records = baseMapper.findByAttribute("item_no", itemNo, ItemFormMap.class);
		if(records != null && records.size() > 0){//如果新增加的物料编号存在，则提示增加报错
			json.put("result", ConstantController.ERROR);
			return json.toString();
		}

		baseMapper.addEntity(itemFormMap);

		String rs = null;
		try {
			rs = gradeCalculateService.GradeCalculate("物料维护", null);
			json.put("result", ConstantController.SUCCESS);
			json.put("score", rs);
		} catch (Exception e) {
			json.put("result", ConstantController.ERROR);
		}
		return json.toString();
	}

	@RequestMapping("item_editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("item", baseMapper.findbyFrist("id", id, ItemFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/item_resource/item/item_edit";
	}

	@ResponseBody
	@RequestMapping("item_editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产物料控制",methods="物料维护-修改物料")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception {
		ItemFormMap itemFormMap = getFormMap(ItemFormMap.class);
		baseMapper.editEntity(itemFormMap);
		return "success";
	}

	@ResponseBody
	@RequestMapping("item_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产物料控制",methods="物料维护-删除物料")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			baseMapper.deleteByAttribute("id", id, ItemFormMap.class);
		}
		return "success";
	}

}