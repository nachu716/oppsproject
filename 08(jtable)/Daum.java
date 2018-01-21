package opens;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 import java.util.Calendar;
import java.util.StringTokenizer;
public class Daum {
	String output;
	String []DaumRank = new String[10];
	static String st;
	Daum(){}
	
	
	String[] EditText(String st)
	{
		String [] result= new String[10];
		StringTokenizer stz = new StringTokenizer(st,"\n\r");
		int cnt=0;
		while(stz.hasMoreTokens())
		{
			StringBuffer sb = new StringBuffer(stz.nextToken());
			int len = sb.length();
			if(cnt!=9)
				sb.delete(0, 3);
			else
				sb.delete(0, 4);
			
			result[cnt] = sb.toString();
			cnt++;
		}
		
		return result;
		
	}
    void DaumOut() throws IOException
    	{
    		Calendar cal = Calendar.getInstance();
    		
    		st = new String();
    		st = Integer.toString(cal.get(Calendar.YEAR))+"년"+Integer.toString(cal.get(Calendar.MONTH)+1)+
    				"월"+Integer.toString(cal.get(Calendar.DATE))+"일"+Integer.toString(cal.get(Calendar.HOUR_OF_DAY))+
    				"시"+Integer.toString(cal.get(Calendar.MINUTE))+"분";
    		
    		
	    	FileWriter fo = new FileWriter("C:\\JHS\\Daum\\"+st+"_Daum.txt");
	       // InputStreamReader reader = new InputStreamReader(System.in);
	        
	        //BufferedReader buff = new BufferedReader(reader);
	        
	        //System.out.print("input url: ");
	       // String url = buff.readLine();
	        String url = "https://www.daum.net";
	        
	       
	       // String selector = buff.readLine();
	        String selector = "ol.list_hotissue.issue_row.list_mini";
	        
	     
	         
	        
	        
	        Document doc = Jsoup.connect(url).get();
	        
	        Elements titles = doc.select(selector);
	        
	        output = "";
	        
	        for(Element e: titles) {
	            output += e.text();
	            output += "\r\n";
	
	        }
	        
	        //180116 문자열 픽스
	        StringBuffer sb = new StringBuffer(output);
	        for(int i = 2 ; i <sb.length();i++)
	        {
	           if(sb.charAt(i)=='위' &&(sb.charAt(i-1)>='0' && sb.charAt(i-1)<='9'))
	           {
	        	  if(sb.charAt(i-1)=='0' &&sb.charAt(i-2)>='1')
	        	  {
	        		  sb.insert(i-3, "\r\n");
	        		  i=i+2;
		              sb.deleteCharAt(i-3);
		             
		              continue;
	        	  }
	              sb.insert(i-2, "\r\n");
	              i=i+2;
	              sb.deleteCharAt(i-2);
	           }
	        }

	      
	        //System.out.println( output.replace("\n\n", "\b") );
	        System.out.println("Daum 실시간 검색어" );
	        output=sb.toString();
	        //System.out.println( output );
	        
	        DaumRank = EditText(output);
	        
	        System.out.println("---------------------");
	        for(int i = 0; i<10;i++)
	        	System.out.println(DaumRank[i]);
	        
	        fo.write(output);
	       
	        fo.close();
	        //input selector : ol.list_hotissue.issue_row.list_mini
    	}
}

