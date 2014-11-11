import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.io.FileReader;
import java.io.IOException;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
				}else if(cmd1.toLowerCase().equals("insert")){
					tname=subStrings[2];
					sequence=subStrings[4];
					insert(tname,sequence);
				}else if(cmd1.toLowerCase().equals("delete") && subStrings.length==5){
					tname=subStrings[2];
					id=subStrings[4];
					delete(tname,id);
				}else if(cmd1.toLowerCase().equals("retrieve") && (subStrings.length==5 || subStrings.length==6)){
					tname=subStrings[2];
					
					String cmd2=subStrings[3];  //retrieve from traj1 trajectory 32
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
		try{
		    //All your IO Operations
			System.out.println("caonima");
			//System.out.println(tname+"/"+id+".plt");
			System.out.println(id+".plt");
			String curPath = System.getProperty("user.dir");
			System.out.println(curPath+"/"+id+".plt");
			FileReader fr = new FileReader(curPath+"/"+id+".plt");
			//BufferedReader br = new BufferedReader(new FileReader(curPath+"/"+id+".plt"));
			BufferedReader br = new BufferedReader(fr);
			//System.out.println(br);
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
			String line;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
		}
	    catch(IOException ioe){
			//Handle exception here, most of the time you will just log it.
	    }
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

	private static void insert(String tname, String trajectorys) {
		// TODO Auto-generated method stub
		System.out.println(trajectorys);
		String[] trajectory=trajectorys.split(";");
		String[] subSequence_init=trajectory[0].split(",");
		String[] subDates=subSequence_init[5].split("-");
		String[] subTimes=subSequence_init[6].split(":");
		String id = subDates[0]+subDates[1]+subDates[2]+subTimes[0]+subTimes[1]+subTimes[2];
		double lat_max=0;
		double lat_min=Double.MAX_VALUE;
		double lon_max=0;
		double lon_min=Double.MAX_VALUE;
		double alt_max=0;
		double alt_min=Double.MAX_VALUE;
		long numOfDate_max=0;
		long numOfDate_min=Long.MAX_VALUE;
		Date date_max=new Date(0);
		Date date_min=new Date(99999);
		
		
		for(int i=0;i<trajectory.length;i++){
			System.out.println(trajectory.length);
			String[] subSequences=trajectory[i].split(",");

			System.out.println("before parse");
			
			double lat=Double.parseDouble(subSequences[0]);
			System.out.println("after parse Double");
			double lon=Double.parseDouble(subSequences[1]);
			double alt=Double.parseDouble(subSequences[4]);
			long numOfDate=Long.parseLong(subSequences[3]);
			System.out.println("after numOfDate");
			
			Date date=new Date(0);

			if(lat>lat_max) lat_max=lat;
			if(lat<lat_min) lat_min=lat;
			if(lon>lon_max) lon_max=lon;
			if(lon<lon_min) lon_min=lon;
			if(alt>alt_max) alt_max=alt;
			if(alt<alt_min) alt_min=alt;
			if(numOfDate>numOfDate_max) numOfDate_max=numOfDate;
			if(numOfDate<numOfDate_min) numOfDate_min=numOfDate;
			if(date.after(date_max)) date_max=date;
			if(date.before(date_min)) date_min=date;
			System.out.println("before try");
			
			try {
				System.out.println(id+"(in try)");
				File file = new File("../Data/"+tname+"/Trajectory/"+id+".plt");
				if (file.exists()) {
					FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(trajectory[i]+'\n');
					bw.close();
					
				}else{
					file.createNewFile();
					System.out.println(id+"have been new");
					FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(trajectory[i]+'\n');
					bw.close();
				}
	 
				System.out.println("Trajectory was successfully inserted!");
	 
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		try{
			File indexFile = new File("../Data/"+tname+"/index.txt");
			FileWriter fw_i= new FileWriter(indexFile.getAbsoluteFile(),true);
			BufferedWriter bw_i = new BufferedWriter(fw_i);
			String lat_max_str=Double.toString(lat_max);
			String lat_min_str=Double.toString(lat_min);
			String lon_max_str=Double.toString(lon_max);
			String lon_min_str=Double.toString(lon_min);
			String alt_max_str=Double.toString(alt_max);
			String alt_min_str=Double.toString(alt_min);
			String numOfDate_max_str=Double.toString(numOfDate_max);
			String numOfDate_min_str=Double.toString(numOfDate_min);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date_max_str=dateFormat.format(date_max);
			String date_min_str=dateFormat.format(date_min);
			
			bw_i.write(id+","+"1"+","+lat_max_str+","+lat_min_str+","+lon_max_str+","+lon_min_str+","+alt_max_str+","+alt_min_str+","+numOfDate_max_str+","+numOfDate_min_str+","+date_max_str+","+date_min_str+'\n');
			bw_i.close();
			
		}catch (IOException e) {
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
