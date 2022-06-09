package cs350s22.component.ui.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.concurrent.TimeUnit;
import cs350s22.component.*;
import cs350s22.component.A_Component;
import cs350s22.component.logger.LoggerMessage;
import cs350s22.component.logger.LoggerMessageSequencing;
import cs350s22.component.sensor.reporter.ReporterFrequency;
import cs350s22.message.A_Message;
import cs350s22.message.actuator.MessageActuatorReportPosition;
import cs350s22.message.actuator.MessageActuatorRequestPosition;
import cs350s22.message.ping.MessagePing;
import cs350s22.component.sensor.A_Sensor;
import cs350s22.component.sensor.mapper.*;
import cs350s22.component.controller.A_Controller;
import cs350s22.component.actuator.A_Actuator;
import cs350s22.component.sensor.mapper.A_Mapper;
import cs350s22.component.sensor.mapper.MapperEquation;
import cs350s22.component.sensor.mapper.MapperInterpolation;
import cs350s22.component.sensor.mapper.function.equation.EquationNormalized;
import cs350s22.component.sensor.mapper.function.equation.EquationPassthrough;
import cs350s22.component.sensor.mapper.function.equation.EquationScaled;
import cs350s22.component.sensor.mapper.function.interpolator.InterpolationMap;
import cs350s22.component.sensor.mapper.function.interpolator.InterpolatorLinear;
import cs350s22.component.sensor.mapper.function.interpolator.InterpolatorSpline;
import cs350s22.component.sensor.mapper.function.interpolator.A_Interpolator;
import cs350s22.component.sensor.mapper.function.interpolator.loader.A_MapLoader;
import cs350s22.component.sensor.mapper.function.interpolator.loader.MapLoader;
import cs350s22.component.sensor.watchdog.A_Watchdog;
import cs350s22.component.sensor.watchdog.WatchdogAcceleration;
import cs350s22.component.sensor.watchdog.WatchdogBand;
import cs350s22.component.sensor.watchdog.WatchdogHigh;
import cs350s22.component.sensor.watchdog.WatchdogLow;
import cs350s22.component.sensor.watchdog.WatchdogNotch;
import cs350s22.component.sensor.watchdog.mode.A_WatchdogMode;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeAverage;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeInstantaneous;
import cs350s22.component.sensor.watchdog.mode.WatchdogModeStandardDeviation;
import cs350s22.component.sensor.reporter.A_Reporter;
import cs350s22.component.sensor.reporter.ReporterChange;
import cs350s22.component.sensor.reporter.ReporterFrequency;
import cs350s22.component.ui.CommandLineInterface;
import cs350s22.support.Clock;
import cs350s22.support.Filespec;
import cs350s22.support.Identifier;
import cs350s22.test.ActuatorPrototype;
import cs350s22.test.MySensor;

public class Parser {
		
    private final A_ParserHelper parserHelper;
    private final String commandtext;
    private String userInput; 
   // private List<A_Sensor> sensors = new ArrayList<A_Sensor>();
    private List<Identifier> IDList = new ArrayList<Identifier>();
    
    public Parser(A_ParserHelper parserHelper, String commandtext) throws IOException {
        //not sure if we need this
        this.parserHelper = parserHelper;
        this.commandtext = commandtext;
		this.userInput = "";
    }
   
