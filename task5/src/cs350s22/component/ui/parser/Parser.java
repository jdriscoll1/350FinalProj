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
import cs350s22.component.sensor.reporter.ReporterChange;
import cs350s22.component.sensor.reporter.ReporterFrequency;
import cs350s22.component.ui.CommandLineInterface;
import cs350s22.support.Clock;
import cs350s22.support.Filespec;
import cs350s22.support.Identifier;
import cs350s22.test.ActuatorPrototype;

public class Parser {
    private final A_ParserHelper parserHelper;
    private final String commandtext;
    private String userInput; 
    private List<A_Sensor> sensors = new ArrayList<A_Sensor>();
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
    	
    	 
    	 List<Identifier> groups = null; //Optional 
		 double accelerationLeadin = 0.0; 
		 double accelerationLeadout = 0.0;
		 double accelerationRelax = 0.0;
		 double velocityLimit = 0.0;
		 double velocityInitial = 0.0;
		 double valueMin = 0.0;
		 double valueMax = 0.0;
		 double inflectionJerkThreshold = 0;
		 //List<A_Sensor> sensors = null; //Optional
	     String[] command = this.userInput.split(" ");
	     Identifier ID = Identifier.make(command[3]);
	     
	     
	    //Stars & Squares
	    //First boolean linear or rotary 
	    //Take the id next
	    //Stars and Squares groups & sensors
	    //Linearly 
	     
	     
	     
