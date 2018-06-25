package com.att.automation.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.att.admin.services.AdminService;
import com.att.dbvalidation.model.DBValidationmodel;

@Controller
@RequestMapping(value = "/e2e")
public class AdminController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@RequestMapping(value = "/release", method = RequestMethod.GET)
	public ModelAndView getRelease(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<String> releaselist = adminService.getReleaseProperties();
		model.addAttribute("releaseList", releaselist);
		return new ModelAndView("admin/release", "banlist", new DBValidationmodel());
	}

	@RequestMapping(value = "/retrieveApplication", method = RequestMethod.POST)
	public @ResponseBody String[] retrieveApplication(@RequestParam("release") String release) throws IOException {
		logger.info("Inside Ajax  Controller: " + release);
		List<String> backendList = adminService.retrieveApplication(release);
		String[] arr = backendList.toArray(new String[backendList.size()]);
		return arr;
	}

	@RequestMapping(value = "/addRelease", method = RequestMethod.POST)
	public @ResponseBody void addRelease(@RequestParam("newRelease") String newRelease,
			@RequestParam("presentRelease") String presentRelease, Model model) throws IOException {
		logger.info(newRelease + "- " + presentRelease);
		adminService.createPropertiesFileForApplicationConfig(newRelease, presentRelease);
	}

	@RequestMapping(value = "/deleteRelease", method = RequestMethod.POST)
	public @ResponseBody void deleteRelease(@RequestParam("release") String release) throws IOException {
		adminService.deleteRelease(release);
	}

	@RequestMapping(value = "/retrieveApplicationBackends", method = RequestMethod.POST)
	public @ResponseBody String[] retrieveApplicationBackends(@RequestParam("release") String release,
			@RequestParam("app") String app) throws IOException {
		List<String> backendList = adminService.retrieveApplicationBackends(release, app);
		String[] arr = backendList.toArray(new String[backendList.size()]);
		return arr;
	}

	@RequestMapping(value = "/retrieveDbProperties", method = RequestMethod.POST)
	public @ResponseBody String[] retrieveBackendProperties(@RequestParam("release") String release,
			@RequestParam("backend") String backend) throws IOException {
		logger.info(release + "- " + backend);
		List<String> backendList = adminService.showBackendsProperties(backend, release);
		logger.info("DB Details: " + backendList);
		String[] arr = backendList.toArray(new String[backendList.size()]);
		return arr;
	}

	@RequestMapping(value = "/retrieveApplicationProperties", method = RequestMethod.POST)
	public @ResponseBody String retrieveApplicationProperties(@RequestParam("release") String release,
			@RequestParam("backend") String backend, @RequestParam("app") String app) throws IOException {
		logger.info(release + "--" + backend + "--" + app);
		String result = adminService.retrieveApplicationProperties(release, backend, app);
		logger.info(result + "- " + result);
		return result;
	}

	@RequestMapping(value = "/addProperty", method = RequestMethod.POST)
	public @ResponseBody void addProperty(@RequestParam("release") String release,
			@RequestParam("application") String application, @RequestParam("propertyKey") String propertyKey,
			@RequestParam("propertyValue") String propertyValue) throws IOException {
		adminService.addProperty(release, application, propertyKey, propertyValue);
	}

	@RequestMapping(value = "/addApplication", method = RequestMethod.POST)
	public @ResponseBody void addApplication(@RequestParam("release") String release,
			@RequestParam("applicationKey") String applicationKey) throws IOException {
		adminService.addApplication(release, applicationKey);
	}

	@RequestMapping(value = "/applicationManagement", method = RequestMethod.GET)
	public ModelAndView applicationManagement(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<String> releaselist = adminService.getReleaseProperties();
		model.addAttribute("releaseList", releaselist);

		HashMap<String, String> map = new HashMap<>();
		map.put("app1", "true");
		map.put("app2", "false");
		map.put("app3", "true");
		model.addAttribute("appMap", map);
		return new ModelAndView("admin/applicationManagement", "banlist", new DBValidationmodel());
	}

}