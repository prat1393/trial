/**
 *
 */
package com.itko.lisa.vse.batch.cmd.mgr;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itko.javassist.tools.rmi.RemoteException;
import com.itko.lisa.acl.AuthenticationException;
import com.itko.lisa.acl.User;
import com.itko.lisa.coordinator.TestRegistry;
import com.itko.lisa.coordinator.VirtualService;
import com.itko.lisa.coordinator.VirtualServiceEnvironment;
import com.itko.lisa.model.mar.ModelArchive;
import com.itko.lisa.model.mar.ModelArchiveInfo;
import com.itko.lisa.model.mar.ModelArchiveInfoType;
import com.itko.lisa.model.mar.ModelArchiveUtils;
import com.itko.lisa.repo.ProjectManager;
import com.itko.lisa.test.Environment;
import com.itko.lisa.test.LisaException;
import com.itko.lisa.test.LisaSecurityManager;
import com.itko.lisa.test.Project;
import com.itko.lisa.vse.VirtualServiceStatus;
import com.itko.util.StrUtil;
import com.itko.util.StreamHelp;

/**
 * VSE manager wrapper class for batch operations.
 * 
 */
public class VSEManagerWrapper_try {

	private static Logger				log			= Logger.getLogger(VSEManagerWrapper_try.class);

	private List<Command>				commands;

	private Map<Argument, Object>		arguments;

	private String						registryName;

	private String						environmentName;

	private VirtualServiceEnvironment	environment;

	private static String[]				commandLine;

	private static User					user;


	public static void main(String args[]) {
		int returnCode = 255;
		commandLine = args;
		try {
			new Environment("VSE Manager for batch operations", 9);
			Environment.banner("LISA Virtual Service Environment Manager - The Command Line Tool for Managing VSEs.");
			String text = Environment.getBrand() + " Version :: " + Environment.getVersionString();
			log.info(text);

			LinkedList<String> params = new LinkedList<String>(Arrays.asList(args));

			VSEManagerWrapper_try managerWrapper = new VSEManagerWrapper_try(params);

			managerWrapper.processCommands();

			returnCode = 0;
		} catch (Throwable th) {
			// th.printStackTrace();
			if ((th instanceof RemoteException)) {
				th = th.getCause();
			}
			while (getCause(th) != null) {
				th = getCause(th);
			}
			if (((th instanceof IllegalArgumentException)) || ((th instanceof IllegalStateException))
					|| ((th instanceof SecurityException)) || ((th instanceof AuthenticationException))) {
				System.err.println("ERROR: " + th.getMessage());
				System.err.println();
				System.err.println("Use --help for help.");

				if ((th instanceof AuthenticationException))
					returnCode = 252;
				else if ((th instanceof SecurityException))
					returnCode = 251;
			} else if ((th instanceof ClassNotFoundException)) {
				System.err.println("ERROR: The VSE system cannot find a class required by the VS model.  Please make");
				System.err.println("sure the");
				System.err.println();
				System.err.println("\t" + th.getMessage());
				System.err.println();
				System.err.println("class is available on the VSE server (maybe by dropping a jar in the VSE's");
				System.err.println("hotDeploy directory).");
			} else {
				System.err.println("ERROR: An error occurred processing specified arguments.");
				System.err.println();
				System.err.println("Use --help for help.");

				th.printStackTrace();
			}
		} finally {
			LisaSecurityManager.logout();
		}
		System.exit(returnCode);
	}

	private static final Throwable getCause(Throwable th) {
		return (th instanceof LisaException) ? ((LisaException) th).getCaught() : th.getCause();
	}

	/**
	 * 
	 * @param args
	 *            // convert command line into a list of converted arguments
	 *            // only validation done is that
	 *            // a: it's a valid argument, per the entire list of available
	 *            arguments
	 *            // b: if it requires a parameter that there is one provided
	 *            // - end result is a Map<Argument, Object>
	 *            // -- where Argument is the enum
	 *            // -- Object is the passed value, null if one not required
	 *            // no validation of command vs required arguments is done here
	 */
	public VSEManagerWrapper_try(LinkedList<String> args) {

		this.commands = new ArrayList<Command>();

		this.arguments = new HashMap<Argument, Object>();

		if (args.isEmpty()) {
			
			this.commands.add(Command.HELP);
		}

		else {
			String cmd = (String) args.removeFirst();
			Command command = Command.fromString(cmd);
			if (command == null) {
				this.commands.clear();
				this.commands.add(Command.HELP);
			} else {
				this.commands.add(command);
			}
		}
		
		while (!args.isEmpty()) {
			
			
			String arg = (String) args.removeFirst();
			
			
			if (arg.startsWith("--")) {
				
				arg = arg.substring(2);
				System.out.println(arg);
				
			} else {
				
				throw new IllegalArgumentException("Expecting a parameter but found \"" + arg + "\" instead.");
			}
			
			
			Argument argument = Argument.fromString(arg);
			
			
			if (argument == null) {
				
				if (arg.startsWith(Argument.PASSWORD.argumentName) || arg.startsWith(Argument.USER.argumentName)) {
					continue;
				} else {
					
					this.commands.clear();
					this.commands.add(Command.HELP);
					throw new IllegalArgumentException("Unknown argument:  \"" + arg + "\".");
				}
			} // arg null
			
			
			
			String parm = null;
			
			
			if (argument.hasValue) {
				
				if (args.isEmpty()) {
					
					throw new IllegalArgumentException("Parameter \"" + arg + "\" requires a value.");
				}
				
				parm = (String) args.removeFirst();
			}

			this.arguments.put(argument, parm);
		} // while more args

		processArguments();
		
		
		// processCommands();
	} // ctor

	// private Boolean argumentStringToBoolean(String value)
	// {
	// if (value==null)
	// {
	// value = "false";
	// }
	// Boolean bauto = Boolean.parseBoolean(value);
	//
	// return bauto;
	// }
	//

