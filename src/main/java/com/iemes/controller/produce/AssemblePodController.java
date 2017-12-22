package com.iemes.controller.produce;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iemes.annotation.SystemLog;
import com.iemes.controller.base.BaseResponse;
import com.iemes.controller.index.BaseController;
import com.iemes.entity.BaseExtFormMap;
import com.iemes.entity.ExamInfoFormMap;
import com.iemes.entity.OperationFormMap;
import com.iemes.entity.OperationPodFormMap;
import com.iemes.entity.PodButtonFormMap;
import com.iemes.entity.PodFunctionFormMap;
import com.iemes.entity.SfcAssemblyFormMap;
import com.iemes.entity.SfcNcFormMap;
import com.iemes.entity.SfcStepFormMap;
import com.iemes.entity.ShopOrderFormMap;
import com.iemes.entity.UserFormMap;
import com.iemes.mapper.BaseExtMapper;
import com.iemes.plugin.PageView;
import com.iemes.service.AssemblePodService;
import com.iemes.util.Common;
import com.iemes.util.FormMap;
import com.iemes.util.ResponseHelp;
import com.sun.mail.iap.Response;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/produce/")
public class AssemblePodController extends BaseController {

	@Inject
	protected BaseExtMapper baseExtMapper;
	
	@Inject
	protected AssemblePodService assemblePodService;
	
	protected SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	protected SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	
	protected SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");