    //Write out the Pseudo code and comit that change
    //TODO: Complete this command
    private void A1(Scanner sc) {
    	

    	//Our goal is to create an actuator with identifer id and optional membership in groups and optional embedded sensors id based on values 
    	//crrate an actuator object that takes in:
    	
    	//We have to be able to parse the command and know its parameters 
    	
    	//1) Get the sensors by calling get() with id on SymbolTable<A_Sensor>
    		//Q: How do we call Symbol Table? 
    	 
    	
    	//Let's make a parser
    	
    	 
    	 //List<Identifier> groups = null; //Optional 
		 double accelerationLeadin = 0.0; 
		 double accelerationLeadout = 0.0;
		 double accelerationRelax = 0.0;
		 double velocityLimit = 0.0;
		 double velocityInitial = 0.0;
		 double valueMin = 0.0;
		 double valueMax = 0.0;
		 double inflectionJerkThreshold = 0;
		 //List<A_Sensor> sensors = null; //Optional
	     //String[] command = this.userInput.split(" ");
//	     Identifier ID = Identifier.make(sc.next());
	     ArrayList<Identifier> group = new ArrayList<Identifier>(); 
	     ArrayList<Identifier> currGroup = new ArrayList<Identifier>(); 
	     ArrayList<Identifier> sensors = new ArrayList<Identifier>(); 

	    
	    //First boolean linear or rotary 
	    //Take the id next
	    //Stars and Squares groups & sensors
	    //Linearly 
		boolean groupsFlag = false;
		boolean sensorFlag = false;

		// CREATE ACTURATRO is already done

		// Check if it is a linear or rotary access
		boolean isLinear = sc.next().equals("LINEAR");

		String id = sc.next();
		// IDList.add(id);
		// Stars & Square Loop
		while (sc.hasNext()) {
			String curr = sc.next();

			// If it reaches star 1
			if (curr.equals("GROUPS") || curr.equals("GROUP")) {
				currGroup = group;
				groupsFlag = true;
			}
			// if it reaches star 2
			else if (curr.matches("SENSORS") | curr.matches("SENSOR")) {
				currGroup = sensors;
				sensorFlag = true;
			}
			// If it reaches ACCELERATION{
			// break
			else if (curr.matches("ACCELERATION")) {
				break;
			}
			// Add it to the list
		}

		if (sc.next().matches("LEADIN")) {
			accelerationLeadin = Double.parseDouble(sc.next());
		} else {
			System.out.println("invalid");
		}

		if (sc.next().matches("LEADOUT")) {
			accelerationLeadout = Double.parseDouble(sc.next());
		} else {
			System.out.println("invalid");
		}

		if (sc.next().matches("RELAX")) {
			accelerationRelax = Double.parseDouble(sc.next());
		} else {
			System.out.println("invalid");
		}
		if (sc.next().matches("VELOCITY")) {
			if (sc.next().matches("LIMIT")) {
				velocityLimit = Double.parseDouble(sc.next());
			} else {
				System.out.println("invalid");
			}
		} else {
			System.out.println("invalid");
		}
		if (sc.next().matches("VALUE")) {
			if (sc.next().matches("MIN")) {
				valueMin = Double.parseDouble(sc.next());
			} else {
				System.out.println("invalid");
			}
		} else {
			System.out.println("invalid");
		}
		if (sc.next().matches("MAX")) {
			valueMax = Double.parseDouble(sc.next());
		} else {
			System.out.println("invalid");
		}
		if (sc.next().matches("INITIAL")) {
			velocityInitial = Double.parseDouble(sc.next());
		} else {
			System.out.println("invalid");
		}
		if (sc.next().matches("JERK")) {
			if (sc.next().matches("LIMIT")) {
				inflectionJerkThreshold = Double.parseDouble(sc.next());
			} else {
				System.out.println("invalid");
			}
		} else {
			System.out.println("invalid");
		}

	}

	

	private void B1(Scanner sc) {

	}

	private void B2(Scanner sc) {

	}

