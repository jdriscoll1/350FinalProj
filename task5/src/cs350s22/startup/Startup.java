package cs350s22.startup;

import cs350s22.component.ui.parser.A_ParserHelper;
import cs350s22.component.ui.parser.Parser;
import cs350s22.component.ui.parser.ParserHelper;

public class Startup
{
   private final A_ParserHelper _parserHelper = new ParserHelper();
   
   public Startup()
   {
      System.out.println("STARTUP");
   }

   public static void main(final String[] arguments) throws Exception
   {
      Startup startup = new Startup();
      
     // this command must come first. The filenames do not matter here
     //startup.parse("@CONFIGURE LOG \"a.txt\" DOT SEQUENCE \"b.txt\" NETWORK \"c.txt\" XML \"d.txt\"");
     //startup.parse("CREATE ACTUATOR LINEAR myActuator0 ACCELERATION LEADIN 0.1 LEADOUT -0.2 RELAX 0.3 VELOCITY LIMIT 5 VALUE MIN 1 MAX 10 INITIAL 2 JERK LIMIT 3");
     //startup.parse("@RUN file1.txt");
     startup.parse("@CONFIGURE LOG a DOT SEQUENCE b NETWORK c XML str");
     //startup.parse("SEND MESSAGE PING");
     startup.parse("@CLOCK");
     // run your tests like this
     startup.parse("@exit");
   }
   
   private void parse(final String parse) throws Exception
   {
      System.out.println("PARSE> "+ parse);
      
      Parser parser = new Parser(_parserHelper, parse);
      
      parser.parse();
   }
}
