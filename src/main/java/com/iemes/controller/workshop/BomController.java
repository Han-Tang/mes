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
import com.iemes.entity.BomFormMap;
import com.iemes.entity.ItemBomFormMap;
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
public class BomController extends BaseController {
	
	@Inject
	private GradeCalculateService gradeCalculateService;
	
	@RequestMapping("bom_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/item_resource/bom/bom_list";
	}
	
	@ResponseBody
	@RequestMapping("bom_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		BomFormMap bomFormMap = getFormMap(BomFormMap.class);
		bomFormMap=toFormMap(bomFormMap, pageNow, pageSize,bomFormMap.getStr("orderby"));
		Session session = SecurityUtils.getSubject().getSession();
		bomFormMap.put("site", session.getAttribute("site"));
		pageView.setRecords(baseMapper.findByPage(bomFormMap));
		return pageView;
	}
	
	@RequestMapping("bom_addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/item_resource/bom/bom_add";
	}
	
	@ResponseBody
	@RequestMapping("bom_addEntity")
//	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产物料控制",methods="物料维护-新增物料清单")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		BomFormMap bomFormMap = getFormMap(BomFormMap.class);
		
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		Session session = SecurityUtils.getSubject().getSession();
		bomFormMap.put("site", session.getAttribute("site"));
		bomFormMap.put("by_user", userFormMap.getStr("accountName"));
		baseMapper.addEntity(bomFormMap);
		
		// 物料清单子项
		String itemBomNo = request.getParameter("bomFormMap.item_bom_no");
		String[] itemNoArr = request.getParameterValues("item_no_1");
		String[] itemUseNumberArr = request.getParameterValues("item_use_number_1");
		String[] itemBalanceUpArr = request.getParameterValues("item_balance_up_1");
		String[] itemBalanceDownArr = request.getParameterValues("item_balance_down_1");
		if(null != itemNoArr ){
			for(int i = 0 ;i<itemNoArr.length;i++){
				ItemBomFormMap itemBomFormMap = new ItemBomFormMap();
				itemBomFormMap.put("item_bom_no", itemBomNo);
				itemBomFormMap.put("item_no", itemNoArr[i]);
				itemBomFormMap.put("use_number", itemUseNumberArr[i]);
				itemBomFormMap.put("balance_up", itemBalanceUpArr[i]);
				itemBomFormMap.put("balance_down", itemBalanceDownArr[i]);
				itemBomFormMap.put("site", bomFormMap.getStr("site"));
				baseMapper.addEntity(itemBomFormMap);
			}
		}

		JSONObject json = new JSONObject();
		String rs = null;
		try {
			rs = gradeCalculateService.GradeCalculate("物料组维护", null);
			json.put("result", ConstantController.SUCCESS);
			json.put("score", rs);
		} catch (Exception e) {
			json.put("result", ConstantController.ERROR);
		}
		return json.toString();
	}
	
	@RequestMapping("bom_editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			BomFormMap bomFormMap = baseMapper.findbyFrist("id", id, BomFormMap.class);
			model.addAttribute("bom", bomFormMap);
			
			ItemBomFormMap itemBomFormMap = new ItemBomFormMap();
			itemBomFormMap.put("item_bom_no", bomFormMap.getStr("item_bom_no"));
			List<ItemBomFormMap> itemBom = baseMapper.findByNames(itemBomFormMap);
			model.addAttribute("item_bom", itemBom);
		}
		return Common.BACKGROUND_PATH + "/item_resource/bom/bom_edit";
	}
	
	@ResponseBody
	@RequestMapping("bom_editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产物料控制",methods="物料维护-修改物料清单")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity(HttpServletRequest request) throws Exception {
		BomFormMap bomFormMap = getFormMap(BomFormMap.class);
		baseMapper.editEntity(bomFormMap);
		
		//先删除
		baseMapper.deleteByAttribute("item_bom_no", "'"+bomFormMap.getStr("item_bom_no")+"'", ItemBomFormMap.class);
		
		//再添加
		String itemBomNo = request.getParameter("bomFormMap.item_bom_no");
		String[] itemNoArr = request.getParameterValues("item_no_1");
		String[] itemUseNumberArr = request.getParameterValues("item_use_number_1");
		String[] itemBalanceUpArr = request.getParameterValues("item_balance_up_1");
		String[] itemBalanceDownArr = request.getParameterValues("item_balance_down_1");
		if(null != itemNoArr ){
			for(int i = 0 ;i<itemNoArr.length;i++){
				ItemBomFormMap itemBomFormMap = new ItemBomFormMap();
				itemBomFormMap.put("item_bom_no", itemBomNo);
				itemBomFormMap.put("item_no", itemNoArr[i]);
				itemBomFormMap.put("use_number", itemUseNumberArr[i]);
				itemBomFormMap.put("balance_up", itemBalanceUpArr[i]);
				itemBomFormMap.put("balance_down", itemBalanceDownArr[i]);
				itemBomFormMap.put("site", bomFormMap.getStr("site"));
				baseMapper.addEntity(itemBomFormMap);
			}
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("bom_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="生产物料控制",methods="物料维护-删除物料清单")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			BomFormMap bomFormMap = baseMapper.findbyFrist("id", id, BomFormMap.class);
			String itemBomNo = bomFormMap.getStr("item_bom_no");
			ItemBomFormMap itemBomFormMap = new ItemBomFormMap();
			itemBomFormMap.put("item_bom_no", "'"+itemBomNo+"'");
			baseMapper.deleteByNames(itemBomFormMap);
			baseMapper.deleteByAttribute("id", id, BomFormMap.class);
		}
		return "success";
	}

}