	// TO DO: REPLACE sc.next with pre-declared string (ask Kevin for clarification)
	private void MAPPERcommands(Scanner sc) throws IOException {// MAPPER command also C1 C2 C3 C4
		// create mapper
		SymbolTable<A_Mapper> mapperTable = parserHelper.getSymbolTableMapper();
		Identifier ID = Identifier.make(sc.next());

		// C 1-3
		if (sc.next().matches("EQUATION")) {
			String PLEASE_RENAME_THIS_KELSEY_OR_KEVIN = sc.next();
			if (PLEASE_RENAME_THIS_KELSEY_OR_KEVIN.matches("PASSTHROUGH")) {
				EquationPassthrough passMapper = new EquationPassthrough();
				MapperEquation eqautionMapper = new MapperEquation(passMapper);
				mapperTable.add(ID, eqautionMapper);
			} else if (PLEASE_RENAME_THIS_KELSEY_OR_KEVIN.matches("SCALE")) {
				int value = sc.nextInt();

				EquationScaled scaleMapper = new EquationScaled(value);
				MapperEquation equationMapper = new MapperEquation(scaleMapper);
				mapperTable.add(ID, equationMapper);
			} else if (PLEASE_RENAME_THIS_KELSEY_OR_KEVIN.matches("NORMALIZE")) {
				int value1 = sc.nextInt();
				int value2 = sc.nextInt();

				EquationNormalized normalizeMapper = new EquationNormalized(value1, value2);
				MapperEquation equationMapper = new MapperEquation(normalizeMapper);
				mapperTable.add(ID, equationMapper);
				System.out.println("Work??? Do you??");
			}
		}
		// C4
		else if (sc.next().matches("INTERPOLATION")) {
			if (sc.next().matches("LINEAR")) {
				sc.next();
				String definition = sc.next();
				Filespec filespec = new Filespec(definition);
				A_MapLoader map = new MapLoader(filespec);
				InterpolationMap interpolationMap = map.load();
				InterpolatorLinear interpolatorLinear = new InterpolatorLinear(interpolationMap);
				MapperInterpolation mapperInterpolation = new MapperInterpolation(interpolatorLinear);
				mapperTable.add(ID, mapperInterpolation);
			}
			if (sc.next().matches("SPLINE")) {
				sc.next();
				String definition = sc.next();
				Filespec filespec = new Filespec(definition);
				A_MapLoader map = new MapLoader(filespec);
				InterpolationMap interpolationMap = map.load();
				InterpolatorSpline interpolatorSpline = new InterpolatorSpline(interpolationMap);
				MapperInterpolation mapperInterpolation = new MapperInterpolation(interpolatorSpline);
				mapperTable.add(ID, mapperInterpolation);
			}
		}
	}

	// Sends out a ping from the command line interface
	private void D1() {
		CommandLineInterface cli = parserHelper.getCommandLineInterface();
		MessagePing ping = new MessagePing();
		cli.issueMessage(ping);
		System.out.println("Ping sent");
	}

	// TO-DO: Add Stars & Squares
	private void D2_3(Scanner sc) {
		CommandLineInterface cli = parserHelper.getCommandLineInterface();
		boolean isID = false;

		// All the ids & groups
		ArrayList<Identifier> listOutput;
		ArrayList<Identifier> ids = new ArrayList<Identifier>();
		ArrayList<Identifier> groups = new ArrayList<Identifier>();

		// The current word being parsed
		String curr = "";
		String output = "";

		// Loop until it finds position
		while (!curr.equals("POSITION")) {
			// Grab the current word
			curr = sc.next();
			if (curr.equals("ID")) {
				if (output.equals("")) {
					output = "ID";
				}
				isID = true;

			} else if (curr.equals("GROUPS")) {
				if (output.equals("")) {
					output = "GROUPS";
				}
				isID = false;

			} else {
				// Convert string 2 identifier
				Identifier id = Identifier.make(curr);

				// If it's an ID add it to id, else ad dit to groups
				if (isID) {

					ids.add(id);
				} else {
					groups.add(id);
				}
			}
		}
		listOutput = (output.equals("ID")) ? ids : groups;

		// POSITION
		sc.next();

		boolean isRequest = (sc.next().equals("REQUEST"));
		double value = 0; 
		if(sc.hasNext()) {
			value = sc.nextDouble();
		}

		// Create Message Actuator
		A_Message message = (isRequest) ? new MessageActuatorRequestPosition(listOutput, value)
				: new MessageActuatorReportPosition(listOutput);
		cli.issueMessage(message);
	}

	private void E6(Scanner sc) throws IOException {
		// LOG
		sc.next();
		String logStr = sc.next();
		// DOT
		sc.next();
		// SEQUENCE
		sc.next();
		String dotStr = sc.next();
		sc.next();
		// NETWORK
		String netStr = sc.next();
		// XML String
		while (sc.hasNext()) {
			sc.next();
		}

		// Convert strings to file specs
		Filespec logSpec = new Filespec(logStr);
		Filespec dotSpec = new Filespec(dotStr);
		Filespec netSpec = new Filespec(netStr);

		LoggerMessage.initialize(logSpec);
		LoggerMessageSequencing.initialize(dotSpec, netSpec);
	}

