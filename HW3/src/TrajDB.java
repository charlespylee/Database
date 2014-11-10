import java.io.BufferedReader;
import java.io.InputStreamReader; 

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
					sequence=subStrings[5];
					String[] subSequences=sequence.split(",");
				}else if(cmd1.toLowerCase().equals("insert")){
					tname=subStrings[2];
				}else if(cmd1.toLowerCase().equals("delete")){
					tname=subStrings[2];
					id=subStrings[4];
				}else if(cmd1.toLowerCase().equals("retrieve")){
					tname=subStrings[2];
					String cmd2=subStrings[4];
					if (cmd2.toLowerCase().equals("trajectory")){
						id=subStrings[4];
					}else{
						id=subStrings[5];
					}
				}else{
					System.out.println("your input command is wrong");
				}
				System.out.println("cmd1="+cmd1+"; tname="+tname+"; id="+id+"sequence="+sequence);
			
			}catch(Exception e){

	        }
		}

	}

}
