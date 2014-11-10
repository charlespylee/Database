import java.io.BufferedReader;
import java.io.InputStreamReader; 

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
