package cs350s22.startup;

import cs350s22.component.ui.parser.A_ParserHelper;

import cs350s22.component.ui.parser.Parser;
import cs350s22.component.ui.parser.ParserHelper;

public class Startup {
	private final static Startup startup = new Startup();
	private final A_ParserHelper _parserHelper = new ParserHelper();

	
	//Goal: create a linear actuator with some configurations 
	public static void A1() throws Exception {
		
		startup.parse("CREATE ACTUATOR LINEAR act1 ACCELERATION LEADIN 0.1 LEADOUT -0.2 RELAX 0.3 VELOCITY LIMIT 5 VALUE MIN 1 MAX 20 INITIAL 2 JERK LIMIT 3"); 
		startup.parse("@EXIT");
		
	}
	
	public Startup() {
		System.out.println("STARTUP");
	}

	
	
	public static void main(final String[] arguments) throws Exception {
		
		// this command must come first. The filenames do not matter here
		startup.parse("@CONFIGURE LOG a.txt DOT SEQUENCE b.txt NETWORK c.txt XML d.txt");
		startup.A1();

	}

	private void parse(final String parse) throws Exception {
		System.out.println("PARSE> " + parse);

		Parser parser = new Parser(_parserHelper, parse);

		parser.parse();
	}
}
