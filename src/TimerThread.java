public class TimerThread extends Thread{
	public static int TIME = 0;
	public void run(){
		while(true){
			try {Thread.sleep(1);}
			catch (InterruptedException e) {e.printStackTrace();}
			TIME++;
		}
	}
}