	/**
	 * Now we validate all of the converted arguments against the command to run
	 */
	private void processArguments() {

		// first make sure the command has all the required arguments
		Command command = this.commands.get(0);

		Argument[] args = command.arguments;

		// just in case you do something stupid like add the same parameter
		// twice without noticing in the Command declaration:

		Set<Argument> argset = new HashSet<Argument>(Arrays.asList(args));

		for (Argument arg : argset) {

			// log.info("processing: " + arg);
			if (arg.required) { // if it's a required argument, make sure it was

				// provided
				if (!this.arguments.containsKey(arg)) {
					throw new IllegalArgumentException("Command \"" + command.commandName
							+ "\" requires the parameter \"" + arg.argumentName + "\"");
				} // required argument wasn't provided

			}

			// we made it here, so all of the required arguments, at minimum,
			// have been provided
			// optional ones may still be present
			// now we just do some cleanup on values as needed
			// -- convert strings to boolean values
			// -- convert strings to File that are files or directories
			// -- Argument.SERVICE_NAME is a catch-all string, so don't convert
			// at all
			// if arguments are optional and aren't provided, then set them to
			// default values

			switch (arg) {
				case AUTO_RESTART: {
					String auto = (String) this.arguments.get(arg);

					if (auto == null) {
						auto = "false";
					}
					if (auto.equalsIgnoreCase("on")) {
						auto = "true";
					}
					Boolean bauto = Boolean.parseBoolean(auto);
					this.arguments.put(arg, bauto);
					break;
				}
				case BUILD_ALL: {
					String buildall = (String) this.arguments.get(arg);
					Boolean build = Boolean.parseBoolean(buildall);
					this.arguments.put(arg, build);
					break;
				}
				case CAPACITY: {
					String caps = (String) this.arguments.get(arg);
					if (caps == null) {
						caps = "1";
					}
					try {
						Integer cap = Integer.parseInt(caps);
						if (cap < 1) {
							throw new Exception();
						}
						this.arguments.put(arg, cap);
					} catch (Exception cape) {
						throw new IllegalArgumentException("Argument \"" + arg.argumentName
								+ "\" must be a valid integer greater than 0.");
					}
					break;
				}
				case CONFIG: {
					String config = (String) this.arguments.get(arg);
					File conf = getNormalizedFile(config, false);
					// Project project = Project.loadProject(conf);
					this.arguments.put(arg, conf);
					break;
				}
				case IGNORE_IF_EXIST: {
					String ignore = (String) this.arguments.get(arg);
					if (ignore == null) {
						ignore = "true";
					}

					try {
						Boolean bignore = Boolean.parseBoolean(ignore);
						this.arguments.put(arg, bignore);
					} catch (Exception autoe) {
						throw new IllegalArgumentException("Argument \"" + arg.argumentName
								+ "\" must be true or false.");
					}
					break;
				}
				case MAR_DIR: {
					String marDir = (String) this.arguments.get(arg);
					File mard = getNormalizedFile(marDir, true);
					this.arguments.put(arg, mard);

					break;
				}
				case PASSWORD: {
					break;
				}
				case REGISTRY: {
					this.registryName = (String) this.arguments.get(arg);
					break;
				}
				case SERVICE_NAME: {
					// Argument.SERVICE_NAME is a catch-all string, so don't
					// convert at all
					break;
				}
				case THINK_FACTOR: {
					String think = (String) this.arguments.get(arg);
					if (think == null) {
						think = "1";
					}
					try {
						Integer cap = Integer.parseInt(think);
						this.arguments.put(arg, cap);
					} catch (Exception cape) {
						throw new IllegalArgumentException("Argument \"" + arg.argumentName
								+ "\" must be a valid integer.");
					}
					break;
				}
				
				case USER: {
					break;
				}
				
				case VSE: {
					this.environmentName = (String) this.arguments.get(arg);
					break;
				}
				
				case VS_DIR: {
					
					String vsDir = (String) this.arguments.get(arg);
					vsDir = getNormalizedFile(vsDir, true).toString();
					this.arguments.put(arg, new File(vsDir));
					break;
					
				} case PROJECT_ROOT_DIR: {
					
					String projectRootDir = (String) this.arguments.get(arg);
					projectRootDir = getNormalizedFile(projectRootDir, true).toString();
					this.arguments.put(arg, new File(projectRootDir));
					break;
				}

			} // switch on argument

		} // for each argument in the command
	} // processArguments

	private void processCommands() throws Exception {
		Command cmd = this.commands.get(0);

		switch (cmd) {
			case BUILD_ALL_MAR: {
				ensureTheRegistry();
				// make sure the build-all argument is present!
				this.arguments.put(Argument.BUILD_ALL, true);
				buildFromVSM(false, true);
				break;
			}
			case BUILD_MAR: {
				ensureTheRegistry();
				buildFromVSM(false, true);
				break;
			}
			case BUILD_ALL_MARI: {
				ensureTheRegistry();
				// make sure the build-all argument is present!
				this.arguments.put(Argument.BUILD_ALL, true);
				buildFromVSM(true, false);
				break;
			}
			case BUILD_MARI: {
				ensureTheRegistry();
				buildFromVSM(true, false);
				break;
			}
			case DEPLOY: {
				ensureTheEnvironment();
				deploy();
				break;
			}
			case DEPLOY_ALL: {
				ensureTheEnvironment();
				deployAllModels();
				break;
			}
			case DEPLOY_ALL_MAR: {
				ensureTheEnvironment();
				Project project = Project.createNewProject(new File("."));
				deployAllMar();
				break;
			}
			case DEPLOY_ALL_MARI: {
				ensureTheEnvironment();
				deployAllMari();
				break;
			}
			case DEPLOY_MARI: {
				ensureTheEnvironment();
				Project project = Project.createNewProject(new File("."));
				deploySingleMari();
				break;
			}
			case HELP: {
				showHelpText();
				break;
			}
			case REDEPLOY: {
				ensureTheEnvironment();
				redeployService();
				break;
			}
			case REMOVE_ALL: {
				ensureTheEnvironment();
				removeAllServices();
				break;
			}
			case REMOVE: {
				ensureTheEnvironment();
				removeService();
				break;
			}
			case VSE_START_ALL: {
				ensureTheRegistry();
				ensureTheEnvironment();
				startAllVSEServices();
				break;
			}
			case STATUS_VSE: {
				ensureTheRegistry();
				ensureTheEnvironment();
				statusAllVirtualServices();
				break;
			}
			case RESET_TX_VSE: {
			ensureTheRegistry();
			ensureTheEnvironment();
			resetTransactionCounts();
			break;
			}	
			case PROJECT_STOP_ALL: {
				ensureTheRegistry();
				ensureTheEnvironment();
				stopAllVirtualServices();
				break;
			}
			case PROJECT_START_ALL: {
				ensureTheEnvironment();
				startAllVirtualServices();
				break;
			}

		} // switch
	} // processCommands

