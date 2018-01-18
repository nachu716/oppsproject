package opens;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
//Jframe 상속
public class gui extends JFrame {

	public gui() {
		setTitle("OpenSource");
		getContentPane().setLayout(null); //레이아웃형식X
		setSize(200,200); //팬 사이즈
		
		JButton btnNewButton = new JButton("Renew"); //갱신 버튼 생성
		btnNewButton.addActionListener(new ActionListener() { //버튼클릭이벤트
			public void actionPerformed(ActionEvent arg0) {
				//갱신시 팝업 메시지 발생
				 JOptionPane.showMessageDialog(null, "Renew.", "Message", JOptionPane.INFORMATION_MESSAGE); 
				 new goThread(); //스레드생성
			}
		});
		btnNewButton.setBounds(35, 29, 113, 45); //버튼 좌표
		getContentPane().add(btnNewButton); //콘텐트 팬에 부착
		
		JButton button = new JButton("Pause"); //중지 버튼 생성
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//중지시 팝업 메시지 발생
				JOptionPane.showMessageDialog(null, "Pause.", "Message", JOptionPane.INFORMATION_MESSAGE); 
				goThread.flag = false; //갱신 반복 중지
			}
		});
		button.setBounds(35, 94, 113, 45); //두번째 버튼 생성
		getContentPane().add(button); //두번째 버튼 좌표

		setVisible(true); //창 가시화
		
		
        Runnable runner = new Runnable() {
            public void run() {
              if (SystemTray.isSupported()) {
                SystemTray tray = SystemTray.getSystemTray(); //트레이 생성     
                Image image = Toolkit.getDefaultToolkit().getImage("C:\\JHS\\icon.png"); //트레이 아이콘 삽입
                PopupMenu popup = new PopupMenu(); //트레이 메뉴 생성
                TrayIcon trayIcon = new TrayIcon(image, "Program Name", popup); //트레이 아이콘에 커서 올릴시
                trayIcon.setImageAutoSize(true);
                
                trayIcon.addActionListener(new ActionListener() { //아이콘 눌렀을 때
                    public void actionPerformed(ActionEvent e) {
                        setVisible(isVisible());
                    }
               });
                
                MenuItem item = new MenuItem("Open"); //오른쪽마우스 - Open
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(true);                  
                    }
          });   
                popup.add(item);
                item = new MenuItem("Close"); //오른쪽마우스 - Close 
        item.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
			           tray.remove(trayIcon); //아이콘 삭제
			           System.exit(0);
                  }
        });
                popup.add(item); 


                try {
       tray.add(trayIcon);
                } catch (AWTException e) {
                  System.err.println("Can't add to tray");
                }
              } else {
                System.err.println("Tray unavailable");
              }
            }
          };
          EventQueue.invokeLater(runner);    

	}
}