			if (command[2].matches("LINEAR") || command[2].matches("ROTARY")) {
				IDList.add(ID);
				
				
				// if no group and no sensor
				if (command[4].matches("ACCELERATION")) {
					if (command[5].matches("LEADIN")) {
						accelerationLeadin = Double.parseDouble(command[6]);

						if (command[7].matches("LEADOUT")) {
							accelerationLeadout = Double.parseDouble(command[8]);

							if (command[9].matches("RELAX")) {
								accelerationRelax = Double.parseDouble(command[10]);

								if (command[11].matches("VELOCITY")) {
									if (command[12].matches("LIMIT")) {
										velocityLimit = Double.parseDouble(command[13]);

										if (command[14].matches("VALUE")) {
											if (command[15].matches("MIN")) {
												valueMin = Double.parseDouble(command[16]);
												
												if (command[17].matches("MAX")) {
													valueMax = Double.parseDouble(command[18]);

													if (command[19].matches("INITIAL")) {
														velocityInitial = Double.parseDouble(command[20]);

														if (command[21].matches("JERK")) {
															if (command[22].matches("LIMIT")) {
																inflectionJerkThreshold = Double
																		.parseDouble(command[23]);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				//end of no group and no sensor
				
				//if has group no sensor
				if (command[4].matches("GROUPS")) {
					//group
					if (command[5].matches("ACCELERATION")) {
						if (command[6].matches("LEADIN")) {
							accelerationLeadin = Double.parseDouble(command[7]);

							if (command[8].matches("LEADOUT")) {
								accelerationLeadout = Double.parseDouble(command[9]);

								if (command[10].matches("RELAX")) {
									accelerationRelax = Double.parseDouble(command[11]);

									if (command[12].matches("VELOCITY")) {
										if (command[13].matches("LIMIT")) {
											velocityLimit = Double.parseDouble(command[14]);

											if (command[15].matches("VALUE")) {
												if (command[16].matches("MIN")) {
													valueMin = Double.parseDouble(command[17]);

													if (command[18].matches("MAX")) {
														valueMax = Double.parseDouble(command[21]);

														if (command[19].matches("INITIAL")) {
															velocityInitial = Double.parseDouble(command[20]);

															if (command[21].matches("JERK")) {
																if (command[22].matches("LIMIT")) {
																	inflectionJerkThreshold = Double
																			.parseDouble(command[23]);

																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				// end if group with no sensor
				
				// if no group with sensor
				if (command[4].matches("SENSORS") | command[4].matches("SENSOR")) {
					sensors.add(parserHelper.getSymbolTableSensor().get(Identifier.make(command[5])));
					if (command[6].matches("ACCELERATION")) {

						if (command[7].matches("LEADIN")) {
							accelerationLeadin = Double.parseDouble(command[8]);

							if (command[9].matches("LEADOUT")) {
								accelerationLeadout = Double.parseDouble(command[10]);

								if (command[11].matches("RELAX")) {
									accelerationRelax = Double.parseDouble(command[12]);

									if (command[13].matches("VELOCITY")) {
										if (command[14].matches("LIMIT")) {
											velocityLimit = Double.parseDouble(command[15]);

											if (command[16].matches("VALUE")) {
												if (command[17].matches("MIN")) {
													valueMin = Double.parseDouble(command[18]);

													if (command[19].matches("MAX")) {
														valueMax = Double.parseDouble(command[20]);

														if (command[21].matches("INITIAL")) {
															velocityInitial = Double.parseDouble(command[23]);

															if (command[22].matches("JERK")) {
																if (command[23].matches("LIMIT")) {
																	inflectionJerkThreshold = Double
																			.parseDouble(command[24]);

																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				// end no group with sensor
				
				//if full thing all variables
				if (command[4].matches("GROUPS")) {
					// NOT SURE WHAT TO DO IF GROUP

					if (command[5].matches("SENSORS") | command[5].matches("SENSOR")) {
						sensors.add(parserHelper.getSymbolTableSensor().get(Identifier.make(command[6])));
					}

					if (command[7].matches("ACCELERATION")) {

						if (command[8].matches("LEADIN")) {
							accelerationLeadin = Double.parseDouble(command[9]);

							if (command[10].matches("LEADOUT")) {
								accelerationLeadout = Double.parseDouble(command[11]);

								if (command[12].matches("RELAX")) {
									accelerationRelax = Double.parseDouble(command[13]);

									if (command[14].matches("VELOCITY")) {
										if (command[15].matches("LIMIT")) {
											velocityLimit = Double.parseDouble(command[16]);
											
											if (command[17].matches("VALUE")) {
												if (command[18].matches("MIN")) {
													valueMin = Double.parseDouble(command[19]);

													if (command[20].matches("MAX")) {
														valueMax = Double.parseDouble(command[21]);

														if (command[22].matches("INITIAL")) {
															velocityInitial = Double.parseDouble(command[23]);

															if (command[24].matches("JERK")) {
																if (command[25].matches("LIMIT")) {
																	inflectionJerkThreshold = Double.parseDouble(command[26]);
																	
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

		
//		 while(sc.hasNext()) {
//			 
//			 String s = sc.next(); 
//			 switch(s) {
//			 	case("LINEAR"):
//			 		String id_str = sc.next();
//			 		
//			 		//SymbolTable<Identifier>.getComponents(id_str); 
//			 		
//			 	
//			 	case("SENSORS"):
//			 		System.out.println("THIS IS SENSORS");
//			 		break; 
//			 	case("GROUPS"):
//			 		break; 
//			 	case(""):
//			 		break ;
//			 	
			 
			 
			 }
			 
			 
			 
		 
    	
    	//2) create an ActuatorPrototype object with the arguments 
    	/*ActuatorPrototype a = new ActuatorPrototype(
    			
    			 id,
    			 groups,
    			 accelerationLeadin,
    			 accelerationLeadout,
    			 accelerationRelax,
    			 velocityLimit,
    			 valueInitial,
    			 valueMin,
    			 valueMax,
    			 inflectionJerkThreshold,
    			 sensors);*/
    	//System.out.println("Exits Method");
    	
    	
    	//3) add it to SymbolTable<A_Actuator>.

    
    private void B1(Scanner sc) {		
		
	}
    
    private void B2(Scanner sc) {
		
		
	}
    //TO DO: REPLACE sc.next with pre-declared string (ask Kevin for clarification)
    private void MAPPERcommands(Scanner sc) throws IOException {//MAPPER command also C1 C2 C3 C4
		//create mapper
    	SymbolTable<A_Mapper> mapperTable = parserHelper.getSymbolTableMapper();
        Identifier ID = Identifier.make(sc.next()); 

        //C 1-3
		if(sc.next().matches("EQUATION")) {
			String PLEASE_RENAME_THIS_KELSEY_OR_KEVIN = sc.next(); 
			if(PLEASE_RENAME_THIS_KELSEY_OR_KEVIN.matches("PASSTHROUGH")) {
				EquationPassthrough passMapper = new EquationPassthrough();
            	MapperEquation eqautionMapper = new MapperEquation(passMapper);
            	mapperTable.add(ID, eqautionMapper);
			}
			else if(PLEASE_RENAME_THIS_KELSEY_OR_KEVIN.matches("SCALE")) {
				int value = sc.nextInt();
				
				EquationScaled scaleMapper = new EquationScaled(value);
            	MapperEquation equationMapper = new MapperEquation(scaleMapper);
            	mapperTable.add(ID, equationMapper);
			}
			else if(PLEASE_RENAME_THIS_KELSEY_OR_KEVIN.matches("NORMALIZE")) {
                int value1 = sc.nextInt();
                int value2 = sc.nextInt();

                EquationNormalized normalizeMapper = new EquationNormalized(value1, value2);
                MapperEquation equationMapper = new MapperEquation(normalizeMapper);
                mapperTable.add(ID, equationMapper);
                System.out.println("Work??? Do you??");
            }
		}
        //C4
		else if(sc.next().matches("INTERPOLATION")) {
            if(sc.next().matches("LINEAR")) {
                sc.next();
                String definition = sc.next();
                Filespec filespec = new Filespec(definition);
                A_MapLoader map = new MapLoader(filespec);
                InterpolationMap interpolationMap = map.load();
                InterpolatorLinear interpolatorLinear = new InterpolatorLinear(interpolationMap);
                MapperInterpolation mapperInterpolation = new MapperInterpolation(interpolatorLinear);
                mapperTable.add(ID, mapperInterpolation);
            }
            if(sc.next().matches("SPLINE")) {
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
    
    //Sends out a ping from the command line interface
    private void D1() {
       	CommandLineInterface cli = parserHelper.getCommandLineInterface(); 
    	MessagePing ping = new MessagePing(); 
    	cli.issueMessage(ping);
    	System.out.println("Ping sent");
    }

    //TO-DO: Add Stars & Squares 
    private void D2_3(Scanner sc) {
    	CommandLineInterface cli = parserHelper.getCommandLineInterface(); 
    	boolean isID = false; 
    	
    	//All the ids & groups 
    	ArrayList<Identifier> listOutput;  
    	ArrayList<Identifier> ids = new ArrayList<Identifier>(); 
    	ArrayList<Identifier> groups = new ArrayList<Identifier>(); 
    	
    	//The current word being parsed
    	String curr = ""; 
    	String output = ""; 
    	
    	//Loop until it finds position 
    	while(!curr.equals("POSITION")) {
    		//Grab the current word
    		curr = sc.next(); 
    		if(curr.equals("ID")) {
    			if(output.equals("")) {
    				output = "ID"; 
    			}
    			isID = true; 
    			
    		}
    		else if(curr.equals("GROUPS")) {
    			if(output.equals("")) {
    				output = "GROUPS"; 
    			}
    			isID = false; 
    			
    		}
    		else {
    			//Convert string 2 identifier 
    			Identifier id = Identifier.make(curr); 
    			
    			//If it's an ID add it to id, else ad dit to groups 
    			if(isID) {
    				
    				ids.add(id); 
    			}
    			else {
    				groups.add(id); 	
    			}
    		}
    	}
    	listOutput = (output.equals("ID")) ? ids : groups; 
    	
    	//POSITION
    	sc.next(); 
    	
    	boolean isRequest = (sc.next().equals("REQUEST")); 
    	double value = sc.nextDouble(); 

    	//Create Message Actuator 
    	A_Message message = (isRequest) ?  new MessageActuatorRequestPosition(listOutput, value) : new MessageActuatorReportPosition(listOutput); 
    	cli.issueMessage(message);
    }
    
    private void E6(Scanner sc) throws IOException {
    	//LOG
    	sc.next();
    	String logStr = sc.next();
    	//DOT
    	sc.next();
    	//SEQUENCE
    	sc.next();
    	String dotStr = sc.next(); 
    	sc.next();
    	//NETWORK
    	String netStr = sc.next(); 
    	//XML String 
    	while(sc.hasNext()) {
    		sc.next(); 
    	}
    	
    	//Convert strings to file specs 
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

	//F1 Network Commands
	private void F1(Scanner sc) {
		//COMPONENT
		if(sc.next().equals("COMPONENT")) {
			Identifier ID = Identifier.make(sc.next());
			SymbolTable<A_Controller> controllerTable = parserHelper.getSymbolTableController();
			SymbolTable<A_Sensor> sensorTable = parserHelper.getSymbolTableSensor();
			A_Component component1 = controllerTable.get(ID);
			A_Component component2 = sensorTable.get(ID);
			parserHelper.getControllerMaster().addComponent(component1);
			parserHelper.getControllerMaster().addComponent(component2);
		}
		//COMPONENTS
		if(sc.next().equals("COMPONENTS")) {
			Identifier ID = Identifier.make(sc.next());
			SymbolTable<A_Controller> controllerTable = parserHelper.getSymbolTableController();
			SymbolTable<A_Actuator> actuatorTable = parserHelper.getSymbolTableActuator();
			SymbolTable<A_Sensor> sensorTable = parserHelper.getSymbolTableSensor();
			A_Component component1 = controllerTable.get(ID);
			A_Component component2 = actuatorTable.get(ID);
			A_Component component3 = sensorTable.get(ID);
			parserHelper.getControllerMaster().addComponent(component1);
			parserHelper.getControllerMaster().addComponent(component2);
			parserHelper.getControllerMaster().addComponent(component3);
		}
		System.out.println(parserHelper.getNetwork().generateXML());
	}

	//G1 and G2 Reporter Commands
	private void G1_2(Scanner sc) {
		//CHANGE
		if(sc.next().equals("CHANGE")) {
			Identifier ID = Identifier.make(sc.next());
			//NOTIFY
			sc.next();
			//IDS
			sc.next();
			//get the list of ids to notify
			List<Identifier> ids = Identifier.makeListEmpty();
			List<Identifier> groups = Identifier.makeListEmpty();
			while(!sc.next().equals("GROUPS")) {
				ids.add(Identifier.make(sc.next()));
			}
			while(!sc.next().equals("DELTA")) {
				groups.add(Identifier.make(sc.next()));
			}
			//DELTA
			sc.next();
			//get the delta value
			int delta = Integer.parseInt(sc.next());
			ReporterChange reporterChange = new ReporterChange(ids, groups, delta);

			//add to the symbol table
			parserHelper.getSymbolTableReporter().add(ID, reporterChange);
			
			
		}
		//FREQUENCY
		if(sc.next().equals("FREQUENCY")) {
			Identifier ID = Identifier.make(sc.next());
			//NOTIFY
			sc.next();
			//IDS
			sc.next();
			//get the list of ids to notify
			List<Identifier> ids = Identifier.makeListEmpty();
			List<Identifier> groups = Identifier.makeListEmpty();
			while(!sc.next().equals("GROUPS")) {
				ids.add(Identifier.make(sc.next()));
			}
			while(!sc.next().equals("FREQUENCY")) {
				groups.add(Identifier.make(sc.next()));
			}
			//FREQUENCY
			sc.next();
			//get the frequency value
			int frequency = Integer.parseInt(sc.next());
			ReporterFrequency reporterFrequency = new ReporterFrequency(ids, groups, frequency);

			//add to the symbol table
			parserHelper.getSymbolTableReporter().add(ID, reporterFrequency);
			System.out.println("Frequency has been hit and all of the important things were done");
		}
	}

    //Create Sensor (Speed | Position)
    private void H1(Scanner sc) {
    	/***************** PARSING  ***********/
    	//(POSITION | SPEED)
    	boolean isPosition = sc.next().equals("POSITION");
    	
    	//The id of the postition 
    	String posId = sc.next(); 
    	
    	//The current group which is being added to 
    	ArrayList<Identifier> currGroup = new ArrayList<Identifier>(); 
    	
    	//List of groups 
    	ArrayList<Identifier> groups = new ArrayList<Identifier>(); 
    	
    	//List of reporters
    	ArrayList<Identifier> reporters = new ArrayList<Identifier>(); 
    	
    	//List of watchdogs 
    	ArrayList<Identifier> watchdogs = new ArrayList<Identifier>(); 
    	
    	//List of mappers 
    	ArrayList<Identifier> mapper = new ArrayList<Identifier>(); 

    	//Check if each one is specified
    	boolean groupsFlag= false;
    	boolean reporterFlag = false; 
    	boolean watchdogFlag = false; 
    	boolean mapperFlag = false; 
    	
    	//The List of things to consider: Groups, Reporters, Watchdogs, Mapper 
    	while(sc.hasNext()) {
    		String curr = sc.next(); 
    		
    		//First check if it is any of the stars, if it is, set that to the current 
    		if(curr.equals("GROUPS") || curr.equals("GROUP")) {
    			currGroup = groups; 
   
    		}
    		else if(curr.equals("REPORTERS") || curr.equals("REPORTER")) {
    			currGroup = reporters; 
  
    		}
    		else if(curr.equals("WATCHDOGS") || curr.equals("WATCHDOG")) {
    			currGroup = watchdogs; 
   
    		}
    		else if(curr.equals("MAPPER")) {
    			currGroup = mapper; 
    
    		}
    		//If it's not any of the stars, it's a square 
    		else {
    			Identifier id = Identifier.make(curr); 
    			currGroup.add(id); 
    			
    		}
    		
    		
    		
    		/*******EXECUTING************/
    		//Grab the Necessary Reporters watchdogs and mappers
    		if(groups.size() > 0) {}
    		//Create the sensor 
    		
    		
    		
    	}
    	System.out.println("Groups\n");
    	for(Identifier i : groups) {
    		System.out.println(i + "\n"); 
    	}
    	System.out.println("Mapper\n");
    	for(Identifier i : mapper) {
    		System.out.println(i + "\n"); 
    	}
    	System.out.println("Reporters\n");
    	for(Identifier i : reporters) {
    		System.out.println(i + "\n"); 
    	}
    	System.out.println("Watchdogs\n");
    	for(Identifier i : watchdogs) {
    		System.out.println(i + "\n");
    		
    	}
    	//Now we reach the point where it will reach one group, and the next following ones will be that one until 
    	
    	
    	
    }
    
    //Create a watchdog
    private void I(Scanner sc) {
    	//The watchdog's id
    	String id = ""; 
    	//TThe mode of the watch dog(instantaneous? Average? etc..)
    	String mode = ""; 
    	
    	//A potential number corresponding with the mode 
    	int modeNum = 0; 
    	
    	boolean modeNumFlag = false; 
    	
    	//The Low and High Threshold 
    	double lowThreshold = 0; 
    	double highThreshold = 0;
    	
    	//Only for case in I3
    	double threshold = 0; 
    	
    	boolean graceSet = false; 
    	int grace = 0; 
    	
    	//CREATE WATCHDOG already accounted for 
   
    	//Watchdog Type
    	String watchdogType = sc.next(); 
    	
    	//If it's type is low or high, 
    	boolean I3 = (watchdogType.equals("LOW") || watchdogType.equals("HIGH")); 
    	
    	id = sc.next(); 
    	
    	//MODE
    	sc.next(); 
    	
    	mode = sc.next(); 
    	if(mode.equals("STANDARD")) {
    		//Deviation
    		sc.next(); 
    		
    	}
    	
    	//There may be an integer here, if there is, it is necessary to take it 
    	if(sc.hasNextDouble()) {
    		modeNumFlag = true; 
    		modeNum = sc.nextInt(); 
    		
    	}
    	
    	//THRESHOLD 
    	sc.next(); 
    	
    	
    	
    	if(!I3) {
	    	//LOW
	    	sc.next(); 
	    	
	    
	    	lowThreshold = sc.nextDouble(); 
	    	
	    	//HIGH
	    	sc.next(); 
	    	
	    	highThreshold = sc.nextDouble(); 
    	}
    	else {
    		
    		threshold = sc.nextDouble(); 
    		
    	}
    	//GRACE is optional 
    	if(sc.hasNext()) {
    		//GRACE
    		sc.next(); 
    		graceSet = true; 
    		grace = sc.nextInt(); 
    		
    		
    	}
    	
    	//Create a watchdog mode object
    	A_WatchdogMode w_mode = null; 
    	switch(mode) {
    		case("STANDARD"):
    			w_mode = (modeNumFlag) ? new WatchdogModeStandardDeviation(modeNum) : new WatchdogModeStandardDeviation(); 
    			break; 
    		case("AVERAGE"):
    			w_mode = (modeNumFlag) ? new WatchdogModeAverage(modeNum) : new WatchdogModeAverage(); 
    			break; 
    		
    		case("INSTANTANEOUS"):
    			w_mode = new WatchdogModeInstantaneous(); 
    			break; 
    	}
    	
    	//Get the watchdog symbol table 
    	SymbolTable<A_Watchdog> table  = parserHelper.getSymbolTableWatchdog(); 
    	
    	A_Watchdog w = null; 
    	switch(watchdogType){
    
    		case("ACCELERATION"):
    			w = (graceSet) ? new WatchdogAcceleration(lowThreshold, highThreshold, w_mode, grace) : new WatchdogAcceleration(lowThreshold, highThreshold, w_mode); 
    			break;
    			
    		case("BAND"):
    			w = (graceSet) ? new WatchdogBand(lowThreshold, highThreshold, w_mode, grace) : new WatchdogBand(lowThreshold, highThreshold, w_mode); 
    			break; 
    		case("NOTCH"):
    			w = (graceSet) ? new WatchdogNotch(lowThreshold, highThreshold, w_mode, grace) : new WatchdogNotch(lowThreshold, highThreshold, w_mode);
    			break;
    		case("LOW"):
    			w = (graceSet) ? new WatchdogLow(threshold, w_mode, grace) : new WatchdogLow(threshold, w_mode); 
    			break; 
    		case("HIGH"):
    			w = (graceSet) ? new WatchdogHigh(threshold, w_mode, grace) : new WatchdogHigh(threshold, w_mode); 
    			break; 
    			
    		
    		
    	}
    	
    		
    	
    	Identifier tableID = Identifier.make(id); 
    	table.add(tableID, w);
    	System.out.println(table.get(tableID));
    	 
    	
    	
    	
    	
    	
    }
    
    public void I2(Scanner sc) {
    	
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
                            System.out.println("DO Something else");
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
                            switch(sc.next()) {
                            	case "PING":
                            		D1(); 
                            
                            
                            }
                            
                            break;
                        default:
                            System.out.println("not valid first word");
                    }
                    break;
                case "@CLOCK": System.out.println(java.time.LocalTime.now());
                	if(sc.hasNext()) {
	                    switch(sc.next()) {
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
	                            Clock c4 = Clock.getInstance();
	                            //if the clock is not active
	                            if(!c4.isActive()) {
	                                //if there's another one
	                                if(sc.hasNext()) {
	                                    //go to the next one
	                                    sc.next();
	                                    //get the next thing
	                                    String count = sc.next();
	                                    //Does the one step command work?
	                                    c4.onestep(Integer.parseInt(count));
	                                }
	                                else {
	                                    //Increment by one
	                                    c4.onestep(1);
	                                }
	                            }
	                            break;
	                        //E3
	                        case "SET":
	                        	//Rate
	                        	sc.next();
	                        	String value = sc.next(); 
	                        	Clock c3 = Clock.getInstance(); 
	                        	c3.setRate(Integer.parseInt(value));
	                            
	                            break;
	                        default:
	                            System.out.println("not valid first word");
	
	                        case "WAIT":
	                            if(sc.next() == "FOR") {
	                                sc.next();
	                                String seconds = sc.next();
	                                TimeUnit.SECONDS.sleep(Long.parseLong(seconds));
	                            }
	                            if(sc.next() == "UNTIL") {
	                                sc.next();
	                                String seconds = sc.next();
	                                TimeUnit.SECONDS.wait(Long.parseLong(seconds));
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
