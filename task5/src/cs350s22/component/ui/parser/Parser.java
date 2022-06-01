package cs350s22.component.ui.parser;

import java.io.IOException;

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
    private void A1() {
    	
    	//Our goal is to create an actuator with identifer id and optional membership in groups and optional embeedded sensors id based on values 
    	//crate an actuator object that takes in:
    	
    	//1) Get the sensors by calling get() with id on SymbolTable<A_Sensor>
    		//Q: How do we call Symbol Table? 
    	
    	//2) create an ActuatorPrototype object with the arguments 
    	
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
                            A1();
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
