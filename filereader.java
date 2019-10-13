import java.io.*;
import java.util.*;
public class filereader{
	public static void main(String args[]){
				int counter=1;
				String Start=""; //holds the name of the start State 
				String newStart="";
				Vector End=new Vector(); //holds the sets of End States
				Vector States=new Vector(); //holds the sets of States
				Vector States2=new Vector(); //holds the sets of States with Transitions to the final State
				Vector Alphabets=new Vector(); // Alphabet
				Vector sets=new Vector(); //Vector for storing the transitions
				Vector sets2=new Vector();
				Vector sets3=new Vector();
				Vector reachable=new Vector();
				BinarySearchTree puno=new BinarySearchTree();
				BinarySearchTree state=new BinarySearchTree();
				Vector equivalent=new Vector();
			
				int position=0;
				
				
		try{
				//Some sort of A Parser function
				BufferedReader inputStream= null;
				inputStream= new BufferedReader(new FileReader("dfa.in"));
				
				String line = null;
				line = inputStream.readLine();
				while(line!=null){
					if(line.startsWith("#")==false){ 
						//if the line is not a comment
					if(counter==1){
							//here in we have the list of states lagay sa vector
							String input3[]=line.split(",");
								for(int a=0;a<input3.length;a++){
								States.add(input3[a]);
								States2.add(input3[a]);
								state.insert(input3[a],"");
									}
						}
					if(counter==2){
							//also we have the list of alphabets na ilalagay sa vector
							String input4[]=line.split(",");
								for(int a=0;a<input4.length;a++){
								Alphabets.add(input4[a]);
									}
						}
					if(counter==3){
								//assign the string in line to be the start state
								Start=line;
						}
					if(counter==4){
								//vector of end states
								String input2[]=line.split(",");
								for(int a=0;a<input2.length;a++){
								End.add(input2[a]);
									}
						}
					if(counter>=5){
						//stores the list of array lagay sa hash such that we will create a list of list
						String input5[]=line.split(",");
						String bago="";
						String bago2="";
								for(int a=0;a<3;a++){
								if(a==0){
								bago+=input5[a]+"-";
								bago2+=input5[a]+"-";
									}
								else{	
								bago+=input5[a];
								if(a==1){bago2+=input5[a]+"-";}
								if(a==2){bago2+=input5[a];}
								}
								if(a==2 && reachable.contains(input5[a])==false){
									reachable.add(input5[a]);
									}
									
								}
								
									sets.add(bago);
									sets3.add(bago2);
						}
					counter++;
					}//end of outer if line is not starting with#
					line=inputStream.readLine();
					
				}//end of while loop also end of parsing
				
			
			}
			catch(FileNotFoundException e){
				System.out.println("Exception Occurs File not found!");
			}
			catch(IOException e2){
				System.out.println("Error reading from file");
                        }
                        
         try{
            PrintWriter outputStream=new PrintWriter(new FileOutputStream("dfa.out"));     
			   
            // we assume that in this part that we have already stored the transitions from every state
            // the first thing we need to do here is to check for equivalent states
           
            
            //itransform yung mga transitions such that their transitions contains all the transitions to the other alpahabets
            int counter2=0;
            for(int b=0;b<sets.size();b++){
	             	for(int c=0;c<sets.size();c++){
		           				String bagongline=sets.elementAt(b).toString();
		           				String bagongline2=sets.elementAt(c).toString();
		           				String input7[]=bagongline.split("-");
		           				String input8[]=bagongline2.split("-");
		           				if(input7[0].equals(input8[0])&&input7[1].equals(input8[1])==false&&c%Alphabets.size()!=0){
			           				String g= (States2.elementAt(counter2)).toString()+"-"+input7[1]+input8[1];
			           				String one=(States2.elementAt(counter2)).toString();
			           				String two=	input7[1]+input8[1];
			           				puno.insert(one,two);
			           				sets2.add(g);
		           					counter2++;
			           				}
	           		}
		       }
		           		   
		     //checking for equivalent states and also transformation of transitions of states EVALUATING
		     int under=0;
             		for(int b=0;b<sets2.size();b++){
	             		for(int c=0;c<sets2.size();c++){
		           		  	String bagongline=sets2.elementAt(b).toString();
		           				String bagongline2=sets2.elementAt(c).toString();
		           				String input7[]=bagongline.split("-");
		           				String input8[]=bagongline2.split("-");
		           				
				           			if(reachable.contains(input7[0])&& reachable.contains(input8[0])&& b>c ){//pag nasa sets of reachable states
			           						
			           						String first="";
			           						String second="";
			           						for(int a=0;a<input7[1].length();a++){
				           							if(reachable.contains( Character.toString( input7[1].charAt(a)  )   )  ){
					           						
					           							first+=puno.find( Character.toString( input7[1].charAt(a)  )  );
					           							}
					           						else{
					           							first+=Character.toString( input7[1].charAt(a)  );
				           							}
				           						}
				           					
				           						
				           					for(int a=0;a<input8[1].length();a++){
				           							if(reachable.contains( Character.toString( input8[1].charAt(a)  )   )  ){
					           							second+=puno.find( Character.toString( input8[1].charAt(a)  )  );
					           							}
					           						else{
					           							second+=Character.toString( input8[1].charAt(a)  );
				           							}
				           						}
	
				           						
				           				if(first.equals(second)){
					           				
					           					equivalent.add(input7[0]+","+input8[0]);
					           					int index=States.indexOf(input8[0]);
					           					reachable.remove(input7[0]);
					           					reachable.remove(input8[0]);
					           					if(End.contains(input7[0])||End.contains(input8[0])){
						           					End.remove(input7[0]);
					           						End.remove(input8[0]);
					           						End.add(input8[0]+","+input7[0]);
						           					}
					           					States.add(index,input8[0]+","+input7[0]);
					           					States.remove(input7[0]);
					           					States.remove(input8[0]);
					           					reachable.add(input8[0]+","+input7[0]);
					           					
					           					
					           				}
										}//end of else
										
				           		
				           		
	           				} //inner for loop 	
	           				
	           				
	           					
		           		}//outer forloop
		           		
		           	// Transfer the new set of states prior to the removal of the unreachable state
		           	for(int a=0;a<States.size();a++){
			           		for(int c=0;c<States.elementAt(a).toString().length();c++){
				           		if(Character.toString( States.elementAt(a).toString().charAt(c) ).equals(Start)){
					           		newStart=States.elementAt(a).toString();
					           		}
				           		}
			           	}
			    
			        for(int a=0;a<States.size();a++){
			           		if(reachable.contains(States.elementAt(a).toString())==false){
				           		States.remove(States.elementAt(a).toString());
				           		}
			           	}
			       
			           	
			String tada="";
	        for(int g=0;g<States.size();g++){
		        tada+="{"+States.elementAt(g)+"}";
		        if(g!=States.size()-1){
		        tada+=",";
	        		}
		        }
		    String tada2="";
	        for(int g=0;g<End.size();g++){
		        tada2+="{"+End.elementAt(g)+"}";
		        if(g!=End.size()-1){
		        tada2+=",";
	        		}
		        }
		    System.out.println(States.toString());   
		    outputStream.println(tada); 
			outputStream.println("{"+newStart+"}");
			outputStream.println(tada2); 
		    for(int tried=0;tried<States.size();tried++){
			    for(int l=0;l<Alphabets.size();l++){
				    		String third="";
				    		for(int g=0;g<sets3.size();g++){
					    		String here=Character.toString(sets3.elementAt(g).toString().charAt(0));
					    		String here2=Character.toString(sets3.elementAt(g).toString().charAt(2));
					    		String here3=States.elementAt(tried).toString();
					    		String here4=Alphabets.elementAt(l).toString();
					    		String where=Character.toString(sets3.elementAt(g).toString().charAt(4));
					    		if(here.equals(States.elementAt(tried).toString())&& here2.equals(Alphabets.elementAt(l).toString())&&States.contains(Character.toString(sets3.elementAt(g).toString().charAt(4)))){
						    		third=Character.toString(sets3.elementAt(g).toString().charAt(4));
						    		}
						    	if( here3.startsWith(here)&& here2.equals(Alphabets.elementAt(l).toString()) ){
							    		if(States.contains(Character.toString(sets3.elementAt(g).toString().charAt(4)))){
							    		third=Character.toString(sets3.elementAt(g).toString().charAt(4));
						    				}
						    			else{
							    			for(int v=0;v<States.size();v++){
								    			for(int q=0;q<States.elementAt(v).toString().length();q++){
									    			if(where.equals(Character.toString( States.elementAt(v).toString().charAt(q) ))){
										    			third=States.elementAt(v).toString();
										    			}
									    			}
								    			}
							    			
							    			}
							    	}
						    	
					    		}
				    		outputStream.println("{"+States.elementAt(tried).toString()+"},"+Alphabets.elementAt(l).toString()+",{"+third+"}");
				    }
			    		
			    }
		   
			outputStream.close();
			
		}
			catch(FileNotFoundException e){
				System.out.println("File not found");
			}
			catch(IOException e2){
				System.out.println("Error reading from file");
                        }
			System.exit(0);
			}	
	
}