	private File getNormalizedFile(String arg, boolean mustBeADir) {
		File file = new File(arg);

		file = file.getAbsoluteFile();

		if (mustBeADir) {
			if (!file.isDirectory()) {
				throw new IllegalArgumentException("The file \"" + file + "\" is not a directory.");
			}
		} else if (!file.isFile()) {
			throw new IllegalArgumentException("The file \"" + file + "\" cannot be read.");
		}

		return file;
	}

	private void stopAllVirtualServices() throws Exception {

		File vsdir = (File) this.arguments.get(Argument.VS_DIR);

		File[] projectvirtualServiceModels = vsdir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".vsm");
			}
		});

		List<File> projectModels = Arrays.asList(projectvirtualServiceModels);
		Set<String> serviceNames = this.environment.getServiceNames();

		// / for each service running in the VSE:
		// get the model name
		// if the model name exists in this project, stop it.

		List<List<String>> data = new ArrayList<List<String>>();

		for (String serviceName : serviceNames) {
			VirtualService vs = this.environment.getService(serviceName);
			String modelName = vs.getModelName();
			// log.info("Service: " + serviceName + " is from model: " +
			// modelName);

			File modelFile = new File(vsdir, modelName + ".vsm");

			if (projectModels.contains(modelFile)) {
				boolean wasRunning = (VirtualService.STATUS_RUNNING == vs.getStatus());

				if (wasRunning) {
					this.environment.stopService(serviceName);
				}
				boolean isRunning = (VirtualService.STATUS_RUNNING == vs.getStatus());
				List<String> row = new ArrayList<String>();
				row.add(serviceName);
				row.add(Boolean.valueOf(wasRunning).toString());
				row.add(Boolean.valueOf(isRunning).toString());

				data.add(row);
			} // if models contain model

		} // for each service in the VSE

		log.info("Results of stopping project services on " + environmentName);
		StrUtil.display(data, "Name", "Was Running", "Running");
	}

	/**
	 * This is a helper method for storing data about a service into the list
	 * provided.
	 * 
	 * @param serviceInfo
	 *            the service status information to describe.
	 * @param data
	 *            the place where the description should go.
	 */
	private void describe(VirtualServiceStatus serviceInfo, List<List<String>> data) throws Exception {
		List<String> row = new ArrayList<String>();
		int statusCode = serviceInfo.getStatusCode();

		row.add(serviceInfo.getName());
		row.add(serviceInfo.getResource());

		switch (statusCode) {
			case VirtualService.STATUS_LOADED:
				row.add("Loaded");
				break;
			case VirtualService.STATUS_STARTING:
				row.add("Starting");
				break;
			case VirtualService.STATUS_RUNNING:
				row.add("Running");
				break;
			case VirtualService.STATUS_STOPPING:
				row.add("Stopping");
				break;
			case VirtualService.STATUS_ENDED:
				row.add("Ended");
				break;
			case VirtualService.STATUS_NOT_RUNNABLE:
				row.add("Dead");
				break;
			default:
				row.add("(unknown)");
		}

		row.add(serviceInfo.isAutoRestartEnabled() ? "Yes" : null);
		row.add(String.valueOf(serviceInfo.getConcurrentCapacity()));
		row.add(String.valueOf(serviceInfo.getThinkTimeScale()));
		row.add(String.valueOf(serviceInfo.getTransactionCount()));
		row.add(String.valueOf(serviceInfo.getErrorCount()));
		row.add(serviceInfo.getExecutionMode().toString());
		row.add(serviceInfo.getUpTime());

		data.add(row);
	}

	private Set<String> getBaseModelNamesFromVse() throws Exception {
		Set<String> baseNames = new HashSet<String>();

		Set<String> svcNames = this.environment.getServiceNames();
		for (String svc : svcNames) {
			ModelArchive mar = this.environment.getModelArchive(svc);
			baseNames.add(mar.getMARInfo().getName());
		}

		return baseNames;
	}

	private List<String> getServiceNamesForModel(String model) throws Exception {
		List<String> baseNames = new ArrayList<String>();

		Set<String> svcNames = this.environment.getServiceNames();
		for (String svc : svcNames) {
			ModelArchive mar = this.environment.getModelArchive(svc);
			String modelName = mar.getMARInfo().getName();

			if (model.equals(modelName)) {
				baseNames.add(svc);
			}
		}
		return baseNames;
	}

	private void statusAllVirtualServices() throws Exception {

		File vsdir = getVSDir();

		File[] projectvirtualServiceModels = getFilesWithExtension(vsdir, ".vsm");
		List<File> projectModels = Arrays.asList(projectvirtualServiceModels);

		Set<String> serviceNames = getBaseModelNamesFromVse();
		List<List<String>> data = new ArrayList<List<String>>();

		// for each vsm in the project, get its status from the VSE
		// if not deployed, log as such

		for (File vsmFile : projectModels) {
			// get the base name without the extension
			String baseModelName = vsmFile.getName();
			int pidx = baseModelName.lastIndexOf(".");
			baseModelName = baseModelName.substring(0, pidx);

			if (serviceNames.contains(baseModelName)) {
				// this model has been deployed to the VSE under some name or
				// another
				List<String> deployedNames = getServiceNamesForModel(baseModelName);
				for (String deployedName : deployedNames) {
					VirtualService vs = this.environment.getService(deployedName);
					describe(vs.getServiceInfo(), data);
				}
			} else {
				// this model has not been deployed to the VSE
				List<String> row = new ArrayList<String>();
				row.add(baseModelName);
				row.add("Not deployed");
				for (int i = 0; i < 8; i++) {
					row.add("--");
				}
				data.add(row);
			}
		}

		StrUtil.display(data, "Name", "Resource", "Status", "Auto restart", "Capacity", "Think", "Txns", "Errors", "Exec Mode",
				"Up Time");

	}
	
	private void resetTransactionCounts() throws Exception {

		File vsdir = getVSDir();

		File[] projectvirtualServiceModels = getFilesWithExtension(vsdir,
				".vsm");
		List<File> projectModels = Arrays.asList(projectvirtualServiceModels);

		Set<String> serviceNames = getBaseModelNamesFromVse();

		// for each service running in the VSE:
		// get the model name
		// if the model name exists in this project, reset the transaction count

		List<List<String>> data = new ArrayList<List<String>>();

		for (String serviceName : serviceNames) {
			VirtualService vs = this.environment.getService(serviceName);
			String modelName = vs.getModelName();

			File modelFile = new File(vsdir, modelName + ".vsm");

			if (projectModels.contains(modelFile)) {
				vs.resetTransactionCounts();
				while(vs.getTransactionCount() != 0);
				describe(vs.getServiceInfo(), data);
			} // if models contain model

		} // for each service in the VSE

		StrUtil.display(data, "Name", "Resource", "Status", "Auto", "Capacity",
				"Think", "Txns", "Errors", "Exec Mode", "Up Time");
	}

	private void startAllVirtualServices() throws Exception {

		File vsdir = (File) this.arguments.get(Argument.VS_DIR);

		File[] projectvirtualServiceModels = getFilesWithExtension(vsdir, ".vsm");

		List<File> projectModels = Arrays.asList(projectvirtualServiceModels);

		Set<String> serviceNames = this.environment.getServiceNames();

		List<List<String>> data = new ArrayList<List<String>>();

		for (String serviceName : serviceNames) {

			VirtualService vs = this.environment.getService(serviceName);

			String modelName = vs.getModelName();
			

			File modelFile = new File(vsdir, modelName + ".vsm");

			if (projectModels.contains(modelFile)) {

				VirtualService virtualService = this.environment.getService(serviceName);
				
				if (VirtualService.STATUS_RUNNING == virtualService.getStatus()) {
					
					List<String> row = new ArrayList<String>();
					
					row.add(modelName);
					row.add("true");
					row.add("Skipped, already running");
					data.add(row);

				} else {
					
					this.environment.startService(serviceName);
					List<String> row = new ArrayList<String>();
					
					row.add(modelName);
					row.add("--");
					row.add("Started sucessfully");
					data.add(row);

				}
			}

		} // for each service in the VSE

		StrUtil.display(data, "Model name", "Was running", "Action status");

	} // start all project svcs


	private void deployAllModels() throws Exception {
		
		Project project = getProject();
		
		File vsdir = getVSDir();

		showUsedOptions();
		log.info("\n");

		File[] virtualServiceModels = getFilesWithExtension(vsdir, ".vsm");

		deploy(project, Arrays.asList(virtualServiceModels));

	}

	// [startregion] Utilities

	// **********************************************************************
	// Utility Functions to return information directly from this.arguments
	private File getConfigFile() {
		return (File) this.arguments.get(Argument.CONFIG);
	}

	private int getCapacity() {
		Object capacityo = this.arguments.get(Argument.CAPACITY);
		int capacity = capacityo == null ? 1 : (Integer) capacityo;
		return capacity;
	}

	private int getThinkFactor() {
		Object thinko = this.arguments.get(Argument.THINK_FACTOR);
		int thinkFactor = thinko == null ? 100 : (Integer) thinko;
		return thinkFactor;
	}

	private File getVSDir() {
		return (File) this.arguments.get(Argument.VS_DIR);
	}
	
	private File getProjectRootDir() {
		
		return new File((String)this.arguments.get(Argument.PROJECT_ROOT_DIR));
	}

	private File getMarDir() {
		return (File) this.arguments.get(Argument.MAR_DIR);
	}

	private String getServiceName() {
		return (String) this.arguments.get(Argument.SERVICE_NAME);
	}

	private Boolean getAutoRestart() {
		Object auto = this.arguments.get(Argument.AUTO_RESTART);
		Boolean autoRestartObject = auto == null ? false : (Boolean) auto;
		return autoRestartObject;
	}

	private Boolean getIgnoreIfExists() {
		Object ignoreo = this.arguments.get(Argument.IGNORE_IF_EXIST);
		Boolean ignoreIfExists = ignoreo == null ? true : (Boolean) ignoreo;
		return ignoreIfExists;
	}

	private Project getProject() {
		
		return Project.loadProject(getProjectRootDir());
	}

	// End of Utility Functions to return information directly from
	// this.arguments
	// *******************************************************************************

	// [endregion] Utilities

	/**
	 * Deploys a single VSM as specified in the Argument.SERVICE_NAME
	 * 
	 * @throws Exception
	 */
	private void deploy() throws Exception {
		showUsedOptions();
		log.info("\n");
		String serviceName = getServiceName();
		String serviceFileName = serviceName + ".vsm";
		File vsdir = getVSDir();
		File serviceFile = new File(vsdir, serviceFileName);

		List<File> files = new ArrayList<File>();
		files.add(serviceFile);
		deploy(getProject(), files);

	}

	private void deploy(Project project, List<File> serviceModelFiles) {
		List<List<String>> results = new ArrayList<List<String>>();
		List<String> row = null;
		new ArrayList<String>();

		for (File serviceFile : serviceModelFiles) {
			row = new ArrayList<String>();
			String serviceFileName = serviceFile.getName();
			row.add(serviceFileName);
			try {
				serviceFile = getNormalizedFile(serviceFile.getCanonicalPath(), false);
				deploy(project, serviceFile.getCanonicalPath());
				row.add("Deployed  : " + serviceFile.getCanonicalPath());
			} catch (Exception e) {
				row.add(e.getMessage());
			}

			results.add(row);
		}
		StrUtil.display(results, "Service Model", "Result");
	}

	/**
	 * Deploys a single service model as specified in the parameter,
	 * serviceModel.
	 * This is assumed to be a .vsm file
	 * 
	 * @param serviceModel
	 * @throws Exception
	 */
	private void deploy(Project project, String serviceModelFilePath) throws Exception {
		
		
		String configFilePath = getConfigFile().getCanonicalPath();
		
		int capacity = getCapacity();
		int thinkFactor = getThinkFactor();
		Boolean autoRestartObject = getAutoRestart();
		
		ModelArchiveInfo mari = createVSMarInfo(serviceModelFilePath, configFilePath, capacity, thinkFactor,
				autoRestartObject);
		
		Thread.sleep(1000);
		deploy(project , mari);
	}

	private void deploy(ModelArchive mar) throws Exception {
		deployMar(mar);
	}

	private void deploy(Project project, ModelArchiveInfo mari) throws Exception {
		
		ModelArchive mar = new ModelArchive(project, mari);
		deployMar(mar);
	}

	private void deployMar(ModelArchive mar) throws Exception {
		Set<String> serviceNames = this.environment.getServiceNames();
		String newServiceName = mar.getMARInfo().getName();
		try {

			if (serviceNames.contains(newServiceName) && getIgnoreIfExists()) {
				throw new Exception("Deployment skipped because the service  \"" + newServiceName
						+ "\" is already deployed on " + this.environmentName);
			} else if (serviceNames.contains(newServiceName)) {

				this.environment.updateService(mar);
				// log.info("Sucessfully re-deployed the service \"" +
				// newServiceName + "\" on " + this.environmentName);
			} else {
				this.environment.addService(mar);
				// log.info("Sucessfully deployed the service  \"" + serviceName
				// + "\" on " + this.environmentName);
			}

		} catch (Exception e) {
			// log.info("Problem deploying MAR ("+mar.getMARInfo().getName()+"): "
			// + e.getCause().getMessage());
			throw new Exception(e.getMessage());
		}
	}

	private void deployMarList(List<File> modelArchives) {
		List<List<String>> results = new ArrayList<List<String>>();
		List<String> row = null;
		new ArrayList<String>();

		for (File modelFile : modelArchives) {
			row = new ArrayList<String>();
			try {
				ModelArchive mar = new ModelArchive(modelFile);
				String serviceName = mar.getMARInfo().getName();
				row.add(serviceName);

				deploy(mar);
				row.add("Deployed: " + modelFile.getCanonicalPath());
			} catch (Exception e) {
				row.add(e.getMessage());
			}

			results.add(row);
		}
		StrUtil.display(results, "Model Archive", "Result");
	}

	private void deployAllMar() throws Exception {
		// validateArguments();

		File marDir = (File) this.arguments.get(Argument.MAR_DIR);
		List<File> mars = getFilesToProcess(marDir, ".mar", "", true);
		deployMarList(mars);

	}

	private void deployMariList(Project project, List<File> modelArchiveInfoFiles) {
		List<List<String>> results = new ArrayList<List<String>>();
		List<String> row = null;
		new ArrayList<String>();

		for (File modelFile : modelArchiveInfoFiles) {
			row = new ArrayList<String>();
			try {
				ModelArchiveInfo mari = ModelArchiveInfo.load(modelFile);
				String serviceName = mari.getName();
				row.add(serviceName);
				deploy(project, mari);
				row.add("Deployed: " + modelFile.getCanonicalPath());
			} catch (Exception e) {
				row.add(e.getMessage());
			}

			results.add(row);
		}
		StrUtil.display(results, "Model Archive Info", "Result");
	}

	private void deployAllMari() throws Exception {
		// cheat to make getProject() work: set VS_DIR to the same as MAR_DIR
		this.arguments.put(Argument.VS_DIR, getMarDir());
		// validateArguments();
		log.info("\n");

		File marDir = getMarDir();
		List<File> maris = getFilesToProcess(marDir, ".mari", "", true);
		deployMariList(getProject(),maris);

	}

	private void deploySingleMari() throws Exception {
		// cheat to make getProject() work: set VS_DIR to the same as MAR_DIR
		this.arguments.put(Argument.VS_DIR, getMarDir());
		// validateArguments();
		log.info("\n");

		File marDir = getMarDir();
		List<File> maris = getFilesToProcess(marDir, ".mari", getServiceName(), false);
		deployMariList(getProject(),maris);

	}

	private void startAllVSEServices() throws Exception {

		List<List<String>> data = new ArrayList<List<String>>();
		Set<String> serviceNames = this.environment.getServiceNames();
		for (String serviceName : serviceNames) {

			VirtualService virtualService = this.environment.getService(serviceName);

			if (VirtualService.STATUS_RUNNING == virtualService.getStatus()) {
				// log.info(" The " + serviceName +
				// " virtual service is already running.");
			} else {
				this.environment.startService(serviceName);
				// log.info("Sucessfully started the virtual service : " +
				// serviceName + " from " + this.environmentName);
			}

			describe(virtualService.getServiceInfo(), data);

		} // for each svc name
		StrUtil.display(data, "Name", "Resource", "Status", "Auto", "Capacity", "Think", "Txns", "Errors", "Exec Mode",
				"Up Time");
	} // start all vse services

	private void redeployService() throws Exception {
		String serviceName = getServiceName();
		VirtualService vservice = this.environment.getService(serviceName);

		String serviceModelName = vservice.getModelName();
		List<File> serviceModels = getFilesToProcess(getVSDir(), ".vsm", serviceModelName, false);
		File serviceModel = serviceModels.get(0);

		ModelArchiveInfo mari = createVSMarInfo(serviceModel.getCanonicalPath(), getConfigFile().getCanonicalPath(),
				vservice.getConcurrentCapacity(), vservice.getThinkTimeScaleFactor(), vservice.isAutoRestartEnabled());

		this.environment.removeService(serviceName);

		ModelArchive mar = new ModelArchive(getProject(), mari);

		List<List<String>> data = new ArrayList<List<String>>();
		List<String> row = new ArrayList<String>();

		row.add(serviceName);
		try {
			deploy(mar);
			row.add("Redeployed");
		} catch (Exception e) {
			row.add(e.getMessage());
		}

		data.add(row);
		StrUtil.display(data, "Service Name", "Action");

	}

	private void removeAllServices() throws Exception {
		List<List<String>> data = new ArrayList<List<String>>();

		File vsdir = getVSDir();

		File[] virtualServiceModels = getFilesWithExtension(vsdir, ".vsm");

		for (File virtualServiceModelFile : virtualServiceModels) {

			List<String> row = new ArrayList<String>();

			String modelName = virtualServiceModelFile.getName().substring(0,
					virtualServiceModelFile.getName().lastIndexOf("."));

			List<String> serviceNames = getServiceNamesForModel(modelName);
			for (String serviceName : serviceNames) {

				row.add(modelName);

				row.add(serviceName);
				try {
					this.environment.removeService(serviceName);

					row.add("Removed");

				} catch (Exception e) {
					row.add("Failed" +" : "+ e.getMessage());
				}

				data.add(row);
			}

		}
		StrUtil.display(data, "Model Name", "Service Name", "Action");
	}

	private void removeService() throws Exception {

		String serviceName = getServiceName();
		Set<String> serviceNames = this.environment.getServiceNames();

		if (serviceNames.contains(serviceName)) {
			this.environment.removeService(serviceName);
			log.info("Sucessfully removed the service \"" + serviceName + "\" from " + this.environmentName);
		} else {
			log.info("No service found with name  \"" + serviceName + "\" in " + this.environmentName);
		}
	}

	private File[] getFilesWithExtension(File baseDirectory, final String pattern) {
		File[] matchedFiles = baseDirectory.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(pattern);
			}
		});
		return matchedFiles;
	}

	private List<File> getFilesToProcess(File baseDirectory, String pattern, File serviceFile, boolean getAllFiles) {
		List<File> files = new ArrayList<File>();
		if (getAllFiles) {
			File[] vfiles = getFilesWithExtension(baseDirectory, pattern);
			files = (Arrays.asList(vfiles));
		} else {
			files.add(serviceFile);
		}

		return files;
	}

	private List<File> getFilesToProcess(File baseDirectory, String pattern, String serviceFile, boolean getAllFiles) {
		List<File> files = new ArrayList<File>();
		if (getAllFiles) {
			File[] vfiles = getFilesWithExtension(baseDirectory, pattern);
			files = (Arrays.asList(vfiles));
		} else {
			File workingFile = new File(baseDirectory, serviceFile + pattern);
			files.add(workingFile);
		}

		return files;
	}

	private void showUsedOptions() {
		List<List<String>> data = new ArrayList<List<String>>();
		List<String> row = new ArrayList<String>();

		File config = (File) this.arguments.get(Argument.CONFIG);
		int cap = (Integer) this.arguments.get(Argument.CAPACITY);
		int think = (Integer) this.arguments.get(Argument.THINK_FACTOR);
		boolean auto = (Boolean) this.arguments.get(Argument.AUTO_RESTART);
		row.add(config.getName());
		row.add(Integer.toString(cap));
		row.add(Integer.toString(think));
		row.add(Boolean.toString(auto));

		data.add(row);
		StrUtil.display(data, "Config", "Capacity", "Think Factor", "Auto Restart");

	}

	/**
	 * Converts a .vsm from a project to a .mar
	 * MAR is saved in Argument.MAR_DIR
	 * If BUILD_ALL==true, then SERVICE_NAME is ignored, and all .vsm are
	 * converted
	 * @throws IOException 
	 */

	private List<ModelArchiveInfo> createMariData(List<File> models) throws IOException {

		File configFile = (File) this.arguments.get(Argument.CONFIG);
		int capacity = (Integer) this.arguments.get(Argument.CAPACITY);
		int thinkFactor = (Integer) this.arguments.get(Argument.THINK_FACTOR);
		boolean autoRestart = (Boolean) this.arguments.get(Argument.AUTO_RESTART);

		List<ModelArchiveInfo> mariFiles = new ArrayList<ModelArchiveInfo>();

		for (File model : models) {
			ModelArchiveInfo mari = createVSMarInfo(model.getCanonicalPath(), configFile.getCanonicalPath(), capacity,
					thinkFactor, autoRestart);
			mariFiles.add(mari);
		}

		return mariFiles;
	}

	/**
	 * 
	 * @param buildMari
	 * @param buildMar
	 * @throws IOException 
	 */
	private void buildFromVSM(boolean buildMari, boolean buildMar) throws IOException {

		if (buildMar == false && buildMari == false) {
			log.info("I cannot build a MAR or MARI from the VSM specified unless you specify which one to build.");
			return;
		}
		log.info("\n");
		showUsedOptions();
		// Check argument list for Argument.BUILD_ALL
		Object bobj = this.arguments.get(Argument.BUILD_ALL);
		boolean buildAll = (bobj == null) ? false : true;
		File vsdir = (File) this.arguments.get(Argument.VS_DIR);
		File serviceFile = null;

		Project project = getProject();
		File destDir = (File) this.arguments.get(Argument.MAR_DIR);

		if (buildAll == false) {
			String service = (String) this.arguments.get(Argument.SERVICE_NAME);
			serviceFile = new File(vsdir, service + ".vsm");
		}
		// this call will return the list of either just the single file
		// specified, or all matching vsm
		List<File> serviceList = getFilesToProcess(vsdir, ".vsm", serviceFile, buildAll);

		List<List<String>> results = new ArrayList<List<String>>();

		List<ModelArchiveInfo> mariFiles = createMariData(serviceList);

		for (ModelArchiveInfo mari : mariFiles) {
			List<String> row = new ArrayList<String>();
			try {
				row.add(mari.getName());

				if (buildMari) {
					File mariDestFile = new File(destDir, mari.getName() + ".mari");
					mari.save(mariDestFile);
					row.add(mariDestFile.getCanonicalPath());
					results.add(row);

					if (buildMar) {
						row = new ArrayList<String>();
						row.add("");
					}
				}

				if (buildMar) {
					ModelArchive mar = new ModelArchive(project, mari);
					File marDestFile = new File(destDir, mari.getName() + ".mar");

					mar.writeModelArchive(marDestFile);
					row.add(marDestFile.getCanonicalPath());
					// log.info("Wrote " + destFile.getCanonicalPath());
					results.add(row);
				}
			} catch (Exception e) {
				row.add("Failure: " + e.getMessage());
				results.add(row);
				// log.info("oops: " + e.getMessage());
			} // try

		} // for each mari

		log.info("\n");
		StrUtil.display(results, "Model", "Result");
	}

	private static ModelArchiveInfo createVSMarInfo(String modelFile, String configFile, int capacity, int thinkFactor,
			Boolean autoRestartObject) {
		
//		String name = StreamHelp.stripNameAway(modelFile, ".vsm");
//	    String vsProjectPath = ProjectManager.getProjectRoot(modelFile);
//	    String cfgProjectPath = StrUtil.isEmpty(configFile) ? null : ProjectManager.getProjectRoot(configFile);
//	    ModelArchiveInfo marInfo = ModelArchiveInfo
//
//	    if (vsProjectPath == null) {
//	      throw new IllegalArgumentException("The given service model is not in a project.\nVS Model: " + modelFile);
//	    }
//	    if (cfgProjectPath != null) {
//	      if (!vsProjectPath.equals(cfgProjectPath)) {
//	        throw new IllegalArgumentException("The configuration is not part of the same project as the virtual service model.\nVS Model: " + modelFile + "\nConfiguration: " + configFile);
//	      }
//
//	      configFile = ModelArchiveUtils.asAsset(vsProjectPath, configFile);
//	    }
//
//	    marInfo.setName(name);
//	    
//	    System.out.println("PrimaryAsset	: " +	ModelArchiveUtils.asAsset(vsProjectPath, modelFile));
//	    
//	    marInfo.setDeploymentParameter("PrimaryAsset", ModelArchiveUtils.asAsset(vsProjectPath, modelFile));
//
//	    if (cfgProjectPath != null) {
//	      marInfo.setDeploymentParameter("Configuration", configFile);
//	    }
//	    marInfo.setDeploymentParameter("ConcurrentCapacity", Integer.toString(capacity));
//	    marInfo.setDeploymentParameter("ThinkTimePercent", Integer.toString(thinkFactor));
//	    marInfo.setDeploymentParameter("AutoRestart", autoRestartObject ? "true" : "false");
		
		
		return ModelArchiveInfo.createVSMARInfo(modelFile, configFile, capacity, thinkFactor, autoRestartObject);
	}


	private void showHelpText() {
		// for (String line : HELP_TEXT)
		// log.info(line);
		for (Command cmd : Command.values()) {
			log.info(cmd + " : " + cmd.helpText);
			for (Argument arg : cmd.arguments) {
				String line = "    ";
				line += arg.required ? "REQUIRED" : "OPTIONAL";
				line += "    --" + arg.argumentName;
				if (arg.hasValue) {
					line += "=[value]";
				}
				log.info(line);
			}
		}
	}

	private void checkACL() throws Exception {
		TestRegistry reg = Environment.getTestRegistry();
		if (reg.isSecurityEnabled()) {
			setUser(LisaSecurityManager.authenticateUserFromCommandLine(VSEManagerWrapper_try.commandLine));
		}
	}

	private void ensureTheEnvironment() throws Exception {
		ensureTheRegistry();

		if (this.environment == null) {
			if (this.environmentName == null) {
				throw new IllegalArgumentException("There is no current environment name set.");
			}
			this.environment = Environment.getTestRegistry().getVirtualServiceEnvironment(this.environmentName);

			log.info("Virtual service environment : " + this.environmentName);
			if (this.environment == null)
				throw new IllegalArgumentException("Could not locate an environment with name " + this.environmentName
						+ ".");

		}
	}

	private void ensureTheRegistry() throws Exception {

		if (Environment.getTestRegistry() == null) {
			TestRegistry tr = Environment.setTestRegistry(this.registryName);

			if (tr == null)
				throw new IllegalArgumentException("Could not connect to LISA registry with name " + this.registryName
						+ ".");
			checkACL();
			log.info("Connected to registry : " + this.registryName);
		}
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		VSEManagerWrapper_try.user = user;
	}

	/**
	 * private static final String SERVICE_NAME = "service-name"; private static
	 * final String CONFIG = "config"; private static final String
	 * PARAM_REGISTRY = "registry";
	 * 
	 */
	public enum Argument {
		AUTO_RESTART("auto-restart", true, false), 
		BUILD_ALL("build-all", false, false), 
		CAPACITY("capacity", true,false), 
		CONFIG("config", true, true), 
		IGNORE_IF_EXIST("ignore-if-exist", true, false), 
		MAR_DIR("mar-dir", true, true), 
		PASSWORD("password", false, false), 
		REGISTRY("registry", true, true), 
		SERVICE_NAME("service-name", true, false), 
		SOURCE_DIR("source-dir", true, true), 
		TARGET_DIR("target-dir", true, true), 
		THINK_FACTOR("think-scale", true, false), 
		USER("username", false, false), 
		VS_DIR("vs-dir", true, true), 
		VSE("vse",true, true),
		PROJECT_ROOT_DIR("proj-root-dir", true, true);

		Argument(String name, boolean value, boolean needed) {
			argumentName = name;
			hasValue = value;
			required = needed;
		}

		public static Argument fromString(String text) {
			if (text != null) {
				for (Argument b : Argument.values()) {
					if (text.equalsIgnoreCase(b.argumentName)) {
						return b;
					}
				}
			}
			return null;
		}

		String	argumentName;
		boolean	hasValue;
		boolean	required;
	}

	public enum Command {
		BUILD_ALL_MAR("build-all-mar", "Build MAR from all VSM in a given project",
				// required
				Argument.CONFIG, Argument.MAR_DIR, Argument.REGISTRY, Argument.VS_DIR,
				// optionals
				Argument.AUTO_RESTART, Argument.CAPACITY, Argument.THINK_FACTOR), BUILD_MAR("build-mar",
				"Build MAR from a VSM in a given project",
				// required
				Argument.CONFIG, Argument.MAR_DIR, Argument.REGISTRY, Argument.SERVICE_NAME, Argument.VS_DIR,
				// optionals
				Argument.AUTO_RESTART, Argument.CAPACITY, Argument.THINK_FACTOR), BUILD_ALL_MARI("build-all-mari",
				"Build MARI from all VSM in a given project",
				// required
				Argument.CONFIG, Argument.MAR_DIR, Argument.REGISTRY, Argument.VS_DIR,
				// optionals
				Argument.AUTO_RESTART, Argument.CAPACITY, Argument.THINK_FACTOR), BUILD_MARI("build-mari",
				"Build MARI from a given project",
				// required
				Argument.CONFIG, Argument.MAR_DIR, Argument.REGISTRY, Argument.SERVICE_NAME, Argument.VS_DIR,
				// optionals
				Argument.AUTO_RESTART, Argument.CAPACITY, Argument.THINK_FACTOR), DEPLOY("deploy",
				"Deploys a single VSM from a given project",
				// required
				Argument.CONFIG, Argument.REGISTRY, Argument.SERVICE_NAME, Argument.VS_DIR, Argument.VSE,
				// optionals
				Argument.AUTO_RESTART, Argument.CAPACITY, Argument.THINK_FACTOR), DEPLOY_ALL("deploy-all",
				"Deploys all SM in a given project",
				// required
				Argument.CONFIG, Argument.REGISTRY, Argument.VS_DIR, Argument.VSE,
				// optionals
				Argument.AUTO_RESTART, Argument.CAPACITY, Argument.IGNORE_IF_EXIST, Argument.THINK_FACTOR), DEPLOY_ALL_MAR(
				"deploy-all-mar", "Deploys all MAR in a given project",
				// required
				Argument.MAR_DIR, Argument.VSE, Argument.REGISTRY,
				// optionals
				Argument.IGNORE_IF_EXIST),

		DEPLOY_ALL_MARI("deploy-all-mari", "Deploys all MARI in a given project",
		// required
				Argument.MAR_DIR, Argument.VSE, Argument.REGISTRY,
				// optionals
				Argument.IGNORE_IF_EXIST), DEPLOY_MARI("deploy-mari", "Deploy a MARI from a given project",
		// required
				Argument.MAR_DIR, Argument.VSE, Argument.REGISTRY, Argument.SERVICE_NAME,
				// optionals
				Argument.IGNORE_IF_EXIST), HELP("help", "This screen... "), // StringUtils.join(HELP_TEXT,
																			// "\n")),
		PROJECT_START_ALL("project-start-all", "Start all....", Argument.VS_DIR, Argument.REGISTRY, Argument.VSE), PROJECT_STOP_ALL(
				"project-stop-all", "Stop all....", Argument.VS_DIR, Argument.REGISTRY, Argument.VSE), REDEPLOY(
				"redeploy", "Redeploys a single VSM from a given project",
				// required
				Argument.CONFIG, Argument.REGISTRY, Argument.SERVICE_NAME, Argument.VS_DIR, Argument.VSE), REMOVE(
				"remove", "Removes a single service from the VSE",
				// required
				Argument.REGISTRY, Argument.SERVICE_NAME, Argument.VSE), REMOVE_ALL("remove-all",
				"Removes all SI in a given project", Argument.VS_DIR, Argument.REGISTRY, Argument.VSE), VSE_START_ALL(
				"vse-start-all", "Start all....", Argument.REGISTRY, Argument.VSE), STATUS_VSE("project-status-vse",
				"Use project for VSE status checking", Argument.REGISTRY, Argument.VSE, Argument.VS_DIR),
				
				RESET_TX_VSE("reset-all","Resets the transaction counts", 
				Argument.REGISTRY,Argument.VSE, Argument.VS_DIR);


		Command(String name, String helpString, Argument... args) {
			commandName = name;
			helpText = helpString;
			arguments = args;

		}

		public static Command fromString(String text) {
			if (text != null) {
				for (Command b : Command.values()) {
					if (text.equalsIgnoreCase(b.commandName)) {
						return b;
					}
				}
			}
			return null;
		}

		String		commandName;	// help, remove, etc...
		String		helpText;
		Argument[]	arguments;

	} // enum command

}