	@RequestMapping("assemble_pod")
	public String listUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/produce/assemble_pod";
	}

	@RequestMapping("assemble_item")
	public String assemble_item(Model model) throws Exception {
		String item_no = getPara("item_no");
		String item_name = getPara("item_name");
		String item_desc = getPara("item_desc");
		String operation_no = getPara("operation_no");
		String wc_sfc = getPara("wc_sfc");

		BaseExtFormMap formMap = new BaseExtFormMap();
		formMap.put("item_no", item_no);
		formMap.put("item_name", item_name);
		formMap.put("item_desc", item_desc);
		formMap.put("operation_no", operation_no);
		formMap.put("wc_sfc", wc_sfc);

		model.addAttribute("res", formMap);
		return Common.BACKGROUND_PATH + "/system/produce/assemble_item";
	}

	@RequestMapping("assemble_item_list")
	public String assemble_item_list(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/produce/assemble_item_list";
	}
	
	/**
	 * pod开始按钮校验数据是否存在
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("assemblingStartValadata")
	public String assemblingStartValadata(Model model){
		@SuppressWarnings("unchecked")
		FormMap<String, String> formMap = getFormMap(FormMap.class);
		try {
			return assemblePodService.assemblingStartValadata(formMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHelp.responseErrorText("操作异常："+e.getMessage());
		}
	}
	

	/**
	 * pod开始按钮功能
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("process_start")
	public String process_start(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/produce/assemble_start";
	}

	@ResponseBody
	@RequestMapping("process_start_list")
	public PageView process_start_list(String pageNow,
			String pageSize) {
		Session session = SecurityUtils.getSubject().getSession();
		//取得前端的值
		FormMap<String, String> formMap = getFormMap(FormMap.class);
		String sfc = formMap.getStr("wc_sfc");
		String operation = formMap.getStr("operation");
		String site = session.getAttribute("site").toString();
		String shopOrder = formMap.getStr("workorderNo").toString();
		
		//查询状态信息
		SfcStepFormMap sfcStepForm1 = new SfcStepFormMap();
		sfcStepForm1.put("where", " where sfc='"+sfc+"'" + 
				" and operation='"+operation+"'" + 
				" and site='"+site+"'" +
				" and shoporder='"+shopOrder+"'" + 
				" and (status = '0' or status ='1' or status ='2' )" +
				" order by id desc"
		);
		
//		sfcStepForm1.put("sfc", sfc);
//		sfcStepForm1.put("operation", operation);
//		sfcStepForm1.put("site", site);
//		sfcStepForm1.put("shoporder", shopOrder);
		List<SfcStepFormMap> sfcStepFormMapList = baseMapper.findByWhere(sfcStepForm1);
		SfcStepFormMap sfcStepForm2 = sfcStepFormMapList.get(0);
		String status = sfcStepForm2.getStr("status");
		if ("0".equals(status)) {
			status = "创建";
		}else if ("1".equals(status)) {
			status = "开始作业中";
		}else if ("2".equals(status)) {
			status = "已完成";
		}else if ("3".equals(status)) {
			status = "报废";
		}else if ("4".equals(status)) {
			status = "不良";
		}
		
		formMap.put("state", status);
		List<FormMap<String, String>> list = new ArrayList<FormMap<String, String>>();
		list.add(formMap);

		//TODO 点击开始将SFC信息存入第一个SFC步骤表
		// 获取request
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		ShopOrderFormMap shopOrderFormMap = new ShopOrderFormMap();
		shopOrderFormMap.put("shoporder_no", shopOrder);
		shopOrderFormMap.put("site", site);
		
		List<ShopOrderFormMap> shopOrderFormList = baseMapper.findByNames(shopOrderFormMap);
		ShopOrderFormMap shopOrderForm = shopOrderFormList.get(0);
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);

		try {
			SfcStepFormMap sfcStepFormMap = new SfcStepFormMap();
			sfcStepFormMap.put("sfc", sfc);
			sfcStepFormMap.put("site", site);
			List<SfcStepFormMap> sfcStepFormList = baseMapper.findByNames(sfcStepFormMap);
			if (sfcStepFormList==null || sfcStepFormList.size()<=0) {
				SfcStepFormMap sfcStepForm = new SfcStepFormMap();
				sfcStepForm.put("process_route", shopOrderForm.getStr("process_route"));
				sfcStepForm.put("sfc", sfc);
				sfcStepForm.put("operation", operation);
				sfcStepForm.put("status", "1");
				sfcStepForm.put("byUser", userFormMap.getStr("accountName"));
				baseMapper.addEntity(sfcStepForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setQueryResult(1, list);
		return pageView;		
	}

	/**
	 * 1、修改当前记录为完成
	 * 2、获取下一操作
	 * 3、如果不是最后一个操作，则添加一条下一步操作的步骤记录
	 * 4、如果是最后一个操作，则不添加
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SystemLog(module="生产过程控制",methods="生产操作员面板操作-完成")//凡需要处理业务逻辑的.都需要记录操作日志
	@RequestMapping("process_finish")
	public String process_finish(Model model) {
		FormMap<String, Object> formMap = getFormMap(FormMap.class);
		try {
			return assemblePodService.process_finish(formMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHelp.responseErrorText("操作异常："+e.getMessage());
		}
	}

	/**
	 * 查找装配的物料清单
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("pod_bom_findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		String shopOrderNo = getPara("shopOrderNo");
		String operationNo = getPara("operationNo");
		String wcSfc = getPara("wc_sfc");

		//根据工单号和站点查找成品物料的组装清单列表
		Map<String, String> pars = new HashMap<String, String>();
		pars.put("shoporderNo", shopOrderNo);
		pars.put("site", session.getAttribute("site").toString());
		pars.put("sfc", wcSfc);
		
		List<Map<String, Object>> data = baseExtMapper.getItemsByShopOrder(pars);
		
		List<BaseExtFormMap> list = new ArrayList<BaseExtFormMap>();
		for (Map<String, Object> map : data) {
			Set<String> set = map.keySet();
			BaseExtFormMap baseExtFormMap = new BaseExtFormMap();
			for (String key : set){
				baseExtFormMap.put(key, map.get(key));
			}
			baseExtFormMap.put("operation_no", operationNo);
			list.add(baseExtFormMap);
		}
		pageView = getPageView(pageNow, pageSize, getPara("orderby"));
		pageView.setQueryResult(list.size(), list);
		return pageView;
	}

	/**
	 * 装配
	 * 录入装配数量后，点击装配，触发此方法
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("fit_out")
	public String fit_out(Model model) {
		SfcAssemblyFormMap sfcAssemblyFormMap = getFormMap(SfcAssemblyFormMap.class);
		try {
			return assemblePodService.fit_out(sfcAssemblyFormMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHelp.responseErrorText("操作异常："+e.getMessage());
		}
	}

	/**
	 * 监听operation_input值的改变 来动态加载pod按钮
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("operation_input_changed")
	public BaseResponse operationInputChanged(String operationNo,Model model) throws Exception{
		Session session = SecurityUtils.getSubject().getSession();
		BaseResponse response = new BaseResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		List<PodButtonFormMap> podButtonFormMapList = new ArrayList<PodButtonFormMap>();
		List<PodFunctionFormMap> podFunctionFormMapList = new ArrayList<PodFunctionFormMap>();
		try {
			//1.根据operation_no 查询对应的id
			OperationFormMap operationForm = new OperationFormMap();
			operationForm.put("operation_no", operationNo);
			operationForm.put("site", session.getAttribute("site"));
			List<OperationFormMap> operationList = baseMapper.findByNames(operationForm);
			if (operationList==null || operationList.size()<=0) {
				response.setResult(false);
				response.setMessage("操作："+operationNo+"不存在，请检查是否存在该操作！");
				return response;
			}
			OperationFormMap operationFormMap = operationList.get(0);
			String operation_id = operationFormMap.getInt("id").toString();
			//2.去查操作和pod按钮关联的表 ly_operation_pod 查出对应的pod_no
			List<OperationPodFormMap> operationPodFormMapList = baseMapper.findByAttribute("operation_no", operation_id, OperationPodFormMap.class);
			if(operationPodFormMapList != null){
				for(OperationPodFormMap operationPodFormMap : operationPodFormMapList){
					PodButtonFormMap podButtonFormMap = baseMapper.findbyFrist("id", operationPodFormMap.getStr("pod_button_no"), PodButtonFormMap.class);
					podButtonFormMapList.add(podButtonFormMap);
					
					String podFunction = podButtonFormMap.getStr("pod_function");
					//根据podButton_no去查对应的pod_function  在查出pod_function_url
					List<PodFunctionFormMap> podFunctionFormMapList1 = baseMapper.findByAttribute("pod_function_no", podFunction, PodFunctionFormMap.class);
					podFunctionFormMapList.add(podFunctionFormMapList1.get(0));
				}		
			}
			map.put("buttonList", podButtonFormMapList);
			map.put("functionList", podFunctionFormMapList);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setResult(false);
			response.setMessage(e.getMessage());
			return response;
		}
		response.setResult(true);
		response.setData(map);
		response.setMessage("success");
		return response;
	} 

	/**
	 * 记录不良跳转
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("recode_nccode_ui")
	public String recode_nccode_ui(Model model)throws Exception{
		return Common.BACKGROUND_PATH + "/system/produce/pod_nccode/record_nccode";
	}
	
	/**
	 * 记录不合格
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("recode_nccode_methods")
	@ResponseBody
	public String recode_nccode_methods(){
		FormMap formMap = getFormMap(FormMap.class);
		try {
			return assemblePodService.recode_nccode(formMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHelp.responseErrorText("操作失败："+e.getMessage());
		}
	}
	
	
	/**
	 * SFC是否在操作上生产
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("isInProduction")
	public BaseResponse isInProduction()throws Exception{
		BaseResponse response = new BaseResponse(); 
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		
		String sfc = getPara("sfc");
		String operation = getPara("operation");
		String shoporder = getPara("shoporder");
		
		SfcStepFormMap sfcStepFormMap = new SfcStepFormMap();
		sfcStepFormMap.put("sfc", sfc);
		sfcStepFormMap.put("operation", operation);
		sfcStepFormMap.put("shoporder", shoporder);
		sfcStepFormMap.put("site", site);
		sfcStepFormMap.put("status", "1");
		List<SfcStepFormMap> sfcStepFormList = baseMapper.findByNames(sfcStepFormMap);
		if (sfcStepFormList==null || sfcStepFormList.size()<=0) {
			response.setResult(false);
			response.setMessage("SFC："+sfc+"未处于"+operation+"操作的生产状态");
			return response;
		}
		response.setResult(true);
		return response;
	}
	
	
	/**
	 * 获取考试信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getScoreInfo")
	public BaseResponse getScoreInfo()throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserFormMap userFormMap = (UserFormMap)Common.findUserSession(request);
		
		BaseResponse response = new BaseResponse(); 
		Session session = SecurityUtils.getSubject().getSession();
		String site = session.getAttribute("site").toString();
		
		//查询状态为启用的考试信息
		ExamInfoFormMap examinfo = new ExamInfoFormMap();
		examinfo.put("site", site);
		examinfo.put("enabled", "1");
		List<ExamInfoFormMap> list = baseMapper.findByNames(examinfo);
		
		
		if (list==null || list.size()<=0) {
			response.setResult(false);
			return response;
		}else {
			ExamInfoFormMap exam = list.get(0);
			long start = exam.getTimestamp("exam_start_time").getTime();
			long end = exam.getTimestamp("exam_end_time").getTime();
			long nowt = new Date().getTime();
			
			
			//如果在考试时间内容
			if (nowt>start &&
					nowt < end) {
				
				//查询考试成绩信息
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("examNo", exam.get("exam_no"));
				map.put("user", userFormMap.getStr("accountName"));
				map.put("site", site);
				exam.put("score", "0");
				List<Map<String, Object>> scoreList = baseExtMapper.findScore(map);
				if (scoreList!=null && scoreList.size()>0) {
					Map<String, Object> rs = scoreList.get(0);
					if (rs!=null && rs.size()>0) {
						exam.put("score", rs.get("score"));
					}
				}
				
				
				exam.put("exam_start_time", format1.format(exam.getTimestamp("exam_start_time")));
				exam.put("exam_end_time", format2.format(exam.getTimestamp("exam_end_time")));
				
				long surplus = ((end-new Date().getTime())/60000)>0?((end-new Date().getTime())/60000):0;
				exam.put("surplus", surplus);
				
				response.setResult(true);
				response.setData(exam);
			}else {
				//如果不在考试时间内，则修改状态为未启用
				
				exam.put("enabled", "0");
				baseMapper.editEntity(exam);
				response.setResult(false);
			}
		}
		return response;
	}
}