	private void E7() {
		Clock c = Clock.getInstance();
		System.out.println(c.getTick());
	}

	// F1 Network Commands
	private void F1(Scanner sc) {

		//COMPONENT
		String s = sc.next();
		switch(s) {
			case ("COMPONENT"):
				Identifier ID = Identifier.make(sc.next());
				SymbolTable<A_Controller> controllerTable = parserHelper.getSymbolTableController();
				SymbolTable<A_Sensor> sensorTable = parserHelper.getSymbolTableSensor();

				//if the component exist, add it
				if(controllerTable.contains(ID) && sensorTable.contains(ID)) {
					A_Component component1 = controllerTable.get(ID);
					A_Component component2 = sensorTable.get(ID);
					parserHelper.getControllerMaster().addComponent(component1);
					parserHelper.getControllerMaster().addComponent(component2);

					System.out.println("Componet has been added");
					System.out.println(parserHelper.getNetwork().generateXML());
				}
				else {
					System.out.println("Component does not exist, please create it before adding to the network");
				}
				break;

			case ("COMPONENTS"):
				Identifier ID2 = Identifier.make(sc.next());
				Identifier ID3 = Identifier.make(sc.next());
				SymbolTable<A_Controller> controllerTable2 = parserHelper.getSymbolTableController();
				SymbolTable<A_Actuator> actuatorTable2 = parserHelper.getSymbolTableActuator();
				SymbolTable<A_Sensor> sensorTable2 = parserHelper.getSymbolTableSensor();

				//if the components exist, add it
				if((controllerTable2.contains(ID2) && sensorTable2.contains(ID2))
						&& (actuatorTable2.contains(ID3) && sensorTable2.contains(ID3))) {
					A_Component components1 = controllerTable2.get(ID2);
					A_Component components2 = sensorTable2.get(ID2);
					parserHelper.getControllerMaster().addComponent(components1);
					parserHelper.getControllerMaster().addComponent(components2);

					A_Component components3 = actuatorTable2.get(ID3);
					A_Component components4 = sensorTable2.get(ID3);
					parserHelper.getControllerMaster().addComponent(components3);
					parserHelper.getControllerMaster().addComponent(components4);

					System.out.println("Componets have been added");
					System.out.println(parserHelper.getNetwork().generateXML());
				}
				else {
					System.out.println("Components do not exist, please create them before adding to the network");
				}
				break;

		}
	}


	//G1 Reporter Commands
	private void G1(Scanner sc) {
		//CHANGE
		Identifier ID = Identifier.make(sc.next());
		//NOTIFY
		sc.next();
		//IDS
		sc.next();

		//get the list of ids to notify
		ArrayList<Identifier> ids = new ArrayList<Identifier>();
		ArrayList<Identifier> groups = new ArrayList<Identifier>();

		//Adding IDs or Groups to the lists
		String scannerItem = sc.next();
		while( (!scannerItem.equals("GROUPS")) && (!scannerItem.equals("DELTA")) ) {
			ids.add(Identifier.make(scannerItem));
			scannerItem = sc.next();
		}

		if(scannerItem.equals("GROUPS")) {
			scannerItem = sc.next();
			while(!scannerItem.equals("DELTA")) {
				groups.add(Identifier.make(scannerItem));
				scannerItem = sc.next();
			}
		}

		//get the delta value
		scannerItem = sc.next();
		int delta = Integer.parseInt(scannerItem);
		ReporterChange reporterChange = new ReporterChange(ids, groups, delta);

		//add to the symbol table
		parserHelper.getSymbolTableReporter().add(ID, reporterChange);
		System.out.println("Delta has changed with " + delta);
	}

