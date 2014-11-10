import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.io.FileReader;
import java.io.IOException;

public class TrajDB {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean isExit=false;
		String cmdStr="";
		while(!isExit){
			System.out.println("command:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try{
				cmdStr=br.readLine();
				if(cmdStr.toLowerCase().equals("exit")){isExit=false; break;}
				System.out.println("your input is "+cmdStr);
				String[] subStrings=cmdStr.split(" ");
				String cmd1=subStrings[0].toLowerCase();
				String tname="";
				String id="";
				String sequence="";
				if(cmd1.toLowerCase().equals("create")){
					tname=subStrings[1];
					create(tname);
				}else if(cmd1.toLowerCase().equals("insert")){
					tname=subStrings[2];
					sequence=subStrings[4];
					insert(tname,sequence);
				}else if(cmd1.toLowerCase().equals("delete")){
					tname=subStrings[2];
					id=subStrings[4];
					delete(tname,id);
				}else if(cmd1.toLowerCase().equals("retrieve")){
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
				System.out.println("cmd1="+cmd1+"; tname="+tname+"; id="+id+"sequence="+sequence);
			
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
	}

	private static void delete(String tname, String id) {
		// TODO Auto-generated method stub
		
	}

	private static void insert(String tname, String sequence) {
		// TODO Auto-generated method stub
		String[] subSequences=sequence.split(",");
	}

	private static void create(String tname) {
		// TODO Auto-generated method stub
		
	}

}
