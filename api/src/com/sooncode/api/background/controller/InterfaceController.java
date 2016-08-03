package com.sooncode.api.background.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sooncode.api.background.entity.Example;
import com.sooncode.api.background.entity.Interfac;
import com.sooncode.api.background.entity.Parameter;
import com.sooncode.api.background.entity.Project;
import com.sooncode.api.background.service.ExampleService;
import com.sooncode.api.background.service.InterfacService;
import com.sooncode.api.background.service.ParameterService;
import com.sooncode.api.background.service.ProjectService;
import com.sooncode.api.background.util.MyUUID;

@Controller
@RequestMapping("/interface")
public class InterfaceController {
	@Autowired
	private InterfacService interfacService;

	@Autowired
	private ParameterService parameterService;

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ExampleService exampleService;

	@RequestMapping("/saveInterface")

	public ModelAndView saveInterface(HttpServletRequest request, HttpSession session) {

		String moduleId = request.getParameter("moduleId");
		if (moduleId != null) {
			moduleId = moduleId.replace("-", "");
		}
		String interfacId = request.getParameter("interfacId").replace("-", "");
		String interfacName = request.getParameter("interfacName");
		String interfacCode = request.getParameter("interfacCode");
		String interfacIntro = request.getParameter("interfacIntro");

		String url = request.getParameter("url");
		String returnDataType = request.getParameter("returnDataType");
		String requestType = request.getParameter("requestType");
		String parameterFormat = request.getParameter("parameterFormat");
		String type = request.getParameter("type");
		String style = request.getParameter("style");

		Interfac i = new Interfac();

		i.setInterfacId(interfacId);
		i.setModuleId(moduleId);
		i.setInterfacName(interfacName);
		i.setInterfacCode(interfacCode);
		i.setInterfacIntro(interfacIntro);

		i.setUrl(url);
		i.setReturnDataType(returnDataType);
		i.setRequestType(requestType);
		i.setParameterFormat(parameterFormat);
		i.setStyle(style);

		Map<String, Object> map = new HashMap<>();
		// 更新
		if (type != null && type.equals("UPDATE")) {
			String weight = request.getParameter("weight");
			i.setWeight(Integer.parseInt(weight));
			interfacService.interfacDao.update(i);
			map.put("interfac", i);
		} else {// 添加

			Integer weight = interfacService.interfacDao.getMaxWeight(moduleId);

			i.setWeight(weight == null ? 0 : weight + 1);

			interfacService.interfacDao.save(i);
			map.put("interfac", i);
			// 创建 返回值示例
			Example ex = new Example();
			ex.setExampleId(MyUUID.getUUID());
			ex.setInterfacId(interfacId);
			exampleService.exampleDao.save(ex);

		}

		return new ModelAndView("interface/return_interface", map);

	}

	@RequestMapping("/getInterfaces")
	public ModelAndView getInterfaces(HttpServletRequest request, HttpSession session) {
		String moduleId = request.getParameter("moduleId");
		Map<String, Object> map = new HashMap<String, Object>();

		Interfac i = new Interfac();
		i.setModuleId(moduleId);
		List<Interfac> interfaces = interfacService.interfacDao.getPager(1L, Long.MAX_VALUE, i).getLists();

		map.put("interfaces", interfaces);
		map.put("moduleId", moduleId);
		return new ModelAndView("interface/ed_interface_list", map);
	}

	/**
	 * 获取某个接口和他的参数
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/getInterfaceParameterFormatParameterSize")
	@ResponseBody
	public Map<String, Object> getInterfaceParameterFormatParameterSize(HttpServletRequest request, HttpSession session) {
		String interfacId = request.getParameter("interfacId");
		Interfac i = new Interfac();
		i.setInterfacId(interfacId);
		Interfac interfac = interfacService.interfacDao.get(i);

		Parameter p = new Parameter();
		p.setInterfacId(interfacId);
		int parameterSize = parameterService.parameterDao.getPager(1L, Long.MAX_VALUE, p).getLists().size();

		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("parameterFormat", interfac.getParameterFormat());
		newMap.put("parameterSize", parameterSize);
		return newMap;
	}

	/**
	 * 删除接口信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteInterface")
	@ResponseBody
	public String deleteInterface(HttpServletRequest request, HttpSession session) {

		String interfacId = request.getParameter("interfacId");
		Interfac i = new Interfac();
		i.setInterfacId(interfacId);
		int n = interfacService.interfacDao.delete(i);
		if (n == 1) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 移动接口位置
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/upOrdownInterface")
	public ModelAndView upOrdownInterface(HttpServletRequest request, HttpSession session) {

		String interfacId = request.getParameter("interfacId");
		String weight = request.getParameter("weight");
		String type = request.getParameter("type");

		Interfac i = new Interfac();

		i.setInterfacId(interfacId);
		if (type.equals("up")) {
			i.setWeight(Integer.parseInt(weight) - 2);
		} else {
			i.setWeight(Integer.parseInt(weight) + 2);
		}

		interfacService.interfacDao.update(i);
		String projectId = request.getParameter("projectId");
		Project project = projectService.projectDao.getProjectAllInfo(projectId);
		Map<String, Object> map = new HashMap<>();
		map.put("project", project);
		return new ModelAndView("ed/ed_index", map);
	}

	/**
	 * 跳转至更新接口页面
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/toEditInterface")

	public ModelAndView toEditInterface(HttpServletRequest request, HttpSession session) {

		String interfacId = request.getParameter("interfacId");

		Interfac i = new Interfac();
		i.setInterfacId(interfacId);
		i = interfacService.interfacDao.get(i);
		Map<String, Object> map = new HashMap<>();
		map.put("interfac", i);
		return new ModelAndView("interface/add_ed_interface", map);
	}

	/**
	 * 跳转至添加接口页面
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/toAddInterface")

	public ModelAndView toAddInterface(HttpServletRequest request, HttpSession session) {
		String moduleId = request.getParameter("moduleId");

		Interfac i = new Interfac();
		i.setModuleId(moduleId);
		Map<String, Object> map = new HashMap<>();
		map.put("interfac", i);
		return new ModelAndView("interface/add_ed_interface", map);
	}

	/**
	 * 获得接口
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/getInterfac")

	public ModelAndView getInterfac(HttpServletRequest request, HttpSession session) {

		String interfacId = request.getParameter("interfacId");

		Interfac i = new Interfac();

		i = projectService.projectDao.getInterfacAllInfo(interfacId);
		Map<String, Object> map = new HashMap<>();
		map.put("interfac", i);
		return new ModelAndView("interface/ed_interface_detail", map);
	}

	@RequestMapping("/saveJsonParameters")
	public ModelAndView saveJsonParameters(HttpServletRequest request) {

		String interfacId = request.getParameter("interfacId");
		String jsonParameters = request.getParameter("jsonParameters");

		Interfac i = new Interfac();
		i.setInterfacId(interfacId);
		i = interfacService.interfacDao.get(i);
		i.setJsonParameters(jsonParameters);
		interfacService.interfacDao.update(i);

		return null;

	}

}
