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

		//Create the mapper
		startup.parse("CREATE MAPPER map1 EQUATION PASSTHROUGH");

		//create a watchdog
		startup.parse("CREATE WATCHDOG BAND watchdog1 MODE INSTANTANEOUS THRESHOLD LOW 0 HIGH 15");

		//create the actuator with sensors
		startup.parse("CREATE ACTUATOR LINEAR act1 ACCELERATION LEADIN 0.1 LEADOUT -0.2 RELAX 0.3 VELOCITY LIMIT 5 VALUE MIN 1 MAX 20 INITIAL 2 JERK LIMIT 3");

		startup.parse("CREATE REPORTER CHANGE reporter1 NOTIFY act1 DELTA 15");

		//create the sensors (SPEED and POSITION)
		startup.parse("CREATE SENSOR POSITION positionSensor1 REPORTERS reporter1 WATCHDOGS watchdog1 MAPPER map1");
		//startup.parse("CREATE SENSOR SPEED speedSensor1 WATCHDOGS watchdog1 MAPPER map1");

		//change the position to 15
		startup.parse("SEND MESSAGE ID act1 POSITION REQUEST 15");

		//startup.parse("@EXIT");
		
	}
	
	public Startup() {
		System.out.println("STARTUP");
	}

	
	
	public static void main(final String[] arguments) throws Exception {
		
		// this command must come first. The filenames do not matter here
		LoggerActuator.initialize(Filespec.make("blah"));

		startup.parse("@CONFIGURE LOG a.txt DOT SEQUENCE b.txt NETWORK c.txt XML d.txt");
		startup.A1();

	}

	   private void parse(final String command) throws Exception
	   {
	      System.out.println("PARSE> " + command);

	      _parserHelper.schedule(command);
	   }
}