	//G2 Reporter Commands
	private void G2(Scanner sc) {
		//FREQUENCY
		Identifier ID = Identifier.make(sc.next());
		System.out.println(ID.toString());
		//NOTIFY
		sc.next();
		//IDS
		sc.next();

		//get the list of ids to notify
		ArrayList<Identifier> ids = new ArrayList<Identifier>();
		ArrayList<Identifier> groups = new ArrayList<Identifier>();

		//Adding IDs or Groups to the lists
		String scannerItem = sc.next();
		while( (!scannerItem.equals("GROUPS")) && (!scannerItem.equals("FREQUENCY")) ) {
			ids.add(Identifier.make(scannerItem));
			scannerItem = sc.next();
		}

		if(scannerItem.equals("GROUPS")) {
			scannerItem = sc.next();
			while(!scannerItem.equals("FREQUENCY")) {
				groups.add(Identifier.make(scannerItem));
				scannerItem = sc.next();
			}
		}

		//get the frequency value
		scannerItem = sc.next();
		int frequency = Integer.parseInt(scannerItem);
		ReporterChange reporterFrequency = new ReporterChange(ids, groups, frequency);

		//add to the symbol table
		parserHelper.getSymbolTableReporter().add(ID, reporterFrequency);
		System.out.println("Frequency has changed with " + frequency);
	}


	// Create Sensor (Speed | Position)
	private void H1(Scanner sc) {
		/***************** PARSING ***********/
		// (POSITION | SPEED)
		boolean isPosition = sc.next().equals("POSITION");

		// The id of the postition
		String idStr = sc.next();

		Identifier id = Identifier.make(idStr);

		// The current group which is being added to
		List<Identifier> currGroup = new ArrayList<Identifier>();

		// List of groups
		List<Identifier> groups = new ArrayList<Identifier>();

		// List of reporters
		List<Identifier> reportersID = new ArrayList<Identifier>();

		// List of watchdogs
		List<Identifier> watchdogsID = new ArrayList<Identifier>();

		// List of mappers
		List<Identifier> mapperID = new ArrayList<Identifier>();

		// Check if each one is specified
		boolean groupsFlag = false;
		boolean reporterFlag = false;
		boolean watchdogFlag = false;
		boolean mapperFlag = false;

		// The List of things to consider: Groups, Reporters, Watchdogs, Mapper
		while (sc.hasNext()) {
			
			String curr = sc.next();
			System.out.println(curr);
			// First check if it is any of the stars, if it is, set that to the current
			if (curr.equals("GROUPS") || curr.equals("GROUP")) {
				
				currGroup = groups;

			} else if (curr.equals("REPORTERS") || curr.equals("REPORTER")) {
				currGroup = reportersID;

			} else if (curr.equals("WATCHDOGS") || curr.equals("WATCHDOG")) {
				currGroup = watchdogsID;

			} else if (curr.equals("MAPPER")) {
				currGroup = mapperID;

			}
			// If it's not any of the stars, it's a square
			else {
				System.out.println("Add " + curr);
				currGroup.add(Identifier.make(curr));

			}
		}

		// Obtain the tables
		SymbolTable<A_Reporter> reporterTable = parserHelper.getSymbolTableReporter();
		SymbolTable<A_Watchdog> watchdogTable = parserHelper.getSymbolTableWatchdog();
		SymbolTable<A_Mapper> mapperTable = parserHelper.getSymbolTableMapper();
	
		// List of reporters
		List<A_Reporter> reporters = new ArrayList<A_Reporter>();
		for (Identifier r_id : reportersID) {
			// Get the reporter
			A_Reporter r = reporterTable.get(r_id);
			reporters.add(r);
	
		}
	
		// List of watchdogs
		List<A_Watchdog> watchdogs = new ArrayList<A_Watchdog>();
		for (Identifier w_id : watchdogsID) {
			// Get the reporter
			A_Watchdog w = watchdogTable.get(w_id);
			watchdogs.add(w);
	
		}
		// List of mappers
		A_Mapper mapper = null;
	
		if (!mapperID.isEmpty()) {
			mapper = mapperTable.get(mapperID.get(0));
	
		}
		
	
		// Create the new Sensor
		SymbolTable<A_Sensor> sensorTable = parserHelper.getSymbolTableSensor();
		
		MySensor s = new MySensor(id, groups, reporters, watchdogs, mapper);
	
		sensorTable.add(id, s);



	}

