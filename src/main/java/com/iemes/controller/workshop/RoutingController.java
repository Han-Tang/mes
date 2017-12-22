package com.iemes.controller.workshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
import com.iemes.entity.RoutingFormMap;
import com.iemes.entity.RoutingStepFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.mapper.operation.OperationMapper;
import com.iemes.mapper.routing.RoutingMapper;
import com.iemes.plugin.PageView;
import com.iemes.score.GradeCalculateService;
import com.iemes.util.Common;

import net.sf.json.JSONObject;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/workshop/")
public class RoutingController extends BaseController {
	
	@Inject
	private RoutingMapper routingMapper;
	@Inject
	private OperationMapper operationMapper;
	
	@Inject
	private GradeCalculateService gradeCalculateService;
	
	@RequestMapping("routing_list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/workshopdata/process_route/routing_list";
	}
	
	@ResponseBody
	@RequestMapping("routing_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		RoutingFormMap routingFormMap = getFormMap(RoutingFormMap.class);
		routingFormMap.put("siteBo", session.getAttribute("site"));
		routingFormMap=toFormMap(routingFormMap, pageNow, pageSize,routingFormMap.getStr("orderby"));
		pageView.setRecords(routingMapper.findByPage(routingFormMap));
		return pageView;
	}

	@RequestMapping("routing_addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/workshopdata/process_route/routing_add";
	}
	
	@RequestMapping("routing_designerUI")
	public String designer(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/process_designer/gooFlow/demo";
	}
	
