package cs350s22.component.ui.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.concurrent.TimeUnit;


import cs350s22.component.*;
import cs350s22.component.logger.LoggerMessage;
import cs350s22.component.logger.LoggerMessageSequencing;
import cs350s22.message.A_Message;
import cs350s22.message.actuator.MessageActuatorReportPosition;
import cs350s22.message.actuator.MessageActuatorRequestPosition;
import cs350s22.message.ping.MessagePing;
import cs350s22.component.sensor.A_Sensor;

import cs350s22.component.sensor.mapper.*;

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
import cs350s22.component.ui.CommandLineInterface;
import cs350s22.support.Clock;
import cs350s22.support.Filespec;
import cs350s22.support.Identifier;
import cs350s22.test.ActuatorPrototype;

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
    //Enum stating which is being added 
    private enum Object {
    	Group, Reporter, Watchdog, Mapper 
    	
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
    	
    	 Identifier id = null;  
    	 List<Identifier> groups = null; //Optional 
		 double accelerationLeadin = 0.0; 
		 double accelerationLeadout = 0.0;
		 double accelerationRelax = 0.0;
		 double velocityLimit = 0.0;
		 double valueInitial = 0.0;
		 double valueMin = 0.0;
		 double valueMax = 0.0;
		 double inflectionJerkThreshold = 0;
		 List<A_Sensor> sensors = null; //Optional
		 
		 
		 while(sc.hasNext()) {
			 
			 String s = sc.next(); 
			 switch(s) {
			 	case("LINEAR"):
			 		String id_str = sc.next();
			 		
			 		//SymbolTable<Identifier>.getComponents(id_str); 
			 		
			 	
			 	case("SENSORS"):
			 		System.out.println("THIS IS SENSORS");
			 		break; 
			 	case("GROUPS"):
			 		break; 
			 	case(""):
			 		break ;
			 	
			 
			 
			 }
			 
			 
			 
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
    	System.out.println("Exits Method");
    	
    	
    	//3) add it to SymbolTable<A_Actuator>.

    }
    private void B1(Scanner sc) {
		
		
	}
    
    private void B2(Scanner sc) {
		
		
	}
    
    private void MAPPERcommands(Scanner sc) throws IOException {//MAPPER command also C1 C2 C3 C4
		//create mapper
    	SymbolTable<A_Mapper> mapperTable = parserHelper.getSymbolTableMapper();
        Identifier ID = Identifier.make(sc.next());

        //C 1-3
		if(sc.next().matches("EQUATION")) {
			if(sc.next().matches("PASSTHROUGH")) {
				EquationPassthrough passMapper = new EquationPassthrough();
            	MapperEquation eqautionMapper = new MapperEquation(passMapper);
            	mapperTable.add(ID, eqautionMapper);
			}
			if(sc.next().matches("SCALE")) {
				int value = sc.nextInt();
				
				EquationScaled scaleMapper = new EquationScaled(value);
            	MapperEquation equationMapper = new MapperEquation(scaleMapper);
            	mapperTable.add(ID, equationMapper);
			}
            if(sc.next().matches("NORMALIZE")) {
                int value1 = sc.nextInt();
                int value2 = sc.nextInt();

                EquationNormalized normalizeMapper = new EquationNormalized(value1, value2);
                MapperEquation equationMapper = new MapperEquation(normalizeMapper);
                mapperTable.add(ID, equationMapper);
            }
		}
        //C4
        if(sc.next().matches("INTERPOLATION")) {
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
    //D2-3
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

    //Create Sensor (Speed | Position)
    private void H1(Scanner sc) {
    	
    	 
    	// The current id being added to 
    	Object currObj; 
    	//(POSITION | SPEED)
    	boolean isPosition = sc.next().equals("POSITION");
    	
    	//The id of the postition 
    	String posId = sc.next(); 
    	
    	//The current group which is being added to 
    	ArrayList<Identifier> currGroup; 
    	
    	//List of groups 
    	ArrayList<Identifier> groups = new ArrayList<Identifier>(); 
    	
    	//List of reporters
    	ArrayList<Identifier> reporters = new ArrayList<Identifier>(); 
    	
    	//List of watchdogs 
    	ArrayList<Identifier> watchdogs = new ArrayList<Identifier>(); 
    	
    	//List of mappers 
    	ArrayList<Identifier> mapper = new ArrayList<Identifier>(); 

    	//The List of things to consider: Groups, Reporters, Watchdogs, Mapper 
    	while(sc.hasNext()) {
    		String curr = sc.next(); 
    		//First check if it is any of the stars, if it is, set that to the current 
    		if(curr.equals("GROUPS")) {
    			currObj = Object.Group; 
    			currGroup = groups; 
    		}
    		else if(curr.equals("REPORTS")) {
    			currObj = Object.Reporter;
    			currGroup = reporters; 
    		}
    		else if(curr.equals("WATCHDOGS")) {
    			currObj = Object.Watchdog; 
    			currGroup = watchdogs; 
    		}
    		else if(curr.equals("MAPPER")) {
    			currObj = Object.Mapper; 
    			currGroup = mapper; 
    			
    		}
    		else {
    			Identifer id = Identifier.make("curr"); 
    			currGroup.add(id); 
    			
    		}
    		
    		
    	}
    	
    	//Now we reach the point where it will reach one group, and the next following ones will be that one until 
    	
    	
    	
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
                            System.out.println("DO Something else");
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
                            System.out.println("DO Something else");
                            break;
                    }
                    break;

                        //defaults if not one of the options
                        default:
                            System.out.println("not valid first word");

        }
    }

	
}
