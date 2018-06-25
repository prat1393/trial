package com.att.automation.controller.dbvalidation;

import static com.att.dbvalidation.properties.DataValidationEnum.getBEListFromPropertis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.att.admin.services.AdminService;
import com.att.dbvalidation.dao.ValidationDAOUtility;
import com.att.dbvalidation.model.DBValidationmodel;
import com.att.dbvalidation.services.DataValidationCommonServices;
import com.att.dbvalidation.services.UnifiedService;
import com.att.dbvalidation.services.UverseService;
import com.att.dbvalidation.services.WirelessService;

@Controller
@RequestMapping(value = "/e2e")
public class DBValidationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UverseService uverseService;

	private WirelessService wirelessService;

	private UnifiedService unifiedService;

	private DataValidationCommonServices dataValidationCommonServices;

	private AdminService adminService;

	@Autowired
	public DBValidationController(UverseService uverseService, WirelessService wirelessService,
			UnifiedService unifiedService, DataValidationCommonServices dataValidationCommonServices,
			AdminService adminService) {
		super();
		this.uverseService = uverseService;
		this.wirelessService = wirelessService;
		this.unifiedService = unifiedService;
		this.dataValidationCommonServices = dataValidationCommonServices;
		this.adminService = adminService;
	}

	@GetMapping(value = "/test")
	public String test() {
		return "blank";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "errorpage";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String accessdenied() {
		return "accessdenied";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView getAdminForm(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");

		List<String> backends = getBEListFromPropertis("_CURRENT_DRIVER", 0);

		HttpSession session = request.getSession();
		session.setAttribute("release", dataValidationCommonServices.getRelease());
		session.setAttribute("backends", backends);

		model.addAttribute("release", dataValidationCommonServices.getRelease());
		model.addAttribute("backends", backends);
		return new ModelAndView("admin/admin", "banlist", new DBValidationmodel());
	}

	@RequestMapping(value = "/testrelease", method = RequestMethod.POST)
	public String adminValidation(@ModelAttribute("banlist") DBValidationmodel banlist, Model model,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "view") String view,
			@RequestParam(required = false, value = "submit") String submit,
			@RequestParam(required = false, value = "testconnection") String testconnection,
			@RequestParam(required = false, value = "updateproperties") String updateproperties) throws IOException {

		String releasePropertiesPath = banlist.getRelease();
		logger.info("Release:::::" + releasePropertiesPath);
		List<String> list = new LinkedList<>();

		if (view != null) {
			List<String> backends = adminService.retrieveDBBackends(releasePropertiesPath);
			model.addAttribute("backends", backends);
		} else if (submit != null) {
			String backends = banlist.getBackends();
			list = adminService.showBackendsProperties(backends, releasePropertiesPath);
			model.addAttribute("resultList", list);
		} else {
			String[] connectionDetails = new String[3];
			connectionDetails[0] = banlist.getDriveclass();
			connectionDetails[1] = banlist.getUsername();
			connectionDetails[2] = banlist.getPassword();
			if (testconnection != null) {
				if (connectionDetails[0].contains("jdbc:oracle:")) {
					ValidationDAOUtility.checkoracleConnection(connectionDetails);
				} else if (connectionDetails[0].contains("jdbc:db2:")) {
					ValidationDAOUtility.checkdb2Connection(connectionDetails);
				}

			} else {
				// adminService.updateProperties(release, backends,
				// connectionDetails);
			}

			list.add(connectionDetails[0]);
			list.add(connectionDetails[1]);
			list.add(connectionDetails[2]);
			model.addAttribute("resultList", list);
		}

		model.addAttribute("releasemap", adminService.getReleaseProperties());

		return "admin/releasemanagement";
	}

	@RequestMapping(value = "/uverseValidation", method = RequestMethod.GET)
	public ModelAndView getUverseValidation(Model model) throws IOException {
		model.addAttribute("release", dataValidationCommonServices.getRelease());
		return new ModelAndView("dbvalidation/uverseValidationInput", "banlist", new DBValidationmodel());
	}

	@RequestMapping(value = "/retrieveUverseDBApplication", method = RequestMethod.POST)
	public @ResponseBody String retrieveUverseDBApplication(@RequestParam("release") String release)
			throws IOException {
		HashMap<String, String> statusMap = adminService.retrieveEnableApplication(release);
		String result = statusMap.entrySet().stream().filter(x -> "STATUS_DB_VALIDATION_UVERSE".equals(x.getKey()))
				.map(map -> map.getValue()).collect(Collectors.joining());
		return result;
	}

	@RequestMapping(value = "/unifiedValidation", method = RequestMethod.GET)
	public ModelAndView getUnifiedValidation(Model model) throws IOException {
		model.addAttribute("release", adminService.getReleaseProperties());
		return new ModelAndView("dbvalidation/unifiedValidationInput", "banlist", new DBValidationmodel());
	}

	@RequestMapping(value = "/retrieveUnifiedDBApplication", method = RequestMethod.POST)
	public @ResponseBody String retrieveUnifiedDBApplication(@RequestParam("release") String release)
			throws IOException {
		HashMap<String, String> statusMap = adminService.retrieveEnableApplication(release);
		String result = statusMap.entrySet().stream().filter(x -> "STATUS_DB_VALIDATION_UNIFIED".equals(x.getKey()))
				.map(map -> map.getValue()).collect(Collectors.joining());
		return result;
	}

	@RequestMapping(value = "/wirelessValidation", method = RequestMethod.GET)
	public ModelAndView getWirelessValidation(Model model) throws IOException {
		model.addAttribute("release", adminService.getReleaseProperties());
		return new ModelAndView("dbvalidation/wirelessValidationInput", "banlist", new DBValidationmodel());
	}

	@RequestMapping(value = "/retrieveWirelessDBApplication", method = RequestMethod.POST)
	public @ResponseBody String retrieveWirelessDBApplication(@RequestParam("release") String release)
			throws IOException {
		HashMap<String, String> statusMap = adminService.retrieveEnableApplication(release);
		String result = statusMap.entrySet().stream().filter(x -> "STATUS_DB_VALIDATION_WIRELESS".equals(x.getKey()))
				.map(map -> map.getValue()).collect(Collectors.joining());
		return result;
	}

	@RequestMapping(value = "/uversevalidation", method = RequestMethod.POST)
	public String uversevalidation(@ModelAttribute("banlist") DBValidationmodel banlist, Model model,
			MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<String> list = new ArrayList<>();
		String release = banlist.getRelease();

		if (file.isEmpty()) {
			String bans[] = banlist.getBan().split(",");
			list = Arrays.asList(bans);
		} else {
			list = dataValidationCommonServices.readUverseWirelessFile(file);
		}
		HttpSession session = request.getSession();
		session.setAttribute("release", release);
		session.setAttribute("allBans", list);

		banlist.setE2eQueries("allQueries");
		uverseService.getUverseQueries(model);
		model.addAttribute("bans", list);
		model.addAttribute("release", release);
		return "dbvalidation/uverseValidationOutput";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uversevalidationoutput", method = RequestMethod.POST)
	public String uversevalidationoutput(@ModelAttribute("banlist") DBValidationmodel dbmodel, Model model,
			HttpServletRequest request) {

		List<String> banList = new ArrayList<>();
		List<String> result = new ArrayList<>();

		HttpSession session = request.getSession(false);
		String release = (String) session.getAttribute("release");
		List<String> allBans = (List<String>) session.getAttribute("allBans");
		model.addAttribute("bans", allBans);

		String selectedbans = dbmodel.getBan();
		if (selectedbans != null && !selectedbans.isEmpty()) {
			if (selectedbans.contains("ALL")) {
				banList = allBans;
			} else {
				String bans[] = selectedbans.split(",");
				banList = Arrays.asList(bans);
			}

			String e2eQueries = dbmodel.getE2eQueries();
			dbmodel.setE2eQueries("allQueries");
			result = uverseService.uversevalidation(dbmodel, model, banList, e2eQueries, release);
			model.addAttribute("resultList", result);
			uverseService.getUverseQueries(model);
		} else {
			System.out.println("Ban is empty");
		}
		return "dbvalidation/uverseValidationOutput";

	}

	@RequestMapping(value = "/unifiedvalidation", method = RequestMethod.POST)
	public String unifiedvalidation(@ModelAttribute("banlist") DBValidationmodel banlist, Model model,
			MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String release = banlist.getRelease();
		model.addAttribute("release", release);

		List<String> list = new ArrayList<>();
		if (file.isEmpty()) {

		} else {
			list = dataValidationCommonServices.readUnifiedFile(file);
			System.out.println("List Unified Ban: " + list);
		}

		HttpSession session = request.getSession();
		session.setAttribute("release", release);
		session.setAttribute("allBans", list);
		unifiedService.getUnifiedQueries(model);
		model.addAttribute("bans", list);

		return "dbvalidation/unifiedValidationOutput";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/unifiedValidationOutput", method = RequestMethod.POST)
	public String unifiedValidationOutput(@ModelAttribute("banlist") DBValidationmodel dbmodel, Model model,
			HttpServletRequest request) {

		List<String> banList = new ArrayList<>();
		List<String> result = new ArrayList<>();

		HttpSession session = request.getSession(false);
		String release = (String) session.getAttribute("release");
		List<String> allBans = (List<String>) session.getAttribute("allBans");
		model.addAttribute("bans", allBans);

		String selectedbans = dbmodel.getBan();
		if (selectedbans != null && !selectedbans.isEmpty()) {
			if (selectedbans.contains("ALL")) {
				banList = allBans;
			} else {
				String bans[] = selectedbans.split(",");
				banList = Arrays.asList(bans);
			}
			String e2eQueries = dbmodel.getE2eQueries();

			result = unifiedService.unifiedvalidation(dbmodel, model, banList, e2eQueries, release);
			model.addAttribute("resultList", result);
			unifiedService.getUnifiedQueries(model);
		} else {
			System.out.println("Ban is empty");
		}
		return "dbvalidation/unifiedValidationOutput";
	}

	@RequestMapping(value = "/wirelessvalidation", method = RequestMethod.POST)
	public String wirelessvalidation(@ModelAttribute("banlist") DBValidationmodel banlist, Model model,
			MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<String> list = new ArrayList<>();
		String release = banlist.getRelease();
		model.addAttribute("release", release);
		if (file.isEmpty()) {
			String bans[] = banlist.getBan().split(",");
			list = Arrays.asList(bans);
		} else {
			list = dataValidationCommonServices.readUverseWirelessFile(file);
		}
		HttpSession session = request.getSession();
		session.setAttribute("release", release);

		banlist.setE2eQueries("allQueries");
		wirelessService.getWLSQueries(model);
		list = dataValidationCommonServices.retrieveBANfromCTN(list, release);
		model.addAttribute("bans", list);
		session.setAttribute("allBans", list);
		return "dbvalidation/wirelessValidationOutput";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/wirelessvalidationoutput", method = RequestMethod.POST)
	public String wirelessvalidationoutput(@ModelAttribute("banlist") DBValidationmodel dbmodel, Model model,
			HttpServletRequest request) {

		List<String> banList = new ArrayList<>();
		List<String> result = new ArrayList<>();

		HttpSession session = request.getSession(false);
		String release = (String) session.getAttribute("release");
		List<String> allBans = (List<String>) session.getAttribute("allBans");
		model.addAttribute("bans", allBans);

		String selectedbans = dbmodel.getBan();
		if (selectedbans != null && !selectedbans.isEmpty()) {
			if (selectedbans.contains("ALL")) {
				banList = allBans;
			} else {
				String bans[] = selectedbans.split(",");
				banList = Arrays.asList(bans);
			}
			String e2eQueries = dbmodel.getE2eQueries();
			dbmodel.setE2eQueries("allQueries");
			result = wirelessService.wirelessvalidation(dbmodel, model, banList, e2eQueries, release);
			model.addAttribute("resultList", result);
			wirelessService.getWLSQueries(model);
		} else {
			System.out.println("Ban is empty");
		}
		return "dbvalidation/wirelessValidationOutput";
	}
}