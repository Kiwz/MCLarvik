package net.kiwz.mclarvik.runnables;

public class Threads {
	
	public static RunABM threadABM() {
		RunABM run = new RunABM();
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
}