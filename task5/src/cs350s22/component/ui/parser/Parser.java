package cs350s22.component.ui.parser;

import java.io.IOException;
import java.util.List;

import cs350s22.component.sensor.A_Sensor;
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
    //Goal: 
    private void A1(String[] command) {
    	for(String c : command) {
    		System.out.println(c);
    		
    	}
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
    	
    	//2) create an ActuatorPrototype object with the arguments 
    	ActuatorPrototype a = new ActuatorPrototype(
    			
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
    			 sensors);
    	
    	//3) add it to SymbolTable<A_Actuator>.

    	
    }
    public void parse() throws IOException{
        
    	
    	//makes it so we seperate words from empty spaces (" ")
        this.userInput = this.commandtext.toUpperCase();
        String[] command = this.userInput.split(" ");
        String verb = command[0]; 
        System.out.println("The command is: " + userInput);
        String subject = command[1]; 

            //switch statement
            //for each command starter (first word)
            switch (command[0]) {
                case "CREATE": System.out.println("DO Something");
                    switch(command[1]) {
                        case "ACTUATOR":
                            A1(command);
                            break;
                        case "CONTROLLER":
                            System.out.println("DO Something else");
                            break;
                        case "DEPENDENCY":
                            System.out.println("DO Something else");
                            break;
                        case "MAPPER":
                            System.out.println("DO Something else");
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
                    switch(command[1]) {
                        case "MESSAGE":
                            System.out.println("DO Something else");
                            break;
                        default:
                            System.out.println("not valid first word");
                    }
                    break;
                case "@CLOCK": System.out.println("DO Something");
                    switch(command[1]) {
                        case "PAUSE":
                            System.out.println("DO Something else");
                            break;
                        case "RESUME":
                            System.out.println("DO Something else");
                            break;
                        case "ONESTEP":
                            System.out.println("DO Something else");
                            break;
                        case "SET":
                            System.out.println("DO Something else");
                            break;
                        default:
                            System.out.println("not valid first word");

                    }
                    break;

                case "@EXIT": System.out.println("DO Something");
                    break;

                case "@RUN": System.out.println("DO Something");
                    break;

                case "@CONFIGURE": System.out.println("DO Something");
                    switch(command[1]) {
                        case "LOG":
                            System.out.println("DO Something else");
                            break;
                        default:
                            System.out.println("not valid first word");
                    }
                    break;
                case "BUILD": System.out.println("DO Something");
                    switch(command[1]) {
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
