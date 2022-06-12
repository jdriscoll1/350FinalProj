
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
	     List<Identifier> group = new ArrayList<Identifier>(); 
	     List<Identifier> currGroup = new ArrayList<Identifier>(); 
	     List<Identifier> sensorsID = new ArrayList<Identifier>(); 

	 	// Check if it is a linear or rotary access
		boolean isLinear = sc.next().equals("LINEAR");

		Identifier id = Identifier.make(sc.next());
		
		
		
			
			
			

		
		
		// Stars & Square Loop
		while (sc.hasNext()) {
				
			String curr = sc.next();

			//Star: GROUP or GROUPS 
			if (curr.equals("GROUPS") || curr.equals("GROUP")) {
				currGroup = group;
	
			}
			//Star: SENSOR or SENSORS 
			else if (curr.matches("SENSORS") || curr.matches("SENSOR")) {
				currGroup = sensorsID; 
			}
			// If it reaches ACCELERATION{
			// break
			else if (curr.matches("ACCELERATION")) {
				break;
			}
			//Square 
			else {
				currGroup.add(Identifier.make(curr));
				
				
				
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
		
		List<A_Sensor> sensors = new ArrayList<A_Sensor>(); 
		SymbolTable<A_Sensor> symbolTable = parserHelper.getSymbolTableSensor(); 
		
		for(Identifier s : sensorsID) {
			
			
			//What is Sensor vs currsensor vs sensors
			sensors.add(symbolTable.get(s));
			
		}
		
		
		//FIX SENSOR DATA TYPE
		ActuatorPrototype actuator = new ActuatorPrototype(id, group, accelerationLeadin, accelerationLeadout, accelerationRelax, velocityLimit, velocityInitial, valueMin, valueMax, inflectionJerkThreshold, sensors);
		parserHelper.getSymbolTableActuator().add(id, actuator);
		//Actuator Incomplete

	}
    

	

	// TO DO: REPLACE sc.next with pre-declared string (ask Kevin for clarification)
	private void MAPPERcommands(Scanner sc) throws IOException {// MAPPER command also C1 C2 C3 C4
		// create mapper
		SymbolTable<A_Mapper> mapperTable = parserHelper.getSymbolTableMapper();
		Identifier id = Identifier.make(sc.next());
		boolean isEquation = sc.next().equals("EQUATION");
		
		A_Mapper mapper = null; 
		//C1-C3
		if(isEquation) {
			
			String s1 = sc.next(); 
			//C1 
			if(s1.equals("PASSTHROUGH")) {
				EquationPassthrough eq = new EquationPassthrough(); 
				mapper = new MapperEquation(eq); 
 
			}
			
			//C2
			else if(s1.equals("SCALE")) {
				double scaleVal = sc.nextDouble(); 
				EquationScaled eq = new EquationScaled(scaleVal); 
				mapper = new MapperEquation(eq); 
				
			}
			//C3
			else if(s1.equals("NORMALIZE")) {
				double v1 = sc.nextDouble(); 
				double v2 = sc.nextDouble(); 
				EquationNormalized eq = new EquationNormalized(v1, v2); 
				mapper = new MapperEquation(eq); 
				
			}
			mapperTable.add(id, mapper);			
		}
		
		//C4
		else {
			//INTERPOLATION 
			boolean isLinear = sc.next().equals("LINEAR"); 
			
			//DEFINITION
			sc.next(); 
			
			String file = sc.next(); 
			Filespec filespec = new Filespec(file); 
			MapLoader mapLoader = new MapLoader(filespec);
			InterpolationMap iMap = mapLoader.load();
			MapperInterpolation mapperInterpolation = new MapperInterpolation
					(isLinear ? new InterpolatorLinear(iMap) : new InterpolatorSpline(iMap));
			
			mapperTable.add(id, mapperInterpolation);
			
		}
		
		
		
	}

	// Sends out a ping from the command line interface
	private void D1() {
		CommandLineInterface cli = parserHelper.getCommandLineInterface();
		MessagePing ping = new MessagePing();
		cli.issueMessage(ping);
	}

	// SEND MESSAGE Already Accounted For 
	private void D2_3(Scanner sc) {
		
		

		// All the ids & groups
		ArrayList<Identifier> currList = new ArrayList<Identifier>();
		ArrayList<Identifier> ids = new ArrayList<Identifier>();
		ArrayList<Identifier> groups = new ArrayList<Identifier>();

		// The current word being parsed
		String curr = "";
		
		//Stars & Squares
		while(sc.hasNext()) {
			curr = sc.next(); 
			//Star: ID
			if(curr.equals("ID") || curr.equals("IDS")) {
				currList = ids;
				
			}
			
			//Star: 
			else if(curr.equals("GROUP") || curr.equals("GROUPS")) {
				currList = groups; 
				
			}
			//exit star 
			else if(curr.equals("POSITION")) {
				break; 
				
			}
			//Square 
			else {
				
				currList.add(Identifier.make(curr)); 
				
			}
			
		}
		
		//Grab the Command Line Interface 
		CommandLineInterface cli = parserHelper.getCommandLineInterface();
		
	
		//is Request 
		boolean isRequest = (sc.next().equals("REQUEST"));
		double value = 0; 
		
		//Another way of saying is Request
		if(sc.hasNextDouble()) {
			value = sc.nextDouble();
		}

		// Create Message Actuator

		
		if(!isRequest) {
			if(ids.size() > 0) {
				
				cli.issueMessage(new MessageActuatorRequestPosition(ids, value)); 
				
			}
			if(groups.size() > 0) {
				cli.issueMessage(new MessageActuatorRequestPosition(groups, value, 0)); 
			}
			
		}
		else {
			if(ids.size() > 0) {
				cli.issueMessage(new MessageActuatorReportPosition(ids)); 
			}
			if(groups.size() > 0) {
				cli.issueMessage(new MessageActuatorReportPosition(groups, 0)); 
			}
			
			
		}
		
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
				//get the actuator
				Identifier ID = Identifier.make(sc.next());
				SymbolTable<A_Actuator> actuatorTable = parserHelper.getSymbolTableActuator();
				SymbolTable<A_Sensor> sensorTable = parserHelper.getSymbolTableSensor();

				//if the component exist, add it
				if(actuatorTable.contains(ID)) {
					A_Component component1 = actuatorTable.get(ID);
					parserHelper.getControllerMaster().addComponent(component1);
					System.out.println("Actuator " + component1.getID() + "  has been added");
					System.out.println(parserHelper.getNetwork().generateXML());
				}
				else {
					System.out.println("Component does not exist, please create it before adding to the network");
				}

				//get the sensor
				Identifier ID2 = Identifier.make(sc.next());
				if(sensorTable.contains(ID2)) {
					A_Component component2 = sensorTable.get(ID2);
					parserHelper.getControllerMaster().addComponent(component2);
					System.out.println("Sensor " + component2.getID() + "  has been added");
					System.out.println(parserHelper.getNetwork().generateXML());
				}
				else {
					System.out.println("Component does not exist, please create it before adding to the network");
				}
				break;

			case ("COMPONENTS"):
				Identifier ID4 = Identifier.make(sc.next());
				Identifier ID3 = Identifier.make(sc.next());
				SymbolTable<A_Controller> controllerTable2 = parserHelper.getSymbolTableController();
				SymbolTable<A_Actuator> actuatorTable2 = parserHelper.getSymbolTableActuator();
				SymbolTable<A_Sensor> sensorTable2 = parserHelper.getSymbolTableSensor();

				//if the components exist, add it
				if((controllerTable2.contains(ID4) && sensorTable2.contains(ID3))
						&& (actuatorTable2.contains(ID3) && sensorTable2.contains(ID3))) {
					A_Component components1 = controllerTable2.get(ID3);
					A_Component components2 = sensorTable2.get(ID3);
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
	
	//CREATE REPORTER CHANGE already accounted for 
	private void G1(Scanner sc) {
		System.out.println("YO MAMA");
		boolean areGroups = false;
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
			areGroups = true;
		}

		//get the delta value
		scannerItem = sc.next();
		int delta = Integer.parseInt(scannerItem);

		//add with or without groups
		ReporterChange reporterChange = (areGroups) ? new ReporterChange(ids, groups, delta) : new ReporterChange(ids, delta);

		parserHelper.getSymbolTableReporter().add(ID, reporterChange);

		//add to the symbol table
		System.out.println("THIS IS IS A TEST OF THE TESTING SYSTEM");
	}

	//G2 Reporter Commands
	private void G2(Scanner sc) {
		boolean areGroups = false;
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

		//add with or without groups
		ReporterFrequency reporterFrequency = (areGroups) ? new ReporterFrequency(ids, groups, frequency) : new ReporterFrequency(ids, frequency);
		parserHelper.getSymbolTableReporter().add(ID, reporterFrequency);

		//add to the symbol table
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
	
	
	//Sets a sensors value 
	private void H2(Scanner sc) {
		
		//Parsing
		//id
		String idStr = sc.next(); 
		Identifier id = Identifier.make(idStr); 
		
		//VALUE
		sc.next(); 
		
		double value = sc.nextDouble(); 
		
		//Execution
		SymbolTable<A_Sensor> sensorTable = parserHelper.getSymbolTableSensor(); 
		A_Sensor sensor = sensorTable.get(id); 
		sensor.setValue(value);
	}
	
	//GET SENSOR 
	private void H3(Scanner sc) {
		
		//Parsing
		String idStr = sc.next(); 
		Identifier id = Identifier.make(idStr);
		//VALUE 
		sc.next();
		
		//Execution
		SymbolTable<A_Sensor> sensorTable = parserHelper.getSymbolTableSensor(); 
		A_Sensor sensor = sensorTable.get(id); 
		parserHelper.output(sensor.toString()); 
		
		
		
		
		
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
	

	}

	
 
	  public void parse() throws IOException, ParseException, InterruptedException {

	    	//makes it so we seperate words from empty spaces (" ")
	        this.userInput = this.commandtext;
	        String[] command = this.userInput.split(" ");
	        System.out.println("The command is: " + userInput);

	        //switch statement
	        //for each command starter (first word)
	       
	       	Scanner sc = new Scanner(this.userInput);
	       
	       	//Takes the first word 
	        switch (sc.next()) {
	        	//if the first word is create
	            case "CREATE": 
	                switch(sc.next()) {
	                
	                	//Situation A1 - if the first word is actuator 
	                    case "ACTUATOR":
	                        A1(sc);
	                        break;
	                    case "MAPPER":
	                        MAPPERcommands(sc);
	                        break;
	                    case "REPORTER":
	                    	System.out.println("YO MAMA!!");
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
	            case "SET":
	            	switch(sc.next()) {
	            		case "SENSOR":
	            			H2(sc); 
	            			break; 
	            	
	            	}
	            	
	            case "GET": 
	            	switch(sc.next()) {
	            		case "SENSOR":
	            			H3(sc); 
	            			break; 
	            	
	            	}
	            	break; 
	            
	            case "BUILD": 
	            	System.out.println(userInput);
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
	        sc.close(); 
	    }
	  

}

