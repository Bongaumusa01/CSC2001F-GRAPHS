import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.io.*;
//import javax.sound.sampled.Line;
//import javax.xml.crypto.NodeSetData;

public class GraphData {

    //create nodes of the length and the length of the edges(they get paired and data is generated randomly)
    static int [] nodesLength = {10,20, 30,40,50};
    static int[] edgeLength = {10,20,30,40,50};
    static Random rand1 = new Random();
    static Random rand2 = new Random();
    static ArrayList<String> nodes = new ArrayList<String>();
    static ArrayList<String> Edges = new ArrayList<String>();
    static Graph g;
    static BufferedWriter writeFile;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
    
    for(int j: nodesLength){   
        System.out.println("Number of  nodes"+j);
        for(int i : edgeLength)
        {
            System.out.println("number of edges is "+i);
             createNodes(j);
             makeEdges(i);  
             filewrite(j,i);
            System.out.println();
        }
    }
    process();
    //FileReader file1 ;
    //System.out.println(Edges);

}//end main

    public static void process() throws IOException
    {   FileReader file1 ;
        for (int i: nodesLength){
            for (int j : edgeLength){
                String filename = "data\\dataset"+i+"."+j+".txt";
                file1 = new FileReader(new File(filename));
                BufferedReader file =  new BufferedReader(file1);
                String line = file.readLine();
                System.out.println(file.readLine());
                g = new Graph( );
                g.addEdge("node1", "node1", 1);
                    int op = readfile(filename);
                    System.out.println(filename+" "+g.toString());
                    
        
                    System.out.println("Processing file: " + filename);
        
                    String node = line.substring(0, line.indexOf("."));
                    String Edge = line.substring(line.indexOf(".")+1,line.indexOf(".txt"));
                    System.out.println(Edge);
        
                    
                    writeFile.write(i +"," +j+","+ op +"\n");
                    writeFile.flush();
            }
        }
        
    }
    public static void createNodes(int range) 
    {
        for (int i = 0; i<range;i++){
            String node = "node"+i;
            nodes.add(node);
        }
    }
    //method that creates possible edges for the nodes and includes the cost 
    // NodeX NodeY Z 
    public static void makeEdges(int range) 
    {
        String node1, node2;
        //int counter = 0;
        System.out.println(range);
        for(int i=0;i<range;i++){
        String arc ;
        int position_1 = rand1.nextInt(nodes.size()-1);
        int position_2 = rand2.nextInt(nodes.size() - 1);
        while(position_1 == position_2){
            position_2 = rand2.nextInt(nodes.size() - 1);
        }
        int cost = rand1.nextInt(10)+1;

        node1 = nodes.get(position_1);
        node2 = nodes.get(position_2);
        
        arc = node1 + " "+node2 + " "+cost ;
       // System.out.println(arc);
        Edges.add(arc);
        //counter ++;       
        
        }     
    }
  
    public static void filewrite(int vertex,int arc )throws IOException
    {
        File file = new File("data\\dataset"+vertex+"."+arc+".txt");
        try (FileWriter f = new FileWriter(file)){
            for (String i:Edges){
                f.write(i+"\n");
                
            }
        }
    }

    public static int readfile(String Filename){
  
        String startnode = new String();
        
        
             try
             {   	
                 FileReader fin = new FileReader(new File(Filename));
                 BufferedReader file = new BufferedReader(fin);
     
                 // Read the edges and insert
                 String line;
                 line = file.readLine();
                 
                 while( line != null )
                 {
                     line = file.readLine( );
                     System.out.println(line);
                     StringTokenizer st = new StringTokenizer( line );
                     String source  = st.nextToken( );
                         String dest = st.nextToken( );
                         int cost = Integer.parseInt( st.nextToken( ) );
                         g.addEdge( source, dest, cost );

                         System.out.println(source);
     
                     try
                     {
                         if( st.countTokens( ) != 3 )
                         {
                             System.err.println( "Skipping ill-formatted line " + line );
                             continue;
                         }
                         
                     }
                     catch( NumberFormatException e )
                       { System.err.println( "Skipping ill-formatted line " + line ); }
                       
                    System.out.println(line);   
                    startnode = line.substring(0,line.indexOf(" "));
                    System.out.println(startnode);
                  
                }
              }
              catch( IOException e )
                { System.err.println( e ); }
             System.out.println(startnode);
               
             g.dijkstra(startnode); 

            System.out.println("Number of operations "+ g.numOfOperations);
            return g.numOfOperations;
            }

}