package net.kiwz.mclarvik.threads;

public class Threads {
	
	public static RunAM threadABM() {
		RunAM run = new RunAM();
		Thread thread = new Thread(run);
	    thread.start();
		return run;
	}
	
	public static RunFC threadFC() {
		RunFC run = new RunFC();
	    Thread thread = new Thread(run);
	    thread.start();
		return run;
	}
	
	public static RunPG threadPG() {
		RunPG run = new RunPG();
	    Thread thread = new Thread(run);
	    thread.start();
		return run;
	}
	
	public static RunSS threadSS() {
		RunSS run = new RunSS();
	    Thread thread = new Thread(run);
	    thread.start();
		return run;
	}
}