	// Create a watchdog
	private void I(Scanner sc) {
		// The watchdog's id
		String id = "";
		// TThe mode of the watch dog(instantaneous? Average? etc..)
		String mode = "";

		// A potential number corresponding with the mode
		int modeNum = 0;

		boolean modeNumFlag = false;

		// The Low and High Threshold
		double lowThreshold = 0;
		double highThreshold = 0;

		// Only for case in I3
		double threshold = 0;

		boolean graceSet = false;
		int grace = 0;

		// CREATE WATCHDOG already accounted for

		// Watchdog Type
		String watchdogType = sc.next();

		// If it's type is low or high,
		boolean I3 = (watchdogType.equals("LOW") || watchdogType.equals("HIGH"));

		id = sc.next();

		// MODE
		sc.next();

		mode = sc.next();
		if (mode.equals("STANDARD")) {
			// Deviation
			sc.next();

		}

		// There may be an integer here, if there is, it is necessary to take it
		if (sc.hasNextDouble()) {
			modeNumFlag = true;
			modeNum = sc.nextInt();

		}

		// THRESHOLD
		sc.next();

		if (!I3) {
			// LOW
			sc.next();

			lowThreshold = sc.nextDouble();

			// HIGH
			sc.next();

			highThreshold = sc.nextDouble();
		} else {

			threshold = sc.nextDouble();

		}
		// GRACE is optional
		if (sc.hasNext()) {
			// GRACE
			sc.next();
			graceSet = true;
			grace = sc.nextInt();

		}

		// Create a watchdog mode object
		A_WatchdogMode w_mode = null;
		switch (mode) {
		case ("STANDARD"):
			w_mode = (modeNumFlag) ? new WatchdogModeStandardDeviation(modeNum) : new WatchdogModeStandardDeviation();
			break;
		case ("AVERAGE"):
			w_mode = (modeNumFlag) ? new WatchdogModeAverage(modeNum) : new WatchdogModeAverage();
			break;

		case ("INSTANTANEOUS"):
			w_mode = new WatchdogModeInstantaneous();
			break;
		}

		// Get the watchdog symbol table
		SymbolTable<A_Watchdog> table = parserHelper.getSymbolTableWatchdog();

		A_Watchdog w = null;
		switch (watchdogType) {

		case ("ACCELERATION"):
			w = (graceSet) ? new WatchdogAcceleration(lowThreshold, highThreshold, w_mode, grace)
					: new WatchdogAcceleration(lowThreshold, highThreshold, w_mode);
			break;

		case ("BAND"):
			w = (graceSet) ? new WatchdogBand(lowThreshold, highThreshold, w_mode, grace)
					: new WatchdogBand(lowThreshold, highThreshold, w_mode);
			break;
		case ("NOTCH"):
			w = (graceSet) ? new WatchdogNotch(lowThreshold, highThreshold, w_mode, grace)
					: new WatchdogNotch(lowThreshold, highThreshold, w_mode);
			break;
		case ("LOW"):
			w = (graceSet) ? new WatchdogLow(threshold, w_mode, grace) : new WatchdogLow(threshold, w_mode);
			break;
		case ("HIGH"):
			w = (graceSet) ? new WatchdogHigh(threshold, w_mode, grace) : new WatchdogHigh(threshold, w_mode);
			break;

		}

		Identifier tableID = Identifier.make(id);
		table.add(tableID, w);
		System.out.println(table.get(tableID));

	}

	
 
