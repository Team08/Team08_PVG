package main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * This class can verify keys in a properties files that is pre defined in this class. It can also generates a config.properties file with some default keys and values
 * (This class is used by the Enduro) 
 * 
 * @author Team08
 *
 */
public class GenerateConfig {
	Properties configFile;
	String start = "";
	String stop = "";
	String name = "";
	String result = "";
	String raceTime  = "";
	String raceType = "";
	int distance = 0;
	String attributeString = "";
	
	/**
	 * Creates the GenerateConfig
	 * @param configFile
	 * 					The properties file to read
	 */
	public GenerateConfig(Properties configFile){
		this.configFile = configFile; 
	}
	
	/**
	 * This method generates a file config.properties
	 */
	public void autogenerateConfig(){
		 try {  
				FileOutputStream createTheFile = new FileOutputStream(new File("config.properties"));

		        BufferedWriter out = new BufferedWriter(new FileWriter("config.properties")); 
		        out.write("#AUTO GENERATED config.properties\n");
		        out.write("#Konfigurationsfil f�r Enduro som har v�rden sparade i formatet <nyckel>=<v�rde> \n");
		        out.write("#Fyll i alla v�rden nedan f�rutom de som �r l�ngst ner eftersom de ej anv�nds. \n\n");
		        
		        out.write("#Vad heter dina filer och var ligger dem?" + "\n");
		        out.write("#A1t1. Dina filer ligger i samma mapp som Enduro, d� anger vi \"Exempel_startfil.txt\"" + "\n");
		        out.write("#Alt2. Du har filerna i en mapp som heter [Tider], d� anger vi \"Tider/Exempel_startfil.txt\"" + "\n");
		        out.write("#(OBS)! Undvik svenska specialtecken p� filnamnen!" + "\n");
		        out.write("STARTFILE=example_start.txt" + "\n");
		        out.write("STOPFILE=aMapp/example_stop.txt" + "\n");
		        out.write("NAMEFILE=" + "\n\n");
		        
		        out.write("#Var ska resultat och den sorterade filen heta och var ska de genereras?" + "\n");
		        out.write("#Alt1. De ska genereras i den mapp som Enduro ligger i. D� anger vi \"Exempel_resultat.txt\"" + "\n");
		        out.write("#Alt2. De ska genereras i mappen [resultat] som ligger i samma mapp som Enduro. D� anger vi \"resultat/Exempel_resultat.txt\"" + "\n");
		        out.write("#OBS! [resultat] mappen m�ste skapas f�rst om alternativ 2 ska fungera !" + "\n");
		        out.write("RESULTFILE=exMyResult.txt" + "\n");
		        out.write("SORTEDFILE=" + "\n\n");
		        
		        out.write("#Vad f�r sorts t�vling �r det? Ex. Ett varvlopp blir d� = \"varv\"" + "\n");
		        out.write("RACETYPE=varv" + "\n\n");
		        
		        out.write("#LAPS=3Hur startas t�vlingen? Ex. Vid enkelstart s� ange:\"enkelstart\", Vid massstart s� �ndra v�rdet till:\"masstart\""  + "\n");
		        out.write("STARTTYPE=enkelstart" + "\n\n");
		        
		        out.write("#Hur l�nge varar hela t�vlingen? (format = \"hh.mm\" ) Ex. Om t�vlingen varar i 10 timmar och 30 minuter s� ange, \"10:30\"" + "\n");
		        out.write("RACETIME=" + "\n\n");
		        
		        out.write("#Hur m�nga varv ska k�ras? Ex. Om vi ska springa 10 varv s� anger vi \"10\"" + "\n");
		        out.write("LAPS=" + "\n\n");
		        
		        out.write("#ALTERNATIV nyckel som ej beh�ver fyllas i!" + "\n");
		        out.write("#Vad alla deltagare i denna t�vlingen beh�ver ange f�r information om sig sj�lva, l�mnas tomt om inget beh�ver anges." + "\n");
		        out.write("#Ex: \"Klubb;MC-fabrikat;Sponsor1;Sponsor2;\"" + "\n");
		        out.write("DRIVER_ATTRIBUTES=" + "\n\n\n\n\n\n\n");
		        
		        
		        
		        
		        
		        
		        out.write("#________Nycklar som inte anv�nds f�r denna programversion______!" + "\n");
		        out.write("#Vilka resultat vill man ha (med o utan sortering, i vilka format" + "\n");
		        out.write("RESULT_FORMAT" + "\n");
		        out.write("#Vilka etapper �r specialstr�ckor? Ange nummer f�r dessa" + "\n");
		        out.write("#Ex \"2,3\" om 2 och 3 är specialsträckor" + "\n");
		        out.write("SPECIAL_DISTANCES=" + "\n");
		        out.write("#Faktorn som specialstr�ckor skall multipliceras med" + "\n");
		        out.write("#Ex: 3" + "\n");
		        out.write("FACTOR=" + "\n");
		        out.write("#Antal etapper, SpecialStr�ckor och faktor" + "\n");
		        out.write("NBR_ETAPP=" + "\n");
		        out.write("#________Nycklar som inte anv�nds f�r denna programversion______!" + "\n");

		        out.close();  
		        System.out.println("Autogenerate file: config.properties.");
		    } catch (IOException e) {  
		    	System.err.println("Failed to generate config.properties");
		    }  
		

	}

	// DESSA NYCKLAR �R OBLIGATORISKA
	public boolean checkKey(){
		boolean toggle = true;
	
		if(configFile.getProperty("STARTFILE").equals("")){
			System.err.println("the key STARTFILE not found, check your configfile");
			toggle = false;
		}
		if(configFile.getProperty("STOPFILE").equals("")){
			System.err.println("the key STOPFILE not found, check your configfile");
			toggle = false;
		}
		if(configFile.getProperty("NAMEFILE").equals("")){
			System.err.println("the key NAMEFILE not found, check your configfile");
			toggle = false;
		}
		if(configFile.getProperty("RESULTFILE").equals("")){
			System.err.println("the key RESULTFILE not found, check your configfile");
			toggle = false;
		}
		if(configFile.getProperty("RACETIME").equals("")){
			System.err.println("the key RACETIME not found, check your configfile");
			toggle = false;
		}
		if(configFile.getProperty("STARTTYPE").equals("")){
			System.err.println("the key STARTTYPE not found, check your configfile");
			toggle = false;
		}
		if(configFile.getProperty("RACETYPE").equals("")){
			System.err.println("the key RACETYPE not found, check your configfile");
			toggle = false;
		}
		if((configFile.getProperty("LAPS")).equals("")){
			System.err.println("the key LAPS not found, check your configfile");
			toggle = false;
		}
		return toggle;
	}
	
}
