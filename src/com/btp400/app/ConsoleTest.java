package com.btp400.app;

import java.util.ArrayList;

import com.btp400.AirStatistics.Pollutant;
import com.btp400.controller.Controller;
import com.btp400.location.*;

public class ConsoleTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller db = new Controller();
		
		String result = db.displayStats("Vung Tau");
		System.out.println(result);
	}
	
	public void menu() {
		System.out.println("---- App Menu ----");
		System.out.println("1. Look up a specific location");
		System.out.println("2. Manage database");
	}

}
