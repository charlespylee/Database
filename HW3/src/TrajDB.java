import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.io.File;
public class TrajDB {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cmdStr="";
		while(true){
			System.out.println("command:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try{
				cmdStr=br.readLine();
				if(cmdStr.toLowerCase().equals("exit")) break;
//				System.out.println("your input is "+cmdStr);
				String[] subStrings=cmdStr.split(" ");
				String cmd1=subStrings[0];
				System.out.println("cmd1="+cmd1);
				String tname="";
				String id="";
				String sequence="";
				if(cmd1.toLowerCase().equals("create") && subStrings.length==2){
					tname=subStrings[1];
					create(tname);
				}else if(cmd1.toLowerCase().equals("insert") && subStrings.length==5){
					tname=subStrings[2];
					sequence=subStrings[4];
					insert(tname,sequence);
				}else if(cmd1.toLowerCase().equals("delete") && subStrings.length==5){
					tname=subStrings[2];
					id=subStrings[4];
					delete(tname,id);
				}else if(cmd1.toLowerCase().equals("retrieve") && (subStrings.length==5 || subStrings.length==6)){
					tname=subStrings[2];
					String cmd2=subStrings[3];
					if (cmd2.toLowerCase().equals("trajectory")){
						id=subStrings[4];
						retrieveTraj(tname,id);
					}else{
						id=subStrings[5];
						retrieveCountOfRecords(tname,id);
					}
				}else{
					System.out.println("your input command is wrong");
				}
			
			}catch(Exception e){

	        }
		}

	}

	private static void retrieveCountOfRecords(String tname, String id) {
		// TODO Auto-generated method stub
		
	}

	private static void retrieveTraj(String tname, String id) {
		// TODO Auto-generated method stub
	    String returnValue = "";
	    FileReader file = null;
	    String line = "";
	    try {
	      file = new FileReader("../Data/"+tname+"/Trajectory/"+id+".plt");
	      BufferedReader reader = new BufferedReader(file);
	      while ((line = reader.readLine()) != null) {
	    	System.out.println(line);
	        returnValue += line + "\n";
	      }
	    } catch (FileNotFoundException e) {
	    	System.out.println("the Trajectory "+id+" was not found!");
	      throw new RuntimeException("File not found");
	    } catch (IOException e) {
	      throw new RuntimeException("IO Error occured");
	    }
		
		
	}

	private static void delete(String tname, String id) {
		// TODO Auto-generated method stub
		try{
			File file = new File("../Data/"+tname+"/Trajectory/"+id+".plt");
			if(file.delete()){
				System.out.println(file.getName()+" is delelted");
			}else{
				System.out.println("Delete operation is failed.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private static void insert(String tname, String sequence) {
		// TODO Auto-generated method stub
		String[] subSequences=sequence.split(",");
		
		try {	 
			String id="test";
			File file = new File("../Data/"+tname+"/Trajectory/"+id+".plt");
 
			// if file doesnt exists, then create it
			if (file.exists()) {
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(sequence);
				bw.close();
			}else{
				System.out.println("the Trajectory Set could not be found!");
			}
 
			System.out.println("Trajectory was successfully inserted!");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void create(String tname) {
		// TODO Auto-generated method stub
		try{
			System.out.println("in create function");
			File files = new File("../Data/"+tname+"/Trajectory/");
			if (!files.exists()) {
				if (files.mkdirs()) {
					 try {
						 File file = new File("../Data/"+tname+"/index.txt");
						 BufferedWriter bw = new BufferedWriter(new FileWriter(file));
						 bw.write("0");
						 bw.close();
						 System.out.println("New Trajectory set "+tname+" are created!");
					 }catch ( IOException e ) {
						 System.out.println("index file in Trajectory set "+tname+" can't be created!");
				         e.printStackTrace();
				        }
				} else {
					System.out.println("Failed to create "+ tname);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
