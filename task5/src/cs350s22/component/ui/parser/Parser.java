package cs350s22.component.ui.parser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs350s22.component.*;
import cs350s22.component.logger.LoggerMessage;
import cs350s22.component.logger.LoggerMessageSequencing;
import cs350s22.component.sensor.A_Sensor;
import cs350s22.component.sensor.mapper.A_Mapper;
import cs350s22.component.sensor.mapper.MapperEquation;
import cs350s22.component.sensor.mapper.function.equation.EquationPassthrough;
import cs350s22.component.sensor.mapper.function.equation.EquationScaled;
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
    
    private void MAPPERcommands(Scanner sc) {//MAPPER command also C1 C2 C3 C4
		//create mapper
    	SymbolTable<A_Mapper> mapperTable = parserHelper.getSymbolTableMapper();
        Identifier ID = Identifier.make(sc.next());
        
        
		if(sc.next().matches("EQUATION")) {
			if(sc.next().matches("PASSTHROUGH")) {
				EquationPassthrough passMapper = new EquationPassthrough();
            	MapperEquation eqMapper = new MapperEquation(passMapper);
            	mapperTable.add(ID, eqMapper);
			}
			if(sc.next().matches("SCALE")) {
				int value = sc.nextInt();
				
				EquationScaled scaleMapper = new EquationScaled(value);
            	MapperEquation equationMapper = new MapperEquation(scaleMapper);
            	mapperTable.add(ID, equationMapper);
				
			}//follow format above should be easy for normalize for C3 maybe a little more complicated for C4
		}
		
		
		
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
    	
    	//Convert strings to filespecs 
    	Filespec logSpec = new Filespec(logStr); 
    	Filespec dotSpec = new Filespec(dotStr); 
    	Filespec netSpec = new Filespec(netStr); 
    	
    	LoggerMessage.initialize(logSpec); 
    	LoggerMessageSequencing.initialize(dotSpec, netSpec); 
    	
    }

    public void parse() throws IOException, ParseException{
        
    	
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
                            System.out.println("DO Something else");
                            break;
                        case "WATCHDOG":
                            System.out.println("DO Something else");
                            break;

                        default:
                            System.out.println("not valid first word");
                    }
                    break;
                case "SEND": System.out.println("DO Something");
                    switch(sc.next()) {
                        case "MESSAGE":
                            System.out.println("DO Something else");
                            break;
                        default:
                            System.out.println("not valid first word");
                    }
                    break;
                case "@CLOCK":
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