	  public void parse() throws IOException, ParseException, InterruptedException {

	    	//makes it so we seperate words from empty spaces (" ")
	        this.userInput = this.commandtext.toUpperCase();
	        String[] command = this.userInput.split(" ");
	        System.out.println("The command is: " + userInput);

	            //switch statement
	            //for each command starter (first word)
	        
	        	Scanner sc = new Scanner(this.userInput);
	        	
	        	//Takes the first word 
	            switch (sc.next()) {
	            	//if the first word is create
	                case "CREATE": System.out.println("Create Something");
	                    switch(sc.next()) {
	                    
	                    	//Situation A1 - if the first word is actuator 
	                        case "ACTUATOR":
	                            A1(sc);
	                            break;
	                        case "CONTROLLER":
	                            B1(sc);
	                            break;
	                        case "DEPENDENCY":
	                            B2(sc);
	                            break;
	                        case "MAPPER":
	                            MAPPERcommands(sc);
	                            break;
	                        case "REPORTER":
								switch(sc.next()) {
									case "CHANGE":
										G1(sc);
										break;
									case "FREQUENCY":
										G2(sc);
										break;
								}
	                            break;
	                        case "SENSOR":
	                            H1(sc); 
	                            break;
	                        case "WATCHDOG":
	                            I(sc); 
	                            break;

	                        default:
	                            System.out.println("not valid first word");
	                    }
	                    break;
	                case "SEND": 
	                    switch(sc.next()) {
	                        case "MESSAGE":
	                        	String next = sc.next(); 
	                            if(next.equals("PING")) {
	                            	D1(); 
	                            }
	                            else {
	                            	D2_3(sc); 
	                            	
	                            }
	                            break;
	                        default:
	                            System.out.println("not valid first word");
	                    }
	                    break;
	                case "@CLOCK": System.out.println(java.time.LocalTime.now());
	                	if(sc.hasNext()) {
		                    switch(sc.next()) {
								//E1
		                        case "PAUSE":
		                        	Clock c1 = Clock.getInstance(); 
		                        	c1.isActive(false);
		                        	System.out.println("Paused");//REMOVE COMMENT WHEN DONE WITH FULL THING (@CLOCK)
		                            break;
		                        case "RESUME":
		                        	Clock c2 = Clock.getInstance(); 
		                        	c2.isActive(true);
		                        	System.out.println("Resumed");//REMOVE COMMENT WHEN DONE (@CLOCK)
		                            break;
		                        //E2
		                        case "ONESTEP":
		                            //Get the instance of a clock
		                            Clock c3 = Clock.getInstance();
		                            //if the clock is not active
		                            if(!c3.isActive()) {
		                                //if there's another one
		                                if(sc.hasNext()) {
		                                    String count = sc.next();
		                                    //Does the one step command work?
											c3.onestep(Integer.parseInt(count));
		                                }
		                                else {
		                                    //Increment by one
											c3.onestep(1);
		                                }
		                            }
		                            break;
		                        //E3
		                        case "SET":
		                        	//Rate
		                        	sc.next();
		                        	String value = sc.next(); 
		                        	Clock c4 = Clock.getInstance();
									c4.setRate(Integer.parseInt(value));
		                            break;
		                        default:
		                            System.out.println("not valid first word");
		
		                        case "WAIT":
									switch(sc.next()) {
										case "FOR":
											String seconds = sc.next();
											Clock c5 = Clock.getInstance();
											c5.waitFor(Double.parseDouble(seconds));
											break;
										case "UNTIL":
											String seconds2 = sc.next();
											Clock c6 = Clock.getInstance();
											c6.waitUntil(Double.parseDouble(seconds2));
											break;
									}
		                    }  
	                    
	                	}
	                	 //Command E7 @CLOCK
	                    else {
	                    	E7();
	                    }
	                    break;

	                case "@EXIT": 
	        
	                	parserHelper.exit();
	                    break;

	                case "@RUN": 

	                	parserHelper.run(sc.next());

	                    break;

	                case "@CONFIGURE": System.out.println("DO Something");
	                    switch(sc.next()) {
	                        case "LOG":
	                            E6(sc); 
	                            break;
	                        default:
	                            System.out.println("not valid first word");
	                    }
	                    break;
	                case "BUILD": 
	                    switch(sc.next()) {
	                        case "NETWORK":
								//WITH
								sc.next();
								//COMPONENT
								F1(sc);

	                            break;
	                    }
	                    break;

	                        //defaults if not one of the options
	                        default:
	                            System.out.println("not valid first word");

	        }
	    }

}