	@ResponseBody
	@RequestMapping("routing_addEntity")
//	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="车间数据维护",methods="工艺路线维护-新增工艺路线")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() {
		RoutingFormMap routingFormMap = getFormMap(RoutingFormMap.class);
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		routingFormMap.put("createUser", userFormMap.getStr("accountName"));
		routingFormMap.put("siteBo", session.getAttribute("site"));
		try{
			routingMapper.addEntity(routingFormMap);
			String data = routingFormMap.getStr("data");
			JSONObject obj = JSONObject.fromObject(data);
			JSONObject lines = (JSONObject) obj.get("lines");
			JSONObject nodes = (JSONObject) obj.get("nodes");
			if (nodes.size()==0) {
				Set<String> set = nodes.keySet();
				int i=0;
				for (String key : set) {
					RoutingStepFormMap map2 = new RoutingStepFormMap();
					map2.put("process_route", routingFormMap.get("process_route"));
					map2.put("operation", key);
					map2.put("next_operation", "");
					map2.put("state", 0);
					map2.put("site", site);
					if (i==0) {
						map2.put("operation_order", 0);
					}
					i++;
					baseMapper.addEntity(map2);
				}
			}else {
				Set<String> set = lines.keySet();
				int i=0;
				for (String key : set) {
					//循环取对象
					JSONObject line = (JSONObject) lines.get(key);
					//取from值
					String operation = line.getString("from");
					//取to值
					String nextOperation = line.getString("to");
					
					//根据to值找from值进行存储
					RoutingStepFormMap map2 = new RoutingStepFormMap();
					map2.put("process_route", routingFormMap.get("process_route"));
					map2.put("operation", operation);
					map2.put("next_operation", nextOperation);
					map2.put("state", 0);
					map2.put("site", site);
					if (i==0) {
						map2.put("operation_order", 0);
					}
					i++;
					baseMapper.addEntity(map2);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return ConstantController.ERROR;
		}
		JSONObject json = new JSONObject();
		String rs = null;
		try {
			rs = gradeCalculateService.GradeCalculate("工艺路线", null);
			json.put("result", ConstantController.SUCCESS);
			json.put("score", rs);
		} catch (Exception e) {
			json.put("result", ConstantController.ERROR);
		}
		return json.toString();
	}
	
	/**
	 * 验证账号是否存在
	 * 
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-19
	 * @param name
	 * @return
	 */
	@RequestMapping("routing_isExist")
	@ResponseBody
	public boolean isExist() {
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		RoutingFormMap routingFormMap = new RoutingFormMap();
		routingFormMap.put("process_route", getPara("process_route"));
		routingFormMap.put("site", site);
		List<RoutingFormMap> list = routingMapper.findByNames(routingFormMap);
		if (list == null || list.size()<=0) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping("routing_editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("routing", routingMapper.findbyFrist("id", id, RoutingFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/process_designer/gooFlow/routing_edit";
	}
	
	@ResponseBody
	@RequestMapping("routing_editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="车间数据维护",methods="工艺路线维护-修改工艺路线")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception {
		RoutingFormMap routingFormMap = getFormMap(RoutingFormMap.class);
		
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		
		// 通过工具类获取当前登录的bean
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		routingFormMap.put("createUser", userFormMap.getStr("accountName"));
		routingFormMap.put("siteBo", site);
		try{
			routingMapper.editEntity(routingFormMap);
			
			//根据工艺路线、站点删除原来的工艺步骤信息
			RoutingStepFormMap map = new RoutingStepFormMap();
			map.put("process_route", "'"+routingFormMap.get("process_route")+"'");
			map.put("site", "'"+site+"'");
			baseMapper.deleteByNames(map);
			
			String data = routingFormMap.getStr("data");
			JSONObject obj = JSONObject.fromObject(data);
			JSONObject lines = (JSONObject) obj.get("lines");
			JSONObject nodes = (JSONObject) obj.get("nodes");
			if (nodes.size()==0) {
				int i=0;
				Set<String> set = nodes.keySet();
				for (String key : set) {
					RoutingStepFormMap map2 = new RoutingStepFormMap();
					map2.put("process_route", routingFormMap.get("process_route"));
					map2.put("operation", key);
					map2.put("next_operation", "");
					map2.put("state", 0);
					map2.put("site", site);
					if (i==0) {
						map2.put("operation_order", 0);
					}
					i++;
					baseMapper.addEntity(map2);
				}
			}else {
				Set<String> set = lines.keySet();
				JSONObject checkLine = null;
				int i=0;
				
				for (String key : set) {
					//循环取对象
					JSONObject line = (JSONObject) lines.get(key);
					//取from值
					String operation = line.getString("from");
					//取to值
					String nextOperation = line.getString("to");
					
					//根据to值查找node对象
					JSONObject node = (JSONObject) nodes.get(nextOperation);
					
					//根据to值找from值进行存储
					RoutingStepFormMap map2 = new RoutingStepFormMap();
					map2.put("process_route", routingFormMap.get("process_route"));
					map2.put("operation", operation);
					map2.put("next_operation", nextOperation);
					map2.put("state", 0);
					map2.put("site", site);
					if (i==0) {
						map2.put("operation_order", 0);
					}
					i++;
					baseMapper.addEntity(map2);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping("routing_queryData")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="车间数据维护",methods="工艺路线维护-修改工艺路线")//凡需要处理业务逻辑的.都需要记录操作日志
	public String queryData() throws Exception {
		RoutingFormMap routingFormMap = routingMapper.findbyFrist("process_route", getPara("process_route"), RoutingFormMap.class);
		JSONObject object = JSONObject.fromObject(routingFormMap);
		return object.toString();
	}
	
	@ResponseBody
	@RequestMapping("routing_deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="车间数据维护",methods="工艺路线维护-删除工艺路线")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception {
		try{
			String[] ids = getParaValues("ids");
			for (String id : ids) {
				RoutingFormMap routingFormMap = baseMapper.findbyFrist("id", id, RoutingFormMap.class);
				if (routingFormMap!=null && routingFormMap.size()>0) {
					baseMapper.deleteByAttribute("process_route", "'"+routingFormMap.getStr("process_route")+"'", RoutingStepFormMap.class);
				}
				routingMapper.deleteByAttribute("id", id, RoutingFormMap.class);
			}
		}catch(Exception e){
			return "error";
		}
		return "success";
	}
	
	/**
	 * 获取所有未被使用的工序
	 * 
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-2-19
	 * @param name
	 * @return
	 */
	@RequestMapping("routing_getOperation")
	@ResponseBody
	public Object getOperation() {
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		List<Map<String, String>> list = operationMapper.getOperationWhereNoUse(site);
		JSONObject json = new JSONObject();
		List<String> rs = new ArrayList<String>();
		for (int i=0;i<list.size();i++) {
			Map<String, String> map = list.get(i);
			rs.add("'"+map.get("operation_no")+"'");
		}
		json.put("success", true);
		json.put("data", rs.toString());
		return json;
	}
}
