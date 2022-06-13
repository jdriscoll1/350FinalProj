
package cs350s22.startup;

import cs350s22.component.ui.parser.A_ParserHelper;
import cs350s22.component.logger.LoggerActuator;
import cs350s22.component.ui.parser.Parser;
import cs350s22.component.ui.parser.ParserHelper;
import cs350s22.support.Filespec;

public class Startup {
	private final static Startup startup = new Startup();
	private final A_ParserHelper _parserHelper = new ParserHelper();

	//Goal: create a linear actuator with some configurations 
	public static void A1() throws Exception {

		startup.parse("CREATE ACTUATOR LINEAR myActuator1 ACCELERATION LEADIN 0.1 LEADOUT -0.2 RELAX 0.3 VELOCITY LIMIT 5 VALUE MIN 1 MAX 20 INITIAL 2 JERK LIMIT 3");

		startup.parse("BUILD NETWORK WITH COMPONENT myActuator1");

		startup.parse("SEND MESSAGE PING");

	}

	public static void A2() throws Exception {

		 startup.parse("CREATE REPORTER FREQUENCY myReporter1 NOTIFY ID cli FREQUENCY 3");

	      startup.parse("CREATE SENSOR POSITION mySensor1"); // add  REPORTERS myReporter1
	      
	      startup.parse("CREATE ACTUATOR LINEAR myActuator1 SENSOR mySensor1 ACCELERATION LEADIN 0.1 LEADOUT -0.2 RELAX 0.3 VELOCITY LIMIT 5 VALUE MIN 1 MAX 20 INITIAL 2 JERK LIMIT 3");
	      
	      startup.parse("BUILD NETWORK WITH COMPONENT myActuator1");
	      	
	      startup.parse("SEND MESSAGE ID myActuator1 POSITION REQUEST 15");
	      
	      startup.parse("@CLOCK WAIT FOR 0.5");
	      
	      startup.parse("SEND MESSAGE ID myActuator1 POSITION REPORT");
	     
	      startup.parse("@EXIT");   

	}

	public static void B1() throws Exception {

		startup.parse("CREATE SENSOR POSITION mySensor3");

		startup.parse("BUILD NETWORK WITH COMPONENT mySensor3");

		startup.parse("SET SENSOR mySensor3 VALUE 5");
		startup.parse("GET SENSOR mySensor3 VALUE");
	}

	public static void B2() throws Exception {

		startup.parse("CREATE SENSOR POSITION mySensor4");

		startup.parse("CREATE ACTUATOR LINEAR myActuator3 SENSOR mySensor4 ACCELERATION LEADIN 0.1 LEADOUT -0.2 RELAX 0.3 VELOCITY LIMIT 5 VALUE MIN 1 MAX 20 INITIAL 2 JERK LIMIT 3");

		startup.parse("BUILD NETWORK WITH mySensor4 myActuator3");

		startup.parse("SET SENSOR mySensor4 VALUE 10");
		startup.parse("GET SENSOR mySensor4 VALUE");
	}
	
	public static void D2() throws Exception{
		//Create a reporter
		 startup.parse("CREATE REPORTER FREQUENCY myReporter1 NOTIFY ID cli FREQUENCY 10");

		//Create le watchdog 
		startup.parse("CREATE WATCHDOG NOTCH myWatchdog1 MODE INSTANTANEOUS THRESHOLD LOW 0 HIGH 15");
		
		//Create Sensr
		startup.parse("CREATE SENSOR POSITION mySensor4 GROUP myGroup1 REPORTER myReporter1 WATCHDOG myWatchdog1");
		
		//What do we need to check if a watchdog
		startup.parse("CREATE ACTUATOR LINEAR myActuator1 SENSOR mySensor4 ACCELERATION LEADIN 0.1 LEADOUT -0.2 RELAX 0.3 VELOCITY LIMIT 5 VALUE MIN 1 MAX 20 INITIAL 2 JERK LIMIT 3");

		//Build the network
		startup.parse("BUILD NETWORK WITH COMPONENT myActuator1");
		
		startup.parse("SEND MESSAGE ID myActuator1 POSITION REQUEST 15");
	    startup.parse("@CLOCK WAIT FOR 0.2");  
	    startup.parse("GET SENSOR mySensor4 VALUE");
		
	    startup.parse("@CLOCK WAIT FOR 0.5");
	      
	    startup.parse("SEND MESSAGE ID myActuator1 POSITION REPORT");
	     
	    startup.parse("@EXIT");   

		
		
	}
	//Instantaneous Band Watchdog
	public static void E1() throws Exception{
		 
		 //Create a reporter
		 startup.parse("CREATE REPORTER FREQUENCY myReporter1 NOTIFY ID cli FREQUENCY 10");

		//Create le watchdog 
		startup.parse("CREATE WATCHDOG NOTCH myWatchdog1 MODE INSTANTANEOUS THRESHOLD LOW 10 HIGH 14");
		
		//Create Sensr
		startup.parse("CREATE SENSOR POSITION mySensor4 GROUP myGroup1 REPORTER myReporter1 WATCHDOG myWatchdog1");
		
		//What do we need to check if a watchdog
		startup.parse("CREATE ACTUATOR LINEAR myActuator1 SENSOR mySensor4 ACCELERATION LEADIN 0.1 LEADOUT -0.2 RELAX 0.3 VELOCITY LIMIT 5 VALUE MIN 1 MAX 20 INITIAL 2 JERK LIMIT 3");

		//Build the network
		startup.parse("BUILD NETWORK WITH COMPONENT myActuator1");
		
		startup.parse("SEND MESSAGE ID myActuator1 POSITION REQUEST 15");
	    startup.parse("@CLOCK WAIT FOR 0.2");  
	    startup.parse("GET SENSOR mySensor4 VALUE");
		
	    startup.parse("@CLOCK WAIT FOR 0.5");
	      
	    startup.parse("SEND MESSAGE ID myActuator1 POSITION REPORT");
	     
	    startup.parse("@EXIT");   


		
	}
	
	//Send Message Ping
	public static void F1() throws Exception {
	      startup.parse("CREATE ACTUATOR LINEAR myActuator1 ACCELERATION LEADIN 0.1 LEADOUT -0.2 RELAX 0.3 VELOCITY LIMIT 5 VALUE MIN 1 MAX 20 INITIAL 2 JERK LIMIT 3");
	      
	      startup.parse("BUILD NETWORK WITH COMPONENT myActuator1");
	      	
	      startup.parse("SEND MESSAGE PING");	
	      
	}
	
	public Startup() {
		System.out.println("STARTUP");
	}

	public static void main(final String[] arguments) throws Exception {
		
		// this command must come first. The filenames do not matter here
		LoggerActuator.initialize(Filespec.make("blah"));

		startup.parse("@CONFIGURE LOG a.txt DOT SEQUENCE b.txt NETWORK c.txt XML d.txt");

		startup.D2(); 
		startup.parse("@EXIT");


	}

	   private void parse(final String command) throws Exception
	   {
	      System.out.println("PARSE> " + command);

	      _parserHelper.schedule(command);
	   }
}