package opens;
 
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class Naver{
    String NaverRank[] = new String[10];
	String output;
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
	Naver()
	{
		
	}
    void NaverOut() throws IOException{
    	
    		
    		Calendar cal = Calendar.getInstance();
    		
    		String st = new String();
    		st = Integer.toString(cal.get(Calendar.YEAR))+"년"+Integer.toString(cal.get(Calendar.MONTH)+1)+
    				"월"+Integer.toString(cal.get(Calendar.DATE))+"일"+Integer.toString(cal.get(Calendar.HOUR_OF_DAY))+
    				"시"+Integer.toString(cal.get(Calendar.MINUTE))+"분";
    		
    		
	    	FileWriter fo = new FileWriter("C:\\JHS\\Naver\\"+st+"_Naver.txt");
    	
        //InputStreamReader reader = new InputStreamReader(System.in);
        
        //BufferedReader buff = new BufferedReader(reader);
        
        //System.out.print("input url: ");
       // String url = buff.readLine();
        String url = "http://www.naver.com/";
        
       // System.out.print("input selector: ");
        //String selector = buff.readLine();
        String selector = "li.ah_item";
        
        Document doc = Jsoup.connect(url).get();
        
        Elements titles = doc.select(selector);
        StringBuffer sb=new StringBuffer();
        //180116 문자열 픽스
        output = "";
        int cnt=0;
        for(Element e: titles) {
        	cnt++;
        	if(cnt==11) break;
        	sb.append(e.text());
        	if(cnt<10)
        		sb.insert(1, '위');
        	else
        		sb.insert(2, '위');
        	
        	output+=sb.toString();
        	sb.delete(0, sb.length());
            output += "\r\n";

        }
        

        
        

        System.out.println("Naver 실시간 검색어" );
        //System.out.println(output);
   
        NaverRank = this.EditText(output);
        
        System.out.println("---------------------");
        for(int i = 0; i<10;i++)
        	System.out.println(NaverRank[i]);
        fo.write(output);     
       fo.close();
        
    }
}
