import java.io.FileWriter;
import java.util.Random;
import java.io.IOException;
import java.util.*;

public class GraphExperimentalData{
   public static void main(String []args){
   Random randNumber = new Random();
   int rand =0; //dataset number
   int num= 1;
   int fileCount = 0;

   
      // while loop to 
      while(rand != 10){
   //create datasets
      try{ 
      String datasetNum = "dataset"+rand+".txt";
      System.out.println(datasetNum);
      
      //store generated datasets
      ArrayList<String> edges = new ArrayList<String>();
      //generate nodes
      for (int j = 0; j<10*rand;j++){
      for(int i  = 0; i < 10*num; i++){
      int startVertex = randNumber.nextInt(10*num);
      int desVertex = randNumber.nextInt(10*num);
      int weight = randNumber.nextInt(10);

   if(startVertex != desVertex && weight != 0){
      String generateString = "node"+startVertex+" node"+desVertex+" "+weight;
      edges.add(generateString);}
   }
num++;}
   //write to file
      FileWriter myWriter = new FileWriter(datasetNum);
      for(String a : edges)
      {
         System.out.println(a);
         myWriter.write(a+"\n");
      }
      myWriter.close();

      }catch(IOException e){
         System.out.println("An error occurred.");
         e.printStackTrace();
         }

      rand ++;
      
      System.out.println();
         }// second while loop
      //first while loop
   }//main
}//class