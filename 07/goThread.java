package opens;

import java.io.IOException;

public class goThread extends Thread{

	static boolean flag;
	goThread()
	{
		flag=true;
		start();
	}
	
	
	public void run()
	{
		while(flag)
		{
			try {
				new Naver();
				new Daum();
				Thread.sleep(5000); //5초마다 갱신
				System.out.println("renew"); //갱신확인메시지
			} catch(InterruptedException | IOException e)
			{
				e.printStackTrace(); //에러발생시 메시지 출력
			}
		}
	}
}
